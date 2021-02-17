package com.sensei.splitipaddress;

import java.util.Arrays;

public class SplitIPAddressCorrect {

    public static void main(String[] args) {
	    String[] octets = "192,.168.1.2".split("\\.");
	    System.out.println(Arrays.toString(octets));
    }
}
