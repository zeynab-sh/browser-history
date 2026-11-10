public class ListTest {
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, boolean condition){
        if(condition){
            System.out.println("PASS: " + name);
            passed++;
        } else {
            System.out.println("FAIL: " + name);
            failed++;
        }
    }

    private static String readForward(DoublyLinkedList<String> list){
        String text = "";
        Node<String> current = list.getHead();
        while(current != null){
            text += current.data;
            current = current.next;
        }
        return text;
    }

    private static String readBackward(DoublyLinkedList<String> list){
        String text = "";
        Node<String> current = list.getTail();
        while(current != null){
            text += current.data;
            current = current.prev;
        }
        return text;
    }

    private static void testEmptyList(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        check("list is empty", list.isEmpty());
        check("size is 0", list.size() == 0);
        check("no head, no tail", list.getHead() == null && list.getTail() == null);
        check("removeFirst on empty list", list.removeFirst() == null);
        check("size stays 0 after removeFirst", list.size() == 0);
    }

    private static void testAddLast(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        Node<String> nodeA = list.addLast("A");
        check("first node is head and tail", list.getHead() == nodeA && list.getTail() == nodeA);

        list.addLast("B");
        Node <String> nodeC = list.addLast("C");
        check("forward is ABC", readForward(list).equals("ABC"));
        check("backward is CBA", readBackward(list).equals("CBA"));
        check("size is 3", list.size() == 3);
        check("returns tail C", list.getTail() == nodeC);
    }

    

    public static void main(String[] args){
        testEmptyList();
        testAddLast();
        System.out.println("Passed: " + passed + " Failed: " + failed);
    }
}