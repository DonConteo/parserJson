import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class JsonWriter {

        ArrayList<OutputJson> outputOrders = new ArrayList<>();

    public void writeFile(ArrayList<IncomeOrder> list){         //Создание коллекции объектов в нужном формате для вывода в консоль

        for(int i = 0; i < list.size(); i++) {
                OutputJson out = new OutputJson();
                out.setId(list.get(i).getOrderId());
                out.setAmount(list.get(i).getAmount());
                out.setCurrency(list.get(i).getCurrency());
                out.setComment(list.get(i).getComment());
                out.setFilename(list.get(i).getFileName());
                out.setLine(list.get(i).getLine());
                outputOrders.add(out);
        }

        for(int i = 0; i < outputOrders.size(); i++) {          //Вывод коллекции в консоль
            String output = null;
            try {
                output = new ObjectMapper().writeValueAsString(outputOrders.get(i));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(output);
        }
    }
}
