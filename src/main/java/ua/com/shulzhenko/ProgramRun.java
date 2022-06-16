package ua.com.shulzhenko;

import ua.com.shulzhenko.bracketexpression.CatalanNumber;
import ua.com.shulzhenko.citylist.MinPrice;
import ua.com.shulzhenko.factorial.FactorialSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {
    /**
     * Method responsible for task selection
     */
    public static void run() {
        menuProgram();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String task;

        try {
            while ((task = reader.readLine()) != null) {
                switch (task) {
                    case "1": {
                        System.out.print("Enter the number of pairs of brackets ---> ");
                        new CatalanNumber().run();
                        menuProgram();
                    }
                    break;
                    case "2": {
                        new MinPrice().run();
                        System.out.println("The result can be seen in the output file");
                        menuProgram();
                    }
                    break;
                    case "3": {
                        System.out.print("Enter the number, factorial and sum of which you want to calculate ---> ");
                        new FactorialSum().run();
                        menuProgram();
                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                    default: {
                        run();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void menuProgram() {
        System.out.println();
        System.out.println("Task 1 ---> select 1");
        System.out.println("Task 2 ---> select 2");
        System.out.println("Task 3 ---> select 3");
        System.out.println("If you want to exit, please select 0");
        System.out.print("Your choice ---> ");
    }
}
