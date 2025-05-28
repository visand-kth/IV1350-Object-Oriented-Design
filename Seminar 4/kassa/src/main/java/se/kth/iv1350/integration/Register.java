package se.kth.iv1350.integration;

public class Register {

    float currentAmount;

    public Register() {

        currentAmount = 0;

    }

    /**
     * Increases the amount present in the register with the amount paid
     * 
     * @param delta
     */
    public void increaseAmount(float amountPaid) {

        currentAmount += amountPaid;

    }

}
