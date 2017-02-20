package javase01.t04.onedimensionalarray;

/**
 * Created by aivanov on 2/12/2017.
 *
 * Find the max value of array.
 */

public class MaxValue {

    public static void main(String... args) {

        double[] data = {-20.1, 5, 10.2222, 32.4, -1.2, 55, -100};

        if (args.length > 0) {
            data = new double[args.length];
            for (int i = 0; i < data.length; i++) {
                data[i] = Double.parseDouble(args[i]);
            }
        }

        double max = data[0] + data[data.length-1];
        double aux = 0.0;

        for (int i = 0; i < (data.length + 1)/2; i++) {
            aux = data[i] + data[data.length-i-1];
            if (aux > max) {
                max = aux;
            }
        }

        System.out.println("Specified array: ");
        System.out.print("{");
        for (double i : data) {
            System.out.print(i + " ");
        }
        System.out.println("}");
        System.out.println("Max value: " + max);
    }
}