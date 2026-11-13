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

    public static void main(String[] args) {
        testEmptyStack();

        System.out.println("Passed:" + passed + "Failed:" + failed);

    }
}
