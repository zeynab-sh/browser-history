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

    private static void testRemove(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        Node<String> nodeA = list.addLast("A");
        list.addLast("B");
        Node<String> nodeC = list.addLast("C");
        Node<String> nodeD = list.addLast("D");

        check("remove returns data", list.remove(nodeC).equals("C"));
        check("forward is ABD", readForward(list).equals("ABD"));
        check("backward is DBA", readBackward(list).equals("DBA"));

        list.remove(nodeA);
        check("forward is BD", readForward(list).equals("BD"));
        check("backward is DB", readBackward(list).equals("DB"));

        list.remove(nodeD);
        check("forward is B", readForward(list).equals("B"));
        check("backward is B", readBackward(list).equals("B"));
        check("remove: size is 1", list.size() == 1);

    }

    //pruning
    private static void testRemoveFirst(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("A");
        list.addLast("B");

        check("removeFirst return A", list.removeFirst().equals("A"));
        check("backward is B", readBackward(list).equals("B"));
        check("removeFirst return B", list.removeFirst().equals("B"));
        check("removeFirst on empty list", list.isEmpty());
    }

    private static void testRemoveAllAfter(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("A");
        Node<String> nodeB = list.addLast("B"); // I am saving B to cut there
        list.addLast("C");
        list.addLast("D");

        list.removeAllAfter(nodeB);
        check("removeAllAfter: forward is ab", readForward(list).equals("AB"));
        check("removeAllAfter: backward is ba", readBackward(list).equals("BA"));
        check("removeAllAfter: b is the tail", list.getTail() == nodeB);
        check("removeAllAfter: size is 2", list.size() == 2);
    }

    private static void testClear(){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.clear();
        check("list is empty", list.isEmpty());
        check("no head or tail", list.getHead() == null && list.getTail() == null);
    }


    public static void main(String[] args){
        testEmptyList();
        testAddLast();
        testRemove();
        testRemoveFirst();
        testRemoveAllAfter();
        testClear();
        System.out.println("Passed: " + passed + " Failed: " + failed);
    }

    
}