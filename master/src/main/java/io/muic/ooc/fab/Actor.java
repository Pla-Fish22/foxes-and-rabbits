package io.muic.ooc.fab;

import java.util.List;

public abstract class Actor {

    protected Location location;
    protected Field field;
    public boolean alive;
    private boolean randomAge;

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void act(List<Actor> newAnimals);

    public abstract Location move();

    public void initialize(boolean randomAge, Field field, Location location){
        this.field = field;
        setLocation(location);
        setAlive(true);
    }
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    public boolean isAlive(){return this.alive;}
}

