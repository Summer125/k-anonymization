package odesk;

import odesk.model.AgeGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Produce anonymization algorizm and return strings with anonymized values
 */
public class Anonymaizer {

    private final int ANONYMIZE = 1;
    private final int MAX_AGE = 100;

    private Map<String, String> workClassMapping = fillWorkClassMap();
    private List<AgeGroup> ageMapping;

    private final int genderLevel;
    private final int ageLevel;
    private final int workClasslevel;
    private final int divider;


    public Anonymaizer(int genderLevel, int ageLevel, int workClasslevel, int divider) {
        this.genderLevel = genderLevel;
        this.ageLevel = ageLevel;
        this.workClasslevel = workClasslevel;
        this.divider = divider;
        this.ageMapping = fillAgeGroups(divider);
    }

    public List<String> anonymize(List<String[]> persons) {
        ArrayList<String> anonymized = new ArrayList();
        for (String[] p : persons) {
            p[0] = anonymizeAge(p[0], ageLevel);
            p[1] = anonymizeWorkClass(p[1], workClasslevel);
            p[9] = anonymizeGender(p[9], genderLevel);
            anonymized.add(Arrays.toString(p));
        }
        return anonymized;
    }

    private String anonymizeGender(String gender, int genderLevel) {
        if (genderLevel == ANONYMIZE) {
            return "*";
        } else {
            return gender;
        }
    }

    private String anonymizeAge(String ag, int level) {
        if (level == ANONYMIZE || divider == 0) {
            int age = Integer.parseInt(ag);
            if (age > MAX_AGE) {
                return ">" + MAX_AGE;
            }

            for (AgeGroup g : ageMapping) {
                if (age < g.getMaxValue()) {
                    return g.getName();
                }
            }
        }
        return ag;

    }

    private String anonymizeWorkClass(String workClass, int level) {
        if (level == 0 || "WorkClass".equals(workClass)) {
            return workClass;
        } else {

            String genClass = workClassMapping.get(workClass.trim());
            if (genClass == null) {
                throw new IllegalArgumentException("can't find class for " + "'" + workClass + "'");
            }
            return anonymizeWorkClass(genClass, level - 1);
        }
    }


    private Map<String, String> fillWorkClassMap() {
        HashMap<String, String> clasess = new HashMap<String, String>();
        clasess.put("Self-emp-not-inc", "Self-employed");
        clasess.put("Self-emp-inc", "Self-employed");
        clasess.put("State-gov", "Goverment");
        clasess.put("Federal-gov", "Goverment");
        clasess.put("Local-gov", "Goverment");
        clasess.put("Without-pay", "Unemployed");
        clasess.put("Private", "WorkClass");
        clasess.put("Goverment", "WorkClass");
        clasess.put("Self-employed", "WorkClass");
        clasess.put("Unemployed", "WorkClass");
        return clasess;
    }

    private List<AgeGroup> fillAgeGroups(int divider) {
        ArrayList<AgeGroup> ageGroups = new ArrayList<AgeGroup>();
        for (int i = divider; divider > 0 && MAX_AGE >= i; i += divider) {
            String generalizedAge = i - divider + "-" + (i - 1);
            ageGroups.add(new AgeGroup(generalizedAge, i));
        }
        return ageGroups;
    }

}
