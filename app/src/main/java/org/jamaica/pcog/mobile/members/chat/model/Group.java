package org.jamaica.pcog.mobile.members.chat.model;



public class Group extends Room {
    public String id;
    public ListFriend listFriend;

    public Group(){
        listFriend = new ListFriend();
    }
}
