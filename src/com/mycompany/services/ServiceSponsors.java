/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Sponsors;
import com.company.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trabelssi
 */
public class ServiceSponsors {
    public ArrayList<Sponsors> sponsors;
    public static ServiceSponsors instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;
    public ArrayList<Sponsors> getAllEvenementssP;

    private ServiceSponsors() {
         req = new ConnectionRequest();
    }

    public static ServiceSponsors getInstance() {
        if (instance == null) {
            instance = new ServiceSponsors();
        }
        return instance;
    }
    public boolean AddSponsor(Sponsors s){
       
  System.out.println(s);
        System.out.println("********");
        
       //"/addSponsorMobile?nomS="+s.getNomS()+"&adresseS="+s.getAdresseS()+"&telS=+s.getTelS();
       String url = Statics.BASE_URL + "/addSponsorMobile?nomS="+s.getNomS()+"&adresseS=" + s.getAdresseS()+"&telS=" + s.getTelS();
               
       req.setUrl(url);
       req.addArgument("Nom", s.getNomS());
       req.addArgument("Adresse", s.getAdresseS());
       req.addArgument("numero", s.getTelS()+"");           
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
    
    public ArrayList<Sponsors> parseSponsors(String jsonText){
           try {
            sponsors=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> sponsorsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)sponsorsListJson.get("root");
            for(Map<String,Object> obj : list){
                Sponsors t = new Sponsors();
                   float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float telS= Float.parseFloat(obj.get("telS").toString());
                t.setTelS((int)telS);
                   t.setNomS(obj.get("nomS").toString());
                t.setAdresseS(obj.get("adresseS").toString());            
                sponsors.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return sponsors;
    }
    
    public ArrayList<Sponsors> getAllSponsor(){
String url = Statics.BASE_URL +"/affichemobileSpons";
    req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sponsors = parseSponsors(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sponsors;
    }
    
   public boolean deleteSponsor(int id)
    {
        String url = Statics.BASE_URL +"/deleteSponsorMobile?id="+id;
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
   
   public boolean updateSponsor (Sponsors s, int id)
   {
       String url = Statics.BASE_URL + "/updateSponsorMobile/"+id+"?nomS="+s.getNomS()+"&adresseS=" + s.getAdresseS()+"&telS=" + s.getTelS();
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
