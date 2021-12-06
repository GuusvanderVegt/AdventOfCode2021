package com.aoc.days;

import com.aoc.AOC;

import java.util.List;

public class Day2 extends AOC {
    public Day2() {
        super("2");

    }

    @Override
    public void solve(List<String> input) {
        int horizontal = 0, depth = 0;

        for(String item : input) {
            String[] parts = item.split(" ");
            if(parts[0].equalsIgnoreCase("forward"))
                horizontal += Integer.parseInt(parts[1]);
            else if(parts[0].equalsIgnoreCase("down"))
                depth += Integer.parseInt(parts[1]);
            else
                depth -= Integer.parseInt(parts[1]);
        }

        System.out.println(horizontal * depth);
    }
}
