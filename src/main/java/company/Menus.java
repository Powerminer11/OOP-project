package company;

public abstract class Menus  {
    //a class for all the menus
    public static final String mainMenu =
            "Main Menu: Please choose among the options below.\n" +
            "0. Close system.\n" +
            "1. Open Item options.\n" +
            "2. Open Review options.\n" +
            "3. Open Transaction history options.\n" +
                    "4. Open Employee options.\n" +
            "\nType an option number:\n";

    public static final String itemMenu =
            "Item options menu:\n" +
                    "0. Return to Main Menu.\n" +
                    "1. Create an Item.\n" +
                    "2. Remove an Item.\n" +
                    "3. Print all registered Items.\n" +
                    "4. Buy an Item.\n" +
                    "5. Update an item’s name.\n" +
                    "6. Update an item’s price.\n" +
                    "7. Print a specific item.\n" +
                    "\nType an option number:\n";

    public static final String reviewMenu =
            "Reviews options menu:\n" +
            "0. Return to Main Menu.\n" +
            "1. Create a review for an Item.\n" +
            "2. Print a specific review of an Item.\n" +
            "3. Print all reviews of an Item.\n" +
            "4. Print mean grade of an Item\n" +
            "5. Print all comments of an Item.\n" +
            "6. Print all registered reviews.\n" +
            "7. Print item(s) with most reviews.\n" +
            "8. Print item(s) with least reviews.\n" +
            "9. Print item(s) with best mean review grade.\n" +
            "10. Print item(s) with worst mean review grade.\n" +
            "\nType an option number:\n";

    public static final String transactionMenu =
            "Transaction History options menu:\n" +
            "0. Return to Main Menu.\n" +
            "1. Print total profit from all item purchases\n" +
            "2. Print total units sold from all item purchases\n" +
            "3. Print the total number of item transactions made.\n" +
            "4. Print all transactions made.\n" +
            "5. Print the total profit of a specific item.\n" +
            "6. Print the number of units sold of a specific item.\n" +
            "7. Print all transactions of a specific item.\n" +
            "8. Print item with highest profit.\n" +
            "\nType an option number:\n";

    public static final String employeeMenu =
            "Employee options menu:\n" +
            "0. Return to Main Menu.\n" +
            "1. Create an employee (Regular Employee).\n" +
            "2. Create an employee (Manager).\n" +
            "3. Create an employee (Director).\n" +
            "4. Create an employee (Intern).\n" +
            "5. Remove an employee.\n" +
            "6. Print specific employee.\n" +
            "7. Print all registered employees.\n" +
                    "8. Print the total expense with net salary.\n" +
                    "9. Print all employees sorted by gross salary.\n" +
                    "\nType an option number:\n";

    public static final String exitMessage = "Exiting the system. Goodbye!";

    public static final String incorrectInput = "Invalid menu option. Please type another option";
}

