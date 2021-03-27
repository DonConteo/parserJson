import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonWriter {

        List<Order> outputOrders = new ArrayList<>();

    public void writeFile(List<Order> list){         //Создание коллекции объектов в нужном формате для вывода в консоль

        for(int i = 0; i < list.size(); i++) {
                Order out = new Order();
                out.setOrderId(list.get(i).getOrderId());
                out.setAmount(list.get(i).getAmount());
                out.setCurrency(list.get(i).getCurrency());
                out.setComment(list.get(i).getComment());
                out.setFileName(list.get(i).getFileName());
                out.setLine(list.get(i).getLine());
                out.setResult(list.get(i).getResult());
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
