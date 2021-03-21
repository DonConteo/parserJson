import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        ArrayList<File> inputFiles = new ArrayList<>();
        ArrayList<IncomeOrder> inputOrders = new ArrayList<>();

        inputFiles.add(new File("src/main/resources/csvFile.csv"));
        inputFiles.add(new File("src/main/resources/jsonFile.json"));

        for(int i = 0; i < inputFiles.size(); i++){     //Чтение каждого файла из коллекции входящих фалов, создание объекта и добавление его в коллекцию входящих заказов
            inputOrders.addAll(readFile(inputFiles.get(i)));
        }

        JsonWriter writer = new JsonWriter();
        writer.writeFile(inputOrders);      //Получение всех объектов из коллекции входящих файлов и выведение их в консоль в нужном формате
    }

    public static ArrayList<IncomeOrder> readFile(File file){      //Метод для чтения файла с расширениями json/csv

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
