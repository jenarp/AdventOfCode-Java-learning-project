package org.basseur.adventofcode.advent2018.Days;

import org.basseur.adventofcode.advent2018.Utils.FileReaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Day02 implements Days {

    private static String fileLocation = "src/main/java/org/basseur/adventofcode/advent2018/Days/Day02Input.txt";

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String firstPart() {
        return "Part 1 - Checksum: " + calculateChecksum();
    }

    @Override
    public String secondPart() {
        return null;
    }

    private int calculateChecksum() {
        List<String> boxIDs = FileReaders.readFileIntoStringList(fileLocation);

        int tripleLetters = 0;
        int doubleLetters = 0;

        for (String boxID : boxIDs) {
            boolean containsDoubleLetters = false;
            boolean containsTripleLetters = false;

            for (int i = 0; i < boxID.length(); i++) {
                String currentLetter = boxID.substring(i, i + 1);

                if (boxID.replace(currentLetter, "").length() == boxID.length() - 2) containsDoubleLetters = true;
                else if (boxID.replace(currentLetter, "").length() == boxID.length() - 3)
                    containsTripleLetters = true;
            }

            if (containsDoubleLetters) doubleLetters++;
            if (containsTripleLetters) tripleLetters++;
        }

        return doubleLetters * tripleLetters;
    }
}





