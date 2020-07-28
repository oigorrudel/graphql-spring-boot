package br.xksoberbado.graphqlspringboot.subscription;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.ConcurrentHashMap;

public class Subscriber<E> extends ConcurrentHashMap<Long, FluxSink<E>> {

    public Publisher<E> subscription(Long id){
        return Flux.create(subscriber -> this.put(id, subscriber.onDispose(() -> this.remove(id, subscriber))),
                FluxSink.OverflowStrategy.LATEST);
    }

    protected void emit(Long id, E object){
        if(containsKey(id))
            get(id).next(object);
    }


}