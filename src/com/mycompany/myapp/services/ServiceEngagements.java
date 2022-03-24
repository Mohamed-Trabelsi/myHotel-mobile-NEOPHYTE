/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Engagements;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trabelssi
 */
public class ServiceEngagements {
    
     public ArrayList<Engagements> engagements;
    public static ServiceEngagements instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;

    private ServiceEngagements() {
         req = new ConnectionRequest();
    }

    public static ServiceEngagements getInstance() {
        if (instance == null) {
            instance = new ServiceEngagements();
        }
        return instance;
    }
    
    
    public boolean AddEngagements(Engagements t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL + "/addEnMobile?event="+t.getEvent()+"&sponsor=" + t.getSponsor();
               
       req.setUrl(url);

       req.addArgument("evenement", t.getEvent()+"");
       req.addArgument("sponsor", t.getSponsor()+"");
        
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

    
    
    public ArrayList<Engagements> parseEvenements(String jsonText){
           try {
            engagements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> engagementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)engagementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Engagements t = new Engagements();
                
                float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 /*float event= Float.parseFloat(obj.get("Evenements").toString());
                t.setId((int)event);
                 float sponsor= Float.parseFloat(obj.get("Sponsors").toString());
                t.setId((int)sponsor);*/
                 t.setE(obj.get("Evenements").toString());
                 t.setS(obj.get("Sponsors").toString());
                 
                engagements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return engagements;
    }
    
    public ArrayList<Engagements> getEngagement(){
String url = Statics.BASE_URL +"/displayEn";
    req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                engagements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return engagements;
    }
    
    public boolean deleteEngagement(int id)
    {
        String url = Statics.BASE_URL +"/deleteEnMobile?id="+id;
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
    
   /*public boolean updateSponsor (Evenements t)
   {
       String url = Statics.BASE_URL + "/updateEventMobile?id="+t.getId()+"&libelleE="+t.getLibelleE()+"&descriptionE=" + t.getDescriptionE()+"&dateDE=" + t.getDateDE()
               +"&dateFE=" + t.getDateFE() + "&espaceE="+ t.getEspaceE() + "&capaciteE=" + t.getCapaciteE();
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
   }*/
}
