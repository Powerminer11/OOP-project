package company;
import facade.*;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Facade facade = new Facade();
    public static void main(String[] args) {
//may be worth using static void or class to implement in other classes
        doMainMenu();
    }
    public static void doMainMenu() {
        System.out.println(Menus.mainMenu);
        int input = scanner.nextInt();
        //Close System
        if (input == 0) {
            System.out.println(Menus.exitMessage);

            //Open Item Options
        } else if (input == 1) {
            doItem();

            //Open Review Options
        } else if (input == 2) {
            doReview();

            //Open Transaction Options
        } else if (input == 3) {
            doTransactions();
            //Open Employee Options
        } else if (input == 4) {
            doEmployee();
            //in case of incorrect input
        } else {
            System.out.println(Menus.incorrectInput);
            doMainMenu();
        }
    }
        public static void doItem(){
        String id = "";
        System.out.println(Menus.itemMenu);
        int input = scanner.nextInt();
            //return to main menu
            if (input == 0) {
                doMainMenu();
                //create an item
            } else if (input == 1) {
                id = Input.stringReader("Please specify id:");
                String name = Input.stringReader("Please specify name:");
                double price = Input.doubleReader("Please specify price:");
                System.out.println(facade.createItem(id, name, price));
                doItem();
                //remove an item
            } else if (input == 2) {
                id = Input.stringReader("Please specify item to be removed:");
                if (!(facade.findItem(id) == -1)) {
                    System.out.println(facade.removeItem(id));
                } else {
                    System.out.println("Item was not registered.");
                } doItem();
                //print all registered items
            } else if (input == 3) {
                facade.printAllItems();
                doItem();
                //buy an items
            } else if (input == 4) {
                id = Input.stringReader("Please specify id of item you wish to purchase:");
                if ((facade.findItem(id) == -1)) {
                    System.out.println("Item is not registered");
                } else {
                int amount = Input.intReader("Please specify the number of items you wish to purchase:");
                if (amount > 0) {
                    System.out.println(facade.buyItem(id, amount));
                } else {
                    System.out.println("Please pick a valid amount.");
                }doItem();
                }
                //update an items name
            } else if (input == 5) {
                String name;
                id = Input.stringReader("Please specify item:");
                name = Input.stringReader("Please specify new name:");
                System.out.println(facade.updateItemName(id, name));
                doItem();
                //update an items price
            } else if (input == 6) {
                double price;
                id = Input.stringReader("Please specify item id:");
                price = Input.doubleReader("Please specify new price:");
                System.out.println(facade.updateItemPrice(id, price));
                doItem();
                //print a specific item
            } else if (input == 7) {
                id = Input.stringReader("Please specify item id:");
                facade.printItem(id);
                doItem();
            } else {
                System.out.println(Menus.incorrectInput);
                doItem();
            }

    }
    public static void doReview(){
        System.out.println(Menus.reviewMenu);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        //Return to Main Menu
        if (input == 0) {
            doMainMenu();
            //Create a review for an Item
        } else if (input == 1) {
            String id = Input.stringReader("Please specify item");
            String comment = Input.stringReader("Do you wish to leave a comment?\n " +
                            "If yes write your comment now else press enter");
            int grade = Input.intReader("Please leave a review between 1 and 5:");
            if (!comment.isEmpty()) {
                System.out.println(facade.reviewItem(id, comment, grade));
            } else
                System.out.println(facade.reviewItem(id, grade));
            doReview();
            //Print a specific review of an Item
        } else if (input == 2) {
            String id = Input.stringReader("Please specify the item you wish to be printed:");
            int reviewNum = Input.intReader("Please specify the review you wish to be printed:");
            System.out.println(facade.getPrintedItemReview(id, reviewNum));
            doReview();
            //Print all reviews of an Item
        } else if (input == 3) {
            String id = Input.stringReader("Please specify the item you wish to be printed:");
            System.out.println(facade.getPrintedReviews(id));
            doReview();
            //Print mean grade of an Item
        } else if (input == 4) {
            String id = Input.stringReader("Please specify the item you wish to be printed:");
            System.out.println(facade.getItemMeanGrade(id));
            doReview();
            //Print all comments of an Item
        } else if (input == 5) {
            String id = Input.stringReader("Please specify the item you wish to be printed:");
            System.out.println(facade.getItemComments(id));
            doReview();
            //Print all registered reviews
        } else if (input == 6) {
            System.out.println(facade.printAllReviews());
            doReview();
            //Print item(s) with most reviews
        } else if (input == 7) {
            System.out.println(facade.printMostReviewedItems());
            doReview();
            //Print item(s) with the least reviews
        } else if (input == 8) {
            System.out.println(facade.printLeastReviewedItems());
            doReview();
            //Print item(s) with best mean review grade
        } else if (input == 9) {
            System.out.println(facade.printBestReviewedItems());
            doReview();
            //Print item(s) with worst mean review grade
        } else if (input == 10) {
            System.out.println(facade.getWorseReviewedItems());
        } else System.out.println(Menus.incorrectInput);
        doReview();
    }
    public static void doTransactions(){
        System.out.println(Menus.transactionMenu);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        //Return to main menu
        if (input == 0) {
            doMainMenu();
            //Print total profit from all item purchases
        } else if (input == 1) {
            System.out.println(facade.getTotalProfit());
            doTransactions();
            //Print total units sold from all item purchases
        } else if (input == 2) {
            System.out.println(facade.getTotalUnitsSold());
            doTransactions();
            //Print the total number of item transactions made
        } else if (input == 3) {
            System.out.println(facade.getTotalTransactions());
            doTransactions();
            //Print all transactions made
        } else if (input == 4) {
            System.out.println(facade.printAllTransactions());
            doTransactions();
            //Print the total profit of a specific item
        } else if (input == 5) {
            String id = Input.stringReader("Please specify the item:");
            System.out.println(facade.getUnitsSolds(id));
            doTransactions();
            //Print the number of units sold of a specific item
        } else if (input == 6) {
            String id = Input.stringReader("Please specify the item:");
            System.out.println(facade.getUnitsSolds(id));
            doTransactions();
            //Print all transactions of a specific item
        } else if (input == 7) {
            String id = Input.stringReader("Please specify the item:");
            System.out.println(facade.printItemTransactions(id));
            doTransactions();
            //Print item with the highest profit
        } else if (input == 8) {
            System.out.println(facade.printMostProfitableItems());
            doTransactions();
        } else System.out.println(Menus.incorrectInput);
        doTransactions();
    }
    public static void doEmployee(){
        System.out.println(Menus.employeeMenu);
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        //Return to Main Menu
        if (input == 0) {
            doMainMenu();
            //Create an employee (Regular Employee)
        } else if (input == 1) {
            String id = Input.stringReader("Please specify employee ID:");
            String name = Input.stringReader("Please specify employee name:");
            double salary = Input.doubleReader("Please specify employee salary:");
            try {
                System.out.println(facade.createEmployee(id, name, salary));
            } catch (Exception e) {
                e.printStackTrace();
                doMainMenu();
            }
            doEmployee();
            //Create an employee (Manager)
        } else if (input == 2) {
            String id = Input.stringReader("Please specify employee ID:");
            String name = Input.stringReader("Please specify employee name:");
            double salary = Input.doubleReader("Please specify employee salary:");
            String degree = Input.stringReader("Please specify degree:");
            try {
                System.out.println(facade.createEmployee(id, name, salary, degree));
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Create an employee (Director)
        } else if (input == 3) {
            String id = Input.stringReader("Please specify employee ID:");
            String name = Input.stringReader("Please specify employee name:");
            double salary = Input.doubleReader("Please specify employee salary:");
            String degree = Input.stringReader("Please specify degree:");
            String department = Input.stringReader("Please specify department:");
            try {
                System.out.println(facade.createEmployee(id, name, salary, degree, department));
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Create an employee (Intern)
        } else if (input == 4) {
            String id = Input.stringReader("Please specify employee ID:");
            String name = Input.stringReader("Please specify employee name:");
            double salary = Input.doubleReader("Please specify employee salary:");
            int gpa = Input.intReader("Please specify employee GPA:");
            try {
                System.out.println(facade.createEmployee(id, name, salary, gpa));
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Remove an employee
        } else if (input == 5) {
            String id = Input.stringReader("Please specify employee ID:");
            try {
                System.out.println(facade.removeEmployee(id));
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Print specific employee
        } else if (input == 6) {
            String id = Input.stringReader("Please specify employee ID:");
            try {
                System.out.println(facade.printEmployee(id));
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Print all registered employees
        } else if (input == 7) {
            try {
                System.out.println(facade.printAllEmployees());
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Print the total expense with net salary
        } else if (input == 8) {
            try {
                System.out.println(facade.getTotalNetSalary());
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
            //Print all employees sorted by gross salary
        } else if (input == 9) {
            try {
                System.out.println(facade.printSortedEmployees());
            } catch (Exception e) {
                e.printStackTrace();
                doEmployee();
            }
            doEmployee();
        }else System.out.println(Menus.incorrectInput);
        doEmployee();
    }
}




