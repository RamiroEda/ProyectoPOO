

//import ch.aplu.xboxcontroller.XboxController;
//import ch.aplu.xboxcontroller.XboxControllerAdapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/*
 * Copyright 2018 Ramiro Estrada García.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Juego extends JPanel
        implements ActionListener, KeyListener{

    private Dificultad dificultad = Dificultad.MEDIO; //Dificultad del juego; en Facil por defecto

    private int FRAME_RATE = this.dificultad.getVelocidad(); //Frame-rate

    //KeyCodes de las teclas de direcciones

    public static final int
            KEY_ARRIBA    = 38,
            KEY_IZQUIERDA = 37,
            KEY_ABAJO     = 40,
            KEY_DERECHA   = 39;

    public static final int
            KEY_W = 87,
            KEY_A = 65,
            KEY_S = 83,
            KEY_D = 68;

    public static final int //Codigos estaticos de las direcciones
            DIR_ARRIBA              = 1,
            DIR_ABAJO               = 2,
            DIR_DERECHA             = 3,
            DIR_IZQUIERDA           = 4,
            DIR_ABAJO_IZQUIERDA     = 5, //Sin implementar
            DIR_ABAJO_DERECHA       = 6, //Sin implementar
            DIR_ARRIBA_IZQUIERDA    = 7, //Sin implementar
            DIR_ARRIBA_DERECHA      = 8, //Sin implementar
            DIR_NULL                = 0;

    private Window window; //La ventana que abre el juego

    private Timer timer; //El timer actualiza el juego cada SEGUNDO/FRAMERATE

    private int lastDirection = KEY_DERECHA; //Aqui se guarda la ultima tecla presionada

    private boolean isPlaying = false; // Atributo para saber si esta el juego en Play

    private Scoreboard score; // El scoreboard

    private Viborita viborita; // Personaje del juego

    private List<Manzanas> manzana; // Objetos del juego

    //private XboxController xboxController; //Adaptadores del control de XBox
    //private XboxControllerAdapter xboxControllerAdapter;

    Juego(){
        this.window = new Window(this);
        this.score= new Scoreboard(this);
        this.manzana = new ArrayList<>();
        //this.xboxController = new XboxController();
        //this.xboxControllerAdapter = new XboxControllerDirecciones(this);
        //this.xboxController.addXboxControllerListener(this.xboxControllerAdapter);
    }

    // Metodos para controlar el tiempo en el juego

    public void start(){ //Se prepara el juego
        int delay = (1000/dificultad.getVelocidad());
        timer = new Timer(delay , this);
        restartViborita();
        resetManzana();
    }

    public void resume(){
        isPlaying = true;
        timer.start();
    }

    public void restart(){
        stop();

        int delay = (1000/dificultad.getVelocidad());
        timer = new Timer(delay , this);
        restartViborita();
        resetManzana();
        timer.start();
        isPlaying = true;
    }

    public void stop(){ //Lo que se hace cuando el juego se PAUSA/DETIENE
        isPlaying = false;
        timer.stop();
    }

    public boolean isPlaying(){
        return this.isPlaying;
    }

    private void frameUpdate(){
        this.repaint();
    }

    // Getters

    public Window getWindow() {
        return window;
    }

    public Scoreboard getScore() {
        return score;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public List<Manzanas> getManzana() {
        return manzana;
    }

    public int getLastDirection() {
        return lastDirection;
    }

    private int getDireccion(int dir){
        if(dir == KEY_ARRIBA || dir == KEY_W){
            return DIR_ARRIBA;
        }else if(dir == KEY_ABAJO || dir == KEY_S){
            return DIR_ABAJO;
        }else if(dir == KEY_DERECHA || dir == KEY_D){
            return DIR_DERECHA;
        }else if(dir == KEY_IZQUIERDA || dir == KEY_A){
            return DIR_IZQUIERDA;
        }

        return DIR_NULL;
    }

    // Setters

    public void setDificultad(Dificultad dificultad) {
        isPlaying = true;
        this.dificultad = dificultad;
        restart();
        this.repaint();
        isPlaying = false;
    }

    public void setLastDirection(int lastDirection, String origen) {
        if(!isPlaying()){
            resume();
        }

        int dirActual = this.viborita.getcuerpo().getDir();

        Log.d("ORIGEN", "---------------"+origen+"--------------");

        switch(lastDirection){
            case DIR_ARRIBA:
                if(dirActual != DIR_ABAJO){
                    this.lastDirection = DIR_ARRIBA;
                }
                Log.d("SET_DIRECCION", "Arriba");
                break;
            case DIR_ABAJO:
                if(dirActual != DIR_ARRIBA){
                    this.lastDirection = DIR_ABAJO;
                }
                Log.d("SET_DIRECCION", "Abajo");
                break;

            case DIR_IZQUIERDA:
                if(dirActual != DIR_DERECHA){
                    this.lastDirection = DIR_IZQUIERDA;
                }
                Log.d("SET_DIRECCION", "Izquierda");
                break;
            case DIR_DERECHA:
                if(dirActual != DIR_IZQUIERDA){
                    this.lastDirection = DIR_DERECHA;
                }
                Log.d("SET_DIRECCION", "Derecha");
                break;

        }
    }

    // Metodos de los objetos dentro del juego

    private boolean verificarViborita(){
        Cuerpo cabeza = this.viborita.getcuerpo();
        Cuerpo cuerpo = cabeza.SigCuerpo();
        do{
            if(cabeza.X == cuerpo.X && cabeza.Y==cuerpo.Y){
                return false;
            }
            cuerpo = cuerpo.SigCuerpo();
        }while(cuerpo != null);

        return true;
    }

    private void restartViborita(){
        viborita = new Viborita(this);
        this.lastDirection = KEY_DERECHA;

        viborita.Listener(new OnViboritaComio() {
            @Override
            public void ComioManzana(Manzanas manzana) {
                score.SumarPuntos();
                window.updateScore(score.puntaje);
                comerManzana(manzana);
                //xboxController.vibrate(0x00FF, 0x00FF);
            }
        });
    }

    private void comerManzana(Manzanas manzana){
        if(this.score.puntaje == this.dificultad.getFilas() * this.dificultad.getColumnas()){
            JOptionPane.showMessageDialog(null, "Has ganado!");
        }

        manzana.setXY();

        while(!manzana.verificar(this.viborita.getcuerpo())){
            manzana.setXY();
        }
    }

    private void resetManzana(){
        this.manzana.clear();

        for(int i = 0 ; i < this.dificultad.getNumManzanas() ; i++){
            Manzanas newManzana = new Manzanas(this);

            while(!newManzana.verificar(this.viborita.getcuerpo())){
                newManzana.setXY();
            }

            this.manzana.add(newManzana);
        }

    }

    // Metodos heredados de JPanel y metodos usados por el mismo

    @Override
    public void paint(Graphics g){ //Aqui se reflejan los cambios del juego ya que aqui se dibujan los elementos
        super.paint(g);

        this.setSize(window.getGameSize());

        int width = getWidth();
        int height = getHeight();

        int gridWidth = width/dificultad.getColumnas();
        int gridHeight = height/dificultad.getFilas();

        // TODO: Despues de aqui se dibujaran los elementos del juego
        drawGrid(g, gridWidth, gridHeight);


        for(Manzanas m : this.manzana){
            m.Imagen(g, gridWidth, gridHeight);
        }

        Cuerpo cuerpo = this.viborita.getcuerpo();
        cuerpo.Imagen(g, gridWidth, gridHeight, true); //
        cuerpo = cuerpo.SigCuerpo(); //
        do{
            cuerpo.Imagen(g, gridWidth, gridHeight, false);
            cuerpo = cuerpo.SigCuerpo();
        }while(cuerpo != null);
    }

    private void drawGrid(Graphics g, int gridWidth, int gridHeight){
        ImageIcon img = new ImageIcon(getClass().getResource("Assets/pastaso.jpg"));

        int width = gridWidth*dificultad.getColumnas();
        int height = gridHeight*dificultad.getFilas();

        g.setColor(Color.black);
        for(int i = 0; i <= dificultad.getFilas() ; ++i){
            for(int e = 0 ; e <= dificultad.getColumnas() ; ++e){
                g.drawImage(img.getImage(),
                        gridWidth*e,
                        gridHeight*i ,
                        gridWidth,
                        gridHeight,
                        null);
                g.drawLine(e*gridWidth, 0,
                        e*gridWidth, height);
            }
            g.drawLine(0, i*gridHeight, width, i*gridHeight);
        }
        //g.setColor(Color.black);
    }

    // Metodos heredados de las interfaces

    @Override
    public void actionPerformed(ActionEvent e) { // Aqui se incluiran las acciones; Una llamada a este metodo equivale a un frame
        try{
            this.viborita.mover(lastDirection);
            Log.d("IS_RUNNING", isPlaying());
            if(!this.verificarViborita()){
                this.viborita.morir(isPlaying);
            }
            frameUpdate();
        }catch (Exception error){
            Log.e("ERROR_UPDATE", error.toString());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { /* NO FUNCIONA PARA ESTE PROYECTO*/ }

    @Override
    public void keyPressed(KeyEvent e) { // Se obtienen las teclas presionadas
        Log.i("KEY_PRESS", e.getExtendedKeyCode());

        int keyCode = getDireccion(e.getExtendedKeyCode());

        setLastDirection(keyCode, "TECLADO");
    }

    @Override
    public void keyReleased(KeyEvent e) { /* NO FUNCIONA PARA ESTE PROYECTO*/ }

}
