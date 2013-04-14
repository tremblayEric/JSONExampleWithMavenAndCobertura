
/**/
package com.INF2015.app.Parsing;

import com.INF2015.app.ProjetAgile.Dollar;
import java.util.Date;

public class JavaObjectReclamation {

    private String soin;
    private String code;//DDC3
    private Date date;
    private int montant;

    public JavaObjectReclamation(String soin, String code, Date date, String montant) {
        this.soin = soin;
        this.code = code;//DDC3
        this.date = date;

        try {
            this.montant = Dollar.doubleMontantToInteger(Dollar.stringToDouble(Dollar.fromStringtoConformCashAmount(montant)));
        } catch (Exception e) {
            System.out.println("plantage de conversion de string en double");
        }
    }

   

    public String getSoin() {
        return this.soin;
    }

    public String getCode() {
        return this.code;
    }

    public Date getDate() {
        return this.date;
    }

    public int getMontant() {
        return this.montant;
    }

    @Override
    public String toString() {
        return "\tsoin: " + soin + "\n\t" + "code " + code + "\n\t" + "Date: " + date.toString() + "\n\t"
                + "montant: " + montant + "\n";
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
