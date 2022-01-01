package company;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final String itemID;
    private String itemName;
    private double itemPrice;
    private List<Reviews> reviews;
    // item constructor
    public Items(String itemID, String itemName, double itemPrice){
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        reviews = new ArrayList<Reviews>();
    }

    public void addReviews(Reviews review) {
        if(!reviews.contains(review)){
            reviews.add(review);
        }
    }


    // getters and setters
    public String getItemID() {return this.itemID;}

    public String getItemName() {return this.itemName;}

    public double getItemPrice() {return this.itemPrice;}

    public void setItemName(String itemName) {this.itemName = itemName;}

    public void setItemPrice(double itemPrice) {this.itemPrice = itemPrice;}

    public List<Reviews> getReviews() {return reviews;}



    // to string method
    @Override
    public String toString() {
        return this.itemID + ": " + this.itemName + ". " + String.format("%.2f",this.itemPrice)+ " SEK";
    }
}

