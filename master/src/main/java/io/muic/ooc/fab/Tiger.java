package io.muic.ooc.fab;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Tiger extends Animal {

    private static final int BREEDING_AGE = 35;

    private static final int MAX_AGE = 120;

    private static final double BREEDING_PROBABILITY = 0.015;

    private static final int MAX_LITTER_SIZE = 3;

    private static final Random RANDOM = new Random();

    private int foodLevel;

    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge, field, location);
        this.foodLevel = RANDOM.nextInt(9) + RANDOM.nextInt(13);
    }


    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    public void act(List<Actor> newAnimals) {
        incrementHunger();
        super.act(newAnimals);
    }

    @Override
    protected int getFoodValue() {
        return 0;
    }

    @Override
    public Location move() {
        Location newLocation = kill();
        if (newLocation == null) {
            newLocation = field.freeAdjacentLocation(location);
        }
        return newLocation;
    }


    public Location kill() {
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object livingThing = field.getObjectAt(where);
            if (livingThing instanceof Rabbit || livingThing instanceof Fox) {

                Animal animal = (Animal) livingThing;
                if (animal.isAlive()) {
                    animal.setDead();
                    foodLevel = animal.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }

    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

}
