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
import com.mycompany.entities.Fournisseurs;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public class ServiceFournisseurs {
     public ArrayList<Fournisseurs> evenements;
    public static ServiceFournisseurs instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;

    
    
    public ServiceFournisseurs() {
         
    }

    public static ServiceFournisseurs getInstance() {
        if (instance == null) {
            instance = new ServiceFournisseurs();
        }
        return instance;
    }
    
    
    
    public boolean AddFournisseur(Fournisseurs t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL+"/ajoutF?nom="+t.getNom()+"&quantite="+t.getNum()+"&type="+t.getAdresse();
       req.setUrl(url);
       req.addArgument("nom", t.getNom());
       req.addArgument("num", t.getNum()+"");
       req.addArgument("adresse", t.getAdresse());
       
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
    
    
    
    
    
    public ArrayList<Fournisseurs> parseEvenements(String jsonText){
           try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Fournisseurs t = new Fournisseurs();
                   float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float quantite= Float.parseFloat(obj.get("num").toString());
                t.setNum((int)quantite);
                   t.setNom(obj.get("nom").toString());
                t.setAdresse(obj.get("adresse").toString());          
                evenements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return evenements;
    }
    
    
    
    
    public ArrayList<Fournisseurs> getAllEvenementssP(){
String url = Statics.BASE_URL +"/displayf";
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
        String url = Statics.BASE_URL +"/deleto?id="+id;
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
    
    
    
    
    public boolean updateFournisseur (Fournisseurs t, int id)
   {
       String url = Statics.BASE_URL+"/modifierF?id="+t.getId()+"&nom="+t.getNom()+"&adresse="+t.getAdresse()+"&num="+t.getNum();
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
