import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    List<Order> list = new ArrayList<>();
    String result;

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
}
