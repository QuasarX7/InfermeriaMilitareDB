package it.difesa.esercito.rav17.infermeria.modello;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dr. Domenico della Peruta
 */
public class Medico {
   private final StringProperty medico;

    public Medico(String medico) {
        this.medico = new SimpleStringProperty(medico);
    }

    
    public String getMedico() {
        return medico.get();
    }

    @Override
    public String toString() {
        return medico.get();
    }
    
    
}