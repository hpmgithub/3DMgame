package com.jash.dmgame.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entity mapped to table "CHAPTER_ENTITY".
 */
public class ChapterEntity {

    @SerializedName("id")
    private Long id;
    @SerializedName("shorttitle")
    private String title;
    @SerializedName("litpic")
    private String pic;
    @SerializedName("feedback")
    private Integer feedback;
    @SerializedName("body")
    private String body;
    @SerializedName("senddate")
    private Date sendDate;
    @SerializedName("typeid")
    private long typeId;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public ChapterEntity() {
    }

    public ChapterEntity(Long id) {
        this.id = id;
    }

    public ChapterEntity(Long id, String title, String pic, Integer feedback, String body, java.util.Date sendDate, long typeId) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.feedback = feedback;
        this.body = body;
        this.sendDate = sendDate;
        this.typeId = typeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getFeedback() {
        return feedback;
    }

    public void setFeedback(Integer feedback) {
        this.feedback = feedback;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public java.util.Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(java.util.Date sendDate) {
        this.sendDate = sendDate;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getDateFormat(){
        return FORMAT.format(sendDate);
    }

}
