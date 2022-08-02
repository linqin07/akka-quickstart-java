package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.example.learn.Message;
import com.example.spring.SpringExtProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AkkaQuickstartTest {

    @Autowired
    private ActorSystem actorSystem;

    @Test
    public void contextLoads() throws Exception {
        Props testActor = SpringExtProvider.getInstance().get(actorSystem).create("testActor");
        akka.actor.ActorRef actorRef = actorSystem.actorOf(testActor);
        actorRef.tell("hello", akka.actor.ActorRef.noSender());
        // actorRef.tell("hello1", akka.actor.ActorRef.noSender());
        // actorRef.tell("hello2", akka.actor.ActorRef.noSender());

        actorRef.tell(new Message("message"), ActorRef.noSender());

        // Props testActor1 = SpringExtProvider.getInstance().get(actorSystem).create("testActor");
        // akka.actor.ActorRef actorRef1 = actorSystem.actorOf(testActor1);
        // actorRef1.tell("h2ello", akka.actor.ActorRef.noSender());
        // actorRef1.tell("h2ello1", akka.actor.ActorRef.noSender());
        // actorRef1.tell("h2ello2", akka.actor.ActorRef.noSender());
        System.in.read();

    }
}
