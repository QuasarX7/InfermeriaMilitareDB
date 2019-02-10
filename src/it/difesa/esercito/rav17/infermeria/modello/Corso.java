package it.difesa.esercito.rav17.infermeria.modello;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe utilizzata per implementare la tabella della finestra "ListaCorso".
 * 
 * @author Dr Domenico della Peruta
 * @version 1.0.0 modifica 21/02/2016
 */
public class Corso {
    private final StringProperty corso;

    public Corso(String cp) {
        this.corso = new SimpleStringProperty(cp);
    }

    
    public String getCorso() {
        return corso.get();
    }

    @Override
    public String toString() {
        return corso.get();
    }
    
    
}
