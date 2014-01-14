package com.mycompany.petulant.octo.wight;

import java.io.IOException;

import org.jsoup.nodes.Element;
import java.awt.Frame;
import org.jsoup.Jsoup;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RURabelais {

    private final JFrame display;
    private final int width;
    private final int height;

    /**
     *
     */
    public RURabelais() {
        display = new JFrame();
        display.setResizable(false);
        display.setBounds(
                new Rectangle(
                        0,
                        0,
                        this.width = 300,
                        this.height = 400
                )
        );
        
        try {
            String menu = "<html>";

            Document doc = Jsoup.connect("http://www.crous-poitiers.fr/fiche.php?id_structure=r_17").get();

            System.out.println("\033[1m[" + doc.select("#date").get(0).text() + "]\033[0m");

            Elements plats = doc.select("#midi .listePlats");
            String[] partName = new String[]{"Entrées", "Plats", "Desserts"};
            int i = 0;
            for (Element part : plats) {
                menu += "<h4>" + partName[i] + "</h4></br>";

                for (Element elem : part.select("li")) {
                    menu += "   " + elem.text()+"<br/>";
                }

                i++;
            }
            menu += "</html>";
  
            display.add(new JLabel(menu));
        } catch (IOException e) {
            System.err.println("Unable to connect to crous-poitiers");
        }
        display.setVisible(true);
    }

}
