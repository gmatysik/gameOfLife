package org.gmatysik.gol;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRunnable implements Runnable{

    CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;
    Board board = null;
    Size size = null;

    public CyclicBarrierRunnable(
            Board board,
            Size size,
            CyclicBarrier barrier1,
            CyclicBarrier barrier2) {
        this.board = board;
        this.size = size;
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    public void run() {
        try {
            while(Game.i > 0){
                int [][] copy = board.copy();

                Thread.sleep(1000);
                //System.out.println(Thread.currentThread().getName() +
                  //      " waiting at barrier 1");
                this.barrier1.await();
                //board.draw();
                board.calculateNextStep(copy, size.getStartx(), size.getStarty(), size.getEndx(), size.getEndy());
                Thread.sleep(1000);
                //System.out.println(Thread.currentThread().getName() +
                  //      " waiting at barrier 2");
                this.barrier2.await();


            }
            System.out.println(Thread.currentThread().getName() +
                    " done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}