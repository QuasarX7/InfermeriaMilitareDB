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
 * @author Dr Domenico della Peruta
 */
public class Rifiuti {
    private final  StringProperty protocollo;
    private final  ObjectProperty<DataOraria> dataProduzione;
    private final  StringProperty codice;
    private final LongProperty contenitori;
    private final LongProperty quantità;
    private final LongProperty volume;
    private final  ObjectProperty<DataOraria> dataVerbale;
    private final  StringProperty verbale;
    private final StringProperty medico;
    private final StringProperty responsabile;

    public Rifiuti(String protocollo, DataOraria dataProduzione, String codice, Long contenitori, 
            Long quantità, Long volume, DataOraria dataVerbale, String verbale, 
            String medico, String responsabile) {
        this.protocollo = new SimpleStringProperty(protocollo);
        this.dataProduzione = new SimpleObjectProperty(dataProduzione);
        this.codice = new SimpleStringProperty(codice);
        this.contenitori = new SimpleLongProperty(contenitori);
        this.quantità = new SimpleLongProperty(quantità);
        this.volume = new SimpleLongProperty(volume);
        this.dataVerbale = new SimpleObjectProperty(dataVerbale);
        this.verbale = new SimpleStringProperty(verbale);
        this.medico = new SimpleStringProperty(medico);
        this.responsabile = new SimpleStringProperty(responsabile);
    }

    public String getProtocollo() {
        return protocollo.get();
    }

    public DataOraria getDataProduzione() {
        return dataProduzione.get();
    }

    public String getCodice() {
        return codice.get();
    }

    public Long getContenitori() {
        return contenitori.get();
    }

    public Long getQuantità() {
        return quantità.get();
    }

    public Long getVolume() {
        return volume.get();
    }

    public DataOraria getDataVerbale() {
        return dataVerbale.get();
    }

    public String getVerbale() {
        return verbale.get();
    }

    public String getMedico() {
        return medico.get();
    }

    public String getResponsabile() {
        return responsabile.get();
    }
    
    
    
    
}
