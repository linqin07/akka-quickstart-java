package com.example.config;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.learn.Message;
import com.example.spring.SpringExtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestActor extends AbstractActor {

    @Autowired
    private ActorSystem actorSystem;
    @Override
    public Receive createReceive() {
        return receiveBuilder().match( String.class, o -> {
            System.out.println("接收到消息" + o);
        }).match(Message.class, o->{
            Props childActor = SpringExtProvider.getInstance().get(actorSystem).create("test1Actor");
            ActorRef actorRef = actorSystem.actorOf(childActor);
            actorRef.tell(new Message("aaaa childActor"), actorRef);
        }).build();
    }
}
