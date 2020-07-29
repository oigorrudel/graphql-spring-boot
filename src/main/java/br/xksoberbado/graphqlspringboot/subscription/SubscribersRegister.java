package br.xksoberbado.graphqlspringboot.subscription;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SubscribersRegister {

    private static SubscribersRegister instance;
    private static ConcurrentHashMap<Class, List<Subscribers>> subscribersMap;

    private SubscribersRegister() {
        subscribersMap = new ConcurrentHashMap<>();
    }

    private synchronized static void instance(){
        if(instance == null)
            instance = new SubscribersRegister();
    }

    public static Subscribers register(Class clazz){
        instance();

        if(!subscribersMap.containsKey(clazz))
            subscribersMap.put(clazz, new LinkedList<>());

        Subscribers sub = new Subscribers<>();
        subscribersMap.get(clazz).add(sub);
        return sub;
    }

    public static void emit(Long id, Object object){
        Class clazz = object.getClass();
        if(subscribersMap.containsKey(clazz))
            subscribersMap.get(clazz).forEach(s -> s.emit(id, object));
    }
}
