package vng.controller;

import vng.model.DBController;
import vng.DTO.*;
import vng.entity.Message;
import vng.entity.User;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GDCRestController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public Object[] GETMessage(@RequestParam(value = "nmsg", defaultValue = "1000") String nMsg) {
        int nMessages = Integer.parseInt(nMsg);

        ArrayList<Chat> history = new ArrayList<Chat>();
        int totalMsg = DBController.dbMessage.size();
        if (nMessages > totalMsg) {
            nMessages = totalMsg;
        }
        for (int i = totalMsg - nMessages; i < totalMsg; i++) {
            Message currentMsg = DBController.dbMessage.get(i);
            history.add(new Chat(currentMsg.getUser(), currentMsg.getContent(), currentMsg.getTimeStamp()));

        }

        return history.toArray();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String POSTMessage(@RequestBody String body) {
        JSONObject x = new JSONObject(body);
        JSONObject usr = new JSONObject(x.get("user").toString());
        JSONObject msg = new JSONObject(x.get("message").toString());

        Message newMsg = new Message(new User(usr.get("username").toString(), usr.get("avatar").toString()),
                msg.get("content").toString(), msg.get("timeStamp").toString());

        boolean result = DBController.dbMessage.add(newMsg);

        return result ? "SUCCESS" : "FAILURE";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object[] GETUser() {
        return DBController.dbUser.toArray();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String POSTUser(@RequestBody String body) {
        JSONObject usr = new JSONObject(body);

        User newUser = new User(usr.getString("username"), usr.getString("avatar"));

        boolean result = DBController.dbUser.add(newUser);

        return result ? "SUCCESS" : "FAILURE";
    }
}
