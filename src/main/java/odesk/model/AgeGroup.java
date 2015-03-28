package odesk.model;

/**
 * Consist information about age group
 */

public class AgeGroup {
    /**
     * Range of age (20-39 or 0-30)
     */
    private final String name;
    /**
     * Value for compare (age 21, group maxVal 30, then age can be belongs group)
     */
    private final int maxValue;

    public AgeGroup(String name, int maxValue) {
        this.name = name;
        this.maxValue = maxValue;
    }

   public String getName() {
        return name;
    }
    public int getMaxValue() {
        return maxValue;
    }

}
