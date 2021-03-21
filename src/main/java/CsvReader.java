import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    List<IncomeOrder> list = new ArrayList<>();

    public void readCsv(File file) throws IOException {

        String fileName = file.getName();

        CSVReader reader = new CSVReader(new FileReader(file));
        String[] mapping;

        while ((mapping = reader.readNext()) != null) {
            IncomeOrder order = new IncomeOrder();
            order.setOrderId(Long.parseLong(mapping[0]));
            order.setAmount(Double.parseDouble(mapping[1]));
            order.setCurrency(mapping[2]);
            order.setComment(mapping[3]);
            order.setFileName(fileName);
            list.add(order);
        }
    }
}
