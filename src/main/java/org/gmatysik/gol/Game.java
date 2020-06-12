package org.gmatysik.gol;

import java.util.concurrent.CyclicBarrier;

public class Game {

    static int i = 15; //15 iterations of game
    volatile static Board board = null;

    static Runnable barrier1Action = new Runnable() {
        public void run() {
            i--;
            //System.out.println("BarrierAction 1 executed ");
        }
    };
    static Runnable barrier2Action = new Runnable() {
        public void run() {
            //clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Board: ");
            board.draw();
        }
    };

    static CyclicBarrier barrier1 = new CyclicBarrier(4, barrier1Action);
    static CyclicBarrier barrier2 = new CyclicBarrier(4, barrier2Action);

    public static void main(String ...args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Cores: " + cores);

        Position[] positions = {new Position(5,4), new Position(5,6),
                new Position(4,5), new Position(6,5), new Position(5,5)};
        board = new Board(10, 10);
        board.initialise(positions);

        Size size1 = new Size(0, 0, 5, 5);
        Size size2 = new Size( 5, 0, 10, 5);
        Size size3 = new Size( 0, 5, 5, 10);
        Size size4 = new Size( 5, 5, 10, 10);

        CyclicBarrierRunnable runnable1 = new CyclicBarrierRunnable(board, size1, barrier1, barrier2);
        CyclicBarrierRunnable runnable2 = new CyclicBarrierRunnable(board, size2, barrier1, barrier2);
        CyclicBarrierRunnable runnable3 = new CyclicBarrierRunnable(board, size3, barrier1, barrier2);
        CyclicBarrierRunnable runnable4 = new CyclicBarrierRunnable(board, size4, barrier1, barrier2);

        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable3).start();
        new Thread(runnable4).start();
    }



}
