
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Copyright 2018 psdh.
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

/**
 *
 * @author psdh
 */
public class Viborita {
    private Cuerpo cuerpo;
    private Juego juego;
    private Cuerpo colita;
    private OnViboritaComio listener;
    public Viborita(Juego juego){
      this.cuerpo= new Cuerpo(juego); 
      this.juego=juego;
      this.colita=this.cuerpo;
    }
    public void comer(){
    //    Cuerpo.add("");
        if(listener!=null){
            listener.ComioManzana();
        }
        Cuerpo cola= new Cuerpo(juego,colita.X,colita.Y);
        this.colita.addColita(cola);
        this.colita=cola;
    }
    public void morir(){
        
    }
    public void mover(int direccion){
        cuerpo.moverse(direccion);
        if(Objects.equals(juego.getManzanaPosition().getKey(), cuerpo.getPosicion().getKey()) &&
           Objects.equals(juego.getManzanaPosition().getValue(), cuerpo.getPosicion().getValue())){
            comer();
        }
    }
    public void Listener(OnViboritaComio listener){
       this.listener=listener;
    }
    public Cuerpo getcuerpo(){
        return cuerpo;
    }
}
