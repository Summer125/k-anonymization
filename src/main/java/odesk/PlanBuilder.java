package odesk;

import odesk.model.Constants;
import odesk.model.Plan;

import java.util.*;


public class PlanBuilder {

    /**
     * Create plans for different values anonymization and returned best precision plan
     */
    public Plan getOptimalPlan(int k) {
        SortedSet<Plan> plans = buildPlans(k);
        return (plans.size() > 0) ? plans.last() : null;
    }

    private SortedSet<Plan> buildPlans(int k) {
        SortedSet<Plan> plans = new TreeSet<Plan>();

        for (int g = 0; Constants.GENDER_LEVEL_DIP > g; g++) {
            for (int a = 0; Constants.AGE_LEVEL_DIP > a; a++) {
                for (int w = 0; Constants.WORKCLASS_LEVEL_DIP > w; w++) {
                    if ((g + a + w) == k - 1) {
                        plans.add(new Plan(g, a, w));
                    }
                }
            }
        }
        return plans;
    }
}
