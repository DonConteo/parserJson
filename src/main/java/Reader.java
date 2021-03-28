import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

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

    public void readCsv(File file) throws IOException {         //Создание коллекции объектов из файла в формате csv

        CSVReader reader = new CSVReader(new FileReader(file));
        String[] mapping;
        long orderLine = 1;

        while ((mapping = reader.readNext()) != null) {
            Order order = new Order();
            order.setOrderId(checkId(mapping[0]));
            order.setAmount(checkAmount(mapping[1]));
            order.setCurrency(checkCurrency(mapping[2]));
            order.setComment(checkComment(mapping[3]));
            order.setFileName(file.getName());
            order.setLine(orderLine);
            if(result == null){order.setResult("OK");}
            list.add(order);
            orderLine++;
        }
    }


    //Проверки
    private long checkId(String str){
        long l = 0;
        try {
            l = Long.parseLong(str);
        }
        catch (Exception e){
            result += "Parsing ID failed! ";
        }
        return l;
    }

    private double checkAmount(String str){
        double l = 0;
        try {
            l = Double.parseDouble(str);
        }
        catch (Exception e){
            result += "Parsing amount failed! ";
        }
        return l;
    }

    private String checkCurrency(String str){
        if(str == null){
            result += "Currency is null! ";
        }
        return str;
    }

    private String checkComment(String str){
        if(str == null){
            result += "Unknown payment! ";
        }
        return str;
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

