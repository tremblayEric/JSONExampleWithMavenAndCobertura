/* Copyright 2013
  
  jpokou
  pdarveau
  sayonCisse
  tremblayEric
  
  UQAM hiver 2013

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
 * 
 */
package MockData;

/*ajout iteration 2 : tout ce qui est relatif au contrat E*/
public class Contracts {
      
    private double ratioA;
    private double ratioB;
    private double ratioC;
    private double ratioD;
    private double ratioE;
    
    private boolean maxA;
    private boolean maxB;
    private boolean maxC;
    private boolean maxD;
    private boolean maxE;
    
    private int maxValueA;
    private int maxValueB;
    private int maxValueC;
    private int maxValueD;
    private int maxValueE;
    
    public Contracts(){  
    }
    
    public void setRatio(double ratioA, double ratioB, double ratioC, double ratioD, double ratioE){
        this.ratioA = ratioA;
        this.ratioB = ratioB;
        this.ratioC = ratioC;
        this.ratioD = ratioD;
        this.ratioE = ratioE;
    }
    
    public void setMax(boolean maxA, boolean maxB, boolean maxC, boolean maxD, boolean maxE){
        this.maxA = maxA;
        this.maxB = maxB;
        this.maxC = maxC;
        this.maxD = maxD;
        this.maxE = maxE;
    }
    
    public void setMaxValue(int maxValueA, int maxValueB, int maxValueC, int maxValueD, int maxValueE){
        this.maxValueA = maxValueA;
        this.maxValueB = maxValueB;
        this.maxValueC = maxValueC;
        this.maxValueD = maxValueD;
        this.maxValueE = maxValueE;
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
    
    public double getRatioE(){
        return ratioE;
    }
    
    public boolean getMaxA(){
        return maxA;
    }
    
    public boolean getMaxB(){
        return maxB;
    }
    
    public boolean getMaxC(){
        return maxC;
    }
    public boolean getMaxD(){
        return maxD;
    }
     public boolean getMaxE(){
        return maxE;
    }
    
    public int getMaxValueA(){
        return maxValueA;
    }
    
    public int getMaxValueB(){
        return maxValueB;
    }
    
    public int getMaxValueC(){
        return maxValueC;
    }
    
    public int getMaxValueD(){
        return maxValueD;
    }
    
    public int getMaxValueE(){
        return maxValueE;
    }
}