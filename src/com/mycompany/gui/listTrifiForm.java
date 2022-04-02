/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;


import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceServ;

/**
 *
 * @author wiem
 */
public class listTrifiForm extends Form {

    private Resources theme;
    Button btnFB;
  
 Form current;
    public listTrifiForm(Form previous) {
        theme = UIManager.initFirstTheme("/theme_1");
      
        ArrayList<service> services = new ArrayList<>();
        ServiceServ ce = new    ServiceServ();
        services.addAll(   ServiceServ.getInstance().getAllCategs());
        
        Style s = UIManager.getInstance().getComponentStyle("Title");
        TextField searchField = new TextField("", "recherche des services");
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> {
            String t = searchField.getText();
            if (t.length() < 1) {
               removeAll();
                for (service service : services) {
                    this.addItem(service);
                }
                for (Component cmp : previous.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }

            } else {
                removeAll();
                for (service service : services) {
                    if ((service.getId_categ().toLowerCase().contains(t.toLowerCase())) || (service.getDb().toLowerCase().contains(t.toLowerCase()))
                            || (service.getDf().toLowerCase().contains(t.toLowerCase())) ) {
                        this.addItem(service);
                    }
                }
            }
            getContentPane().animateLayout(250);
        });
        getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync();
        });

        ServiceServ rs = new    ServiceServ();
        if (!rs.getAllCategs().isEmpty()) {
               ServiceServ SP = new    ServiceServ();
            ArrayList<service> lis = SP.getAllCategs();
            for (service li : lis) {
                Container cc = new Container(BoxLayout.x());
                Container c = new Container(BoxLayout.y());
      
                Label categ = new Label("catégorie : " + li.getId_categ());
                Label db = new Label("date début : " + li.getDb());
                Label df = new Label("date fin : " + li.getDf());
     
                Slider sl = new Slider();
                sl = createStarRankSlider();
            
          
         
                c.add(categ);
                c.add(db);
             
                c.add(df);
             ;
           
                Button service1 = new Button("score");
              
              
                add(c);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }
        } else {
           add(new Label("Aucun service a afficher"));

        }
     
   getToolbar().addCommandToLeftBar("<-", null, (ev) -> {
            HomeServBack h;
            h = new HomeServBack();
            h.show();
        });
    }

 


    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    public Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(6);
        starRank.setProgress(2);
        Font fnt;
        fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(5);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    public void addItem(service a) {
        Container cc = new Container(BoxLayout.x());
        Container c = new Container(BoxLayout.y());
   
        Label categ = new Label("catégorie : " + a.getId_categ());
        Label db = new Label("Date début : " + a.getDb());
        Label df = new Label("Date fin : " + a.getDf());
   
        Slider sl = new Slider();
        sl = createStarRankSlider();
 
      
        c.add(categ);
        c.add(db);
     
        c.add(df);
        
        
        add(c);
       refreshTheme();
    }

}
