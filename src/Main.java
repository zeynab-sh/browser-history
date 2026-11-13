import java.util.Scanner;

/*1. Visit a page        6. Delete a page
2. Back                7. Restore last deleted page
3. Forward             8. Delete all history
4. Show current page   9. Export history
5. Show history        10. Import history
0. Exit
Choose: 2 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Browser History");
        String homepage = readUrl("Enter homepage URL: ");
        BrowserHistory history = new BrowserHistory(homepage); 

        int choice = -1;
        while(choice != 0){
            printMenu();
            choice = readNumber("Choose: ");
            System.out.println(); // empty line

            if(choice==1){
                String url = readUrl("Enter URL: ");
                history.visit(url);
                System.out.println("Current URl: "+ url);
            } else if(choice==2){
                int steps = readSteps();
                String url = history.back(steps);
                if(url != null){ //empty history
                    System.out.println("Current URL: "+ url);
                }
            }else if(choice==3){
                int steps = readSteps();
                String url = history.forward(steps);
                if(url != null){ 
                    System.out.println("Current URL: "+ url);
                }
            }else if(choice==4){
                Page page = history.currentPage();
                if (page != null) {
                    System.out.println("Current page: " + page); //give full paga info
                }
            }else if(choice==5){
                history.displayHistory();
            }
        }
    }
}
