import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Manzanas {
    Juego juego = new Juego();
    public int X,Y;
    public Manzanas(){
        setXY();
    }

    private int getX(){
        Random rand = new Random(System.currentTimeMillis());
        Dificultad dif = juego.getDificultad();
        X= (int) (Math.random()* dif.getColumnas())+1;
        return X;
    }
    private int getY(){
        Random rand = new Random(System.currentTimeMillis());
        Dificultad dif = juego.getDificultad();
        Y= (int) (Math.random()* dif.getColumnas())+1;
        return Y;
    }
    private void setXY(){
        getX();
        getY();

    }

    public void Imagen(Graphics g){
        ImageIcon Img = new ImageIcon(getClass().getResource("manzanota.png"));
        g.drawImage(Img.getImage(), X, Y, 30, 30, null);
    }
}
