package it.quasar_x7.infermeria.modello;

import it.quasar_x7.java.utile.DataOraria;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dott. Domenico della Peruta
 * @version 1.1.0 ultima modifica 08/10/2016
 */
public class Ricovero {
    private  final ObjectProperty<DataOraria> inizio, fine;
    private  final StringProperty diagnosi;
    
    public Ricovero(DataOraria inizio, DataOraria fine, String diagnosi) {
        this.inizio = new SimpleObjectProperty(inizio);
        this.fine = new SimpleObjectProperty(fine);
        this.diagnosi = new SimpleStringProperty(diagnosi);
    }
    /*
    private String getEvento(ObjectProperty<DataOraria> data) {
        DataOraria evento = data.get();
        if(evento != null)
            return evento.stampaGiorno('/')+ " "+ evento.stampaOra();
        return "";
    }
    */
    public DataOraria getInizio() {
        return inizio.get();
    }

    public DataOraria getFine() {
        return fine.get();
    }
    

    public String getDiagnosi() {
        return diagnosi.get();
    }
}
