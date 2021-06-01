package io.muic.ooc.fab;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Tiger extends Animal{

    private static final int BREEDING_AGE = 30;

    private static final int MAX_AGE = 120;

    private static final double BREEDING_PROBABILITY = 0.015;

    private static final int MAX_LITTER_SIZE = 3;

    private static final Random RANDOM = new Random();

    private int foodLevel;


    public Tiger(boolean randomAge, Field field, Location location) {
        age = 0;
        setAlive(true);
        this.field = field;
        setLocation(location);
        if (randomAge) {
            age = RANDOM.nextInt(MAX_AGE);
            foodLevel = 9;
        } else {
            // leave age at 0
            foodLevel = 9;
        }
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
    protected void act(List<Animal> newAnimals) {
        incrementAge();
        incrementHunger();
        if (isAlive()) {
            giveBirth(newAnimals);
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = field.freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }

    }

    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = rabbit.getRabbitFoodValue();
                    return where;
                }
            } else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = fox.getFoxFoodValue();
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

    @Override
    protected Animal createYoung(boolean randomAge, Field field, Location location) {
        return new Tiger(randomAge, field, location);
    }
}
