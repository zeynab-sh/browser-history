import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HistoryFileManager {

    public static void exportHistory(BrowserHistory history, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Node<Page> current = history.getList().getHead();
            while (current != null) {
                writer.write(current.data.getUrl());
                writer.newLine();
                current = current.next;
            }
        }
    }

    public static void importHistory(BrowserHistory history, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String url;
            while ((url = reader.readLine()) != null) {
                if (url.trim().isEmpty()) {
                    continue;
                }
                history.addImportedPage(url, "Imported");
            }
        }
    }
}