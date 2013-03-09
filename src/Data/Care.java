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
package Data;


public class Care {

    private String careNumber;
    private String categorieSoin;
    private Contracts contracts;

    Care(String numeroSoin, String categorieSoin, Contracts contracts){
        this.careNumber = numeroSoin;
        this.categorieSoin = categorieSoin;
        this.contracts = contracts;
    }

    public String getcareNumber() {
       return careNumber;
    }

    public String getCareCategorie() {
        return categorieSoin;
    }

    public Contracts getContract() {
        return contracts;
    }
    
    @Override
    public String toString(){
        return careNumber + " category = " + categorieSoin + "contrat = "+
                contracts;
    }
}
