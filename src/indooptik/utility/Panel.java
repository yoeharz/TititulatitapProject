/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package indooptik.utility;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Yoeda Hari Poernomo
 */
public class Panel extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        Shape shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

        Paint p = new GradientPaint(getWidth()/2, 0, Color.BLUE, getWidth()/3, getHeight()*2, Color.WHITE);
        g2.setPaint(p);
        g2.fill(shape);
        g2.dispose();
    }

}
