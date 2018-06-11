public class lineItem {
    public String itemNumber;
    public int quantity;
    public String description;
    public double price;
    public boolean taxable;

    lineItem(String itemNumber, double price, boolean taxable){
        this.itemNumber = itemNumber;
        this.price = price;
        this.taxable = taxable;
    }
}
