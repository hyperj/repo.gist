package net.hyperj.gist.calcite.plan;

import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.hep.HepPlanner;
import org.apache.calcite.plan.hep.HepProgramBuilder;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.rules.FilterJoinRule;
import org.apache.calcite.rel.rules.PruneEmptyRules;
import org.apache.calcite.rel.rules.ReduceExpressionsRule;

public class HepPlannerTest extends PlannerTest {

    @Override
    public RelOptPlanner setPlanner() {
        return new HepPlanner(new HepProgramBuilder()
                .addRuleInstance(FilterJoinRule.FilterIntoJoinRule.FILTER_ON_JOIN)
                .addRuleInstance(ReduceExpressionsRule.PROJECT_INSTANCE)
                .addRuleInstance(PruneEmptyRules.PROJECT_INSTANCE).build());
    }

    @Override
    public RelNode plan(RelNode converted) {
        planner.setRoot(converted);
        return planner.findBestExp();
    }

    public static void main(String[] args) throws Exception {
        new HepPlannerTest().tester();
    }

}
