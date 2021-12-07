package com.aoc.days;

import com.aoc.AOC;

import java.awt.*;
import java.util.List;

public class Day5 extends AOC {
    public Day5() {
        super("5");
    }

    @Override
    public void solve(List<String> input) {
        int[][] map = new int[1000][1000];

        for(String line : input) {
            String[] parts = line.split(" -> ");
            int x1 = Integer.parseInt(parts[0].split(",")[0]);
            int y1 = Integer.parseInt(parts[0].split(",")[1]);

            int x2 = Integer.parseInt(parts[1].split(",")[0]);
            int y2 = Integer.parseInt(parts[1].split(",")[1]);

                //Xes are equal

                int xLow,xHigh, yLow, yHigh;
                if(x1 < x2) {
                    xLow = x1;
                    xHigh = x2;
                } else {
                    xLow = x2;
                    xHigh = x1;
                }

                if(y1 < y2) {
                    yLow = y1;
                    yHigh = y2;
                } else {
                    yLow = y2;
                    yHigh = y1;
                }

                for (int x = xLow; x <= xHigh; x++) {
                    for (int y = yLow; y <= yHigh; y++) {
                        map[x][y]++;
                    }
                }



        }

        int amountOfOverlap = 0;
        for (int x = 0; x < 1000; x++) {
            for (int y = 0; y < 1000; y++) {
                if(map[x][y] >= 2)
                    amountOfOverlap++;
            }
        }

        System.out.println(amountOfOverlap);
    }
}
