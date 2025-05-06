import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.*;

public class TestData {

    // Read CSV file and return list of records
    public static List<Map<String, String>> readFile(String filename) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        Path path = Paths.get(filename);
        Reader reader = Files.newBufferedReader(path);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for (CSVRecord record : csvParser) {
            Map<String, String> row = new HashMap<>();
            for (String header : csvParser.getHeaderMap().keySet()) {
                row.put(header, record.get(header));
            }
            data.add(row);
        }
        csvParser.close();
        return data;
    }

    // Calculate SMA (Simple Moving Average)
    public static List<Double> SMA(List<Double> data, int n) {
        List<Double> sma = new ArrayList<>();
        for (int i = 0; i <= data.size() - n; i++) {
            double sum = 0;
            for (int j = i; j < i + n; j++) {
                sum += data.get(j);
            }
            sma.add(sum / n);
        }
        return sma;
    }

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir");
        System.out.println("当前项目运行目录: " + path);



        // Load data from CSV (you need to adjust the paths to match your files)
        List<Map<String, String>> btcData = readFile(path+ File.separator+ "BTCUSD.csv");
        List<Map<String, String>> googData = readFile(path+ File.separator+ "GOOG.csv");
        List<Map<String, String>> eurusdData = readFile(path+ File.separator+ "EURUSD.csv");

        // For example, let's assume the "Close" price is in the second column (adjust as necessary)
        List<Double> btcClosePrices = new ArrayList<>();
        for (Map<String, String> row : btcData) {
            String priceStr = row.get("Close"); // Replace "Close" with the correct column header
            if (priceStr != null) {
                btcClosePrices.add(Double.parseDouble(priceStr));
            }
        }

        // Calculate the 5-period SMA for BTC/USD data
        List<Double> sma5 = SMA(btcClosePrices, 5);
        System.out.println("5-period SMA for BTC/USD: " + sma5);
    }
}
