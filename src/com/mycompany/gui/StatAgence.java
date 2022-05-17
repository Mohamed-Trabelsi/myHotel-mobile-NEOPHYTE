/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author hurb
 */
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Button;

import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.util.Resources;
import com.mycompany.entities.Contrats;
import com.mycompany.services.ServiceContrats;
import java.util.ArrayList;
import java.util.Iterator;



/**
 *
 * @author Chédy
 */

public class StatAgence  {
  
   Form Stat;
    private Resources theme;
 
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);
        renderer.setLabelsColor(0x000000);
        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
        protected CategorySeries buildCategoryDataset(String title, double[] values) {
                CategorySeries series = new CategorySeries(title);
        ServiceContrats ss = new ServiceContrats();
        int k = 0;
             // ServiceServ eventService = new ServiceServ();
        // ArrayList<service> listEvent = eventService.getAllCategs();
       // Iterator<service> it = listEvent.iterator();
          ArrayList<Contrats> lescategories = ss.getAllEvenementssP();
              try{  
      ServiceContrats eventService=new ServiceContrats();       
        ArrayList<Contrats> listcateg = eventService.getAllEvenementssP();
 
Iterator<Contrats> it = listcateg.iterator();
while (it.hasNext()) {
  Contrats j=it.next();
 long nb =0;
  String nba =null;
    System.out.println(j.getId()+" id");
        for(Contrats ee : listcateg)
        {        
        String n = ee.getAgence();
        if(n.equals(j.getAgence()))
            nba=n;
        
             
        }
        
          nb++; 
           series.add(nba,nb);  
}
      }
      catch(Exception i)
      {
          i.printStackTrace();
      }
        return series;
 
    /*  int nb = 1;
          String nba =null;
          while (it.hasNext()) {
        for (categ temp : lescategories) {
              
            service ens = it.next();
            if (temp.getName().equals(ens.getId_categ())) {
                nb++;
                System.out.println("nb:" +nb);
    nba=ens.getId_categ();
            }
           
        }  } 
         */
        
        //  series.add(nba,nb); 
     //return series;
       
      }
     /*   for (double value : values) {
            series.add("service " + ++k, value);
        }
 return series;
        return series;*/
    
    

    public Form createPieChartForm() {
        // Generate the values
        double[] values = new double[]{14, 10, 4, 1, 12};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.MAGENTA ,ColorUtil.GRAY,ColorUtil.LTGRAY,ColorUtil.rgb(200, 10, 30),ColorUtil.rgb(100,250, 130)};
        DefaultRenderer renderer = buildCategoryRenderer(colors);

        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setGradientStop(0, ColorUtil.YELLOW);
        r.setGradientStop(0, ColorUtil.CYAN);
        r.setGradientStop(0, ColorUtil.MAGENTA);
  r.setGradientStart(0, ColorUtil.BLACK);
        r.setGradientStop(0, ColorUtil.GRAY);
        r.setGradientStop(0, ColorUtil.LTGRAY);
             r.setGradientStop(0, ColorUtil.rgb(200, 10, 30));
                    r.setGradientStop(0, ColorUtil.rgb(100,250, 130));
          
r.setDisplayBoundingPoints(true);
                    

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("service", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Stat = new Form("Statistique", new BorderLayout());
       
        Stat.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            homeAhmedBack h;
            h = new homeAhmedBack();
            h.show();
        });
        //Stat.getStyle().setBgColor(0x50d3ed);

        Stat.add(BorderLayout.CENTER, c);


        return Stat;

    }

    public void StatistiqueTest() {

        Stat = createPieChartForm();
    
        Stat.show();

    }

    public Form getF() {
        return Stat;
    }

    public void setF(Form f) {
        this.Stat = f;
    }

    public StatAgence() {
        StatistiqueTest();
    }
        
        
        
    }
        
       /* CategorySeries series = new CategorySeries(title);
        
        series.add("agence",prcntRec);
        series.add("contrat",prcntFeed);
        
        return series; */
    


