package javase01.t03.loops;

import java.util.Scanner;

/**
 * Created by aivanov on 2/9/2017.
 *
 * The programm return values of the function F(x) = tg(2x) - 3 for x in range [A,B] with a step H.
 *
 */

class Func {

    public static double getFuncValue(double x) {
        return Math.tan(2 * x) - 3;
    }

    public static void main(String[] args) {

        double a = 0.0;
        double b = 0.0;
        double h = 0.0;

        if (args.length == 3) {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            h = Double.parseDouble(args[2]);
        } else {

            Scanner in = new Scanner(System.in);

            System.out.println("");
            System.out.println("The programm return values of the function F(x) = tg(2x) - 3 for x in ");
            System.out.println("range [A,B] with a step H.");
            System.out.println("");
            System.out.println("Please specify values for A and B:");
            System.out.println("");

            a = in.nextDouble();

            System.out.println("A: " + a);

            b = in.nextDouble();

            System.out.println("B: " + b);
            System.out.println("Please specify a value for H:  ");

            h = in.nextDouble();

            System.out.println("H: " + h);
        }

        System.out.printf("%-10s %-10s", "Value", "Result");
        for (double i = a; i <= b; i += h) {
            System.out.printf("%n%-10f %-10f", i, getFuncValue(i));
        }
    }
}

