package com.aoc.days;

import com.aoc.AOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 extends AOC {

    public Day9(){
        super("9");
    }

    @Override
    public void solve(List<String> input) {
        Point[][] map = getMapAsPoints(input);

        List<Point> lowPoints = new ArrayList<>();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int currentNumber = map[row][col].value;

                boolean leftLarger = true;
                boolean topLarger = true;
                boolean rightLarger = true;
                boolean bottomLarger = true;


                if(col != 0 && map[row][col - 1].value <= currentNumber){
                    leftLarger = false;
                }

                if(col < map[row].length - 1 && map[row][col + 1].value <= currentNumber){
                    rightLarger = false;

                }

                if(row > 0 && map[row - 1][col].value <= currentNumber){
                    topLarger = false;
                }

                if(row < map.length - 1 && map[row + 1][col].value <= currentNumber){
                    bottomLarger = false;
                }

                if(leftLarger && topLarger && rightLarger && bottomLarger){
                    lowPoints.add(map[row][col]);
                }

            }
        }
        System.out.println("Solution part 1: " +calculateSolution(lowPoints));

        //Part 2
        List<Integer> basins = new ArrayList<>();

        for(Point p : lowPoints){
            basins.add(getBasin(p, map));
        }


        Collections.sort(basins);

        int solutionPart2 = basins.get((basins.size() - 1)) * basins.get((basins.size() - 2)) * basins.get((basins.size() - 3));
        System.out.println("Solution part 2: " + solutionPart2);
    }

    private Integer getBasin(Point p, Point[][] tempMap) {
        int total = 0;

        int row = p.row;
        int col = p.col;

        p.visited = true;

        try {
            Point above = tempMap[row-1][col];
            if(above.value < 9 && !above.visited)
                total += getBasin(above, tempMap);

        } catch (IndexOutOfBoundsException ignored){}

        try {
            Point left = tempMap[row][col - 1];
            if(left.value < 9 && !left.visited)
                total += getBasin(left, tempMap);

        } catch (IndexOutOfBoundsException ignored){}

        try {
            Point below = tempMap[row + 1][col];
            if(below.value < 9 && !below.visited)
                total += getBasin(below, tempMap);

        } catch (IndexOutOfBoundsException ignored){}

        try {
            Point right = tempMap[row][col + 1];
            if(right.value < 9 && !right.visited)
                total += getBasin(right, tempMap);

        } catch (IndexOutOfBoundsException ignored){}

        total++;
        return total;
    }

    private int calculateSolution(List<Point> lowPoints) {
        int solution = 0;

        for(Point p : lowPoints){
            solution += (p.value + 1);
        }

        return solution;
    }



    private Point[][] getMapAsPoints(List<String> input) {
        Point[][] map = new Point[input.size()][input.get(0).length()];

        for (int row = 0; row < input.size(); row++) {
            String rowString = input.get(row);
            for (int col = 0; col < rowString.length(); col++) {
                map[row][col] = new Point(row, col, Integer.parseInt(String.valueOf(rowString.charAt(col))));
            }
        }

        return map;
    }

    public static class Point {
        int row, col, value;
        boolean visited = false;

        public Point(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return row == point.row && col == point.col;
        }
    }
}
