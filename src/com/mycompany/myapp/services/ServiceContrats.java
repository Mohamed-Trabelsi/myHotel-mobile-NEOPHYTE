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
import com.mycompany.entities.Contrats;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author asus
 */
public class ServiceContrats {
    public ArrayList<Contrats> evenements;
    public static ServiceContrats instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;

    private ServiceContrats() {
         req = new ConnectionRequest();
    }

    public static ServiceContrats getInstance() {
        if (instance == null) {
            instance = new ServiceContrats();
        }
        return instance;
    }
    
    
     public boolean AddEvenements(Contrats t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL + "/ajoutC?titre="+t.getTitre()+"&contenu=" + t.getContenu()+"&dated=" + t.getDated()
               +"&datef=" + t.getDatef() + "&prix=" + t.getPrix();
       req.setUrl(url);
       req.addArgument("Libelle", t.getTitre());
       req.addArgument("description", t.getContenu());
       req.addArgument("datedebut", t.getDated());
       req.addArgument("datefin", t.getDatef());
       req.addArgument("prix", t.getPrix()+"");
       
        
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
     
     
     
      public ArrayList<Contrats> parseEvenements(String jsonText){
           try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Contrats t = new Contrats();
                   float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float prix= Float.parseFloat(obj.get("prix").toString());
                t.setPrix((int)prix);
                   t.setTitre(obj.get("titre").toString());
                t.setDated(obj.get("dated").toString());
                t.setDatef(obj.get("datef").toString());
                t.setContenu(obj.get("contenu").toString());
                         
                evenements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return evenements;
    }
      public ArrayList<Contrats> getAllEvenementssP(){
String url = Statics.BASE_URL +"/disp";
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
        String url = Statics.BASE_URL +"/deletecc?id="+id;
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
     public boolean validercontrat (int id)
   {
       
        String url = Statics.BASE_URL +"/validercc?id="+id;
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
}
