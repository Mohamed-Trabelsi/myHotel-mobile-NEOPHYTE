/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.entities.Agences;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceAgences {
     public ArrayList<Agences> evenements;
    public static ServiceAgences instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;

    public ServiceAgences() {
         req = new ConnectionRequest();
    }

    public static ServiceAgences getInstance() {
        if (instance == null) {
            instance = new ServiceAgences();
        }
        return instance;
    }
    
    
    
    public boolean AddAgences(Agences t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL+"/ajoutA?nom="+t.getNom()+"&num="+t.getNum()+"&email="+t.getEmail();
       req.setUrl(url);
       req.addArgument("nom", t.getNom());
       req.addArgument("num", t.getNum()+"");
       req.addArgument("email", t.getEmail());
       
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
    
    
    
    
    
    public ArrayList<Agences> parseEvenements(String jsonText){
           try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Agences t = new Agences();
                   float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float num= Float.parseFloat(obj.get("num").toString());
                t.setNum((int)num);
                   t.setNom(obj.get("nom").toString());
                t.setEmail(obj.get("email").toString());          
                evenements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return evenements;
    }
    
    
    
    
    public ArrayList<Agences> getAllEvenementssP(){
String url = Statics.BASE_URL +"/dis";
    req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }
    public boolean deleteEvent(int id)
    {
        String url = Statics.BASE_URL +"/deleteaa?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                req.removeResponseCodeListener(this);
            }    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    

    
    public boolean updateAgence (Agences t, int id)
   {
       String url = Statics.BASE_URL+"/modifiera/"+id+"?nom="+t.getNom()+"&num="+t.getNum()+"&email="+t.getEmail();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                resultOK = req.getResponseCode()==200;
                req.removeResponseCodeListener(this);
            }    
        }); 
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
   }
}
