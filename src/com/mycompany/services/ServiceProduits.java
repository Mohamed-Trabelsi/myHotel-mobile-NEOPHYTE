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
import com.codename1.ui.Button;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.entities.Produits;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceProduits {
     public ArrayList<Produits> evenements;
    public static ServiceProduits instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;

    private ServiceProduits() {
         req = new ConnectionRequest();
    }

    public static ServiceProduits getInstance() {
        if (instance == null) {
            instance = new ServiceProduits();
        }
        return instance;
    }
    
    
    
    public boolean AddProduits(Produits t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL+"/ajoutP?libelle="+t.getLibelle()+"&quantite="+t.getQuantite()+"&type="+t.getType();
       req.setUrl(url);
       req.addArgument("libelle", t.getLibelle());
       req.addArgument("quantite", t.getQuantite()+"");
       req.addArgument("type", t.getType());
       
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
    
    
    
    
    
    public ArrayList<Produits> parseEvenements(String jsonText){
           try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Produits t = new Produits();
                   float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float quantite= Float.parseFloat(obj.get("quantite").toString());
                t.setQuantite((int)quantite);
                   t.setLibelle(obj.get("libelle").toString());
                t.setType(obj.get("type").toString());          
                evenements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return evenements;
    }
    
    
    
    
    public ArrayList<Produits> getAllEvenementssP(){
String url = Statics.BASE_URL +"/displayp";
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
        String url = Statics.BASE_URL +"/delete?id="+id;
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
    
    
    
    
    public boolean updateProduit (Produits t, int id)
   {
       String url = Statics.BASE_URL+"/modifierP?id="+t.getId()+"&libelle="+t.getLibelle()+"&type="+t.getType()+"&quantite="+t.getQuantite();
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
