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

/**
 *
 * @author psdh
 */
public class Cuerpo {
    private final int[]x=new int[()];
    private final int[]y=new int[()];
    private boolean Left=false,Right=false,Up=false,Down=false;
    private int Joints=0;
    
    public boolean left(){
        return Left;
    }
    public void setleft(boolean Left){
        this.Left=Left;
    }
    public boolean right(){
        return Right;
    }
    public void setright(boolean Right){
        this.Right=Right;
    }
    public boolean up(){
        return Up;
    }
    public void setup(boolean Up){
        this.Up=Up;
    }
    public boolean down(){
        return Down;
    }
    public void setdown(boolean Down){
        this.Down=Down;
    }
    public int getViboritaX(int a){
        return x[a];
    }
    public int getViboritaY(int b){
        return y[b];
    }
    public void setViboritaX(int u){
        x[0]=u;
    }
    public void setViboritaY(int du){
        y[0]=du;
    }
    
    
}
