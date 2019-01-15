package vng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "ms_timestamp", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ms_timeStamp;

    @Column(name = "active")
    private boolean active;

    @Transient
    private User user;

    // Constructors
    public Message() {
    }

    public Message(Integer id, String content, Integer userid, Date ms_timeStamp, boolean active, User user) {
        this.id = id;
        this.content = content;
        this.userid = userid;
        this.ms_timeStamp = ms_timeStamp;
        this.active = active;
        this.user = user;
    }

    public Message(User user, String content, Date ms_timeStamp) {
        this.user = new User(user.getUsername(), user.getAvatar());
        this.content = content;
        this.ms_timeStamp = ms_timeStamp;
    }

    // Getters & Setters

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getMSTimeStamp() {
        return this.ms_timeStamp;
    }

    public void setMSTimeStamp(Date ms_timeStamp) {
        this.ms_timeStamp = ms_timeStamp;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}