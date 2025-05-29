package se.kth.iv1350.integration;

/**
 * The register that keeps track of the amount of money
 * This is implemented using the singleton strategy by the GoF
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class Register {

    private static Register instance;
    private float currentAmount;

    /**
     * Constructor for @link Register
     */
    private Register() {

        currentAmount = 0;

    }

    public static Register getRegisterInstance(){

        if(instance == null)
            instance = new Register();

        return instance;

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
