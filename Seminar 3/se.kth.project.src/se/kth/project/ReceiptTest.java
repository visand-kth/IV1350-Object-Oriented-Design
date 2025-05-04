package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.Model.Sale;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.InventoryDB;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ReceiptTest{

    private Controller controller;
    private Sale sale;
    private Receipt receipt;

    @BeforeEach
    void setUp() {

        AccountingDB accountingDB = new AccountingDB();
        InventoryDB inventoryDB = new InventoryDB();
        controller = new Controller(accountingDB, inventoryDB);

        sale = controller.startSale();
        receipt = new Receipt(sale, 0);

    }

    @Test
    void printTest(){

        sale.addItem(0,1);
        sale.addItem(1,2);
        reciept.print();
        String correct = "idfk";
        String result = "idfk this either";
        assertEquals(correct, result, "Receipt does not match");
        
    }

}