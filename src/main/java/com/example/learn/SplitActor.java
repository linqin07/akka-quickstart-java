package com.example.learn;

import akka.actor.AbstractActor;
import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.Arrays;

public class SplitActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Message.class, t -> {
            System.out.println(self() + "  收到来自于 " + sender() + " 的消息: " + t);
            //按照空格拆分数据
            String[] words = String.valueOf(t.getContent()).toLowerCase().split("\\W+");
            //封装消息请求给CountActor
            Message msg = new Message(words);
            System.out.println(self() + "  发送消息 : " + Arrays.toString(words));
            //根据路径查找下一个处理者
            // ActorSelection countActorRef = getContext().actorSelection("/user/CountActor");

            ActorRef countActorRef = getContext().actorOf((Props.create(CountActor.class)), "CountActor");
            //将消息发给下一个处理者CountActor
            countActorRef.tell(msg, self());
        }).build();

    }
}
