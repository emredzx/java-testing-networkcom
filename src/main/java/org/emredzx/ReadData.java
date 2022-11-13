package org.emredzx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadData {

    List<List<String>> data = new ArrayList<>();
    String line;
    String splitBy = ";";

    public List<List<String>> getData() {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/data/userdata.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                this.data.add(Arrays.asList(data));

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return data;
    }
}
