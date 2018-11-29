import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;
import ch.aplu.xboxcontroller.XboxControllerListener;

public class XboxControllerDirecciones extends XboxControllerAdapter implements XboxControllerListener {
    private Juego juego;
    private XboxController xc;
    private static final int
            DPAD_ARRIBA             = 0,
            DPAD_ABAJO              = 4,
            DPAD_DERECHA            = 2,
            DPAD_IZQUIERDA          = 6,
            DPAD_ABAJO_IZQUIERDA    = 5,
            DPAD_ABAJO_DERECHA      = 3,
            DPAD_ARRIBA_IZQUIERDA   = 7,
            DPAD_ARRIBA_DERECHA     = 1;

    private double magnitudIzquierdo;

    XboxControllerDirecciones(Juego juego){
        this.juego = juego;
    }

    @Override
    public void buttonA(boolean b) {

    }

    @Override
    public void buttonB(boolean b) {

    }

    @Override
    public void buttonX(boolean b) {

    }

    @Override
    public void buttonY(boolean b) {

    }

    @Override
    public void back(boolean b) {

    }

    @Override
    public void start(boolean b) {
        if(b){
            if (juego.isPlaying()){
                juego.stop();
            }else{
                juego.resume();
            }
        }
    }

    @Override
    public void leftShoulder(boolean b) {

    }

    @Override
    public void rightShoulder(boolean b) {

    }

    @Override
    public void leftThumb(boolean b) {

    }

    @Override
    public void rightThumb(boolean b) {

    }

    @Override
    public void dpad(int i, boolean b) {
        if(b){
            switch (i){
                case DPAD_ABAJO:
                    juego.setLastDirection(Juego.DIR_ABAJO, "DPAD");
                    break;
                case DPAD_ARRIBA:
                    juego.setLastDirection(Juego.DIR_ARRIBA, "DPAD");
                    break;
                case DPAD_IZQUIERDA:
                    juego.setLastDirection(Juego.DIR_IZQUIERDA, "DPAD");
                    break;
                case DPAD_DERECHA:
                    juego.setLastDirection(Juego.DIR_DERECHA, "DPAD");
                    break;
                case DPAD_ARRIBA_DERECHA:
                    juego.setLastDirection(Juego.DIR_ARRIBA_DERECHA, "DPAD");
                    break;
                case DPAD_ARRIBA_IZQUIERDA:
                    juego.setLastDirection(Juego.DIR_ARRIBA_IZQUIERDA, "DPAD");
                    break;
                case DPAD_ABAJO_DERECHA:
                    juego.setLastDirection(Juego.DIR_ABAJO_DERECHA, "DPAD");
                    break;
                case DPAD_ABAJO_IZQUIERDA:
                    juego.setLastDirection(Juego.DIR_ABAJO_IZQUIERDA, "DPAD");
                    break;
            }
        }
    }

    @Override
    public void leftTrigger(double value) {
        int v = (int)(65535 * value * value);
        xc.vibrate(v, v);
    }

    @Override
    public void rightTrigger(double value) {
        int v = (int)(65535 * value * value);
        xc.vibrate(v, v);
    }

    @Override
    public void leftThumbMagnitude(double v) {
        this.magnitudIzquierdo = v;
    }

    @Override
    public void leftThumbDirection(double v) {
        if(magnitudIzquierdo > 0.4){
            Log.d("STICK_IZQUIERDO", v);
            if(v >= 45.0 && v < 135.0){
                juego.setLastDirection(Juego.DIR_DERECHA, "STICK_IZQUIERDO");
            }else if(v >= 135.0 && v < 225.0){
                juego.setLastDirection(Juego.DIR_ABAJO, "STICK_IZQUIERDO");
            }else if(v >= 225.0 && v < 315.0){
                juego.setLastDirection(Juego.DIR_IZQUIERDA, "STICK_IZQUIERDO");
            }else {
                juego.setLastDirection(Juego.DIR_ARRIBA, "STICK_IZQUIERDO");
            }
        }
    }

    @Override
    public void rightThumbMagnitude(double v) {

    }

    @Override
    public void rightThumbDirection(double v) {

    }

    @Override
    public void isConnected(boolean b) {

    }
}
