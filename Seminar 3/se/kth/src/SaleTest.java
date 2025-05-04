package se.kth.src;

import se.kth.src.Controller.Controller;
import se.kth.src.Model.Sale;
import se.kth.src.External.AccountingDB;
import se.kth.src.External.InventoryDB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaleTest{

    private Controller controller;
    private Sale sale;

    @BeforeEach
    void setUp() {

        AccountingDB accountingDB = new AccountingDB();
        InventoryDB inventoryDB = new InventoryDB();
        controller = new Controller(accountingDB, inventoryDB);

        sale = controller.startSale();

    }

    @Test
    void startSaleTest(){

        assertTrue(sale != null);
        
    }

}