package it.difesa.esercito.rav17.infermeria.modello;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe utilizzata per implementare la tabella della finestra "ListaGrado".
 * 
 * @author Dr Domenico della Peruta
 * @version 1.0.0 modifica 21/02/2016
 */
public class Grado{
    private final StringProperty grado;

    public Grado(String cp) {
        this.grado = new SimpleStringProperty(cp);
    }

    public String getGrado() {
        return grado.get();
    }

    @Override
    public String toString() {
        return grado.get();
    }
    
    
}
