package org.academiadecodigo.bootcamp;

public class Boss extends Enemy{
    @Override
    public void hit(int damage) {
        setHealth(getHealth() - damage);
    }

    @Override
    public boolean isDestroyed() {
        return getHealth() <= 0;
    }
}
