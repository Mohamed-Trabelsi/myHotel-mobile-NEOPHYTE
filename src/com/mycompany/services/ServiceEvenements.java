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
import com.mycompany.myapp.entities.Evenements;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author trabelssi
 */
public class ServiceEvenements {
  public ArrayList<Evenements> evenements;
    public static ServiceEvenements instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;
    public ArrayList<Evenements> getAllEvenementssP;

    
    private ServiceEvenements() {
         req = new ConnectionRequest();
    }

    public static ServiceEvenements getInstance() {
        if (instance == null) {
            instance = new ServiceEvenements();
        }
        return instance;
    }
    public boolean AddEvenements(Evenements t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL + "/addEventMob?libelleE="+t.getLibelleE()+"&dateDE=" 
               + t.getDateDE()+"&dateFE=" + t.getDateFE() +"&descriptionE=" + t.getDescriptionE()+ "&espaceE="+ t.getEspaceE() + "&capaciteE=" + t.getCapaciteE();
       req.setUrl(url);
       req.addArgument("Libelle", t.getLibelleE());
       req.addArgument("datedebut", t.getDateDE());
       req.addArgument("datefin", t.getDateFE());
       req.addArgument("description", t.getDescriptionE());
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
    }
    
    /*public ArrayList<Evenements> parseEvenements(String jsonText){
           try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenements t = new Evenements();
                float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float etatEv= Float.parseFloat(obj.get("EtatEv").toString());
                t.setEtatEv((int)etatEv);
                t.setLibelleE(obj.get("libelleE").toString());
                t.setDateDE(obj.get("dateDE").toString());
                t.setDateFE(obj.get("dateFE").toString());
                t.setDescriptionE(obj.get("descriptionE").toString());
                t.setEspaceE(obj.get("espaceE").toString());  
                float capaciteE= Float.parseFloat(obj.get("capaciteE").toString());
                t.setCapaciteE((int)capaciteE);
               
                evenements.add(t);
                System.out.println(t.getEtatEv());
            }  
        } catch (IOException ex) {
            
        }
        return evenements;
    }
    
    
    public ArrayList<Evenements> getAllEvenementssP(){
String url = Statics.BASE_URL +"/affichemobilEvent";
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
    }*/
    
    public boolean deleteEvent(int id)
    {
        String url = Statics.BASE_URL +"/deleteEventMobile?id="+id;
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
    
        
     public ArrayList<Evenements> getAllEvenementssP() {
        req.setUrl(Statics.BASE_URL + "/affichemobEvent");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                   evenements = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Evenements e;
                        e = new Evenements(
                               (int) Float.parseFloat(obj.get("id").toString()),
                                (int) Float.parseFloat(obj.get("etatEv").toString()),
                                (String) obj.get("libelleE"),
                                (String) obj.get("dateDE"),
                                (String) obj.get("dateFE"),
                                (String) obj.get("descriptionE"),
                                (String) obj.get("espaceE"),
                                (int) Float.parseFloat(obj.get("capaciteE").toString())                                                                
                                
                        );
                        
                        evenements.add(e);
                    }

                } catch (IOException ex) {
                    System.out.println("montage vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return evenements;
    }
    
     public boolean validerEvent(int id)
    {
        String url = Statics.BASE_URL +"/UpdateEtatEv/"+id;
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
    
   public boolean updateEvent (Evenements t, int id)
   {
       String url = Statics.BASE_URL + "/updateEventMobile/"+id+"?libelleE="+t.getLibelleE()+"&descriptionE=" + t.getDescriptionE()+"&dateDE=" + t.getDateDE()
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
   }
   
   public Evenements detailEvenement(int id, Evenements e)
   {
       String url = Statics.BASE_URL+ "/detailEvent/"+id;
       req.setUrl(url);
       String str  = new String (req.getResponseData());
       req.addResponseListener(((evt) -> {
           JSONParser j= new JSONParser();
           try
           {
               Map<String,Object> obj = j.parseJSON(new CharArrayReader(new String(str).toCharArray()));
              e.setLibelleE(obj.get("libelleE").toString());   
              e.setDateDE(obj.get("dateDE").toString());
              e.setDateFE(obj.get("dateFE").toString());
              e.setDescriptionE(obj.get("descriptionE").toString());
              e.setEspaceE(obj.get("espaceE").toString());
              e.setCapaciteE((int) Float.parseFloat(obj.get("capaciteE").toString()));
           }catch (IOException ex)
           {
               System.out.println("error related to sql :("+ex.getMessage());
           }
           System.out.println("data=="+str);
       }));
       NetworkManager.getInstance().addToQueueAndWait(req);
       return e;
   }
}