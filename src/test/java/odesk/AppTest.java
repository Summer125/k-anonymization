package odesk;


import odesk.model.Plan;
import org.junit.Test;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testLine() {
        String s = "39, State-gov, 77516, 4, 13, Never-married, Adm-clerical, Not-in-family, White, Male, 2174, 0, 40, United-States, 0";
        String[] ar = s.split(",");
        for (int i = 0; ar.length > i; i++) {
            System.out.println(i + " = " + ar[i]);
        }
    }

    @Test
    public void testGroup() {
        HashMap<String, String> clasess = new HashMap<String, String>();
        clasess.put("State-gov", "Goverment");
        System.out.println("clasess.get(\"State-gov\") = " + clasess.get("State-gov"));
//        Anonymaizer anonymaizer = new Anonymaizer();
//        anonymaizer.fillAgeGroups(10);

    }

    @Test
    public void testPlanBuilder() {
        PlanBuilder pb = new PlanBuilder();
        Plan p = pb.getOptimalPlan(5);
        System.out.println("p = " + p);
    }

}
