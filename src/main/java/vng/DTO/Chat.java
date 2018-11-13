package vng.DTO;

import vng.entity.*;

public class Chat {
    public User user;
    public Content message;

    public Chat(User usr, String content, String timeStamp) {
        this.user = usr;
        this.message = new Content(content, timeStamp);
    }

    public class Content {
        public String content;
        public String timeStamp;

        public Content(String content, String timeStamp) {
            this.content = content;
            this.timeStamp = timeStamp;
        }
    }
}