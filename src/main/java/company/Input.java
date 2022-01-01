package company;

import java.util.Scanner;

public abstract class Input {
    //a class to handle all scanners
    public static int intReader(String message){
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        int input = in.nextInt();
        return input;
    }
    public static String stringReader(String message){
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        String input = in.nextLine();
        return input;
    }
    public static double doubleReader(String message){
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        Double input = in.nextDouble();
        return input;
    }
}
