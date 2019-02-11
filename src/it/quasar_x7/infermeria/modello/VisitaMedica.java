package it.quasar_x7.infermeria.modello;

import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.0 ultima modifica 07/03/2016
 */
public class VisitaMedica {
    private final ObjectProperty<DataOraria> data;
    private final StringProperty tipo;
    private final StringProperty diagnosi;
    private final StringProperty PML;

    public VisitaMedica(DataOraria data, String tipo, String diagnosi, String PML) {
        this.data = new SimpleObjectProperty(data);
        this.tipo = new SimpleStringProperty(tipo);
        this.diagnosi = new SimpleStringProperty(diagnosi);
        this.PML = new SimpleStringProperty(PML);
    }
    
    public DataOraria getData() {
        return data.get();
    }

    public String getTipo() {
        String stringa = tipo.get();
        if(stringa != null)
            return tipo.get().compareTo(Risorse.Visite.PRONTO_SOCCORSO) == 0 ? " Sì" : "" ;
        return "";
    }

    public String getDiagnosi() {
        return diagnosi.get();
    }

    public String getPML() {
        return PML.get();
    }
    
    
    
    
}
