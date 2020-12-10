package semafor;

import graphicsModel.GraphicsModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import static semafor.ColorEnum.GreenTYellowRed;
import static semafor.ColorEnum.GreenYellowRed;
import static semafor.ColorEnum.GreenYellowTRed;
import static semafor.ColorEnum.TGreenYellowRed;

public class StateSemaphor implements Runnable {

    ChangeColor green;
    ChangeColor red;
    ChangeColor y2; // 
    ChangeColor yellow;
    ChangeColor empty; // 
    ChangeColor state;
    ChangeColor oldState;
    GraphicsModel gm;
    ColorEnum colorEnum;
    boolean suspendFlag = false;
    int time;

    public StateSemaphor(GraphicsModel model) {
        green = new Green();
        red = new Red();
        yellow = new Yellow();
        empty = new Empty();
        
        state = green;
        oldState = green;
        time = 10;
        gm = model;
        colorEnum = ColorEnum.TGreenYellowRed;
        suspendFlag = false;
    }

    public ColorEnum print() {
        return colorEnum;
    }

    public void changeState() {
        state.changeColor();
        gm.setColor(colorEnum);
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            changeState();
            stop();
        }

    }

    private void stop() {
        try {
            Thread.sleep(200);
            synchronized (this) {
                while (suspendFlag) {
                    wait();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(StateSemaphor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void mysuspend() {
        suspendFlag = true;
    }

    public synchronized void myresume() {
        suspendFlag = false;
        notify();
    }

    public interface ChangeColor {

        void changeColor();
    }

    public class Green implements ChangeColor {

        @Override
        public void changeColor() {
            
            oldState = green;
            state = yellow;
            colorEnum = GreenTYellowRed;
            try {
                Thread.sleep(402);
            } catch (InterruptedException ex) {
                Logger.getLogger(Red.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public class Red implements ChangeColor {

        @Override
        public void changeColor() {
            if (oldState == yellow)
            {oldState = y2; state = empty; colorEnum = GreenYellowRed;}
            {   oldState = red;
            state = yellow;
            colorEnum = GreenTYellowRed;}
            try {
                Thread.sleep(402);
            } catch (InterruptedException ex) {
                Logger.getLogger(Red.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public class Empty implements ChangeColor{
        @Override
        public void changeColor(){
            if (y2 == oldState){
                oldState = empty;
                state = red;
                colorEnum = TGreenYellowRed;
                try {
                Thread.sleep(134);
                } catch (InterruptedException ex) {
                Logger.getLogger(Red.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }

    public class Yellow implements ChangeColor {

        @Override
        public void changeColor() {
            if (oldState == red) {
                state = green;
                oldState = yellow;
                colorEnum = TGreenYellowRed;
                
            } else {
                state = red;
                oldState = yellow;
                colorEnum = GreenYellowTRed;
            }
            try {
                Thread.sleep(402);
            } catch (InterruptedException ex) {
                Logger.getLogger(Yellow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
