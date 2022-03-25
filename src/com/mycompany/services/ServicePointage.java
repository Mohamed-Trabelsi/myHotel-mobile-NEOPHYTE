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
import com.codename1.ui.events.ActionListener;
import com.company.utils.Statics;
import com.mycompany.entities.Pointage;
import com.mycompany.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class ServicePointage {

    public ArrayList<Pointage> Abs;
    public static ServicePointage instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePointage() {
        req = new ConnectionRequest();
    }

    public static ServicePointage getInstance() {
        if (instance == null) {
            instance = new ServicePointage();
        }
        return instance;
    }

    public boolean addAbs(Pointage p) {
        System.out.println(p);
        System.out.println("********");

        String url = Statics.BASE_URL + "/AbsMobileAdd?user_id=" + p.getId_user()
                + "&dateDepart=" + p.getDateDepart()
                + "&duree=" + p.getDuree()
                + "&typePtg=" + p.getTypePtg();

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

   public ArrayList<Pointage> ParseAbs(String jsonText){
        try {
            Abs=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> categListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)categListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
               Pointage p = new Pointage();
             
                float duree = Float.parseFloat(obj.get("duree").toString());
                p.setUserNom((String) obj.get("userNom"));
                p.setUserPrenom((String) obj.get("userPrenom"));
                
                    p.setTypePtg(obj.get("typePtg").toString());
                   
                p.setDuree((int)duree);
               
              Abs.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
          return Abs;
    }

public ArrayList<Pointage> getAllAbs() {

        String url = Statics.BASE_URL+"/displayAbs";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
        public void actionPerformed(NetworkEvent evt) {
                Abs = ParseAbs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Abs;
    }

    public boolean deleteAbs(int idPtg) {
        String url = Statics.BASE_URL + "/AbsMobileDelete?idPtg=" + idPtg;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
        public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateAbs(Pointage p, int id) {
        String url = Statics.BASE_URL + "/userMobileUpdate/" + id + "?dateDepart=" + p.getDateDepart()
                + "&typePtg=" + p.getTypePtg()
                + "&duree=" + p.getDuree();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
        public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public int nbservice(User u){
        ServicePointage eventService = new ServicePointage();
        ArrayList<Pointage> listEvent = eventService.getAllAbs();

        Iterator<Pointage> it = listEvent.iterator();
        int s = 0;
        int nb = 0;
        while (it.hasNext()) {
            Pointage ens = it.next();
            if (u.getNom().equals(ens.getId_user())) {
                nb++;
                System.out.println("nb:" + nb);

            }
        }
        return nb;
    }

}
