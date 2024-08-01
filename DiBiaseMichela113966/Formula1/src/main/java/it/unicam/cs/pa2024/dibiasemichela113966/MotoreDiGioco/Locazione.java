package it.unicam.cs.pa2024.dibiasemichela113966.MotoreDiGioco;

public class Locazione implements ILocazione {

    private int x;
    private int y;

    public Locazione(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
