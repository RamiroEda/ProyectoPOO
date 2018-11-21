import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Manzanas {
    Juego juego;
    public int X,Y;
    public Manzanas(Juego juego){
        this.juego = juego;
        setXY();
    }

    private int getX(){
        Random rand = new Random(System.currentTimeMillis());
        Dificultad dif = juego.getDificultad();
        X= (int) (Math.random()* dif.getColumnas());
        return X;
    }
    private int getY(){
        Random rand = new Random(System.currentTimeMillis());
        Dificultad dif = juego.getDificultad();
        Y= (int) (Math.random()* dif.getFilas());
        return Y;
    }
    private void setXY(){
        getX();
        getY();

    }

    public void Imagen(Graphics g, int gridWidth, int gridHeight){

        ImageIcon Img = new ImageIcon(getClass().getResource("manzanota.png"));
        g.drawImage(Img.getImage(),
                (((juego.getWidth())/(juego.getDificultad().getColumnas()))*X),
                (((juego.getHeight())/(juego.getDificultad().getFilas()))*Y) ,
                gridWidth,
                gridHeight,
                null);
        //Log.d("x", (((juego.getWidth())/(juego.getDificultad().getColumnas()))*X));
        //Log.d("y", (((juego.getHeight())/(juego.getDificultad().getFilas()))*Y));
        Log.d("X", X);
        Log.d("Y", Y);
    }
}
