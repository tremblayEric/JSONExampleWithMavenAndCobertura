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


public class Soins {

    private String numeroSoin;
    private String categorieSoin;
    private Contracts contracts;

    /**
     * Constructeur dans lequel on donne le numero de soin la categorie de soin et le contrat 
     * de la mise.
     * @param numeroSoin    Le numero de soin.
     * @param categorieSoin La categorie de soin.
     * @param contracts Le contrat associe au soin.
     */
    Soins(String numeroSoin, String categorieSoin, Contracts contracts){
        this.numeroSoin = numeroSoin;
        this.categorieSoin = categorieSoin;
        this.contracts = contracts;
    }

    /**
     * Retourne le numero de soin.
     * @return Le numero du soim.
     */
    public String getcareNumber() {
       return this.numeroSoin;
    }

    /**
     * Retourne le numero de categorie.
     * @return Le numero de categorie.
     */
    public String getCareCategorie() {
        return this.categorieSoin;
    }

    /**
     * Retourne le contrat du soin.
     * @return Le contrat associe au soin.
     */
    public Contracts getContract() {
        return this.contracts;
    }
    
    /**
     * Affiche les caracteristique du soin.
     * @return Les caracteristique du soin.
     */
    @Override
    public String toString(){
        return this.numeroSoin + " category = " + this.categorieSoin + "contrat = "+
                this.contracts;
    }
}
