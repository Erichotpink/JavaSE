package javase01.t05.twodimensionalarray;

import java.util.Arrays;

/**
 * Created by aivanov on 2/12/2017.
 *
 * Get an matrix according to the following pattern:
 *          1 0 0 ... 0 0 1
 *          0 1 0 ... 0 1 0
 *          0 0 1 ... 1 0 0
 *          . . . ... . . .
 *          0 0 1 ... 1 0 0
 *          0 1 0 ... 0 1 0
 *          1 0 0 ... 0 0 1
 *
 */


public class Matrix {

    public static void main(String... args) {

        if (args.length < 1) {
            System.out.println("You must provide an argument. Please specify a matrix size.");
            return;
        }

        int x = Integer.parseInt(args[0]);

        if (x <= 0 || x > 50) {
            System.out.println("The value is out of the range 1 - 50.");
            return;
        }

        byte[][] matrix = new byte[x][x];

        for (int i = 0; i <= x/2; i++) {
            matrix[i][i] = 1;
            matrix[i][x-i-1] = 1;
            matrix[x - i - 1] = matrix[i];
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}