import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<IncomeOrder> list = new ArrayList<>();
        ArrayList<OutputJson> outList = new ArrayList<>();

        File file1 = new File("src/main/resources/csvUnlimint.csv");
        File file2 = new File("src/main/resources/jsonUnlimint.json");

        list.addAll(readFile(file1));
        list.addAll(readFile(file2));

        for(int i = 0; i < list.size(); i++){
            OutputJson out = new OutputJson();
            out.setId(list.get(i).getOrderId());
            out.setAmount(list.get(i).getAmount());
            out.setComment(list.get(i).getComment());
            out.setFilename(list.get(i).getFileName());
            outList.add(out);
        }

        for(int i = 0; i < outList.size(); i++) {
            String json = new ObjectMapper().writeValueAsString(outList.get(i));
            System.out.println(json);
        }
    }

    public static ArrayList<IncomeOrder> readFile(File file){

        ArrayList<IncomeOrder> list = new ArrayList<>();

        String fileName = file.getName();
        String fileExtension = FilenameUtils.getExtension(fileName);

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
