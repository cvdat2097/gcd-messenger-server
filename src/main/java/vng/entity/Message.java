package vng.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "ms_timestamp", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msTimestamp;

    @Column(name = "active")
    private boolean active;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

    // Constructors
    public Message() {
    }

    public Message(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Message(User user, String content, Date msTimestamp) {
        this.user = new User(user.getUsername(), user.getAvatar());
        this.content = content;
        this.msTimestamp = msTimestamp;
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

    public Date getmsTimestamp() {
        return this.msTimestamp;
    }

    public void setmsTimestamp(Date msTimestamp) {
        this.msTimestamp = msTimestamp;
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
