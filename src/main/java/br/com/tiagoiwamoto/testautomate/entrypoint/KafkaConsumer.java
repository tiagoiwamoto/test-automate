package br.com.tiagoiwamoto.testautomate.entrypoint;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("mytopic")
    @Acknowledgment(Acknowledgment.Strategy.MANUAL)
    public void consume(ConsumerRecord<String, String> record){
        var body = record.value();
        System.out.println(body);
    }

}
