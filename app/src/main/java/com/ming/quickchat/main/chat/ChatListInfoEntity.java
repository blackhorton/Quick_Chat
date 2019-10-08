package com.ming.quickchat.main.chat;

import java.util.Date;

/**
 * @author Hortons
 * on 19-10-3
 */


public class ChatListInfoEntity {

    private String name;
    private String content;
    private Date date;
    private String headUrl;
    private int unReadCount = 0;

    public ChatListInfoEntity() {

    }

    public ChatListInfoEntity(String name, String content, Date date, String headUrl, int unReadCount) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.headUrl = headUrl;
        this.unReadCount = unReadCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }
}
