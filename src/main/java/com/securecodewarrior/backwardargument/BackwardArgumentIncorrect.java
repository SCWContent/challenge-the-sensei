package com.securecodewarrior.backwardargument;

public class BackwardArgumentIncorrect {

    public static void main(String[] args) {
        if(args.length < 1){
            return;
        }

        args[0].toLowerCase();

        Boolean backwards = args[0].charAt(0) == 'm';
    }
}
