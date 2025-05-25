package se.kth.iv1350.model;

public abstract class TotalRevenueObserverTemplate {

    /**
     * Adds a finished sale price to the observer to process
     * 
     * @param addSalePrice The final price of a finished sale
     */
    public void addSale(float addSalePrice){

        calculateTotalIncome(addSalePrice);
        showTotalIncome();

    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalIncome() throws ObserverTotalIncomeDisplayException;

    protected abstract void handleErrors(Exception e);

    protected abstract void calculateTotalIncome(float addSalePrice);
    
}
