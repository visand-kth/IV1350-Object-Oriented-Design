package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.Model.Sale;

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