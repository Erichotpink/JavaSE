package javase01.t02.epsilon;

/**
 * Created by aivanov on 2/9/2017.
 *
 * Find and print the smallest number in the chain A = 1, 2, 3 ... n,
 * where An = 1/(n+1)^2, An < E.
 */
public class Epsilon {

    public static void main(String... args) {

        double epsilon = 0.001;

        if (args.length > 0) {
            epsilon = Double.parseDouble(args[0]);
        }

        int i = 0;
        double ans = 0.0;

        do {
            ++i;
            ans = 1 / Math.pow((i + 1), 2);
            System.out.println(ans);
        } while(ans > epsilon);

        System.out.printf("Max N is: %d; \nRequested epsilon is: %g", i, epsilon);
    }
}