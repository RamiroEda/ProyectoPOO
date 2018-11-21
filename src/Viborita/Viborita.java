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
package Viborita;

import Dificultad;

/**
 *
 * @author psdh
 */
public class Viborita {
void inicio(){
    Viborita.setJoints(4);
    for(int i=0;i<Viborita.setJoints();i++){
        Viborita.setViboritaX(//ancho del borde/2);
        Viborita.setViboritaY(//altura del borde/2);
    }
    Viborita.setMoverRight(true);
    //manzanita.creaciondelamanzanita();
    timer=new Timer(Dificultad,this);
    timer.empezar();
}
void comerManzanitas(){
    if((Viborita.getViborita(0),/*manzanita.getManzanita()*/,30)&&(Viborita.getViborita(0),/*manzanita.getManzanita()*/,30)){
    System.out.println("");
    Viborita.setJoints(Viborita.getJoints()+1);
    //manzanita.crearManzanita();
}
}
void checkCollisions(){
    for(int i=Viborita.getJoints();i>0;i--){
        if((i>5)&&(Viborita.getViboritaX(0)==Viborita.getVIboritaX(i)&&(Viborita.getViboritaY(0)==Viborita.getViborita(i)))
    }
}
}

