public class NavigationTest {
    static int passed = 0;
    static int failed = 0;

    static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS  " + name);
        } else {
            failed++;
            System.out.println("FAIL  " + name);
        }
    }

    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory("a.com");
        check("start: current is a.com", history.currentPage().getUrl().equals("a.com"));
        check("start: visit number is 1", history.currentPage().getVisitNumber() == 1);

        history.visit("b.com");
        history.visit("c.com");
        check("visit: current is c.com", history.currentPage().getUrl().equals("c.com"));
        check("visit: visit number is 3", history.currentPage().getVisitNumber() == 3);
        check("back(1) goes to b.com", history.back(1).equals("b.com"));
        check("back(1) again goes to a.com", history.back(1).equals("a.com"));
        check("forward(2) goes to c.com", history.forward(2).equals("c.com"));
        check("back(100) stops at a.com", history.back(100).equals("a.com"));
        check("forward(100) stops at c.com", history.forward(100).equals("c.com"));
        check("back(0) does not move", history.back(0).equals("c.com"));

        history.currentPage();
        history.currentPage();
        check("currentPage does not change anything", history.currentPage().getUrl().equals("c.com"));

        history = new BrowserHistory("only.com");
        check("one page: back(5) stays", history.back(5).equals("only.com"));
        check("one page: forward(5) stays", history.forward(5).equals("only.com"));

        history = new BrowserHistory("a.com");
        history.visit("b.com");
        history.visit("c.com");
        history.back(2);
        history.visit("x.com");
        check("branch: current is x.com", history.currentPage().getUrl().equals("x.com"));
        check("branch: forward pages are gone", history.forward(5).equals("x.com"));
        check("branch: back from x.com goes to a.com", history.back(5).equals("a.com"));
        check("branch: forward(1) goes to x.com", history.forward(1).equals("x.com"));

        history = new BrowserHistory("a.com", 3);
        history.visit("b.com");
        history.visit("c.com");
        history.visit("d.com");
        check("capacity 3: a.com was removed", history.back(100).equals("b.com"));

        history = new BrowserHistory("p0");
        for (int i = 1; i < 120; i++) {
            history.visit("p" + i);
        }
        check("capacity 50: oldest page is p70", history.back(1000).equals("p70"));
        check("capacity 50: newest page is p119", history.forward(1000).equals("p119"));

        System.out.println();
        history = new BrowserHistory("a.com");
        history.visit("b.com");
        history.visit("c.com");
        history.back(1);
        history.displayHistory();

        System.out.println();
        System.out.println("Passed: " + passed + "   Failed: " + failed);
    }
}