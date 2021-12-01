package com.aoc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AOC {
    public AOC(String day) {
        File file = new File("src/com/aoc/resources/day" + day + ".txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: day" + day + ".txt");
        }

        List<String> fileLines = new ArrayList<>();

        try{

            String line;
            while((line = reader.readLine()) != null){
                fileLines.add(line);
            }

            reader.close();

        } catch(IOException e) {e.printStackTrace();}

        solve(fileLines);

    }

    public abstract void solve(List<String> input);
}
