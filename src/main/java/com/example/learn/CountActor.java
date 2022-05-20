package com.example.learn;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Message.class, t -> {
            //收到消息
            String[] words = (String[]) t.getContent();
            System.out.println(self() + " 收到来自于 " + sender() + " 的消息: " + Arrays.toString(words));

            //统计处理
            Map conutMap = new HashMap<>();
            for (String word : words) {
                Integer num = (Integer) conutMap.get(word);
                conutMap.put(word, num == null ? 1 : num + 1);
            }

           ActorSelection actorSelection = getContext().actorSelection("/user/CountActor");
           // System.out.println(self() + " 每个单词出现次数的统计结果为 : " + conutMap);

           Result result = new Result();
           result.info = self() + " 每个单词出现次数的统计结果为 : " + conutMap.entrySet();
           actorSelection.tell(result, ActorRef.noSender());
        }).match(Result.class, t ->{
            // 处理再次调用
            System.out.println(t.info);
                               }).build();

    }

    static class Result {
        public String info;
    }

}
