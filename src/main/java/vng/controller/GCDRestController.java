package vng.controller;

import vng.repository.MessageRepository;
import vng.repository.UserRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GCDRestController {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    UserRepository userRepo;

    // ================= TESTING PURPOSE
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/test")
    public Object testMapping(@RequestParam Integer x) {

        // ArrayList<Message> list = new ArrayList<Message>();

        // PageRequest pageInfo = PageRequest.of(1, 3);
        // Page<Message> page = messageRepo.findAll(pageInfo);

        // page.iterator().forEachRemaining(msg -> list.add(msg));

        // return list.toArray();

        if (x == 1) {

            Message msg = messageRepo.findById(16).get();
            return msg;
        } else {
            User usr = userRepo.findById(1).get();
            return usr;
        }

        // return msg;

    }
    // ================= / TESTING PURPOSE

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
    @PostMapping(value = "/chat")
    public String POSTMessage(@RequestBody String body) {
        JSONObject x = new JSONObject(body);
        JSONObject usr = new JSONObject(x.get("user").toString());
        JSONObject msg = new JSONObject(x.get("message").toString());

        // Message newMsg = new Message(new User(usr.get("username").toString(),
        // usr.get("avatar").toString()),
        // msg.get("content").toString(),new Date(msg.get("timeStamp").toString()));

        // boolean result = DBController.dbMessage.add(newMsg);

        String username = usr.get("username").toString();
        String content = msg.get("content").toString();
        User sender = userRepo.findByUsername(username);
        if (sender != null) {
            Message newMsg = new Message(sender, content);

            messageRepo.save(newMsg);
            return "1";
        }

        return "0";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ArrayList<User> GETUser() {
        ArrayList<User> userList = new ArrayList<User>();

        Iterable<User> it = userRepo.findAll();

        it.forEach(msg -> msg.setMessages(null));
        it.forEach(msg -> userList.add(msg));

        return userList;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String POSTUser(@RequestBody String body) {
        JSONObject usr = new JSONObject(body);

        User foundUser = userRepo.findByUsername(usr.getString("username"));
        if (foundUser != null) {
            return "USER EXISTS";
        }

        User newUser = new User(usr.getString("username"), usr.getString("avatar"));
        newUser.setPass("123");
        newUser.setActive(true);

        User savedUser = userRepo.save(newUser);

        return (savedUser != null) ? "1" : "0";
    }
}
