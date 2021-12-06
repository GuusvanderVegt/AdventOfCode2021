package com.aoc.days;

import com.aoc.AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day4 extends AOC {

    public Day4() {
        super("4");
    }

    //TODO: Finish
    @Override
    public void solve(List<String> input) {
        int[] numbers = getBingoNumbers(input.get(0).split(","));
        input.remove(0);
        input.remove(0);

        List<List<List<Integer>>> boards = getBoards(input);
        List<List<Integer>> fastestBoard = new ArrayList<>();
//        List<Boolean[][]> boardsStatus = getInitialBoardStatusses(boards);

        for(int number : numbers){
            for(List<List<Integer>> board : boards){
                for(List<Integer> row : board){

                    //If the index = -1, the number is not in that row
                    //If it is in that row, set that index to -1 (marked);

                    int index = row.indexOf(number);
                    if(index != -1 ){
                        row.set(index, - 1);
                    }
                }

                if(checkBoard(board)){
                    fastestBoard = board;
                    break;
                }
            }
        }

        int solution = calculateSolution(fastestBoard);
        System.out.println(solution);
    }

    private int calculateSolution(List<List<Integer>> board) {
        int solution = 0;
        for(List<Integer> row : board){
            for(Integer col : row){
                if(col != -1 ){
                    solution += col;
                }
            }
        }
        return solution;
    }

    private boolean checkBoard(List<List<Integer>> board) {
        boolean isCorrect = true;

        for (List<Integer> row: board) {
            for(Integer col : row){
                if(col != -1) {
                    isCorrect = false;
                }
            }
        }

        if(isCorrect) {
            int colIndex = 0;

            while(colIndex < board.get(0).size()){
                for(List<Integer> row : board){
                    if(row.get(colIndex) != -1){
                        isCorrect = false;
                    }
                }

                colIndex++;
            }
        }

        return isCorrect;
    }

    private List<Boolean[][]> getInitialBoardStatusses(List<Integer[][]> boards) {
        List<Boolean[][]> temp = new ArrayList<>();

        for (int i = 0; i < boards.size(); i++) {
            Boolean[][] tempBoard = new Boolean[5][5];
            for (int j = 0; j < 5; j++) {
                Arrays.fill(tempBoard[j], false);
            }
            temp.add(tempBoard);
        }


        return temp;
    }

    private List<List<List<Integer>>> getBoards(List<String> input) {
        List<List<List<Integer>>> boards = new ArrayList<>();
        List<List<Integer>> board = new ArrayList<>();
        int index = 0;

//        for (int i = 0; i < input.size(); i++) {
        for(String line : input){
            line = line.trim();
            if(!line.equalsIgnoreCase("")){
                List<String> ruleNumbers = Arrays.stream(line.split(" ")).toList();
                board.add(new ArrayList<>());

                for (int j = 0; j < ruleNumbers.size(); j++) {
                    board.get(index).add(Integer.parseInt(ruleNumbers.get(j)));
//                    board.add(Integer.parseInt(ruleNumbers.get(j)));
                }

                index++;
            } else {
                //Empty rule is new board
                boards.add(board);
                index = 0;
                board = new ArrayList<>();
            }
        }

        return boards;
    }

    private int[] getBingoNumbers(String[] split) {
        int[] numbers = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            numbers[i] = Integer.parseInt(split[i]);

        }

        return numbers;
    }
}
