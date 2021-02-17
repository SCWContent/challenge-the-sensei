package com.sensei.bitwiseoperators;

public class BitwiseOperatorsCorrect {

    public static void main(String[] args) {
        parseArgs(new String[]{null, "-h"});
    }

    public static void parseArgs(String[] args) {
        for (String arg : args) {
            if (arg != null && arg.contains("-h")) {
                System.out.println("Help!");
            }
        }
    }
}
