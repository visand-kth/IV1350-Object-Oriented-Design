package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.Model.Sale;
import se.kth.project.Model.Receipt;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.DiscountDB;
import se.kth.project.External.InventoryDB;
import se.kth.project.Model.Payment;

public class ReceiptTest{

    private Controller controller;
    private Sale sale;
    private Receipt receipt;

    void printTest(){

        AccountingDB accountingDB = new AccountingDB();
        InventoryDB inventoryDB = new InventoryDB();
        DiscountDB discountDB = new DiscountDB();
        controller = new Controller(accountingDB, discountDB, inventoryDB);

        sale = controller.startSale();
        receipt = new Receipt(sale, new Payment(sale, new DiscountDB(), 0));
        
        sale.addItem(0,1);
        sale.addItem(1,2);
        receipt.print();
        String correct = "idfk";
        String result = "idfk this either";
        // assertEquals(correct, result, "Receipt does not match");
        
    }

}