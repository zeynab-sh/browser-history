import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HistoryFileManager {

    
    public static void exportHistory(
            DoublyLinkedList<Page> history,
            String filename) throws IOException {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(filename))) {

            Node<Page> current = history.getHead();

            while (current != null) {

                writer.write(current.data.getUrl());
                writer.newLine();

                current = current.next;
            }
        }
    }

    
    public static void importHistory(
            DoublyLinkedList<Page> history,
            String filename) throws IOException {

        try (BufferedReader reader =
                new BufferedReader(new FileReader(filename))) {

            String url;
            int visitNumber = 1;

            
            history.clear();

            while ((url = reader.readLine()) != null) {

                
                if (url.trim().isEmpty()) {
                    continue;
                }

                
                Page page = new Page(
                        url,
                        visitNumber,
                        "Imported"
                );

                // Adding page to doubly linked list
                history.addLast(page);

                visitNumber++;
            }
        }
    }
}
