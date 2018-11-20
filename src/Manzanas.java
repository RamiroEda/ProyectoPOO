import java.util.Random;

public class Manzanas {
    Juego juego = new Juego();
    public int X,Y;
    public Manzanas(){

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
    private setXY(int x, int y){
        this.X=x;
        this.Y=y;
    }

}
