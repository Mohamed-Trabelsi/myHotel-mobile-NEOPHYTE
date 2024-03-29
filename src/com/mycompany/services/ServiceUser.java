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
    User gUser = new User();

    public ArrayList<User> users;
    public static ServiceUser instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    public ServiceUser() {
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
                + "&Genre=" + u.getGenre() + "&password=" + u.getPassword();
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
 public User parseUserJson(String json) {
        User u = null;
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            if (!user.isEmpty()) {
                u = new User();
                float id = Float.parseFloat(user.get("idUser").toString());
                u.setId_user((int) id);
                
                float age = Float.parseFloat(user.get("age").toString());
                u.setAge((int) age);
                
                 float tel = Float.parseFloat(user.get("telUser").toString());
                    u.setTel_user((int) tel);
                    
                     float cin = Float.parseFloat(user.get("cin").toString());
                    u.setCin((int) cin);
                    
                     float etat = Float.parseFloat(user.get("etat").toString());
                    u.setEtat((int) etat);
               
               
                u.setNom(user.get("nom").toString());
                u.setPrenom(user.get("prenom").toString());
                u.setEmail_user(user.get("emailUser").toString());
                u.setRole(user.get("role").toString());
                u.setPassword(user.get("password").toString());
                u.setGenre(user.get("Genre").toString());
                   
                
                
            }
        } catch (IOException ex) {
        }
        return u;
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
   
    
    
     public User login(String emailUser, String password) {
        
        String url = Statics.BASE_URL+"/userMobileLogin?emailUser="+emailUser+"&password="+password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                String d = new String(req.getResponseData());
                System.out.println("data"+d);
                gUser = ser.parseUserJson(new String(req.getResponseData()));
                System.out.println("vbvvvvvvvvvvvv " + gUser);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return gUser;
    }
     


    public User getUserParId(int id) {
     
        String url = Statics.BASE_URL+"/userMobilegetByID/"+id;
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                gUser = ser.parseUserJson(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return gUser;
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
    public ArrayList<User> getNUsers() {
        String url = Statics.BASE_URL + "/userNMobileShow";
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
    public void Confirmer (int id) {
        
        String url = Statics.BASE_URL+"/userMobileConfirmer?idUser=" + id;
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            req.removeResponseCodeListener(this);
           
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    public void Ban (int id) {
        
        String url = Statics.BASE_URL+"/userMobileBan?idUser=" + id;
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            req.removeResponseCodeListener(this);
           
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        
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

    public boolean updateUser (User u,int id)
   {
       String url = Statics.BASE_URL + "/userMobileUpdate/"+id+ "?nom=" + u.getNom()+ "&prenom=" + u.getPrenom()
                + "&age=" + u.getAge() + "&cin=" + u.getCin() + "&telUser=" + u.getTel_user()
                + "&emailUser=" + u.getEmail_user() + "&role=" + u.getRole()
                + "&Genre=" + u.getGenre();
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
