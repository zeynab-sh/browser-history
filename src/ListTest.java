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

    public static void testEmptyList(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        check("list is empty", list.isEmpty());
        check("size is 0", list.size() == 0);
        check("no head, no tail", list.getHead() == null && list.getTail() == null);
        check("removeFirst on empty list", list.removeFirst() == null);
        check("size stays 0 after removeFirst", list.size() == 0);
    }

    public static void main(String[] args){
        testEmptyList();
        System.out.println("Passed: " + passed + " Failed: " + failed);
    }
}