package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ActorFactory {

    private static Map<ActorType, Class> actorClassMap = new HashMap<>(){{
        ActorType[] actorTypes = ActorType.values();
        for(int idx = 0; idx < actorTypes.length; idx++){
            put(actorTypes[idx], actorTypes[idx].getActorClass());
        }
    }};


    public static Actor createActor(ActorType actorType, Field field, Location location) {
        Class actorClass = actorClassMap.get(actorType);
        return createActor(actorClass, field, location);
    }

    public static Actor createActor(Class actorClass, Field field, Location location){
        if(actorClass != null){
            try {
                Actor actor = (Actor) actorClass.getDeclaredConstructor().newInstance();
                actor.initialize(true, field, location);
                return actor;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("Unknown actorType");
    }
}
