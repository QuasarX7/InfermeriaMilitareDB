package it.difesa.esercito.rav17.infermeria.modello;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Dott. Domenico della Peruta
 */
public class SituazioneVaccini {

    //sesso
    public static final String MASCHI = "M";
    public static final String FEMMINE = "F";
    //età
    public static final String MAGGIORE_25 = " > 25";
    public static final String MINORE_25 = " ≤ 25";

    private StringProperty profilassi;
    private StringProperty dose;
    private StringProperty sesso;
    private StringProperty età;
    private LongProperty numero;

    public SituazioneVaccini(String profilassi, String dose, String sesso, String età, Long numero) {
        this.profilassi = new SimpleStringProperty(profilassi);
        this.dose = new SimpleStringProperty(dose);
        this.sesso = new SimpleStringProperty(sesso);
        this.età = new SimpleStringProperty(età);
        this.numero = new SimpleLongProperty(numero);
    }

    public String getProfilassi() {
        return profilassi.get();
    }

    public String getDose() {
        return dose.get();
    }

    public String getSesso() {
        return sesso.get();
    }

    public String getEtà() {
        return età.get();
    }

    public Long getNumero() {
        return numero.get();
    }
    
    

}
