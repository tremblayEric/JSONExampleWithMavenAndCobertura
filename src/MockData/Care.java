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

public class Care {

    private String careNumber;
    private String careCategory;
    private Contracts contracts;
    private int maxMonthlyRefund;//ajout iteration #3

    public Care(String numeroSoin, String categorieSoin, Contracts contracts, int maxMonthlyRefund){
        this.careNumber = numeroSoin;
        this.careCategory = categorieSoin;
        this.contracts = contracts;
        this.maxMonthlyRefund = maxMonthlyRefund;
    }

    public String getCareNumber() {
       return careNumber;
    }

    public String getCareCategorie() {
        return careCategory;
    }

    public Contracts getContract() {
        return contracts;
    }
    
    public int getMonthlyMaxLimit() {
        return maxMonthlyRefund;
    }
    
    @Override
    public String toString(){
        return careNumber + " category = " + careCategory + "contrat = "+
                contracts;
    }
}
