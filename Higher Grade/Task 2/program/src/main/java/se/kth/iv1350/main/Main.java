package se.kth.iv1350.main;

public class Main {

    public static void main(String[] args) {

        System.out.println("Creating RandomCharInherited");
        RandomCharInherited randomCharInherited = new RandomCharInherited();
        System.out.println("Requesting random uppercase char:");
        System.out.println("Random uppercase char: " + randomCharInherited.nextUppercaseChar());
        System.out.println("Requesting random lowercase char:");
        System.out.println("Random lowercase char: " + randomCharInherited.nextLowercaseChar());
        System.out.println("Requesting random double:");
        System.out.println("Random double: " + randomCharInherited.nextDouble());

        System.out.println("Creating RandomCharComposed");
        RandomCharComposed randomCharComposed = new RandomCharComposed();
        System.out.println("Requesting random uppercase char:");
        System.out.println("Random uppercase char: " + randomCharComposed.nextUppercaseChar());
        System.out.println("Requesting random lowercase char:");
        System.out.println("Random lowercase char: " + randomCharComposed.nextLowercaseChar());
        System.out.println("Requesting random double:");
        System.out.println("RandomCharComposed cannot process doubles because the function is not implemented.");

    }

}
