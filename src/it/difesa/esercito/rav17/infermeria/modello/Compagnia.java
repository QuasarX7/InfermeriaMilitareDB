package it.difesa.esercito.rav17.infermeria.modello;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe utilizzata per implementare la tabella della finestra "ListaCp".
 * 
 * @author Dr Domenico della Peruta
 * @version 1.1.0 modifica 21/02/2016
 */
public class Compagnia{
    private final StringProperty cp;

    public Compagnia(String cp) {
        this.cp = new SimpleStringProperty(cp);
    }

    public String getCp() {
        return cp.get();
    }
    
    @Override
    public String toString(){
        return cp.get();
    }
    
}
