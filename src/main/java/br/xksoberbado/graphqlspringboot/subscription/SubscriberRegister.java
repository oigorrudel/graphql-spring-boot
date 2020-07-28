package br.xksoberbado.graphqlspringboot.subscription;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SubscriberRegister {

    private static SubscriberRegister instance;
    private static ConcurrentHashMap<Class, List<Subscriber>> subscribersMap;

    private SubscriberRegister() {
        subscribersMap = new ConcurrentHashMap<>();
    }

    private synchronized static void instance(){
        if(instance == null)
            instance = new SubscriberRegister();
    }

    public static Subscriber register(Class clazz){
        instance();

        if(!subscribersMap.containsKey(clazz))
            subscribersMap.put(clazz, new LinkedList<>());

        Subscriber sub = new Subscriber<>();
        subscribersMap.get(clazz).add(sub);
        return sub;
    }

    public static void emit(Long id, Object object){
        Class clazz = object.getClass();
        if(subscribersMap.containsKey(clazz))
            subscribersMap.get(clazz).forEach(s -> s.emit(id, object));
    }
}
