import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<File> inputFiles = new ArrayList<>();
        ArrayList<IncomeOrder> inputOrders = new ArrayList<>();
        ArrayList<OutputJson> outputOrders = new ArrayList<>();

        inputFiles.add(new File("src/main/resources/csvFile.csv"));
        inputFiles.add(new File("src/main/resources/jsonFile.json"));

        for(int i = 0; i < inputFiles.size(); i++){
            inputOrders.addAll(readFile(inputFiles.get(i)));
        }

        for(int i = 0; i < inputOrders.size(); i++){
            OutputJson out = new OutputJson();
            out.setId(inputOrders.get(i).getOrderId());
            out.setAmount(inputOrders.get(i).getAmount());
            out.setCurrency(inputOrders.get(i).getCurrency());
            out.setComment(inputOrders.get(i).getComment());
            out.setFilename(inputOrders.get(i).getFileName());
            out.setLine(inputOrders.get(i).getLine());
            outputOrders.add(out);
        }

        for(int i = 0; i < outputOrders.size(); i++) {
            String output = new ObjectMapper().writeValueAsString(outputOrders.get(i));
            System.out.println(output);
        }
    }

    public static ArrayList<IncomeOrder> readFile(File file){

        ArrayList<IncomeOrder> list = new ArrayList<>();
        String fileExtension = FilenameUtils.getExtension(String.valueOf(file));

        if (fileExtension.equals("json")){
            JsonReader reader = new JsonReader();
            reader.readJson(file);
            list.addAll(reader.list);
        }

        if (fileExtension.equals("csv")){
            CsvReader reader = new CsvReader();
            try {
                reader.readCsv(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.addAll(reader.list);
        }
        return list;
    }
}
