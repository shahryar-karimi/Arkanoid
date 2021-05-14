package models.cells;

import java.awt.*;

public abstract class Cell extends Rectangle {
    protected int heal;
    protected boolean isDead;

    public Cell(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.isDead = false;
        this.heal = 1;
    }

    public void loseHeal() {
        if (!isDead()) setHeal(getHeal() - 1);
        if (getHeal() == 0) setDead(true);
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
