import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<IncomeOrderJson> list = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file());
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                IncomeOrderJson order = mapper.readValue(line, IncomeOrderJson.class);
                list.add(order);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        IncomeOrderJson order = mapper.readValue(file(), IncomeOrderJson.class);

        ArrayList<OutputJson> outList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            OutputJson out = new OutputJson();
            out.setId(list.get(i).getOrderId());
            out.setAmount(list.get(i).getAmount());
            out.setComment(list.get(i).getComment());
            out.setFilename(file().getName());
            outList.add(out);
        }

        for(int i = 0; i < outList.size(); i++) {
            String json = new ObjectMapper().writeValueAsString(outList.get(i));
//            mapper.writeValue(outputFile(), json);
            System.out.println(json);
        }
    }

    private static File file() {
        return new File("src/main/resources/jsonUnlimint.json");
    }
    
    private static File outputFile(){
        return new File("src/main/resources/jsonUnlimintOut.json");
    }
}
