package company;

public class Transactions {
    private final int amount;
    private final double price;
    private final String itemID;
    //transactions constructor
    public Transactions(String itemID, int amount, double price){
        this.itemID = itemID;
        this.amount = amount;
        this.price = price;
    }
    //getters and setters
    public String getItemID() {return this.itemID;}

    public double getPrice() {return this.price;}

    public int getAmount() {return this.amount;}

    //to string method
    @Override
    public String toString() {
        return this.itemID + ": " + this.amount + " item(s). " + String.format("%.2f",this.price) +  " SEK";}
}
