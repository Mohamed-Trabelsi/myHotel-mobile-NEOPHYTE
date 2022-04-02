/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.categ;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wiem
 */
public class ServiceCateg {
      public ArrayList<categ> Categs;
    
    public static ServiceCateg instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCateg() {
         req = new ConnectionRequest();
    }

    public static ServiceCateg getInstance() {
        if (instance == null) {
            instance = new ServiceCateg();
        }
        return instance;
    }

    public boolean addCateg(categ t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics.BASE_URL + "/addcateg?name="+t.getName();
    
       req.setUrl(url);
    

      // req.addArgument("name", t.getName()+"");
    
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<categ> parseCategs(String jsonText){
        try {
            Categs=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               categ t = new categ();
              
                float id = Float.parseFloat(obj.get("id").toString());
                t.setName(obj.get("name").toString());
                t.setId((int)id);
           
              Categs.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
          return Categs;
    }
    
 public ArrayList<categ> getAllCategs(){
       
      
        String url = Statics.BASE_URL + "/displaycateg";
           
        req.setUrl(url);
     req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categs = parseCategs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Categs;
    }
    
      public void deleteCateg(int id) {
              String url = Statics.BASE_URL + "/deletecateg?id=" +id;
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public boolean modifcateg(categ f,int id ) {
    
             String url = Statics.BASE_URL + "/updatecateg/"+id+"?name="+f.getName();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
        public void Notification(){
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_beep-01a.mp3");
            // alert sound file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
    }
      
}
