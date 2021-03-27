import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    ObjectMapper mapper = new ObjectMapper();
    List<Order> list = new ArrayList<>();
    String result;

    public void readJson(File file) throws FileNotFoundException {           //Создание коллекции объектов из файла в формате json

        String fileName = file.getName();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        try {
            String line = reader.readLine();
            long orderLine = 1;
            while (line != null) {
                Order order = checkOrder(mapper.readValue(line, Order.class));
                order.setFileName(fileName);
                order.setLine(orderLine);
                if(result == null){order.setResult("OK");}
                list.add(order);
                orderLine++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Order checkOrder(Order order){
        if(order.getOrderId() == 0){
            result += "ID is null! ";
        }
        if(order.getCurrency() == null){
            result += "Currency is null! ";
        }
        if(order.getComment() == null){
            result += "Unknown payment! ";
        }
        return order;
    }
}

