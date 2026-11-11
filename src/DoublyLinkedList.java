public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<T> addLast(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return newNode;
    }

    public Node<T> insertBefore(Node<T> node, T data){
        if(node == null){
            return addLast(data);
        }
        Node<T> newNode = new Node<>(data);
        newNode.next = node;
        newNode.prev = node.prev;
        if(node.prev == null){
            head = newNode;
        } else {
            node.prev.next = newNode;
        }
        node.prev = newNode;
        size++;
        return newNode;
    }

    public T remove(Node<T> node){
        if(node == null){
            return null;
        }
        if(node.prev == null){
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if(node.next == null){
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
        size--;
        return node.data;
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        return remove(head);
    }

    public void removeAllAfter(Node<T> node){
        if(node == null){
            return;
        }
        Node<T> current = node.next;
        while(current != null){
            size--;
            current = current.next;
        }
        node.next = null;
        tail = node;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }
}

