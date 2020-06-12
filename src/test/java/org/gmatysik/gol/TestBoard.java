package org.gmatysik.gol;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @Test
    void create(){
        Position[] positions = {new Position(1,1), new Position(1,2)};
        Board board = new Board(10, 10);
        board.initialise(positions);
        board.draw();

        assertNotNull(board);
    }

    @Test
    void numberOfAliveNeighbours_0_0(){
        Position[] positions = {new Position(1,0), new Position(1,1), new Position(0,1)};
        Board board = new Board(10, 10);
        board.initialise(positions);

        assertEquals(3, board.numberOfAliveNeighboursFor(new Position(0,0)));
        assertEquals(2, board.numberOfAliveNeighboursFor(new Position(1,0)));
        assertEquals(1, board.numberOfAliveNeighboursFor(new Position(2,2)));

    }

    @Test
    void numberOfAliveNeighbours_9_9(){
        Position[] positions = {new Position(9,8), new Position(8,8), new Position(8,9)};
        Board board = new Board(10, 10);
        board.initialise(positions);

        assertEquals(3, board.numberOfAliveNeighboursFor(new Position(9,9)));
        assertEquals(2, board.numberOfAliveNeighboursFor(new Position(7,9)));
        assertEquals(1, board.numberOfAliveNeighboursFor(new Position(7,7)));

    }

    @Test
    void numberOfAliveNeighbours_5_5(){
        Position[] positions = {new Position(5,4), new Position(5,6),
                new Position(4,5), new Position(6,5)};
        Board board = new Board(10, 10);
        board.initialise(positions);

        assertEquals(4, board.numberOfAliveNeighboursFor(new Position(5,5)));
        assertEquals(2, board.numberOfAliveNeighboursFor(new Position(4,6)));
        assertEquals(1, board.numberOfAliveNeighboursFor(new Position(4,7)));
        board.draw();
    }

    @Test
    void calculateNextStep() throws IOException {
        /**
         *
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000010000
         * 0000111000
         * 0000010000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        Position[] positions = {new Position(5,4), new Position(5,6),
                new Position(4,5), new Position(6,5), new Position(5,5)};
        Board board = new Board(10, 10);
        board.initialise(positions);
        int[][] result = board.getBoard();

        board.draw();


        board.calculateNextStep(board.copy());
        /**
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000111000
         * 0000101000
         * 0000111000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        assertEquals(1, result[4][4]);
        assertEquals(1, result[4][5]);
        assertEquals(1, result[4][6]);

        assertEquals(1, result[5][4]);
        assertEquals(0, result[5][5]);
        assertEquals(1, result[5][6]);

        assertEquals(1, result[6][4]);
        assertEquals(1, result[6][5]);
        assertEquals(1, result[6][6]);

        assertEquals(0, result[5][5]);
        System.out.println("-------------------------");

        board.draw();

        System.out.println("-------------------------");
        board.calculateNextStep(board.copy());

        /**
         *
         *         0000000000
         *         0000000000
         *         0000000000
         *         0000010000
         *         0000101000
         *         0001000100
         *         0000101000
         *         0000010000
         *         0000000000
         *         0000000000
         *
         */
        board.draw();

        assertEquals(1, result[5][3]);

        assertEquals(1, result[4][4]);
        assertEquals(1, result[6][6]);

        assertEquals(1, result[3][5]);
        assertEquals(1, result[7][5]);

        assertEquals(1, result[4][6]);
        assertEquals(1, result[6][6]);

        assertEquals(1, result[5][7]);

    }

    @Test
    void calculateNextStep_offset_5_5_10_10() throws IOException {
        /**
         *
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000010000
         * 0000111000
         * 0000010000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        Position[] positions = {new Position(5,4), new Position(5,6),
                new Position(4,5), new Position(6,5), new Position(5,5)};
        Board board = new Board(10, 10);
        board.initialise(positions);
        int[][] result = board.getBoard();

        board.draw();

        board.calculateNextStep(board.copy(), 5, 5, 10, 10);
        System.out.println("-------------------------");

        board.draw();

        /**
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000010000
         * 0000101000
         * 0000011000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        assertEquals(0, result[4][4]);
        assertEquals(1, result[4][5]);
        assertEquals(0, result[4][6]);

        assertEquals(1, result[5][4]);
        assertEquals(0, result[5][5]);
        assertEquals(1, result[5][6]);

        assertEquals(0, result[6][4]);
        assertEquals(1, result[6][5]);
        assertEquals(1, result[6][6]);

        assertEquals(0, result[5][5]);
    }



    @Test
    void calculateNextStep_offset_0_0_5_5() throws IOException {
        /**
         *
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000010000
         * 0000111000
         * 0000010000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        Position[] positions = {new Position(5,4), new Position(5,6),
                new Position(4,5), new Position(6,5), new Position(5,5)};
        Board board = new Board(10, 10);
        board.initialise(positions);
        int[][] result = board.getBoard();

        board.draw();

        board.calculateNextStep(board.copy(), 0, 0, 5, 5);
        System.out.println("-------------------------");

        board.draw();

        /**
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000110000
         * 0000111000
         * 0000010000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        assertEquals(1, result[4][4]);
        assertEquals(1, result[4][5]);
        assertEquals(0, result[4][6]);

        assertEquals(1, result[5][4]);
        assertEquals(1, result[5][5]);
        assertEquals(1, result[5][6]);

        assertEquals(0, result[6][4]);
        assertEquals(1, result[6][5]);
        assertEquals(0, result[6][6]);

        assertEquals(1, result[5][5]);
    }




    @Test
    void calculateNextStep_4_offsets() throws IOException {
        /**
         *
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000010000
         * 0000111000
         * 0000010000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        Position[] positions = {new Position(5,4), new Position(5,6),
                new Position(4,5), new Position(6,5), new Position(5,5)};
        Board board = new Board(10, 10);
        board.initialise(positions);
        int[][] result = board.getBoard();

        board.draw();

        int[][] copy = board.copy();

        board.calculateNextStep(copy, 0, 0, 5, 5);
        board.calculateNextStep(copy, 5, 0, 10, 5);
        board.calculateNextStep(copy, 0, 5, 5, 10);
        board.calculateNextStep(copy, 5, 5, 10, 10);
        System.out.println("-------------------------");

        board.draw();

        /**
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000000000
         * 0000111000
         * 0000101000
         * 0000111000
         * 0000000000
         * 0000000000
         * 0000000000
         *
         *
         */
        assertEquals(1, result[4][4]);
        assertEquals(1, result[4][5]);
        assertEquals(1, result[4][6]);

        assertEquals(1, result[5][4]);
        assertEquals(0, result[5][5]);
        assertEquals(1, result[5][6]);

        assertEquals(1, result[6][4]);
        assertEquals(1, result[6][5]);
        assertEquals(1, result[6][6]);

        assertEquals(0, result[5][5]);

        copy = board.copy();

        board.calculateNextStep(copy, 0, 0, 5, 5);
        board.calculateNextStep(copy, 5, 0, 10, 5);
        board.calculateNextStep(copy, 0, 5, 5, 10);
        board.calculateNextStep(copy, 5, 5, 10, 10);
        System.out.println("-------------------------");


        /**
         *
         *         0000000000
         *         0000000000
         *         0000000000
         *         0000010000
         *         0000101000
         *         0001000100
         *         0000101000
         *         0000010000
         *         0000000000
         *         0000000000
         *
         */
        board.draw();

        assertEquals(1, result[5][3]);

        assertEquals(1, result[4][4]);
        assertEquals(1, result[6][6]);

        assertEquals(1, result[3][5]);
        assertEquals(1, result[7][5]);

        assertEquals(1, result[4][6]);
        assertEquals(1, result[6][6]);

        assertEquals(1, result[5][7]);
        board.draw();

    }
}
