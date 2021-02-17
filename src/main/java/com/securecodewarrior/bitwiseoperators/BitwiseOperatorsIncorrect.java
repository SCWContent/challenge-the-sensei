package com.securecodewarrior.bitwiseoperators;

public class BitwiseOperatorsIncorrect {

    public static void main(String[] args) {
        parseArgs(new String[]{null, "-h"});
    }

    public static void parseArgs(String[] args) {
        for (String arg : args) {
            if (arg != null & arg.contains("-h")) {
                System.out.println("Help!");
            }
        }
    }
}
