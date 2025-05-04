import java.util.Scanner;

public class Payment {
    private float change;
    private Sale sale;
    private AccountingDB accounts;
    private float discount;
    public float totalCost;
    public float cashPaid;
    /**
     * constructor for Payment object
     * @param sale Sale object containing the items and the 
     * @param accountingDB the accounting database to update to when payment is complete
     * @param discounts discount database to see if person has a discount
     * @param id id of the person
     */
    public Payment(Sale sale, AccountingDB accountingDB, DiscountDB discounts, int id) {
        this.sale = sale;
        this.change = 0;
        this.accounts = accountingDB;
        this.discount = discounts.calcDiscount(id);
    }

    private float getCash(){
        System.out.println("How much cash is the customer paying?");
        Scanner scanner = new Scanner(System.in);
        float cashPaid = scanner.nextFloat();
        scanner.close();
        return cashPaid;
    }
    /**
     * function which updates inventory and adds sale to accounting db, calculates change and prints reciept
     * @param accounts the database of accounting which holds sale per customer
    */
    public void makePayment() {
        //first make it so that the person gets their discount
        this.totalCost = sale.runningTotal;
        this.totalCost *= 1 - discount;
        this.cashPaid = getCash();
        while(cashPaid < totalCost){
            System.out.println("Thats not enough, try again!");
            this.cashPaid = getCash();
        }
        this.change = this.cashPaid - this.totalCost;
        //make and print receipt
        Receipt rec = new Receipt(this.sale, this);
        rec.print();
    }
}
