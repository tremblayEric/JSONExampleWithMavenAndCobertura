package com.INF2015.app.Parsing;
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
 */
import com.INF2015.app.ProjetAgile.Dollar;
import java.util.Date;

public class JavaObjectReclamation {

    private String care;
    private String code;//DDC3
    private Date date;
    private int amount;

    public JavaObjectReclamation(String care, String code, Date date, String amount) {
        this.care = care;
        this.code = code;//DDC3
        this.date = date;

        try {
            this.amount = Dollar.doubleAmountToInteger(Dollar.stringToDouble(Dollar.fromStringtoConformCashAmount(amount)));
        } catch (Exception e) {
            System.out.println("plantage de conversion de string en double");
        }
    }

   

    public String getCare() {
        return this.care;
    }

    public String getCode() {
        return this.code;
    }

    public Date getDate() {
        return this.date;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "\tsoin: " + care + "\n\t" + "code " + code + "\n\t" + "Date: " + date.toString() + "\n\t"
                + "montant: " + amount + "\n";
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
