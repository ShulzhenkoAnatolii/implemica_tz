package ua.com.shulzhenko.bracketexpression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CatalanNumber {

    /**
     * The method responsible for receiving data from the user and displaying the results after execution
     */
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n;
        try {
            n = Integer.parseInt(reader.readLine());
            if (n >= 0) {
                System.out.println(getCatalanNumber(n));
            } else {
                System.out.println("Invalid input");
            }
        } catch (NumberFormatException | IOException exception) {
            System.out.println("Invalid input");
        }
    }

    /**
     *
     * @param number - the entered number
     * @return a natural number from the Catalan sequence
     */
    private static int getCatalanNumber(int number){
        int sum = 0;
        if (number == 0)
            return 1;
        for (int i = 0; i < number; i++) {
            sum += getCatalanNumber(i) * getCatalanNumber((number - 1) - i);
        }
        return sum;
    }
}
