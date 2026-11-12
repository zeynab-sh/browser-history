public class BrowserHistory {
    public static final int CAPACITY = 50;
    
    private DoublyLinkedList<Page> list;
    private Node<Page> current;
    private DeletedPageStack deletedPages;
    private int nextVisitNumber;
    private int capacity;
}
