/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author WiiWii
 */

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.entities.Reservation;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author WiiWii
 */
public class ReservationChambre {
    
    
    
    
     public ArrayList<Reservation> Reservations;
    
    public static ReservationChambre instance=null;
    public boolean resultOK = true;
    private ConnectionRequest req;
    
 public ArrayList<Reservation> getAllReservations;
    public ReservationChambre() {
         req = new ConnectionRequest();
        
        
    }

    public static ReservationChambre getInstance() {
        if (instance == null) {
            instance = new ReservationChambre();
        }
        return instance;
    }

    public boolean addReser(Reservation t) {
        System.out.println(t);
        System.out.println("********");
       String url;
         url =  Statics.BASE_URL + "/addResMobile?client="+t.getClient()+"&dateD=" 
               + t.getDateD()+"&dateF=" + t.getDateF() ;
         System.out.println(url);
       req.setUrl(url);
    

       req.addArgument("Client", t.getClient()+"");
          req.addArgument("datedebut", t.getDateD().toString());
             req.addArgument("datedebut", t.getDateF().toString());
              
    
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

    public ArrayList<Reservation> parseReservations(String jsonText){
        try {
            Reservations=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> ReservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)ReservationListJson.get("root");
            
            //Parcourir la liste des tâches Json
             for(Map<String,Object> obj : list)
             {
                //Création des tâches et récupération de leurs données
                Reservation t = new Reservation();
                float id = Float.parseFloat(obj.get("id").toString());
                String Client = obj.get("client").toString();
                t.setClient(Client);
                t.setId((int) id);
                                                try {  
                            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateD").toString());
                            t.setDateD(date1);
                            Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateF").toString());
                            t.setDateF(date2);

                        } catch (ParseException ex) {
                            System.out.println(ex);
                        }


                        
           
               
                  
                    
                
           
                Reservations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
          return Reservations;
    }
    
 public ArrayList<Reservation> getAllReservations(){
       
      
        String url = Statics.BASE_URL + "/reservation/mobile/affreserv";
           
        req.setUrl(url);
     req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reservations;
    }

    
      
          public boolean deleteRes(int id) {
        String url = Statics.BASE_URL + "/deleteRes?id=" + id;
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

     public boolean modifReservation(Reservation t ) {
    
             String url = Statics.BASE_URL + "/updateRes?id="+t.getId()+"&client="+t.getClient()+"&dateD=" + t.getDateD()
               +"&dateF=" + t.getDateF();
             System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed( NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     

      
  
}
