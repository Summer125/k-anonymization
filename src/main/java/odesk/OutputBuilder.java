package odesk;

import odesk.model.Plan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by heruv1m on 29.01.15.
 */
public class OutputBuilder {
    /**
     * Build file with anonymized persons and write it to the disk
     * @param members
     * @param fileName
     * @throws IOException
     */
    public void buildList(List<String> members, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String m : members) {
            writer.write(m + "\n");
        }
        writer.close();
    }

    /**
     * Write file with parameters to the disk
     * @param p
     * @param fileName
     * @throws IOException
     */
    public void buildPlan(Plan p, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(p + "\n");
        writer.close();
    }


}
