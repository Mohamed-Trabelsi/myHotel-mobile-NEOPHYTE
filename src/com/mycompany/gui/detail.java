/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.location.Location;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.categ;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceCateg;
import com.mycompany.services.ServiceServ;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;




/**
 *
 * @author user
 */
public class detail {
Form f = new Form();
    public ArrayList<service> categs;
  MapContainer cnt = null;
        private static final String HTML_API_KEY = "AIzaSyDcwHdDoI65jU6iHlOGv54Efo67fE_AWw0";
public detail() {
             //private Resources theme = UIManager.initFirstTheme("/theme");
   
    try{
        cnt = new MapContainer("AIzaSyCy-fMWerzvXcPCV0FDI07hW2DAzs_mnpY");
    }catch(Exception ex) {
        ex.printStackTrace();
    }

        Button btnMoveCamera = new Button("My Hotel");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(36.8189700, 10.1657900));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        
    


        cnt.addTapListener(e->{
    
            
                cnt.clearMapLayers();
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        ""+cnt.getCameraPosition().toString(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+cnt.getName(), FontImage.MATERIAL_PLACE);
                        }
                );
             ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://maps.google.com/maps/api/geocode/json?latlng="+cnt.getCameraPosition().getLatitude()+","+cnt.getCameraPosition().getLongitude()+"&oe=utf8&sensor=false");
                     NetworkManager.getInstance().addToQueueAndWait(r);

            JSONParser jsonp = new JSONParser();
            java.util.Map<String, Object> tasks = null;
        try {
            tasks = jsonp.parseJSON(new CharArrayReader(new String(r.getResponseData()).toCharArray()));
        } catch (IOException ex) {
           // Logger.getLogger(detail.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("roooooot:" +tasks.get("results"));
            List<java.util.Map<String, Object>> list1 = (List<java.util.Map<String, Object>>)tasks.get("results");


            
            
        });
      /*   categs = ServiceServ.getInstance().getAllCategs();
            for (service obj : categs) {
         cnt.zoom(getCoords(obj.getId_categ()), 18);
            
                cnt.setCameraPosition(getCoords(obj.getId_categ())); // since the image is iin the jar this is unlikely
                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), getCoords(obj.getId_categ()), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                    }
                
                });*/
        Container root = new Container();
         f.setLayout(new BorderLayout());
         f.addComponent(BorderLayout.CENTER, cnt);
         f.addComponent(BorderLayout.SOUTH, btnMoveCamera);
f.show();
}}
/*  public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }*/

