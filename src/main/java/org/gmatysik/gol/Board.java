package org.gmatysik.gol;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class Board {

    private int[][] board = null;
    private int width = 0;
    private int height = 0;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        board = new int[width][height];
    }

    public Board(int [][] copy){
        this.width = copy.length;
        this.height = copy[0].length;
        board = copy;
    }

    public void initialise(Position ...positions){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                board[i][j] = 0;
            }
        }

        for (Position position : positions) {
            board[position.x][position.y] = 1;
        }
    }

    public int[][] getBoard(){
        return board;
    }

    public int[][] copy(){
        return Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }

    public void draw(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                System.out.print(board[i][j] == 1 ? 1 : " ");
            }
            System.out.println();
        }
    }

    public int numberOfAliveNeighboursFor(Position position) {
        int x = position.x;
        int y = position.y;
        int count = 0;

        //x -1 , y -1
        //x -1 , y
        //x -1 , y + 1

        //x  , y - 1
        //x  , y  + 1

        //x + 1  , y  - 1
        //x + 1  , y
        //x + 1  , y  + 1

        if(x - 1 >=0 && y -1 >= 0){
            count = count + board[x-1][y - 1];
        }

        if(x - 1 >=0){
            count = count + board[x-1][y];
        }

        if(x - 1 >=0 && y + 1 < height){
            count = count + board[x-1][y + 1];
        }

        if(y - 1 >= 0){
            count = count + board[x][y - 1];
        }

        if(y + 1 < height){
            count = count + board[x][y + 1];
        }

        //x + 1  , y  - 1
        //x + 1  , y
        //x + 1  , y  + 1

        if(x + 1 < width && y - 1 >= 0){
            count = count + board[x +1][y - 1];
        }

        if(x + 1 < width){
            count = count + board[x + 1][y];
        }

        if(x + 1 < width && y + 1 < height){
            count = count + board[x + 1][y + 1];
        }

        return count;
    }

    public void calculateNextStep(int[][] copy) {
        calculateNextStep(copy, 0, 0, this.width, this.height);
    }

    public void calculateNextStep(int[][] copy, int startx, int starty, int endx, int endy) {
        Board copyBoard = new Board(copy);

        for(int i = startx ; i < endx; i++){
            for(int j = starty; j < endy; j++) {
                int count = copyBoard.numberOfAliveNeighboursFor(new Position(i, j));
                if(count == 3 && copy[i][j] == 0){
                    board[i][j] = 1;
                } else if(copy[i][j] == 1 && (count == 3 || count == 2)){
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}