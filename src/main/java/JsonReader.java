import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class JsonReader {

    ObjectMapper mapper = new ObjectMapper();
    ArrayList<IncomeOrder> list = new ArrayList<>();

    public void readJson(File file) {

        String fileName = file.getName();

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fr);

        try {
            String line = reader.readLine();
            while (line != null) {
                IncomeOrder order = mapper.readValue(line, IncomeOrder.class);
                order.setFileName(fileName);
                list.add(order);
                line = reader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
