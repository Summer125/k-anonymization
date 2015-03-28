package odesk.model;

/**
 * Calculate precision
 */
public class Plan implements Comparable<Plan> {
    private double precision;
    private final double genderLevel;
    private final double ageLevel;
    private final double workClasslevel;
    private final double LEVELS = 3;

    public Plan(int genderLevel, int ageLevel, int workClasslevel) {
        this.genderLevel = genderLevel;
        this.ageLevel = ageLevel;
        this.workClasslevel = workClasslevel;
        this.precision = calculatePrecision();
    }

    private double calculatePrecision() {
        return (1.0 - (
                (genderLevel / Constants.GENDER_LEVEL_DIP)
                        + (ageLevel / Constants.AGE_LEVEL_DIP)
                        + (workClasslevel / Constants.WORKCLASS_LEVEL_DIP)
        ) / LEVELS);
    }

    public double getPrecision() {
        return precision;
    }

    public double getGenderLevel() {
        return genderLevel;
    }

    public double getAgeLevel() {
        return ageLevel;
    }

    public double getWorkClasslevel() {
        return workClasslevel;
    }

    @Override
    public int compareTo(Plan a) {
        if (this.precision == a.getPrecision()) {
            return 0;
        }

        if (this.precision > a.getPrecision()) {
            return 10;
        }
        if (this.precision < a.getPrecision()) {
            return -10;
        }
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return String.format("precision %s, genderLevel %s, ageLevel %s, workClasslevel %s", precision, genderLevel, ageLevel, workClasslevel);
    }
}
