package it.quasar_x7.infermeria.modello;

import it.quasar_x7.java.utile.DataOraria;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.0
 */
public class Verbale {
    private final ObjectProperty<DataOraria> data;
    private final StringProperty diagnosi;

    public Verbale(DataOraria data, String diagnosi) {
        this.data = new SimpleObjectProperty(data);
        this.diagnosi = new SimpleStringProperty(diagnosi);
    }

    public DataOraria getData() {
        return data.get();
    }

    public String getDiagnosi() {
        return diagnosi.get();
    }
}
