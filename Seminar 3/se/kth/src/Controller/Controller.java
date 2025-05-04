package se.kth.src.Controller;

import se.kth.src.Model.Payment;
import se.kth.src.Model.Receipt;
import se.kth.src.Model.Sale;
import se.kth.src.External.AccountingDB;
import se.kth.src.External.InventoryDB;
import se.kth.src.External.DiscountDB;

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