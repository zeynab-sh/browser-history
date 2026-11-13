import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BrowserHistory {
    private DoublyLinkedList<Page> list;
    private Node<Page> current;
    private DeletedPageStack deletedPages;
    private int nextVisitNumber;
    private int capacity;

    public BrowserHistory(String homepage) {
        this(homepage, 50);
    }

    public BrowserHistory(String homepage, int capacity) {
        this.capacity = capacity;
        list = new DoublyLinkedList<>();
        deletedPages = new DeletedPageStack();
        nextVisitNumber = 1;
        current = list.addLast(new Page(homepage, nextVisitNumber, getTimeNow()));
        nextVisitNumber++;
    }

    private String getTimeNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return now.format(format);
    }

    public String visit(String url) {
        if (current != null) {
            list.removeAllAfter(current);
        }
        Page page = new Page(url, nextVisitNumber, getTimeNow());
        nextVisitNumber++;
        current = list.addLast(page);
        if (list.size() > capacity) {
            list.removeFirst();
        }
        return current.data.getUrl();
    }

    public String back(int steps) {
        if (current == null) {
            System.out.println("History is empty");
            return null;
        }
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.data.getUrl();
    }

    public String forward(int steps) {
        if (current == null) {
            System.out.println("History is empty");
            return null;
        }
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.data.getUrl();
    }
    public Page currentPage() {
        if (current == null) {
            System.out.println("History is empty");
            return null;
        }
        return current.data;
    }

    public void displayHistory() {
        if (list.isEmpty()) {
            System.out.println("History is empty");
            return;
        }
        int number = 1;
        Node<Page> node = list.getHead();
        while (node != null) {
            String marker = "";
            if (node == current) {
                marker = " [<]";
            }
            System.out.println(number + ". " + node.data.toString() + marker);
            node = node.next;
            number++;
        }
    }

}
