package net.hyperj.gist.calcite.plan;

import com.google.common.base.Strings;
import org.apache.calcite.config.CalciteConnectionConfigImpl;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.plan.ConventionTraitDef;
import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.rel.RelDistributionTraitDef;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.rel.type.RelDataTypeSystemImpl;
import org.apache.calcite.rex.RexBuilder;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.BasicSqlType;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorUtil;
import org.apache.calcite.sql2rel.RelDecorrelator;
import org.apache.calcite.sql2rel.SqlToRelConverter;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.RelBuilder;

import java.util.Properties;

abstract class PlannerTest {

    String sql = "select j.company as company_name, u.name as user_name" +
            " from jobs j" +
            " join users u on u.id=j.id" +
            " where j.id>0 and u.id>0" +
            " order by j.company";

    SqlTypeFactoryImpl sqlTypeFactory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
    SchemaPlus schema = Frameworks.createRootSchema(true);

    public PlannerTest() {
        initSchema();
    }

    void initSchema() {
        schema.add("USERS", new AbstractTable() {
            @Override
            public RelDataType getRowType(RelDataTypeFactory typeFactory) {
                RelDataTypeFactory.Builder builder = typeFactory.builder()
                        .add("ID", new BasicSqlType(new RelDataTypeSystemImpl() {
                        }, SqlTypeName.INTEGER))
                        .add("NAME", new BasicSqlType(new RelDataTypeSystemImpl() {
                        }, SqlTypeName.CHAR));
                return builder.build();
            }
        });
        schema.add("JOBS", new AbstractTable() {
            @Override
            public RelDataType getRowType(RelDataTypeFactory typeFactory) {
                RelDataTypeFactory.Builder builder = typeFactory.builder()
                        .add("ID", new BasicSqlType(new RelDataTypeSystemImpl() {
                        }, SqlTypeName.INTEGER))
                        .add("COMPANY", new BasicSqlType(new RelDataTypeSystemImpl() {
                        }, SqlTypeName.CHAR));
                return builder.build();
            }
        });
    }

    FrameworkConfig frameworkConfig = Frameworks.newConfigBuilder().defaultSchema(schema).parserConfig(SqlParser.Config.DEFAULT).traitDefs(ConventionTraitDef.INSTANCE, RelDistributionTraitDef.INSTANCE).build();
    CalciteCatalogReader calciteCatalogReader = new CalciteCatalogReader(CalciteSchema.from(schema), CalciteSchema.from(schema).path(null), sqlTypeFactory, new CalciteConnectionConfigImpl(new Properties()));
    SqlValidator validator = SqlValidatorUtil.newValidator(SqlStdOperatorTable.instance(), calciteCatalogReader, sqlTypeFactory, SqlConformanceEnum.DEFAULT);
    SqlToRelConverter.Config config = SqlToRelConverter.configBuilder().withConfig(frameworkConfig.getSqlToRelConverterConfig()).withTrimUnusedFields(false).withConvertTableAccess(false).build();
    RelOptPlanner planner = setPlanner();

    abstract RelOptPlanner setPlanner();

    RelOptCluster cluster = RelOptCluster.create(planner, new RexBuilder(sqlTypeFactory));


    public void tester() throws SqlParseException {
        SqlNode parsed = parse(sql);
        SqlNode validated = validate(parsed);
        RelNode converted = convert(validated);
        System.out.println(RelOptUtil.toString(converted));
        System.out.println(Strings.repeat("-", 1 << 6));
        System.out.println(RelOptUtil.toString(plan(converted)));

    }

    private SqlNode parse(String sql) throws SqlParseException {
        SqlParser parser = SqlParser.create(sql, SqlParser.Config.DEFAULT);
        return parser.parseStmt();
    }


    private SqlNode validate(SqlNode parsed) {
        return validator.validate(parsed);
    }

    private RelNode convert(SqlNode validated) {
        SqlToRelConverter sqlToRelConverter = new SqlToRelConverter((rowType, queryString, schemaPath, viewPath) -> null,
                validator, calciteCatalogReader, cluster, frameworkConfig.getConvertletTable(), config);
        RelRoot root = sqlToRelConverter.convertQuery(validated, false, true);
        root = root.withRel(sqlToRelConverter.flattenTypes(root.rel, true));
        RelBuilder relBuilder = config.getRelBuilderFactory().create(cluster, null);
        root = root.withRel(RelDecorrelator.decorrelateQuery(root.rel, relBuilder));
        return root.rel;
    }

    abstract RelNode plan(RelNode converted);

}
