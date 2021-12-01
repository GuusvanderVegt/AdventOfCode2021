package com.aoc.days;

import com.aoc.AOC;

import java.util.List;

public class Day1 extends AOC {
    public Day1(){
        super("1");
    }

    @Override
    public void solve(List<String> input) {

        //Part 1
        int amountLarger = 0;
        for (int i = 0; i < input.size() - 1; i++) {
            if (Integer.parseInt(input.get(i)) < Integer.parseInt(input.get(i + 1))) {
                amountLarger++;
            }
        }

        System.out.println(amountLarger);

        //Part 2
        int currentSum = 0;
        int nextSum = 0;
        amountLarger = 0;
        for (int i = 0; i < input.size() - 3; i++) {
            currentSum = Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(i + 1)) + Integer.parseInt(input.get(i + 2));
            nextSum = Integer.parseInt(input.get(i + 1)) + Integer.parseInt(input.get(i + 2)) + Integer.parseInt(input.get(i + 3));

            if (nextSum > currentSum) amountLarger++;
        }

        System.out.println(amountLarger);
    }
}
