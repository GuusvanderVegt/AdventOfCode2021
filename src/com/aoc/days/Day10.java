package com.aoc.days;

import com.aoc.AOC;

import java.util.*;

public class Day10 extends AOC {
    public Day10(){
        super("10");
    }

    @Override
    public void solve(List<String> input) {
        List<Character> closingTags = new ArrayList<>(Arrays.asList(']', '}', '>', ')'));

        Stack<Character> openingTags = new Stack<>();
        List<Character> corruptedTags = new ArrayList<>();

        List<Integer> scores = new ArrayList<>();

        for (int j = 0; j < input.size(); j++) {
            String line = input.get(j);

            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);

                if(closingTags.contains(character)){
                    char lastOpeningTag = openingTags.pop();

                    //Part 1
//                    if((character == ']' && lastOpeningTag != '[') || (character == '}' && lastOpeningTag != '{') || (character == ')' && lastOpeningTag != '(') || (character == '>' && lastOpeningTag != '<')){
//                        corruptedTags.add(character);
//                        break;
//                    }

                    //Part 2
                    if((character == ']' && lastOpeningTag != '[') || (character == '}' && lastOpeningTag != '{') || (character == ')' && lastOpeningTag != '(') || (character == '>' && lastOpeningTag != '<')){
                        input.remove(line);
                    }

                } else {
                    openingTags.add(character);
                }
            }

        }

        openingTags = new Stack<>();

        for(String line : input){
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);

                if(closingTags.contains(character)){
                    char lastOpeningTag = openingTags.peek();


                    if((character == ']' && lastOpeningTag == '[') || (character == '}' && lastOpeningTag == '{') || (character == ')' && lastOpeningTag == '(') || (character == '>' && lastOpeningTag == '<')){
                        openingTags.pop();
                    }

                } else {
                    openingTags.add(character);
                }
            }

            scores.add(calculateScore(openingTags));
            openingTags = new Stack<>();
        }



        System.out.println(scores.size() / 2);

        Collections.sort(scores);
        System.out.println(scores.get((scores.size()/2) - 1));

//        int solPart1 = 0;

//        for(char c : corruptedTags){
//            switch(c) {
//                case ')' -> solPart1 += 3;
//                case ']' -> solPart1 += 57;
//                case '}' -> solPart1 += 1197;
//                case '>' -> solPart1 += 25137;
//            }
//        }
//
//        System.out.println(solPart1);

    }

    private Integer calculateScore(Stack<Character> openingTags) {
        char tag;
        int total = 0;

        for (int i = 0; i < openingTags.size(); i++) {
            tag = openingTags.pop();

            if(tag == '['){
                total += (total * 5) + 2;
            }

            if(tag == '{'){
                total += (total * 5) + 3;

            }

            if(tag == '('){
                total += (total * 5) + 1;

            }

            if(tag == '<'){
                total += (total * 5) + 4;

            }
        }

        return total;
    }
}
