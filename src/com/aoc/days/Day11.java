package com.aoc.days;

import com.aoc.AOC;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 extends AOC {
    public Day11(){
        super("11");
    }

    int totalFlashes = 0;
    int tempFlashes;
    Point[][] field;
//    List<Point> flashedPoints;

    @Override
    public void solve(List<String> input) {
        field = getField(input);

        int itteration = 0;

        while(true) {
            tempFlashes = totalFlashes;
            itteration++;

            //First add energy
            increaseEnergyLevelByOneForAll();

            //second, check if any number is higher than 9 (10)
            for (int y = 0; y < field[0].length; y++) {

                for (int x = 0; x < field.length; x++) {
                    flash(field[y][x], x, y, false);
                }
            }

            for (Point[] points : field) {
                for (Point point : points) {
                    point.isFlashed = false;
                }
            }

            for(Point[] points : field){
                for(Point point : points){
                    if(point.value != 0){
                        break;
                    }
                }
            }

            tempFlashes = totalFlashes - tempFlashes;

            if(tempFlashes == 100) {
                break;
            }
        }

        System.out.println(itteration);
    }

    private void flash(Point point, int x, int y, boolean flashedByOther) {
        if(flashedByOther && !point.isFlashed){
            point.increase();
        }


        if(point.value > 9 && !point.isFlashed){
            totalFlashes++;
            field[y][x].value = 0;
            point.isFlashed = true;

            for (int j = y - 1; j <= y + 1; j++) {
                for (int i = x - 1; i <= x + 1; i++) {

                    try{
                        flash(field[j][i], i, j, true);

                    } catch (IndexOutOfBoundsException ignored){

                    }

                }
            }
        }
    }

    private void increaseEnergyLevelByOneForAll(){
        for (Point[] points : field) {
            for (Point point : points) {
                point.increase();
            }
        }
    }

    private Point[][] getField(List<String> input) {
        Point[][] field = new Point[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                field[i][j] = new Point(i, j, Integer.parseInt(String.valueOf(line.charAt(j))));
            }
        }

        return field;
    }

    public class Point {
        int x, y;
        int value;
        boolean isFlashed = false;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public void increase(){
            value++;
        }
    }
}
