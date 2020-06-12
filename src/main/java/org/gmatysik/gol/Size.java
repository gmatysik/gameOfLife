package org.gmatysik.gol;

public class Size {
    private int startx = 0;
    private int starty = 0;

    public Size(int startx, int starty, int endx, int endy) {
        this.startx = startx;
        this.starty = starty;
        this.endx = endx;
        this.endy = endy;
    }

    private int endx = 0;
    private int endy = 0;


    public int getStartx() {
        return startx;
    }

    public int getStarty() {
        return starty;
    }

    public int getEndx() {
        return endx;
    }

    public int getEndy() {
        return endy;
    }
}
