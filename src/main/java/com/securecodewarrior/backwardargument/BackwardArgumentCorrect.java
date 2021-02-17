package main.java.com.securecodewarrior.backwardargument;

public class BackwardArgumentCorrect {

    public static void main(String[] args) {
        if(args.length < 1){
            return;
        }

        args[0] = args[0].toLowerCase();

        Boolean backwards = args[0].charAt(0) == 'm';
    }
}
