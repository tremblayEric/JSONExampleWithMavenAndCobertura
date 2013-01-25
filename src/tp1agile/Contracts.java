/* Copyright 2011 Jacques Berger

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 * 
 * Modifié dans le cadre du cours : 
 * Programmation dans un environnement agile INF2015 
 * TP1
 * 
 * Par:
 * jpokou
 * pdarveau
 * sayonCisse
 * tremblayEric
 * 
 * UQAM hiver 2013
 */
package tp1agile;


public class Contracts {
    
    
    private double ratioA;
    private double ratioB;
    private double ratioC;
    private double ratioD;
    
    private boolean maxA;
    private boolean maxB;
    private boolean maxC;
    private boolean maxD;
    
    private int maxValueA;
    private int maxValueB;
    private int maxValueC;
    private int maxValueD;
    
    public Contracts(){
      
    }
    
    public void setRatio(double ratioA, double ratioB, double ratioC, double ratioD){
        this.ratioA = ratioA;
        this.ratioB = ratioB;
        this.ratioC = ratioC;
        this.ratioD = ratioD;
    }
    
    public void setMax(boolean maxA, boolean maxB, boolean maxC, boolean maxD){
        this.maxA = maxA;
        this.maxB = maxB;
        this.maxC = maxC;
        this.maxD = maxD;
    }
    
    public void setMaxValue(int maxValueA, int maxValueB, int maxValueC, int maxValueD){
        this.maxValueA = maxValueA;
        this.maxValueB = maxValueB;
        this.maxValueC = maxValueC;
        this.maxValueD = maxValueD;
    }
 
    public double getRatioA(){
        return ratioA;
    }
    public double getRatioB(){
        return ratioB;
    }
    public double getRatioC(){
        return ratioC;
    }
    public double getRatioD(){
        return ratioD;
    }
    
    public boolean getmaxA(){
        return maxA;
    }
    public boolean getmaxB(){
        return maxB;
    }
    public boolean getmaxC(){
        return maxC;
    }
    public boolean getmaxD(){
        return maxD;
    }
    
    public int getMavalueA(){
        return maxValueA;
    }
    public int getMavalueB(){
        return maxValueB;
    }
    public int getMavalueC(){
        return maxValueC;
    }
    public int getMavalueD(){
        return maxValueD;
    }
    
}
