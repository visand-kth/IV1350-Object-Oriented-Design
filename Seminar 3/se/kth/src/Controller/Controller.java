package se.kth.src.Controller;

import se.kth.src.Model.Payment;
import se.kth.src.Model.Receipt;
import se.kth.src.Model.Sale;
import se.kth.src.External.AccountingDB;
import se.kth.src.External.InventoryDB;
import se.kth.src.External.DiscountDB;

public class Controller {

    Sale currentSale;
    AccountingDB accountingDB;
    DiscountDB discounts;
    InventoryDB inv;
    int id;
    public Controller(AccountingDB accountingDB, InventoryDB inv) {
        this.accountingDB = accountingDB;
        this.inv = inv;
    }
    public void startSale() {
        // Create Sale object
        currentSale = new Sale(inv);
        
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