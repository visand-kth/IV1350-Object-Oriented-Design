package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.InventoryDB;
import se.kth.project.Model.Sale;

public class SaleTest{

    private Controller controller;
    private Sale sale;

    void StartSaleTest() {

        AccountingDB accountingDB = new AccountingDB();
        InventoryDB inventoryDB = new InventoryDB();
        controller = new Controller(accountingDB, inventoryDB);

        sale = controller.startSale();
        System.out.println(sale != null);

    }

}