package graphicsModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;
import semafor.ColorEnum;
import static semafor.ColorEnum.TGreenYellowRed;

public class GraphicsModel extends Observable {

    Color green = Color.green;
    Color red = Color.red;
    Color yellow = Color.yellow;
    ColorEnum colorEnum;
    RectangularShape oneShape = new Ellipse2D.Double();
    RectangularShape shape[] = new RectangularShape[3];

    public GraphicsModel() {
        Point2D loc = new Point2D.Double();
        colorEnum = TGreenYellowRed;
        //oneShape.setFrame(loc, size);
    }

    public void paint(Graphics g) {
        //   this.setBackground(Color.black );
        //  super.paintComponent(g);
        if (colorEnum != null) {
            g.setColor(Color.red);
            int x = -88, y = -88;
            if (colorEnum.green) {
                g.fillOval(165 + x, 100 + y, -2 * x, -2 * y);
            } else {
                g.drawOval(165 + x, 100 + y, -2 * x, -2 * y);
            }
            g.setColor(Color.yellow);
            if (colorEnum.yellow) {
                g.fillOval(165 + x, 285 + y, -2 * x, -2 * y);
            } else {
                g.drawOval(165 + x, 285 + y, -2 * x, -2 * y);
            }
            g.setColor(Color.green);
            if (colorEnum.red) {
                g.fillOval(165 + x, 470 + y, -2 * x, -2 * y);
            } else {
                g.drawOval(165 + x, 470 + y, -2 * x, -2 * y);
            }
        }
    }

    public void setColor(ColorEnum c) {
        colorEnum = c;
        setChanged();
        notifyObservers();
    }
}
