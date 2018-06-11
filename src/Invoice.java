import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Invoice {
    public static void main(String[] args){

        HashMap<String, lineItem> itemByDescription = new HashMap<String, lineItem>();
        itemByDescription.put("Java Book", new lineItem("J001", 45.00, true));
        itemByDescription.put("Cobol Book", new lineItem("C001", 50.00, false));

        ArrayList<lineItem> items = new ArrayList<lineItem>();

        char quit = '0';

        while(true) {
            Scanner input = new Scanner(System.in);
            if(quit == 'Q'){
                break;
            }
            System.out.println("Please enter item purchased: ");
            String keyboard1 = input.nextLine();
            System.out.println("Please enter quantity: ");
            Integer keyboard2 = input.nextInt();
            lineItem item = null;

            if(itemByDescription.containsKey(keyboard1)) {
                item = new lineItem(itemByDescription.get(keyboard1).itemNumber, itemByDescription.get(keyboard1).price, itemByDescription.get(keyboard1).taxable);
                item.description = keyboard1;
                item.quantity = keyboard2;
                items.add(item);
            } else {
                System.out.println("Item not found in the database! ");
            }

            System.out.println("Enter Q to quit and see items purchased or any letter to continue purchasing: ");
            quit = input.next().charAt(0);
        }
        double tax = 0, taxableSubtotal = 0, untaxableSubtotal = 0;
        System.out.println("Item#:  Quantity:  Description:  Price:  Total: ");

        for(lineItem item: items){
            System.out.println(item.itemNumber + "    " + item.quantity + "          " + item.description+ "     " + item.price + "   " + (item.price * item.quantity));

            if(item.taxable){
                taxableSubtotal += (item.price * item.quantity);
            } else {
                untaxableSubtotal += (item.price * item.quantity);
            }
        }

        DecimalFormat money = new DecimalFormat("$0.00");
        tax = 0.05 * taxableSubtotal;

        System.out.println("Taxable subtotal: " + money.format(taxableSubtotal));
        System.out.println("Untaxable subtotal: " + money.format(untaxableSubtotal));
        System.out.println("Tax: " + money.format(tax));
        System.out.println("Grandtotal: " + money.format(taxableSubtotal + untaxableSubtotal + tax));
    }
}
