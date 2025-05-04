package se.kth.project;

import se.kth.project.Controller.Controller;
import se.kth.project.External.AccountingDB;
import se.kth.project.External.DiscountDB;
import se.kth.project.External.InventoryDB;
import se.kth.project.Model.Sale;

public class SaleTest{

    private Controller controller;
    private Sale sale;

    void StartSaleTest() {

        AccountingDB accountingDB = new AccountingDB();
        InventoryDB inventoryDB = new InventoryDB();
        DiscountDB discountDB = new DiscountDB();
        controller = new Controller(accountingDB, discountDB, inventoryDB);

        sale = controller.startSale();
        System.out.println(sale != null);

    }

}