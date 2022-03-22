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
import com.codename1.ui.events.ActionListener;
import com.serv.entities.categ;
import com.serv.utils.Statics;
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
       String url = Statics.BASE_URL + "add?name="+t.getName();
    
       req.setUrl(url);
    

       req.addArgument("name", t.getName()+"");
    
       
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
       
      
        String url = Statics.BASE_URL + "display";
           
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
      
      
}
