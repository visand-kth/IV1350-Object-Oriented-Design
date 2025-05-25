package se.kth.iv1350.model;

public class ObserverTotalIncomeDisplayException extends Exception {
    
    /**
     * When the observer cannot display the total income logged
     * 
     * @param message
     */
    public ObserverTotalIncomeDisplayException(String message) {
        super(message);
    }

}
