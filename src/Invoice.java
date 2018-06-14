import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Invoice {
    public static void main(String[] args) {

        HashMap<String, lineItem> itemByDescription = new HashMap<String, lineItem>();
        itemByDescription.put("Java Book", new lineItem("J001", 45.00, true));
        itemByDescription.put("Cobol Book", new lineItem("C001", 50.00, false));

        ArrayList<lineItem> items = new ArrayList<lineItem>();

        char quit = '0';

        while (true) {
            Scanner input = new Scanner(System.in);
            if (quit == 'Q'|| quit == 'q') {
                break;
            }
            System.out.println("Please enter item purchased: ");
            String keyboard1 = input.nextLine();
            System.out.println("Please enter quantity: ");
            Integer keyboard2 = input.nextInt();
            lineItem item = null;

            if (itemByDescription.containsKey(keyboard1)) {
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


        FileWriter fwriter = null;
        BufferedWriter bwriter = null;
        try {
            fwriter = new FileWriter("C:/Users/GBTC441006ur/IdeaProjects/Invoice/invoice.txt");
            bwriter = new BufferedWriter(fwriter);
            bwriter.write("<!DOCTYPE html>");
            bwriter.newLine();
            bwriter.write("<html>");
            bwriter.newLine();
            bwriter.write("<head>");
            bwriter.newLine();
            bwriter.write("<meta charset=\"UTF-8\">");
            bwriter.newLine();
            bwriter.write("<title> Java integrated with HTML </title>");
            bwriter.newLine();
            bwriter.write("</head>");
            bwriter.newLine();
            bwriter.write("<body>");
            bwriter.newLine();
            bwriter.write("<h3> Item#:  Quantity:  Description:  Price:  Total: </h3>");
            bwriter.newLine();
            for(lineItem item: items){
                bwriter.write("<p>" + item.itemNumber + "        ");
                bwriter.write(item.quantity+ "        ");
                bwriter.write(item.description+ "        ");
                bwriter.write(String.valueOf(item.price)+ "        ");
                bwriter.write(String.valueOf(item.price * item.quantity)+ "        " + "</p>");
                bwriter.newLine();
            }
            bwriter.write("<br>");
            bwriter.newLine();
            bwriter.write("<p>" + "Taxable subtotal: ");
            bwriter.write(String.valueOf(taxableSubtotal) + "</p>");
            bwriter.newLine();
            bwriter.write("<p>" + "Untaxable subtotal: ");
            bwriter.write(String.valueOf(untaxableSubtotal) + "</p>");
            bwriter.newLine();
            bwriter.write("<p>"+ "Tax: ");
            bwriter.write(String.valueOf(tax)+ "</p>");
            bwriter.newLine();
            bwriter.write("<p>" + "Grandtotal: ");
            bwriter.write(String.valueOf(taxableSubtotal + untaxableSubtotal + tax)+ "</p>");
            bwriter.newLine();
            bwriter.write("</body>");
            bwriter.newLine();
            bwriter.write("</html>");
            bwriter.newLine();
            bwriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
