package com.aoc.days;

import com.aoc.AOC;

import java.util.Arrays;
import java.util.List;

public class Day8 extends AOC {
    public Day8(){
        super("8");
    }

    @Override
    public void solve(List<String> input) {

        //Part 1
        int amount = 0;
        for(String line :  input){
            String linePart = line.split("\\|")[1].trim();

            String[] parts = linePart.split(" ");

            for(String part : parts){
                int length = part.length();
                if(length == 2 || length == 4  || length == 3 || length == 7)
                    amount++;
            }
        }

        System.out.println(amount);


        //Part 2
        int sum = 0;
        for(String s : input)
        {
            String[] parts = s.split("\\|");
            String[] firstHalf = parts[0].strip().split(" ");
            String[] secondHalf = parts[1].strip().split(" ");
            String[] digits = new String[10];
            for(String sPart : firstHalf)
            {
                switch(sPart.length())
                {
                    case 2:
                        digits[1] = sPart;
                        break;
                    case 3:
                        digits[7] = sPart;
                        break;
                    case 4:
                        digits[4] = sPart;
                        break;
                    case 7:
                        digits[8] = sPart;
                        break;
                }
            }
            for(String sPart : firstHalf)
            {
                if(sPart.length() == 6)
                {
                    if(isNine(digits, sPart))
                        digits[9] = sPart;
                    else if(isZero(digits, sPart))
                        digits[0] = sPart;
                    else
                        digits[6] = sPart;
                }
            }

            for(String sPart : firstHalf)
            {
                if(sPart.length() == 5)
                {
                    if(isThree(digits, sPart))
                        digits[3] = sPart;
                    else if(isFive(digits, sPart))
                        digits[5] = sPart;
                    else
                        digits[2] = sPart;
                }
            }

            for(int i = 0; i < 10; i++)
                digits[i] = orderString(digits[i]);

            int number = 0;
            for(String toFind : secondHalf)
            {
                String sorted = orderString(toFind);
                for(int i = 0; i < 10; i++)
                    if(digits[i].equals(sorted))
                        number = (number * 10) + i;
            }
            sum += number;
        }
        System.out.println(sum);
    }

    public String orderString(String toOrder)
    {
        char[] toSort = toOrder.toCharArray();
        Arrays.sort(toSort);
        return new String(toSort);
    }

    public boolean isNine(String[] digits, String sPart)
    {
        boolean nine = true;
        for(char c : digits[4].toCharArray())
        {
            if(!sPart.contains(String.valueOf(c)))
            {
                nine = false;
                break;
            }
        }
        return nine;
    }

    public boolean isZero(String[] digits, String sPart)
    {
        boolean one = true;
        for(char c : digits[1].toCharArray())
        {
            if(!sPart.contains(String.valueOf(c)))
            {
                one = false;
                break;
            }
        }
        return one;
    }

    public boolean isThree(String[] digits, String sPart)
    {
        boolean three = true;
        for(char c : digits[1].toCharArray())
        {
            if(!sPart.contains(String.valueOf(c)))
            {
                three = false;
                break;
            }
        }
        return three;
    }

    public boolean isFive(String[] digits, String sPart)
    {
        int missing = 0;
        for(char c : digits[6].toCharArray())
            if(!sPart.contains(String.valueOf(c)))
                missing++;
        return missing == 1;
    }
}
