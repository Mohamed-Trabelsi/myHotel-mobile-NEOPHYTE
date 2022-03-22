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
import com.mycompany.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class ServiceUser {

    public ArrayList<User> users;
    public static ServiceUser instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    private ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean AddUser(User u) {

        System.out.println(u);
        System.out.println("********");

        String url = Statics.BASE_URL + "/userMobileAdd?nom=" + u.getNom() + "&prenom=" + u.getPrenom()
                + "&age=" + u.getAge() + "&cin=" + u.getCin() + "&telUser=" + u.getTel_user()
                + "&emailUser=" + u.getEmail_user() + "&role=" + u.getRole()
                + "&genre=" + u.getGenre() + "&password=" + u.getPassword();
        req.setUrl(url);
        req.addArgument("nom", u.getNom());
        req.addArgument("prenom", u.getPrenom());
        req.addArgument("age", u.getAge() + "");
        req.addArgument("cin", u.getCin() + "");
        req.addArgument("telUser", u.getTel_user() + "");
        req.addArgument("emailUser", u.getEmail_user());
        req.addArgument("password", u.getPassword());
        req.addArgument("role", u.getRole());
        req.addArgument("genre", u.getGenre());

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

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> UsersListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) UsersListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                float idUser = Float.parseFloat(obj.get("idUser").toString());
                u.setId_user((int) idUser);

                float age = Float.parseFloat(obj.get("age").toString());
                u.setAge((int) age);

                float cin = Float.parseFloat(obj.get("cin").toString());
                u.setCin((int) cin);

                float tel_user = Float.parseFloat(obj.get("telUser").toString());
                u.setTel_user((int) tel_user);

                u.setPrenom(obj.get("prenom").toString());
                u.setEmail_user(obj.get("emailUser").toString());
                u.setPassword(obj.get("password").toString());
                u.setRole(obj.get("role").toString());
                u.setGenre(obj.get("Genre").toString());
                u.setNom(obj.get("nom").toString());
                users.add(u);
            }
        } catch (IOException ex) {

        }
        return users;
    }

    public ArrayList<User> getAllUsers() {
        String url = Statics.BASE_URL + "/userMobileShow";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public boolean deleteUser(int idUser) {
        String url = Statics.BASE_URL + "/userMobileDelete?idUser=" + idUser;
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

    /* public boolean updateSponsor (User u)
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
