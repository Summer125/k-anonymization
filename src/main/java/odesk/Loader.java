package odesk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by heruv1m on 29.01.15.
 */
public class Loader {
    /**
     * Load input file, parse it and put to the List
     * @param fileName
     * @return
     * @throws IOException
     */
    public ArrayList<String[]> getData(String fileName) throws IOException {
        ArrayList<String[]> members = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] person = line.split(",");
            members.add(person);
        }
        return members;
    }

}
