package io.muic.ooc.fab;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Hunter extends Actor{

    public Location kill() {
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object living = field.getObjectAt(where);
            if (living instanceof Animal) {
                Animal animal = (Animal) living;
                if (animal.isAlive()) {
                    animal.setDead();
                    return where;
                }
            }

        }
        return null;
    }

    @Override
    public void act(List<Actor> newHunters) {
        Location newLocation = move();
        if (newLocation != null) {
            setLocation(newLocation);
        }
    }

    @Override
    public Location move(){
        Location newLocation = kill();
        if (newLocation == null) {
            newLocation = field.freeAdjacentLocation(location);
        }
        return newLocation;
    }
}
