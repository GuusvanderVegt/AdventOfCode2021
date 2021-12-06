package com.aoc.days;

import com.aoc.AOC;

import java.util.ArrayList;
import java.util.List;

public class Day3 extends AOC {
    public Day3() {
        super("3");
    }

    @Override
    public void solve(List<String> input) {
        int wordLength = input.get(0).length();
        int charIndex = 0;

        int amountOfOnes;
        int amountOfZeroes;

        String gamma = "", epsilon = "";

        //Part 1
        for (int i = 0; i < wordLength; i++) {
            amountOfOnes = 0;
            amountOfZeroes = 0;
            for(String line : input){
                if(line.charAt(charIndex) == '0'){
                    amountOfZeroes++;
                } else {
                    amountOfOnes++;
                }
            };

            if(amountOfOnes > amountOfZeroes){
                gamma += "1";
                epsilon += "0";
            } else {
                gamma += "0";
                epsilon += "1";
            }
            charIndex++;
        }

        System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));

        //Part 2
        List<char[]> oxygen = new ArrayList<>();
        List<char[]> O2 = new ArrayList<>();
        for(String line : input){
            oxygen.add(line.toCharArray());
            O2.add(line.toCharArray());
        }

        String oxygenSolution = solveOxygen(oxygen, 0);
        String o2Solution = solveCO2(O2, 0);

        System.out.println(Integer.parseInt(oxygenSolution, 2) * Integer.parseInt(o2Solution, 2));

    }

    public String solveOxygen(List<char[]> list, int index) {
        List<char[]> newList = new ArrayList<>(list);

        if(newList.size() == 1) {
            return String.copyValueOf(newList.get(0));
        } else {
            int one = 0;
            int zero = 0;

            for (char[] a : newList) {
                if (a[index] == '1') {
                    one++;
                }
                if (a[index] == '0') {
                    zero++;
                }
            }

            if (one > zero || one == zero) {
                int finalIndex = index;
                newList.removeIf(a -> a[finalIndex] == '0');
            } else {
                int finalIndex = index;
                newList.removeIf(a -> a[finalIndex] == '1');
            }

            index++;
            return solveOxygen(newList, index);
        }

    }

    public String solveCO2(List<char[]> list, int index) {
        List<char[]> newList = new ArrayList<>(list);

        if(newList.size() == 1) {
            return String.copyValueOf(newList.get(0));
        } else {
            int one = 0;
            int zero = 0;

            for (char[] a : newList) {
                if (a[index] == '1') {
                    one++;
                }
                if (a[index] == '0') {
                    zero++;
                }
            }

            if (one > zero || one == zero) {
                int finalIndex = index;
                newList.removeIf(a -> a[finalIndex] == '1');
            } else {
                int finalIndex = index;
                newList.removeIf(a -> a[finalIndex] == '0');
            }

            index++;
            return solveCO2(newList, index);
        }
    }


}