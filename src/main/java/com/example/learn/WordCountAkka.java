package com.example.learn;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.IOException;

public class WordCountAkka {
    public static void main(String[] args) {
        //1、创建Actor系统，名字为wordcount
        ActorSystem actorSystem = ActorSystem.create("wordcount");
        try {
            //2、创建SplitActor，用于拆分每行的单词
            ActorRef splitActor = actorSystem.actorOf((Props.create(SplitActor.class)), "SplitActor");
            //2.1、创建CountActor，用于统计单词的次数
            ActorRef countActor = actorSystem.actorOf((Props.create(CountActor.class)), "CountActor");

            //3、创建消息
            //TODO 接收的消息串，可以修改为从控制台输入，本次就直接写死了
            Message msg = new Message("Hello Akka Akka Hello ni hao aa hao");
            //4、给SplitActor发消息
            splitActor.tell(msg, ActorRef.noSender());

            //5、按回车退出应用
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            actorSystem.terminate();
        }
    }
}
