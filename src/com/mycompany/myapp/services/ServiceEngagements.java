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
   /* public boolean AddEvenements(Engagements t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL + "/addEventMobile?libelleE="+t.getLibelleE()+"&descriptionE=" + t.getDescriptionE()+"&dateDE=" + t.getDateDE()
               +"&dateFE=" + t.getDateFE() + "&espaceE="+ t.getEspaceE() + "&capaciteE=" + t.getCapaciteE();
       req.setUrl(url);
       req.addArgument("Libelle", t.getLibelleE());
       req.addArgument("description", t.getDescriptionE());
       req.addArgument("datedebut", t.getDateDE());
       req.addArgument("datefin", t.getDateFE());
       req.addArgument("espace", t.getEspaceE());
       req.addArgument("capacite", t.getCapaciteE()+"");
        
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/

    
    
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
                float event= Float.parseFloat(obj.get("event").toString());
                t.setEvent((int)event);
                           
                float spons= Float.parseFloat(obj.get("event").toString());
                t.setSponsor((int)spons);
                engagements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return engagements;
    }
    
    public ArrayList<Engagements> getAllEvenementssP(){
String url = Statics.BASE_URL +"/afficheEnEvent";
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
    
    public boolean deleteEngagementt(int id)
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
