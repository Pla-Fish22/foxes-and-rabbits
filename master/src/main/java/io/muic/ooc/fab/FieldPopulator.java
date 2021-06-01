package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;

public class FieldPopulator {
    // Each actors' create probablity.
    private static final Random RANDOM = new Random();


    private Map<ActorType, Double> probabilityMap = new HashMap<>() {{
        ActorType[] actorTypes = ActorType.values();
        for(int idx = 0; idx < actorTypes.length; idx++){
            put(actorTypes[idx], actorTypes[idx].getCreationProbability());
        }
    }};

    public void populate(Field field, List<Actor> actors) {
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                for(Map.Entry<ActorType, Double> entry : probabilityMap.entrySet()){
                    if(RANDOM.nextDouble() <= entry.getValue()){
                        Location location = new Location(row, col);
                        Actor actor = ActorFactory.createActor(entry.getKey(), field, location);
                        actors.add(actor);
                        break;
                    }
                }
            }
        }
    }

}
