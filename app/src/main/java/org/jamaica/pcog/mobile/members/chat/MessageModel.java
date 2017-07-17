package org.jamaica.pcog.mobile.members.chat;

import java.util.Date;

/**
 * Created by ennur on 11/13/16.
 */

public class MessageModel {
    private String messageText;
    private String messageUser;
    private String messageEmail;
    private long messageTime;

    public MessageModel(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageEmail = messageEmail;
        messageTime = new Date().getTime();
    }

    public MessageModel(){

    }

    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }
    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageEmail() {
        return messageEmail;
    }
    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public long getMessageTime() {
        return messageTime;
    }
    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
