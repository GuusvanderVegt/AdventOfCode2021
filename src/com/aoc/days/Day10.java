package com.aoc.days;

import com.aoc.AOC;

import java.util.*;

public class Day10 extends AOC {
    public Day10(){
        super("10");
    }

    @Override
    public void solve(List<String> input) {
        List<String> incompleteLines = new ArrayList<>(input);
        List<Character> closingTags = new ArrayList<>(Arrays.asList(']', '}', '>', ')'));

        Stack<Character> openingTags = new Stack<>();
        List<Character> corruptedTags = new ArrayList<>();

        List<Long> scores = new ArrayList<>();

        for (int j = 0; j < input.size(); j++) {
            String line = input.get(j);

            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);

                if(closingTags.contains(character)){
                    char lastOpeningTag = openingTags.pop();

                    //Part 1
                    if((character == ']' && lastOpeningTag != '[') || (character == '}' && lastOpeningTag != '{') || (character == ')' && lastOpeningTag != '(') || (character == '>' && lastOpeningTag != '<')){
                        corruptedTags.add(character);
                        incompleteLines.remove(line);
                        break;
                    }

                } else {
                    openingTags.add(character);
                }
            }

            openingTags = new Stack<>();
        }

        openingTags = new Stack<>();

        for(String line : incompleteLines){
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

        Collections.sort(scores);
        System.out.println(scores.get((scores.size()/2)));

    }

    private Long calculateScore(Stack<Character> openingTags) {
        char tag;
        long total = 0;

        while(openingTags.size() > 0) {
            tag = openingTags.pop();
            total *= 5;

            if(tag == '['){
                total += 2;
            }

            if(tag == '{'){
                total += 3;

            }

            if(tag == '('){
                total += 1;

            }

            if(tag == '<'){
                total += 4;

            }
        }
        return total;
    }
}
