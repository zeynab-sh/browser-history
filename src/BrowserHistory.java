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

    public boolean deleteByNumber(int number) {
        if (number < 1 || number > list.size()) {
            return false;
        }

        Node<Page> node = list.getHead();

        for (int i = 1; i < number; i++) {
            node = node.next;
        }

        if (node == current) {
            if (node.prev != null) {
                current = node.prev;
            } else {
                current = node.next;
            }
        }

        Page deletedPage = list.remove(node);
        deletedPages.push(deletedPage);

        return true;
    }

    public boolean restoreLast() {
        if (deletedPages.isEmpty()) {
            return false;
        }

        Page restoredPage = deletedPages.pop();

        Node<Page> node = list.getHead();

        while (node != null &&
               node.data.getVisitNumber() <
               restoredPage.getVisitNumber()) {
            node = node.next;
        }

        Node<Page> restoredNode =
            list.insertBefore(node, restoredPage);

        if (current == null) {
            current = restoredNode;
        }

        if (list.size() > capacity) {
            Node<Page> first = list.getHead();

            if (first == current) {
                current = first.next;
            }

            list.removeFirst();
        }

        return true;
    }

    public void deleteHistory() {
        list.clear();
        current = null;
        deletedPages.clear();
    }

    public void addImportedPage(String url, String time) {
        Page page = new Page(url, nextVisitNumber, time);
        nextVisitNumber++;

        current = list.addLast(page);

        if (list.size() > capacity) {
            list.removeFirst();
        }
    }

    public DoublyLinkedList<Page> getList() {
        return list;
    }

    public int deletedCount() {
        return deletedPages.size();
    }
}

    