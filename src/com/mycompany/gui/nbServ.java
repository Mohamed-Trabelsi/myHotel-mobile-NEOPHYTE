/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceServ;
import java.util.ArrayList;

/**
 *
 * @author wiem
 */
public class nbServ extends Form {
      public ArrayList<service> categs;
      Label level,kk;
    Form current;
     private Resources theme;
    public nbServ(Form previous) {
        theme = UIManager.initFirstTheme("theme");
          Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           Container c1 = new Container(new FlowLayout(Component.CENTER));
           ServiceServ ser = new ServiceServ();
        setTitle("nbre de catégorie par service"); 
        categs = ServiceServ.getInstance().getAllCategs();
        int k = ser.calculetoiles();
        
            System.out.println(k);
        if (k > 0 && k <= 4) {
            level = new Label("Nombre des étoiles :  2 étoiles");
        } else if (k > 4 && k <= 10) {
            level = new Label("Nombre des étoiles :  3 étoiles ");
         
        } else if (k > 10 && k <= 25) {
            level = new Label("Nombre des étoiles:  5 étoiles ");
        }
level.getStyle().setFgColor(0xb88273);
    c.add(FlowLayout.encloseCenter(level));
      
    add(c);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeServBack().show());
    }
}
