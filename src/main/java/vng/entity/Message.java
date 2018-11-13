package vng.entity;


public class Message {
    private String content;
    private String timeStamp;
    private User user;

    public Message(User user, String content, String timeStamp) {
        this.user = new User(user.getUsername(), user.getAvatar());
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return this.user;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public String getContent() {
        return this.content;
    }
}