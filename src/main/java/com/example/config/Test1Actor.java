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
public class Test1Actor extends AbstractActor {
    @Autowired
    private ActorSystem actorSystem;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Message.class, o -> {
            System.out.println("接收到消息" + o.getContent().toString());
            // ActorSystem.create("child", actorSystem.)

            System.out.println("-------------");
        }).build();
    }
}
