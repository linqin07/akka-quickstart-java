package com.example.learn;

/**
 * 定义消息
 */
public class Message {

    private Object content;

    public Message(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
