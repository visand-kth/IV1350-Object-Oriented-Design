package se.kth.iv1350.main;

import java.util.Random;

public class RandomCharInherited extends Random {
    
    public RandomCharInherited(){

    }

    public RandomCharInherited(long seed){
        
        super(seed);

    }

    public char nextUppercaseChar(){

        int index = nextInt() % 26;
        index = Math.abs(index);
        System.out.println("Random int: " + index);
        char[] upperCase = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return upperCase[index];

    }

    public char nextLowercaseChar(){

        int index = nextInt() % 26;
        index = Math.abs(index);
        System.out.println("Random int: " + index);
        char[] lowerCase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return lowerCase[index];

    }

}
