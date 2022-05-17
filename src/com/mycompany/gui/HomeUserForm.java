/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.gui.Abs.showAbsForm;
import static com.mycompany.gui.HomeForm.Menu;
import com.mycompany.gui.User.detailUser;
import com.mycompany.gui.User.showUserForm;
import java.io.IOException;

/**
 *
 * @author dell
 */
public class HomeUserForm extends Form{

    public HomeUserForm(User u, Form precedent) {
 
        setTitle("Bonjour :" + u.getPrenom()+" "+ u.getNom());
        setLayout(BoxLayout.y());
        
         Toolbar tb = this.getToolbar();
         tb.addCommandToOverflowMenu("Déconnecter", null,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment se déconnecter ?", "oui", "non");
                if (yes) {
                    precedent.show();
                }
            }
        });
         //yetzed fl front
        /* getToolbar().addMaterialCommandToRightBar(" ", FontImage.MATERIAL_ACCOUNT_BOX, (evt1) -> {
            new detailUser(u,this).show();
        });
*/
        // back
        Button btnShowUser = new Button("Show Users");
        btnShowUser.addActionListener(e -> new showUserForm(this).show());
        Button btnShowAbs = new Button("Show Abs");
        btnShowAbs.addActionListener(e -> new showAbsForm(this).show());
        addAll(btnShowUser,btnShowAbs);
          Menu(this);
        
    }

    public static void Menu(Form f) {

  
        Resources res = null;
             f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des utilisateurs", " ".charAt(0),e -> new showUserForm(f).show());
               f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des pointages", " ".charAt(0),e -> new showAbsForm(f).show());
                 f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e ->new HomeEvenement().show() );
      
          f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new homeagence() .show());
         f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new homeAhmedBack().show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServBack() .show());
         f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique catégorie", " ".charAt(0),e -> new statistique().getF().show());
                     f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservations", " ".charAt(0),e -> new HomeResBack() .show());
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    }
       
    void addSideMenu(Resources res, Form f) {
        
        
        
    }
}
