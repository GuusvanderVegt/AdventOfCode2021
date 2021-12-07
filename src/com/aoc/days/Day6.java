package com.aoc.days;

import com.aoc.AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 extends AOC {

    public Day6(){
        super("6");
    }

    @Override
    public void solve(List<String> input) {
        List<Integer> fishies = new ArrayList<>();


        for(String fish : input.get(0).split(",")){
            fishies.add(Integer.parseInt(fish));
        }


        long[] data = new long[9];

        for(int i : fishies){
            data[i]++;
        }


        for (int x = 0; x < 256; x++) {
            long[] dayFishes = new long[9];

            for (int i = 0; i < data.length; i++) {
                if(i == 0){
                    long value = data[i];
                    dayFishes[6] += value;
                    dayFishes[8] = value;
                } else {
                    long value = data[i];
                    dayFishes[i - 1] += value;
                }

            }

            System.arraycopy(dayFishes, 0, data, 0, dayFishes.length);

        }



        System.out.println(Arrays.stream(data).sum());
    }
}
