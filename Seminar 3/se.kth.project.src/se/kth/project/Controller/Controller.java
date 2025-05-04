package se.kth.project.Controller;

import se.kth.project.Model.Payment;
import se.kth.project.Model.Receipt;
import se.kth.project.Model.Sale;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.InventoryDB;
import se.kth.project.External.DiscountDB;

public class Controller {

    public Sale currentSale;
    private AccountingDB accountingDB;
    private DiscountDB discounts;
    private InventoryDB inv;
    private int id;
    public Controller(AccountingDB accountingDB, InventoryDB inv) {
        this.accountingDB = accountingDB;
        this.inv = inv;
    }
    public Sale startSale() {
        // Create Sale object
        this.currentSale = new Sale(inv);
        return currentSale;
    }
    public void endSale() {
        Payment payment = new Payment(currentSale, accountingDB, discounts,  id);
        payment.makePayment();
        Receipt rec = new Receipt(payment.sale, payment);
        rec.print();
        accountingDB.saveSale(id, rec);
        inv.updateInventory(currentSale);
    }
}