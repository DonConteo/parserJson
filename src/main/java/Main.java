import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<File> inputFiles = new ArrayList<>();
        List<Order> inputOrders = new ArrayList<>();

        inputFiles.add(new File("src/main/resources/csvFile.csv"));
        inputFiles.add(new File("src/main/resources/jsonFile.json"));

        for (File f : inputFiles){          //Чтение каждого файла из коллекции входящих фалов, создание объекта и добавление его в коллекцию входящих заказов
            try {
                inputOrders.addAll(readFile(f));
            }
            catch (FileNotFoundException e){
                System.out.println("File not found");
            }
        }
        new JsonWriter().writeFile(inputOrders);      //Получение всех объектов из коллекции входящих файлов и выведение их в консоль в нужном формате
    }

    public static List<Order> readFile(File file) throws FileNotFoundException {      //Метод для чтения файла с расширениями json/csv

        List<Order> list = new ArrayList<>();
        Reader reader = new Reader();

        switch (FilenameUtils.getExtension(String.valueOf(file)).toLowerCase()){
            case "json" -> {
                reader.readJson(file);
                list.addAll(reader.list);
            }
            case "csv" -> {
                try {
                    reader.readCsv(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.addAll(reader.list);
            }
            default -> System.out.println("Extension is not valid");
        }
        return list;
    }
}
