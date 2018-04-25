/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author JuanLH
 */
public class Utilidades {
    
    public static Point getScreenCenter(JFrame form){
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = form.getSize().width;
        int h = form.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        
        return new Point(x, y);
    }
    
    public static Point getScreenCenter(JDialog form){
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = form.getSize().width;
        int h = form.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        
        return new Point(x, y);
    }
}
