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

/**
 *
 * @author Ramiro Estrada García
 */
public enum Dificultad{
    FACIL(4, 12 ,8, 1),MEDIO(8, 24, 16, 1),DIFICIL(16, 36, 24, 1), EXTREMA(30, 74, 48, 4);

    private final int velocidad;
    private final int columnas;
    private final int filas;
    private final int numManzanas;

    Dificultad(int vel, int cols, int filas, int numManzanas){
        this.velocidad = vel;
        this.columnas = cols;
        this.filas = filas;
        this.numManzanas = numManzanas;
    }
    
    public int getVelocidad(){
        return velocidad;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getFilas() {
        return filas;
    }

    public double getAspectRatio(){
        return columnas/(double)filas;
    }

    public int getNumManzanas() {
        return numManzanas;
    }
}
