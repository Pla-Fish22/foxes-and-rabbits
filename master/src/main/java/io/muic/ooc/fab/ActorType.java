package io.muic.ooc.fab;

import io.muic.ooc.fab.Fox;
import io.muic.ooc.fab.Hunter;
import io.muic.ooc.fab.Rabbit;
import io.muic.ooc.fab.Tiger;

import java.awt.*;

public enum ActorType {
    RABBIT(0.08, Rabbit.class, Color.ORANGE),
    FOX(0.02, Fox.class, Color.BLUE),
    TIGER(0.006, Tiger.class, Color.RED),
    HUNTER(0.0003, Hunter.class, Color.BLACK);

    private double creationProbability;
    private Class actorClass;
    private Color color;

    ActorType(double creationProbability, Class actorClass, Color color){
        this.creationProbability = creationProbability;
        this.actorClass = actorClass;
        this.color = color;
    }

    public double getCreationProbability(){
        return creationProbability;
    }

    public Class getActorClass(){
        return actorClass;
    }

    public Color getColor(){
        return color;
    }


}
