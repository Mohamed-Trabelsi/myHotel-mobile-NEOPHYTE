/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author hurb
 */
public class imagea {
    
    Form f = new Form("hi",BoxLayout.y());
    public imagea() {
        
    Button btCapture = new Button("caputre");
    Label lbltImage = new Label ();
    f.add(btCapture);
    f.add(lbltImage);
    
    btCapture.addActionListener( e->{
   String path= Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
    if (path!=null)
    {
    try
    {
        Image img = Image.createImage(path);
    lbltImage.setIcon(img);
    f.revalidate();
        }catch(IOException ex){}
    
    
    
    }
}
    );
    f.show();
            }







}
           
