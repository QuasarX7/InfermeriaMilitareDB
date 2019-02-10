package it.difesa.esercito.rav17.infermeria.modello;

import it.quasar_x7.java.utile.DataOraria;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.0 ultima modifica 07/03/16
 */
public class Vaccinazione {
    private  ObjectProperty<DataOraria> data;
    private  StringProperty profilassi;
    private  StringProperty vaccino;//nome
    private  StringProperty dose;
    private  StringProperty inadempienza;
    private  StringProperty somministrazione;
    private  StringProperty ditta;
    private  StringProperty lotto;
    private  ObjectProperty<DataOraria> scadenza;
    

    public Vaccinazione(DataOraria data, String profilassi, String vaccino, String dose, String inadempienza) {
        this.data = new SimpleObjectProperty(data);
        this.profilassi = new SimpleStringProperty(profilassi);
        this.vaccino = new SimpleStringProperty(vaccino);
        this.dose = new SimpleStringProperty(dose);
        this.inadempienza = new SimpleStringProperty(inadempienza);
        somministrazione = new SimpleStringProperty("");
        ditta = new SimpleStringProperty("");
        lotto = new SimpleStringProperty("");
        scadenza = new SimpleObjectProperty(DataOraria.NULL());
    }
    
    public Vaccinazione(DataOraria data, String profilassi,String dose, String somministrazione, String vaccino,String ditta, String lotto,DataOraria scadenza, String inadempienza) {
        this(data,profilassi,vaccino,dose,inadempienza);
        this.somministrazione = new SimpleStringProperty(somministrazione);
        this.ditta = new SimpleStringProperty(ditta);
        this.lotto = new SimpleStringProperty(lotto);
        this.scadenza = new SimpleObjectProperty(scadenza);
    }

    /**
     * Costruttore tramite record della base di dati vaccinazione.
     * 
     * @param record 
     */
    public Vaccinazione(Object[] record) {
        this(
                (DataOraria)record[0],  // data
                (String)record[1],      // profilassi
                (String)record[5],      // dose
                (String)record[6],      // via somm.
                (String)record[7],      // nome vaccino
                (String)record[8],      // ditta
                (String)record[9],      // lotto
                (DataOraria)record[11], // scadenza
                (String)record[12]      // inadempienza
        );
                                       
    }

    public String getSomministrazione(){
        return somministrazione.get();
    }
    
    public String getDitta(){
        return ditta.get();
    }
    
    public String getLotto(){
        return lotto.get();
    }
    
    public DataOraria getScadenza(){
        return scadenza.get();
    }
    
    public DataOraria getData() {
        return data.get();
    }

    public String getProfilassi() {
        return profilassi.get();
    }

    public String getVaccino() {
        return vaccino.get();
    }

    public String getDose() {
        return dose.get();
    }

    public String getInadempienza() {
        return inadempienza.get();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.data);
        hash = 89 * hash + Objects.hashCode(this.profilassi);
        hash = 89 * hash + Objects.hashCode(this.vaccino);
        hash = 89 * hash + Objects.hashCode(this.dose);
        return hash;
    }

    /**
     * Metodo indirettamente nel rimuovere una voce simili in due liste differenti ma simili.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vaccinazione)
            return this.toString().equals(obj.toString());
        return false;
    }

    @Override
    public String toString() {
        return profilassi.get() +" "+dose.get()+" "+data.get().stampaGiorno()+" "+vaccino.get();
    }

    
 

    
    

}
