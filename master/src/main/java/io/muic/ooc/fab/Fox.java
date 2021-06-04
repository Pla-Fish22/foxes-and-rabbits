package io.muic.ooc.fab;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Fox extends Animal {
    // Characteristics shared by all foxes (class variables).

    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a fox can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // Food value os a foc
    private static final int FOX_FOOD_VALUE = 13;
    // Random generator
    private static final Random RANDOM = new Random();

    // The fox's food level, which is increased by eating rabbits.
    private int foodLevel;


    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge,field, location);
        this.foodLevel = RANDOM.nextInt(9);
    }

    @Override
    public void act(List<Actor> newAnimals) {
        incrementHunger();
        super.act(newAnimals);
    }

    public Location move(){
        Location newLocation = kill();
        if (newLocation == null) {
            newLocation = field.freeAdjacentLocation(location);
        }
        return newLocation;
    }

    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    public Location kill() {
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object livingThing = field.getObjectAt(where);
            if (livingThing instanceof Rabbit) {
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
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    public int getFoodValue(){
        return FOX_FOOD_VALUE;
    }
}
