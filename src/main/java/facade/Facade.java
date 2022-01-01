package facade;


import company.*;
import employees.Directors;
import employees.Employees;
import employees.Interns;
import employees.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {
    ArrayList<Items> itemsList = new ArrayList<Items>();
    ArrayList<Transactions> transactions = new ArrayList<>();
    ArrayList<Employees> employees = new ArrayList<>();
    public static final String EOL = System.lineSeparator();


    // index functions for all types of custom objects
    public int findItem(String itemID) {
        int index = -1;
        for (int i = 0; i < itemsList.size(); i++) {
            if (itemsList.get(i).getItemID().equals(itemID)) {
                index = i;
                break;
            }
        }return index;
}
    public int findTran(String itemID){
        int index = -1;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getItemID().equals(itemID)) {
                index = i;
                break;
            }
        }return index;
    }
    public int findEmployee(String itemID) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(itemID)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // truncates a value to two decimal places
    public static double truncate(double value, int decimalParts) {
        if(decimalParts==1) {
            value = value * 10.0;
            int temp = (int) value;
            value = temp / 10.0;
        }else if(decimalParts==2){
            value = value * 100.0;
            int temp = (int) value;
            value = temp / 100.0;
        }
        return value;
    }
    //makes a list of items to string
    public String convertPrint(List<String> input){
        String result = "";
        for(String in:input){
            int index = findItem(in);
            result += (itemsList.get(index).toString()) + EOL;
        }
    return result;}


    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade() {

    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        String result = "Invalid data for item.";
        if (!itemID.equals("") && !itemName.equals("") && !(unitPrice <= 0)) {
            Items item = new Items(itemID, itemName, unitPrice);
            itemsList.add(item);
            result = "Item " + itemID + " was registered successfully.";

        }
        return result;
    }

    public String printItem(String itemID) {
        String result = "Item " + itemID + " was not registered yet.";
        int k = findItem((itemID));
        if (k != -1) {
            result = itemsList.get(k).toString();
        }
        return result;
    }

    public String removeItem(String itemID) {
        String result = "Item " + itemID + " could not be removed.";
        int k = findItem(itemID);
        if (k != -1) {
            itemsList.remove(k);
            result = "Item " + itemID + " was successfully removed.";
        }
        return result;
    }

    public boolean containsItem(String itemID) {
        boolean result = false;
        for (int i = 0; i < itemsList.size(); i++) {
            if (itemsList.get(i).getItemID().equals(itemID)) {
                result = true;
                break;
            }
        }
        return result;
    }

    //dumb decimal wont take string
    public double buyItem(String itemID, int amount) {
        double result = -1;
        int k = findItem(itemID);
        if (k != -1) {
            result = itemsList.get(k).getItemPrice();
            if (amount <= 4 && amount > 0) {
                result = result * amount;
            } else if (amount > 0) {
                double discountItems = amount - 4;
                discountItems *= result * 0.7;
                result = (result * 4) + discountItems;
                result = truncate(result,2);
            }
        }
        Transactions transaction = new Transactions(itemID, amount, result);
        transactions.add(transaction);

        return result;
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        String result;
        int k = findItem(itemID);
        if (k != -1){
           if (reviewGrade > 0 && reviewGrade < 6){
               Reviews review = new Reviews(reviewGrade, reviewComment);
               itemsList.get(k).addReviews(review);

               result = "Your item review was registered successfully.";
           }else {
               result = "Grade values must be between 1 and 5.";
           }
        }else {
            result = "Item " + itemID + " was not registered yet.";
        }
        return result;
    }

    public String reviewItem(String itemID, int reviewGrade) {
        String result = "Item " + itemID + " was not registered yet.";

        int k = findItem(itemID);
        if(k != -1){
            if (reviewGrade > 0 && reviewGrade < 6){
                Reviews review = new Reviews(reviewGrade, "");
                itemsList.get(k).addReviews(review);
                result = "Your item review was registered successfully.";
            }else {
                result = "Grade values must be between 1 and 5.";
            }
        }else {
            result = "Item " + itemID + " was not registered yet.";
        }
        return result;
    }

    public String getItemCommentsPrinted(String itemID) {

        return "";
    }

    public List<String> getItemComments(String itemID) {
        int k = findItem(itemID);
        List<String> comments = new ArrayList<>();
        if(k!=-1){
            if(!itemsList.get(k).getReviews().isEmpty()){
                for (Reviews reviews:itemsList.get(k).getReviews()){
                    if (!reviews.getComment().isEmpty())
                    comments.add(reviews.getComment());}}}
        return comments;
    }

    public double getItemMeanGrade(String itemID) {
        double result = 0;
        int itemIndex = findItem(itemID);
        double sum = 0;
        int k = getNumberOfReviews(itemID);
        if(!itemsList.get(itemIndex).getReviews().isEmpty()){
            for(Reviews reviews:itemsList.get(itemIndex).getReviews()){
                sum += reviews.getGrade();
            }
        }
        result = sum/k;
        result = truncate(result, 1);
        return result;
    }

    public int getNumberOfReviews(String itemID) {
        int k = findItem(itemID);
        int result = 0;
        if(k != -1){
            result = itemsList.get(k).getReviews().size();
        }
        return result;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        int k = findItem(itemID);
        int numReview = getNumberOfReviews(itemID);
        String result = "Item " + itemID + " was not registered yet.";
        if (k != -1) {
            if (numReview == 0) {
                result = "Item " + itemsList.get(k).getItemName() + " has not been reviewed yet.";
            } else {
                if (reviewNumber < 1 || reviewNumber > numReview) {
                    result = "Invalid review number. Choose between 1 and " + numReview + ".";
                } else {
                    int count = 0;
                    result = "Item " + itemsList.get(k).getItemName() + " has not been reviewed yet.";
                    for (Reviews reviews : itemsList.get(k).getReviews()) {
                        count += 1;
                        if (reviewNumber == count) {
                            result = reviews.toString();
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public String getPrintedReviews(String itemID) {
        int k = findItem(itemID);
        String result = "Item " + itemID + " was not registered yet.";
        if(k !=-1){
            String itemToString = "Review(s) for " + itemsList.get(k).toString();
            if(!itemsList.get(k).getReviews().isEmpty()){
                result = "";
                for (Reviews reviews: itemsList.get(k).getReviews()){
                    result += reviews + EOL;
                }
                result = itemToString + EOL + result;
            } else {
                result =  itemToString + EOL + "The item " +itemsList.get(k).getItemName() +
                        " has not been reviewed yet.";
            }
        }
        return result;
    }

    public String printMostReviewedItems() {
        String result = "No items registered yet.";
        if(!itemsList.isEmpty()) {
            if (!getMostReviewedItems().isEmpty()){
            result = "Most reviews: " + getNumberOfReviews(getMostReviewedItems().get(0)) +
                    " review(s) each." + EOL + convertPrint(getMostReviewedItems());
            } else result = "No items were reviewed yet.";
        }
        return result;
    }

    public List<String> getMostReviewedItems() {
        List<String> mostReview = new ArrayList<>();
        if (!itemsList.isEmpty()) {
            String itemID = itemsList.get(0).getItemID();
            int max = getNumberOfReviews(itemsList.get(0).getItemID());
            for (Items reviews : itemsList) {
                int numReviews = getNumberOfReviews(reviews.getItemID());
                    if (numReviews > max && numReviews != 0) {
                        max = numReviews;
                        itemID = reviews.getItemID();
                    }
            }
            if (getNumberOfReviews(itemID) != 0){
                mostReview.add(itemID);
            }
                for (int i = 1; i < itemsList.size(); i++) {
                    int numReviews = getNumberOfReviews(itemsList.get(i).getItemID());
                    if (numReviews == max && itemID != itemsList.get(i).getItemID() && numReviews != 0) {
                        mostReview.add(itemsList.get(i).getItemID());
                    }
                }
        }
        return mostReview;
    }

    public List<String> getLeastReviewedItems() {
        List<String> leastReview = new ArrayList<>();
        if (!itemsList.isEmpty()) {
            String itemID = itemsList.get(0).getItemID();
            int min = getNumberOfReviews(itemsList.get(0).getItemID());
            for (Items reviews : itemsList) {
                int numReviews = getNumberOfReviews(reviews.getItemID());
                    if (numReviews < min && numReviews != 0) {
                        min = numReviews;
                        itemID = reviews.getItemID();
                    }
            }
            if (getNumberOfReviews(itemID) != 0){
                leastReview.add(itemID);
            }
            for (int i = 1; i < itemsList.size(); i++) {
                int numReviews = getNumberOfReviews(itemsList.get(i).getItemID());
                if (numReviews == min && itemID != itemsList.get(i).getItemID() && numReviews != 0) {
                    leastReview.add(itemsList.get(i).getItemID());
                }
            }
        }
        return leastReview;
    }

    public String printLeastReviewedItems() {
        String result = "No items registered yet.";
        if(!itemsList.isEmpty()) {
            if (!getMostReviewedItems().isEmpty()){
                result = "Least reviews: " + getNumberOfReviews(getLeastReviewedItems().get(0)) +
                        " review(s) each." + EOL + convertPrint(getLeastReviewedItems());
            } else result = "No items were reviewed yet.";
        }
        return result;
    }

    public double getTotalProfit() {
        double result = 0;
        for (Transactions transactions:transactions){
          result += transactions.getPrice();
        }
        return result;
    }


//needs a for each loop
    public String printItemTransactions(String itemID) {
        String result = "Item " + itemID + " was not registered yet.";
        int k = findTran(itemID);
        if (findItem(itemID) != -1) {
            if (k != -1) {
                result = "Transactions for item: " + itemID + ": " + itemsList.get(findItem(itemID)).getItemName() + "."
                        + " " + itemsList.get(findItem(itemID)).getItemPrice() + " SEK" + EOL;
                for (Transactions transactions : transactions) {
                    if (transactions.getItemID() == itemID) {
                        result += transactions.toString() + EOL;
                    }
                }
            } else result = "Transactions for item: " + itemsList.get(findItem(itemID)).toString() + EOL +
                    "No transactions have been registered for item " + itemID + " yet.";
        }
        return result;
    }

    public int getTotalUnitsSold() {
        int result = 0;
        for (Transactions transactions:transactions){
            if (transactions.getAmount() != 0){
                result += transactions.getAmount();
            }
        }
        return result;
    }

    public int getTotalTransactions() {
        int result = transactions.size();
        return result;
    }

    public double getProfit(String itemID) {
        double result = 0;
        for (Transactions transactions : transactions) {
            if (transactions.getItemID() == itemID) {
                result += transactions.getPrice();
            }
        }
        return result;
    }
    public int getUnitsSolds(String itemID) {
        int result = 0;
        for (Transactions transactions:transactions) {
            if (transactions.getAmount() != 0 && transactions.getItemID() == itemID) {
                result += transactions.getAmount();
            }
        }
        return result;
    }
//again roundval, and remove the zero
    public String printAllTransactions() {
        String result = "All purchases made: " + EOL + "Total profit: " +  String.format("%.2f",getTotalProfit()) +
                 " SEK"+ EOL + "Total items sold: " + getTotalUnitsSold() + " units" +
                EOL + "Total purchases made: " + getTotalTransactions() + " transactions"
                + EOL + "------------------------------------" + EOL;
        if (getTotalUnitsSold() != 0) {
            for (Transactions transactions : transactions) {
                result += transactions.toString() + EOL;
            }
        }
        result += "------------------------------------" + EOL;
        return result;
    }
    public String printWorseReviewedItems() {
        String result = "No items registered yet.";
        if(!itemsList.isEmpty()) {
            if (!getLeastReviewedItems().isEmpty()) {

                result = "Items with worst mean reviews:" + EOL + "Grade: " + getItemMeanGrade(getWorseReviewedItems().get(0)) +
                        EOL + convertPrint(getWorseReviewedItems());
            } else result = "No items were reviewed yet.";
        }
        return result;
    }

    public String printBestReviewedItems() {
        String result = "No items registered yet.";
        if(!itemsList.isEmpty()) {
            if(!getLeastReviewedItems().isEmpty()) {
                result = "Items with best mean reviews:" + EOL + "Grade: " + getItemMeanGrade(getBestReviewedItems().get(0)) +
                        EOL + convertPrint(getBestReviewedItems());
            } else result = "No items were reviewed yet.";
        }
        return result;
    }

    public List<String> getWorseReviewedItems() {
        List<String> worstReviewed = new ArrayList<>();
        if(!itemsList.isEmpty()) {
            String itemID = itemsList.get(0).getItemID();
            double min = getItemMeanGrade(itemsList.get(0).getItemID());
            for (Items reviews : itemsList) {
                double numReviews = getItemMeanGrade(reviews.getItemID());
                    if (numReviews < min && numReviews != 0) {
                        min = numReviews;
                        itemID = reviews.getItemID();
                    }
            }
            if (getNumberOfReviews(itemID) != 0){
                worstReviewed.add(itemID);}
            for (int i = 1; i < itemsList.size(); i++) {
                double secondMean = getItemMeanGrade(itemsList.get(i).getItemID());
                if (secondMean == min && itemID != itemsList.get(i).getItemID() && secondMean != 0) {
                    worstReviewed.add(itemsList.get(i).getItemID());
                }
            }
        }
        return worstReviewed;
    }

    public List<String> getBestReviewedItems() {
        List<String> bestReviewed = new ArrayList<>();
        if (!itemsList.isEmpty()) {
            String itemID = itemsList.get(0).getItemID();
            double max = getItemMeanGrade(itemsList.get(0).getItemID());
            for (Items reviews : itemsList) {
                double numReviews = getItemMeanGrade(reviews.getItemID());
                if (numReviews > max && numReviews != 0) {
                    max = numReviews;
                    itemID = reviews.getItemID();
                }
            }
            if (getNumberOfReviews(itemID) != 0){
                bestReviewed.add(itemID);}
            for (int i = 1; i < itemsList.size(); i++) {
                double secondMean = getItemMeanGrade(itemsList.get(i).getItemID());
                if (secondMean == max && itemID != itemsList.get(i).getItemID() && secondMean != 0) {
                    bestReviewed.add(itemsList.get(i).getItemID());
                }
            }
        }
        return bestReviewed;
    }

    public String printAllReviews() {
        String result = "No items registered yet.";
        String other = "";
        if(!itemsList.isEmpty()){
            for (Items reviews:itemsList){
                if (!reviews.getReviews().isEmpty()){
                    other += reviews.getItemID();
                }
            }
            if (!other.isEmpty()) {
                result = "All registered reviews:" + EOL + "------------------------------------" + EOL;
                for (Items reviews : itemsList) {
                    if (!reviews.getReviews().isEmpty()) {
                        result += getPrintedReviews(reviews.getItemID()) +
                                "------------------------------------" + EOL;
                    }
                }
            } else result = "No items were reviewed yet.";
        }
        return result;
    }

    public String updateItemName(String itemID, String newName) {
        String result = "Item " + itemID + " was not registered yet.";
        int k = findItem(itemID);
        if (k != -1) {
            if(newName.isEmpty()){
                result = "Invalid data for item.";
            }else {
                itemsList.get(k).setItemName(newName);
                result = "Item " + itemID + " was updated successfully.";}
        }
        return result;
    }

    public String updateItemPrice(String itemID, double newPrice) {
        String result = "Item " + itemID + " was not registered yet.";
        int k = findItem(itemID);
        if (k != -1) {
            if(newPrice < 1){
                result = "Invalid data for item.";
            }else{
            itemsList.get(k).setItemPrice(newPrice);
            result = "Item " + itemID + " was updated successfully.";}
        }
        return result;
    }

    public String printAllItems() {
        String result = "No items registered yet.";
        if (!itemsList.isEmpty()) {
            result = "All registered items:";
            for (Items items : itemsList){
                result += EOL + items.toString();}
            result += EOL;
        }
        return result;}

// roundVal wont work
    public String printMostProfitableItems() {
        String result = "No items registered yet.";
        double maxVal = 0;
        double price = 0;
        String itemID = "";
        if (!transactions.isEmpty()){
            result = "No reviews made.";
            for ( Transactions transactions:transactions){
                if (getProfit(transactions.getItemID()) > maxVal){
                    maxVal = getProfit(transactions.getItemID());
                    itemID = transactions.getItemID();
                    price = transactions.getPrice();
                }
            }
            result = "Most profitable items: " + EOL + "Total profit: " + String.format("%.2f",maxVal) + " SEK"
                    + EOL + itemID + ": " + itemsList.get(findItem(itemID)).getItemName() + ". " + String.format("%.2f",price) +" SEK" + EOL;

            for (int i = 1; i < transactions.size(); i++) {
                double secondMax = getProfit(transactions.get(i).getItemID());
                if (secondMax == maxVal && itemID != transactions.get(i).getItemID() && secondMax != 0) {
                result +=  itemsList.get(i).getItemID() + ":" +
                        itemsList.get(i).getItemName() + ".  " + truncate(transactions.get(i).getPrice(),2) + " SEK" + EOL;
                }
            }
        }
        return result;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        String result = "";
        grossSalary = truncate(grossSalary,2);
        if (!employeeID.isEmpty()){
            if (employeeName!="    "||employeeName.isEmpty()){ //maybe try is blank?
                if (grossSalary>0){
                    Employees employee = new Employees(employeeID, employeeName, truncate(grossSalary,2));
                    employees.add(employee);
                    result = "Employee " + employeeID + " was registered successfully.";
                } else throw new Exception("Salary must be greater than zero.");
            } else throw new Exception("Name cannot be blank.");
        } else throw new Exception("ID cannot be blank.");
        return result;
    }

    public String printEmployee(String employeeID) throws Exception {
        String result = "";
        if (findEmployee(employeeID)!=-1){
            result = employees.get(findEmployee(employeeID)).toString();
        } else {
            throw new Exception("Employee " + employeeID + " was not registered yet.");
        }
        return result;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        String result = "";
        if (!employeeID.isEmpty()){
            if (employeeName!=" "){
                if (grossSalary>0){
                    if (degree.contains("BSc")||degree.contains("MSc")||degree.contains("PhD")) {
                        Managers manager = new Managers(employeeID, employeeName, truncate(grossSalary, 2), degree);
                        employees.add(manager);
                        result = "Employee " + employeeID + " was registered successfully.";
                    } else throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
                } else throw new Exception("Salary must be greater than zero.");
            } else throw new Exception("Name cannot be blank.");
        } else throw new Exception("ID cannot be blank.");
        return result;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
       String result = "";
        if (!employeeID.isEmpty()){
            if (employeeName!=" "){
                if (grossSalary>0){
                    if (gpa>=0 && gpa<=10) {
                        Interns intern = new Interns(employeeID, employeeName, grossSalary, gpa);
                        employees.add(intern);
                        result = "Employee " + employeeID + " was registered successfully.";
                    } else throw new Exception(gpa+ " outside range. Must be between 0-10.");
                } else throw new Exception("Salary must be greater than zero.");
            } else throw new Exception("Name cannot be blank.");
        } else throw new Exception("ID cannot be blank.");
       return result;
    }

    public double getNetSalary(String employeeID) throws Exception {
        int index = findEmployee(employeeID);
        double salary = 0;
        if (index != -1){
        salary = employees.get(index).getNetSalary();
        }else throw new Exception("Employee " + employeeID +" was not registered yet.");
        return salary;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
       String result = ""; if (!employeeID.isEmpty()){
            if (employeeName!=" "){
                if (grossSalary>0){
                    if (degree.contains("BSc")||degree.contains("MSc")||degree.contains("PhD")) {
                        if (dept.contains("Business")||dept.contains("Human Resources")||dept.contains("Technical")) {
                            Directors director = new Directors(employeeID, employeeName, truncate(grossSalary, 2), degree, dept);
                            employees.add(director);
                            result = "Employee " + employeeID + " was registered successfully.";
                        } else throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
                    } else throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
                } else throw new Exception("Salary must be greater than zero.");
            } else throw new Exception("Name cannot be blank.");
        } else throw new Exception("ID cannot be blank.");
        return result;
    }

    public String removeEmployee(String empID) throws Exception {
        String result = "";
        if (findEmployee(empID)!=-1){
            employees.remove(findEmployee(empID));
            result = "Employee " + empID +" was successfully removed.";
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String printAllEmployees() throws Exception {
        String result = "All registered employees:" + EOL;
        if(!employees.isEmpty()) {
            for (Employees employees : employees) {
                result += printEmployee(employees.getEmployeeID()) + EOL;
            }
        } else throw new Exception("No employees registered yet.");
        return result;
    }

    public double getTotalNetSalary() throws Exception {
        double totalSalary = 0;
        if(employees.isEmpty()){
            throw new Exception("No employees registered yet.");
        } else {
            for (Employees employees:employees){
                totalSalary += employees.getNetSalary();
            }
        }
        return truncate(totalSalary,2);
    }

    public String printSortedEmployees() throws Exception {
        String sortedEmployees = "Employees sorted by gross salary (ascending order):" + EOL;
        //created new list to avoid changing the employees list
        if(!employees.isEmpty()) {
            List<Employees> sorted = new ArrayList<>();
            sorted.addAll(employees);
            int n = sorted.size();
            for (int i = 0; i < n -1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (sorted.get(j).getGrossSalary() > sorted.get(j + 1).getGrossSalary()) {
                        Employees temp = sorted.get(j);
                        sorted.set(j, sorted.get(j + 1));
                        sorted.set(j + 1, temp);
                    }
                }
            }
            for (Employees employees:sorted){
                sortedEmployees += employees.toString() + EOL;
            }
        } else {
            throw new Exception("No employees registered yet.");
        }
        return sortedEmployees;
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        String result = "";
        if (findEmployee(empID)!=-1){
            if (!newName.isEmpty()){
        employees.get(findEmployee(empID)).setName(newName);
        result = "Employee " + empID + " was updated successfully";
            }else throw new Exception("Name cannot be blank.");
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        String result = "";
        if (findEmployee(empID)!=-1){
            if (newGPA>=0 && newGPA<=10) {
                int k = findEmployee(empID);
                ((Interns) employees.get(k)).setGpa(newGPA);
                result = "Employee " + empID + " was updated successfully";
            } else throw new Exception(newGPA + " outside range. Must be between 0-10.");
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        String result = "";
        if (findEmployee(empID)!=-1){
            if (newDegree.contains("BSc")||newDegree.contains("MSc")||newDegree.contains("PhD")){
            int k = findEmployee(empID);
            ((Managers) employees.get(k)).setDegrees(newDegree);
            result = "Employee " + empID + " was updated successfully";
            } else throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        String result = "";
        if (findEmployee(empID)!=-1){
            if (newDepartment.contains("Business")||newDepartment.contains("Human Resources")||newDepartment.contains("Technical")) {
                int k = findEmployee(empID);
                ((Directors) employees.get(k)).setDepartment(newDepartment);
                result = "Employee " + empID + " was updated successfully";
            } else throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        String result = "";
        if (findEmployee(empID)!=-1){
            if (newSalary>0){
                employees.get(findEmployee(empID)).setRawSalary(newSalary);
                result = "Employee " + empID + " was updated successfully";
            } else throw new Exception("Salary must be greater than zero.");
        }else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        Map<String, Integer> theMap = new HashMap<>();
        int BSc = 0;
        int MSc = 0;
        int PhD = 0;
        if (!employees.isEmpty()){
           for (Employees employee:employees){
               if (employee.getClass()==Managers.class||employee.getClass() ==Directors.class){
                  String degree = ((Managers) employee).getDegrees();
                  if (degree.equals("BSc")){
                      BSc += 1;
                  } else if (degree.equals("MSc")){
                      MSc += 1;
                  } else if (degree.equals("PhD")){
                      PhD += 1;
                  }
               }
           }
            theMap.put("BSc",BSc);
           theMap.put("MSc", MSc);
           theMap.put("PhD", PhD);
           if (theMap.containsKey("PhD") && theMap.containsValue(0)){
               theMap.remove("PhD");
           } else if (theMap.containsKey("MSc") && theMap.containsValue(0)){
               theMap.remove("MSc");
           } else if (theMap.containsKey("BSc") && theMap.containsValue(0)){
               theMap.remove("BSc");
           }
        } else throw new Exception("No employees registered yet.");
        return theMap;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        String result = "";
        int index = findEmployee(empID);
        if (index!=-1){
            String name = employees.get(index).getName();
            double rawSalary = employees.get(index).getRawSalary();
            employees.remove(index);
            createEmployee(empID,name,rawSalary,degree);
            result = empID + " promoted successfully to Manager.";
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        String result = "";
        int index = findEmployee(empID);
        if (index!=-1){
            String name = employees.get(index).getName();
            double rawSalary = employees.get(index).getRawSalary();
            employees.remove(index);
            createEmployee(empID,name,rawSalary,degree,department);
            result = empID + " promoted successfully to Director.";
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        String result = "";
        int index = findEmployee(empID);
        if (index!=-1){
            String name = employees.get(index).getName();
            double rawSalary = employees.get(index).getRawSalary();
            employees.remove(index);
            createEmployee(empID,name,rawSalary,gpa);
            result = empID + " promoted successfully to Intern.";
        } else throw new Exception("Employee " + empID +" was not registered yet.");
        return result;
    }
}
