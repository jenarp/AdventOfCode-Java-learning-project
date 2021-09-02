package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day03 implements Days {

/** The puzzle status {@code HashMap} */
private final Map<Integer, ProblemStatusEnum> problemStatus;

    // Adds a logger
    private static final Logger logger = LoggerFactory.getLogger(Day03.class);


    // Read content of input file
    public InputStream resource = getClass().getResourceAsStream("/data/day03/input_day03.txt");

//    File resource;
//    {
//        try {
//            resource = new ClassPathResource(
//                    "data/day03/input_day03.txt").getFile();
//        } catch (IOException e) {
//            logger.error("Raw Data (Input) file not found: " + e.getMessage());
//        }
//    }


    private final String[] data = getRawDataAsArray(resource);


    @Autowired
    Day03() {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put(1, ProblemStatusEnum.SOLVED);
        this.problemStatus.put(2, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        return "Trees encountered: " + getNumTrees(data);
    }

    @Override
    public String secondPart() {
        return "Product of all slopes: " + getProduct(data);
    }

    public String[] getTestData() {
        final String test = "..##.......\n" +
                "#...#...#..\n" +
                ".#....#..#.\n" +
                "..#.#...#.#\n" +
                ".#...##..#.\n" +
                "..#.##.....\n" +
                ".#.#.#....#\n" +
                ".#........#\n" +
                "#.##...#...\n" +
                "#...##....#\n" +
                ".#..#...#.#";

        return test.split("\\n");
    }

    public String[] getRawDataAsArray(InputStream resource) {
//    List<String> rawData = new ArrayList<>();
//    try (Scanner s = new Scanner(new File(String.valueOf(resource.toPath()))).useDelimiter("\n")){
//            while (s.hasNext()) {
//                rawData.add(s.next());
//            }
//        } catch (FileNotFoundException e) {
//            logger.error("File not found!" + e.getMessage());
//        }
        ArrayList<String> rawData;
        try (Scanner scan = new Scanner(resource)) {
            rawData = new ArrayList<>();

            while (scan.hasNextLine()) {
                rawData.add(scan.nextLine());
            }
        }

        String[] rawData_array = new String[rawData.size()];
        for(int i = 0; i < rawData.size(); i++) rawData_array[i] = rawData.get(i);

        return rawData_array;
    }

//    method for answer of puzzle day03 part 1
//    search for number of trees encountered
    public String getNumTrees(String[] data){

        int sizeX = data[0].length(); // vertical direction
        int sizeY = data.length;      // horizontal direction
        int numTrees = 0; // number of trees encountered
        char square = 0; // each coordinate on grid is called square

        for (int i=1; i<sizeY; i++){
            // every step: 1 square vertical, 3 squares horizontal
            if((i*3)<=sizeX) {
                square = data[i*1].charAt(i*3);
            }
            // as in horizontal direction the same pattern repeats to the right many times, use modulo in if-condition
            else if (i*3>sizeX) {
                int repeatedPosition = (i*3)%sizeX;
                square = data[i*1].charAt(repeatedPosition);
            }
            if (square == '#'){
                numTrees++;
            }
        }
        return "" + numTrees;
    }

    // method for answer of puzzle day03 part 1
    public String getProduct(String[] data){
        // 5 slopes need to be checked
        int[] stepY = new int[5];
        int[] stepX = new int[5];

        stepY[0] = 1;
        stepY[1] = 1;
        stepY[2] = 1;
        stepY[3] = 1;
        stepY[4] = 2;

        stepX[0] = 1;
        stepX[1] = 3;
        stepX[2] = 5;
        stepX[3] = 7;
        stepX[4] = 1;

        int sizeX = data[0].length(); // horizontal direction
        int sizeY = data.length;      // vertical direction
        long product = 1; // number of product of all trees with all slopes

        for (int j=0; j<stepY.length; j++){
            int numTrees = 0; // number of trees encountered

            for (int i=1; i * stepY[j] < sizeY; i++) {
                if(data[i * stepY[j]].charAt((i * stepX[j]) % sizeX) == '#'){
                    numTrees++;
                }
            }
            product *= numTrees;
        }
        return "" + product;
    }

}
