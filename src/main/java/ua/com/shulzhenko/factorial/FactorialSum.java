package ua.com.shulzhenko.factorial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class FactorialSum {

    /**
     * The method responsible for receiving data from the user and displaying the results after execution
     */
    public void run(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        try {
            n = Integer.parseInt(reader.readLine());
            if (n >= 0) {
                System.out.println(n + "!" + " = " + FactorialSum.getFactorial(n));
                System.out.println("sum = " + FactorialSum.sumDigits(FactorialSum.getFactorial(n).toString()));
            } else {
                System.out.println("Invalid input");
            }
        } catch (NumberFormatException | IOException exception) {
            System.out.println("Invalid input");
        }
    }

    /**
     *
     * @param n - the number of factorial to be calculated
     * @return returns factorial n
     */
    public static BigInteger getFactorial(int n) {
        if (n < 0) {
            return BigInteger.ZERO;
        } else if (n > 0) {
            BigInteger factorial = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }
            return factorial;
        } else {
            return BigInteger.ONE;
        }
    }

    /**
     *
     * @param s - input numbers
     * @return the sum of all numbers of a number
     */
    public static int sumDigits(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += Integer.parseInt(s.substring(i, i + 1));
        }
        return sum;
    }
}
