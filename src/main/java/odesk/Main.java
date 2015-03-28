package odesk;

import odesk.model.Plan;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by heruv1m on 29.01.15.
 */
public class Main {
    private static String INPUT_FILE = "adults.txt";
    private static String OUTPUT_FILE_PERSONS = "output-persons.txt";
    private static String OUTPUT_FILE_PLAN = "output-plan.txt";
    private static int DIVIDER = 10;
    private static Loader loader = new Loader();
    private static Anonymaizer anonymaizer;
    private static PlanBuilder planBuilder = new PlanBuilder();
    private static OutputBuilder builder = new OutputBuilder();


    public static void main(String[] args) throws IOException {
        Scanner scanIn = new Scanner(System.in);


        System.out.println("Please enter k");
        int k = getLevel(scanIn.nextLine());
        scanIn.close();

        //loading file
        List<String[]> persons = loader.getData(INPUT_FILE);

        //search parameters for best precision
        Plan p = planBuilder.getOptimalPlan(k);

        if (p == null) {
            System.out.println("Non k-anonymous person:" + Arrays.toString(persons.get(0)));
        } else {
            anonymaizer = new Anonymaizer((int) p.getGenderLevel(), (int) p.getAgeLevel(), (int) p.getWorkClasslevel(), DIVIDER);
            List<String> anonimPersosns = anonymaizer.anonymize(persons);
            //generating file with persons
            builder.buildList(anonimPersosns, OUTPUT_FILE_PERSONS);
            //generating file with parameters
            builder.buildPlan(p, OUTPUT_FILE_PLAN);
            System.out.println("Generalization finished");
        }
    }

    /**
     * Check input param
     *
     * @param anLevel
     * @return
     */
    private static int getLevel(String anLevel) {
        try {
            int level = Integer.parseInt(anLevel);
            if (level >= 2) {
                return level;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
