import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        IncomeOrderJson order = mapper.readValue(jsonFile(), IncomeOrderJson.class);

        OutputJson out = new OutputJson();
        out.setId(order.getOrderId());
        out.setAmount(order.getAmount());
        out.setComment(order.getComment());

        String json = new ObjectMapper().writeValueAsString(out);
        mapper.writeValue(outJsonFile(), json);
        System.out.println(json);
    }

    private static File jsonFile() {
        return new File("src/main/resources/jsonUnlimint.json");
    }
    
    private static File outJsonFile(){
        return new File("src/main/resources/jsonUnlimintOut.json");
    }
}
