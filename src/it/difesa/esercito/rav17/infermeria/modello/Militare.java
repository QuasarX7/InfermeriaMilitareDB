package it.difesa.esercito.rav17.infermeria.modello;

import it.difesa.esercito.rav17.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe che implementa il modello di militare, usato della tabella di ricerca militare
 * (FinestraPrincipale), costituito da: nome, cognome, data di nscita, compagnia e corso.
 * 
 * @author Dr Domenico della Peruta
 * @version 1.2.0 modifica 10/04/2016
 */
public class Militare{
    private final StringProperty grado;
    private final StringProperty cognome;
    private final StringProperty nome;
    private final ObjectProperty<DataOraria> nascita;
    private final StringProperty luogo;
    private final StringProperty cp;
    private final StringProperty corso;
    private final StringProperty info;
    

    public Militare(String grado, String cognome, String nome, DataOraria nascita,String luogo, String cp, String corso,String info) {
        this.cognome = new SimpleStringProperty(cognome);
        this.nome = new SimpleStringProperty(nome);
        this.nascita = new SimpleObjectProperty<>(nascita);
        this.cp = new SimpleStringProperty(cp);
        this.corso = new SimpleStringProperty(corso);
        this.grado = new SimpleStringProperty(grado);
        this.luogo = new SimpleStringProperty(luogo);
        this.info = new SimpleStringProperty(info);
    }
    
    public Militare(String grado, String cognome, String nome, DataOraria nascita,String luogo, String cp, String corso) {
        this(grado,cognome,nome,nascita,luogo,cp,corso,"");
    }
    
    /**
     * Genera un vettore di dati compatibile con i (primi attributi) del record DB 
     * Militare.
     * 
     * @param scuola
     * @return 
     */
    public Object[] vettore(String scuola){
        return  new Object[]{
            cognome.get(),
            nome.get(),
            nascita.get(),
            luogo.get(),
            grado.get(),
            scuola,
            cp.get()
        };
    }
    
    public String getInfo(){
        return info.get();
    }
    
     
    public Object[] vettore(){
        return vettore("");
    }

    public String getCognome() {
        return cognome.get();
    }

    public String getNome() {
        return nome.get();
    }

    public DataOraria getNascita() {
        return nascita.get();
    }

    public String getCp() {
        return cp.get();
    }

    public String getCorso() {
        return corso.get();
    }

    public String getGrado() {
        return grado.get();
    }

    public String getLuogo() {
        return luogo.get();
    }

    @Override
    public String toString() {
        return String.format(Risorse.FORMAT.STAMPA_MILITARE,grado.get(), cognome.get(),nome.get(),nascita.get().stampaGiorno());
    }  
   
}
    