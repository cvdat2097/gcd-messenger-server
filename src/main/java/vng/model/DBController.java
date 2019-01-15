package vng.model;

import vng.entity.*;

import java.util.Date;
import java.util.ArrayList;

public class DBController {
    public static ArrayList<User> dbUser = new ArrayList<User>();
    public static ArrayList<Message> dbMessage = new ArrayList<Message>();

    public static void init() {
        // Create Users
        dbUser.add(new User("BarackObama","https://pickaface.net/gallery/avatar/zazasales53fe6493782a5.png"));
        dbUser.add(new User("StevebJobs","https://pickaface.net/gallery/avatar/12289886_170530_2201_2q1y3hu.png"));

        // // Create Messages
        // dbMessage.add(new Message(dbUser.get(0),"I saw you standing here, and I just had to come tell you you have the most striking sense of style I've seen all day. I'm Joe.",new Date("1-1-1990")));
        // dbMessage.add(new Message(dbUser.get(1),"Hi… I'm Tina.",new Date("1-1-1990")));
        // dbMessage.add(new Message(dbUser.get(0),"Hi Tina. How's your night going?",new Date("1-1-1990")));
        // dbMessage.add(new Message(dbUser.get(1),"Okay. How's your night going?",new Date("1-1-1990")));
        // dbMessage.add(new Message(dbUser.get(0),"t's going all right. So tell me, New York native or you come from somewhere far away?",new Date("1-1-1990")));
    }
}