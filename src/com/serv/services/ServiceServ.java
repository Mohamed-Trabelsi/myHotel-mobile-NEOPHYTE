/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serv.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.serv.entities.categ;
import com.serv.entities.service;
import com.serv.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 *
 * @author wiem
 */
public class ServiceServ {
     public ArrayList<service> Categs;
    
    public static ServiceServ  instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceServ () {
         req = new ConnectionRequest();
    }

    public static ServiceServ  getInstance() {
        if (instance == null) {
            instance = new ServiceServ();
        }
        return instance;
    }

    public boolean addCateg(service t) {
        System.out.println(t);
        System.out.println("********");
      
       String url = Statics.BASE_URL + "addserv?category_id=" + t.getId_categ()
                + "&dbserv=" + t.getDb()
                + "&dfserv=" + t.getDf() ;
    
       req.setUrl(url);
   

      
    
       
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

    public ArrayList<service> parseCategs(String jsonText){
        try {
            Categs=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               service t = new service();
             
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_categ((String) obj.get("categorie"));
                  t.setDb((String) obj.get("date ouverture"));
                    t.setDf( obj.get("date fermeture").toString());
                   
                t.setIdServ((int)id);
               
              Categs.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
          return Categs;
    }
     public ArrayList<service> getAllCategs(){
       
      
        String url = Statics.BASE_URL + "displayserv";
           
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
              String url = Statics.BASE_URL + "delete?id=" +id;
        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public boolean modifcateg(categ f,int id ) {
   
             String url = Statics.BASE_URL + "update/"+id+"?name="+f.getName();
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
       
           public int nbservice(categ c) {
        ServiceServ eventService = new  ServiceServ();
        ArrayList<service> listEvent = eventService.getAllCategs();
  
        Iterator<service> it = listEvent.iterator();
        int s = 0;
        int nb = 0;
        while (it.hasNext()) {
            service ens = it.next();
            if (c.getName().equals(ens.getId_categ())) {
                nb++;
                System.out.println("nb:" + nb);

            }
        }
        return nb;
    }
}
