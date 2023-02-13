package br.com.tiagoiwamoto.testautomate.adapter;


import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class KafkaPublisherAdapter {

    private KafkaPublisherAdapter() {
    }

//    public static KafkaPublisherAdapter of(){
//        return new KafkaPublisherAdapter();
//    }

    @Inject
    @Channel("mytopic-out")
    private Emitter<Record<String, String>> emitter;

    public void send(String message){
        this.emitter.send(Record.of(UUID.randomUUID().toString(), message));
    }

}
