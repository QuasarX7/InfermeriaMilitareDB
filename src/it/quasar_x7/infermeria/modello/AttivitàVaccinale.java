package it.quasar_x7.infermeria.modello;

import it.quasar_x7.java.utile.DataOraria;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dott. Domenico della Peruta
 */
public class AttivitàVaccinale {
    private final  ObjectProperty<DataOraria> data;
    private final  StringProperty cp;
    private final  LongProperty militari;

    public AttivitàVaccinale(DataOraria data, String cp, Long militari) {
        this.data = new SimpleObjectProperty(data);
        this.cp = new SimpleStringProperty(cp);
        this.militari = new SimpleLongProperty(militari);
    }

    public DataOraria getData() {
        return data.get();
    }

    
    public String getCp() {
        return cp.get();
    }

    

    public Long getMilitari() {
        return militari.get();
    }

    

}
