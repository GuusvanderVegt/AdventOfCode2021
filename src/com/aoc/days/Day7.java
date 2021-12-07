package com.aoc.days;

import com.aoc.AOC;

import java.util.ArrayList;
import java.util.List;

public class Day7 extends AOC {
    public Day7(){
        super("7");
    }

    @Override
    public void solve(List<String> input) {
        int[] horizontalPositions = new int[input.get(0).split(",").length];

        //Parse strings to ints
        for (int i = 0; i < horizontalPositions.length; i++) {
            horizontalPositions[i] = Integer.parseInt(input.get(0).split(",")[i]);
        }

        //Get the highest value in array
        int highestValue = 0;
        for (int number: horizontalPositions) {
            highestValue = Math.max(number, highestValue);
        }



        int leastAmountOfFuel = -1;
        for (int horizontalPosition = 0; horizontalPosition <= highestValue; horizontalPosition++) {
            int total = 0;
            for(int number : horizontalPositions){
                int fuelUsage = Math.max(number, horizontalPosition) - Math.min(number, horizontalPosition);
//                total += fuelUsage;

                for (int i = 1; i <= fuelUsage; i++) {
                    total += i;
                }
            }

            if(leastAmountOfFuel == -1){
                leastAmountOfFuel = total;
            } else if(leastAmountOfFuel > total){
                leastAmountOfFuel = total;
            }
        }

        System.out.println(leastAmountOfFuel);
    }
}
