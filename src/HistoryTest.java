public class HistoryTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, boolean condition) {
        if (condition){
            System.out.println("PASS: " + name);
            passed ++;
        }else{
            System.out.println("FAIL: " + name);
            failed ++;
        }
    }

    private static void testEmptyStack(){
        DeletedPageStack stack = new DeletedPageStack();
        check("New stack is empty", stack.isEmpty());
        check("New stack size is zero",  stack.size() == 0);
        check("Peek on empty stack", stack.peek() == null);
        check("Pop on empty stack", stack.pop() == null);
    }
    private static void testStack() {
        DeletedPageStack stack = new DeletedPageStack();
        Page p1 = new Page("google.com", 1, "10:00");
        Page p2 = new Page("youtube.com", 2, "10:05");
        stack.push(p1);
        stack.push(p2);

        check("Stack size", stack.size() == 2);
        check("LIFO order", stack.pop() == p2);
        check("Peek", stack.peek() == p1);
        stack.clear();
        check("Clear stack", stack.isEmpty());
    }

    private static void testCapacity() {
        BrowserHistory history = new BrowserHistory("google.com", 2);
        history.visit("youtube.com");
        history.visit("github.com");

        check("Capacity limit",   history.getList().size() == 2);
        check("Oldest page removed", history.getList().getHead().data.getUrl().equals("youtube.com"));
    }
    private static void testHistory() {
        BrowserHistory history = new BrowserHistory("google.com");

        history.visit("youtube.com");
        history.visit("github.com");

        check("Initial history size",history.getList().size() == 3);
        check("Invalid deletion",!history.deleteByNumber(10));
        check("Delete page",history.deleteByNumber(2));
        check("Deleted count",history.deletedCount() == 1);
        check("Restore page", history.restoreLast());
        check("Deleted count after restore", history.deletedCount() == 0);
        check("Restore empty stack",!history.restoreLast());
        history.addImportedPage("ada.edu.az", "10:30");
        check("Imported timestamp",history.currentPage().getVisitTime().equals("10:30"));
        history.deleteHistory();
        check("Delete all history", history.getList().isEmpty());
        check("Current page cleared", history.currentPage() == null);
    }


      

    public static void main(String[] args) {
        testEmptyStack();
        testCapacity();
        testHistory();
        testStack();

        System.out.println("Passed:" + passed + "Failed:" + failed);

    }
}
