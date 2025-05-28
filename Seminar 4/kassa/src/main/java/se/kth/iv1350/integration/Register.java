package se.kth.iv1350.integration;

/**
 * The register that keeps track of the amount of money
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class Register {

    private float currentAmount;

    /**
     * Constructor for @link Register
     */
    public Register() {

        currentAmount = 0;

    }

    /**
     * Increases the amount present in the register with the amount paid
     * 
     * @param amountPaid The amount paid to be added in the register
     */
    public void increaseAmount(float amountPaid) {

        currentAmount += amountPaid;

    }

}
