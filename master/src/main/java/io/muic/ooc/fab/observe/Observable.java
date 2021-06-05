package io.muic.ooc.fab.observe;

import io.muic.ooc.fab.Field;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

     private List<Observer> observerList = new ArrayList<>();

     public void addObserver(Observer observer){
         observerList.add(observer);
     }

     public void notifyAll(int step, Field field){
         for(Observer observer : observerList){
             observer.update(step , field);
         }
     }
}
