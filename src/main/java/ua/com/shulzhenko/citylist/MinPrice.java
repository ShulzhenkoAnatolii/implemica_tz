package ua.com.shulzhenko.citylist;

import ua.com.shulzhenko.util.InvalidInputException;
import ua.com.shulzhenko.util.ReadInputFile;
import ua.com.shulzhenko.util.WriteOutFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinPrice {

    public static final String INPUT_FILE = "input.txt";
    public static final String OUTPUT_FILE = "output.txt";
    public static final int INFINITY = Integer.MAX_VALUE;

    private static int numOfCities;

    /**
     * Reads an incoming file
     * Checks the input file for correctness
     * Using findCheapestCost finds the cheapest price
     * Writes the result to the output file
     */
    public void run() {
        List<String> inputLine = new ArrayList<>(); //Array of input values
        inputLine = Arrays.asList(ReadInputFile.readInputFile(INPUT_FILE).split("\\n"));
        StringBuilder output = new StringBuilder();
        int readingLine = 1;
        int numberOfTests;
        String cityName;
        try {
            numberOfTests = Integer.parseInt(inputLine.get(0));
            if (numberOfTests > 10)
                throw new InvalidInputException("The number of tests cannot exceed 10");
            for (int currentTest = 0; currentTest < numberOfTests; currentTest++) {
                List<Integer> result = new ArrayList<>();
                List<String> cities = new ArrayList<>();
                numOfCities = Integer.parseInt(inputLine.get(readingLine++));
                if (numOfCities > 10000)
                    throw new InvalidInputException("The number of cities cannot exceed 10,000");
                int[][] graph = new int[numOfCities][numOfCities];
                for (int i = 0; i < numOfCities; i++) {
                    cityName = inputLine.get(readingLine++);
                    if (cityName.length() > 10 || !cityName.matches("[a-zA-Z]+"))
                        throw new InvalidInputException("The city name cannot contain more than 10 characters.\nOnly Latin characters are allowed");
                    cities.add(cityName);
                    int numOfNeighbours = Integer.parseInt(inputLine.get(readingLine++));
                    for (int j = 0; j < numOfNeighbours; j++) {
                        String[] neighbours = inputLine.get(readingLine++).split(" ");
                        int cost = Integer.parseInt(neighbours[1]);
                        if (cost > 200000) {
                            throw new InvalidInputException("The maximum price must not exceed 200000");
                        } else {
                            graph[i][Integer.parseInt(neighbours[0]) - 1] = cost;
                        }
                    }
                }
                if (cities.size() != numOfCities)
                    throw new InvalidInputException("Invalid input");
                for (int i = 0; i < numOfCities; i++) {
                    for (int j = 0; j < numOfCities; j++) {
                        if (graph[i][j] == 0) graph[i][j] = INFINITY;
                    }
                }
                for (int i = 0; i < numOfCities; i++) {
                    for (int j = 0; j < numOfCities; j++) {
                        if (graph[i][j] != graph[j][i])
                            throw new InvalidInputException("Invalid input");
                    }
                }
                int waysToFind = Integer.parseInt(inputLine.get(readingLine++));
                if (waysToFind > 100)
                    throw new InvalidInputException("The maximum the number of paths to find  not exceed 100");
                output.append("Test").append(currentTest + 1).append("\n");
                for (int i = 0; i < waysToFind; i++) {
                    String[] paths = inputLine.get(readingLine++).split(" ");
                    int startPoint, endPoint;
                    startPoint = cities.indexOf(paths[0]);
                    endPoint = cities.indexOf(paths[1]);
                    boolean[] visited = new boolean[numOfCities];
                    result.add(findCheapestCost(startPoint, endPoint, visited, graph));
                    if (result.get(result.size() - 1) == INFINITY) {
                        output.append("End and start points are disconnected\n");
                    } else {
                        output.append(result.get(result.size() - 1)).append("\n");
                    }
                }
                if (result.size() != waysToFind)
                    throw new InvalidInputException("Invalid input");
            }
        } catch (NumberFormatException | InvalidInputException | IndexOutOfBoundsException e) {
            output = new StringBuilder("Invalid input data");
        } finally {
            WriteOutFile.write(OUTPUT_FILE, output.toString());
        }
    }

    /**
     * @param startPoint - First city
     * @param endPoint   - Last city
     * @param visited    - Used path (true), unused path (false)
     * @param graph      - Base of cities and prices
     * @return the cheapest price
     */
    private static int findCheapestCost(int startPoint, int endPoint, boolean[] visited, int[][] graph) {
        if (startPoint == endPoint)
            return 0;
        visited[startPoint] = true;
        int cost = INFINITY;
        for (int i = 0; i < numOfCities; i++) {
            if (graph[startPoint][i] != INFINITY && !visited[i]) {
                int current = findCheapestCost(i, endPoint, visited, graph);
                if (current < INFINITY) {
                    cost = Math.min(cost, graph[startPoint][i] + current);
                }
            }
        }
        visited[startPoint] = false;
        return cost;
    }

}