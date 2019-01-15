package vng.controller;

import vng.model.DBController;
import vng.repository.MessageRepository;
import vng.DTO.*;
import vng.entity.Message;
import vng.entity.User;

import java.util.ArrayList;
import java.util.LinkedList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GCDRestController {

    @Autowired
    MessageRepository messageRepo;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/test")
    public Object[] testMapping() {

        ArrayList<Message> list = new ArrayList<Message>();

        PageRequest pageInfo = PageRequest.of(1, 3);
        Page<Message> page = messageRepo.findAll(pageInfo);

        page.iterator().forEachRemaining(msg -> list.add(msg));

        return list.toArray();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/chat")
    public Object[] GETMessage(@RequestParam(value = "nmsg", defaultValue = "1000") String nMsg) {
        long nMessages = Integer.parseInt(nMsg);

        LinkedList<Message> history = new LinkedList<Message>();
        long totalMsg = messageRepo.count();
        if (nMessages > totalMsg) {
            nMessages = totalMsg;
        }

        // Get data with pagination
        PageRequest pageinfo = PageRequest.of(0, (int) nMessages, Sort.by("id").descending());
        Page<Message> dataPage = messageRepo.findAll(pageinfo);

        dataPage.forEach(msg -> history.addFirst(msg));

        return history.toArray();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public String POSTMessage(@RequestBody String body) {
        JSONObject x = new JSONObject(body);
        JSONObject usr = new JSONObject(x.get("user").toString());
        JSONObject msg = new JSONObject(x.get("message").toString());

        // Message newMsg = new Message(new User(usr.get("username").toString(),
        // usr.get("avatar").toString()),
        // msg.get("content").toString(),new Date(msg.get("timeStamp").toString()));

        // boolean result = DBController.dbMessage.add(newMsg);

        // return result ? "SUCCESS" : "FAILURE";
        // FIXME: Uncomment the above block
        return "";
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

        int nUser = DBController.dbUser.size();
        for (int i = 0; i < nUser; i++) {
            if (DBController.dbUser.get(i).getUsername().equalsIgnoreCase(usr.getString("username"))) {
                return "USER EXISTS";
            }
        }

        User newUser = new User(usr.getString("username"), usr.getString("avatar"));

        boolean result = DBController.dbUser.add(newUser);

        return result ? "SUCCESS" : "FAILURE";
    }
}
