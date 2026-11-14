import java.util.Scanner;
import java.io.IOException;

/*1. Visit a page        6. Delete a page
2. Back                7. Restore last deleted page
3. Forward             8. Delete all history
4. Show current page   9. Export history
5. Show history        10. Import history
0. Exit
Choose: 2 */

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static String readText(String prompt){
            System.out.print(prompt);
            return scanner.nextLine().trim();
        }

    private static String readUrl(String prompt){
        String url = readText(prompt);
        while(url.isEmpty()){
            System.out.println("URL cannot be empty.");
            url = readText(prompt);
        }
        return url;
    }

    private static int readNumber(String prompt){
        while(true){
            String text = readText(prompt);
            try{
                return Integer.parseInt(text);
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static int readSteps() {
        int steps = readNumber("How many steps: ");
        while (steps < 0) {
            System.out.println("Steps must be 0 or more");
            steps = readNumber("How many steps: ");
        }
        return steps;
    }

    private static void printMenu(){
        System.out.println("1. Visit a page        6. Delete a page");
        System.out.println("2. Back                7. Restore last deleted page");
        System.out.println("3. Forward             8. Delete all history");
        System.out.println("4. Show current page   9. Export history");
        System.out.println("5. Show history        10. Import history");
        System.out.println("0. Exit");
    }

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
                System.out.println("Current URL: "+ url);
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
            }else if(choice == 6){
                history.displayHistory(); //choose from
                int number = readNumber("Enter visit number to delete: ");
                if(history.deleteByNumber(number)){
                    System.out.println("Page deleted");
                }
            }else if(choice==7){
                if(history.restoreLast()){
                    System.out.println("Page restored");
                }
            }else if(choice==8){
                history.deleteHistory();
                System.out.println("History cleared");
            }else if(choice==9){
                String fileName = readText("Enter file name to export history: ");
                try {
                    HistoryFileManager.exportHistory(history, fileName);
                    System.out.println("History exported to " + fileName);
                } catch (IOException e) {
                    System.out.println("Could not export history");
                }
            }else if(choice==10){
                String fileName = readText("Enter file name to import history: ");
                try {
                    HistoryFileManager.importHistory(history, fileName);
                    System.out.println("History imported from " + fileName);
                } catch (IOException e) {
                    System.out.println("Could not import history");
                }
            }else if(choice==0){
                System.out.println("Exiting...");
            }else{
                System.out.println("Please choose a number from menu.");
            }
        }
    }
}
