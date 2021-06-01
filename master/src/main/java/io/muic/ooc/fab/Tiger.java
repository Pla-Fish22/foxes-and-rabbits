package io.muic.ooc.fab;

import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Tiger extends Animal{

    private static final int BREEDING_AGE = 35;

    private static final int MAX_AGE = 120;

    private static final double BREEDING_PROBABILITY = 0.015;

    private static final int MAX_LITTER_SIZE = 3;

    private static final Random RANDOM = new Random();

    private int foodLevel;

    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge,field, location);
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
    public Location move(){
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

}
