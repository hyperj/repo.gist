package net.hyperj.gist.calcite.plan;

import org.apache.calcite.adapter.enumerable.EnumerableConvention;
import org.apache.calcite.adapter.enumerable.EnumerableRules;
import org.apache.calcite.plan.ConventionTraitDef;
import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.plan.volcano.VolcanoPlanner;
import org.apache.calcite.rel.RelDistributionTraitDef;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.rules.FilterJoinRule;
import org.apache.calcite.rel.rules.PruneEmptyRules;
import org.apache.calcite.rel.rules.ReduceExpressionsRule;

public class VolcanoPlannerTest extends PlannerTest {

    @Override
    RelOptPlanner setPlanner() {
        VolcanoPlanner planner = new VolcanoPlanner();
        planner.addRelTraitDef(ConventionTraitDef.INSTANCE);
        planner.addRelTraitDef(RelDistributionTraitDef.INSTANCE);
        planner.addRule(FilterJoinRule.FilterIntoJoinRule.FILTER_ON_JOIN);
        planner.addRule(ReduceExpressionsRule.PROJECT_INSTANCE);
        planner.addRule(PruneEmptyRules.PROJECT_INSTANCE);
        planner.addRule(EnumerableRules.ENUMERABLE_MERGE_JOIN_RULE);
        planner.addRule(EnumerableRules.ENUMERABLE_SORT_RULE);
        planner.addRule(EnumerableRules.ENUMERABLE_VALUES_RULE);
        planner.addRule(EnumerableRules.ENUMERABLE_PROJECT_RULE);
        planner.addRule(EnumerableRules.ENUMERABLE_FILTER_RULE);
        return planner;
    }

    @Override
    public RelNode plan(RelNode converted) {
        RelTraitSet desiredTraits = converted.getCluster().traitSet().replace(EnumerableConvention.INSTANCE);
        converted = planner.changeTraits(converted, desiredTraits);
        planner.setRoot(converted);
        return planner.findBestExp();
    }

    public static void main(String[] args) throws Exception {
        new VolcanoPlannerTest().tester();
    }

}
