package it.quasar_x7.infermeria.modello;

import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe che implementa il modello dei dati della tabella storia vaccinale.
 * 
 * @author Dr Domenico della Peruta
 * @version 1.0.0 ultima modifica 07/03/16
 */
public class StoriaVaccinale {

    
    private StringProperty vaccino;
    private BooleanProperty pregressa;
    private BooleanProperty pregressaDocumentata;
    private StringProperty civile;
    private StringProperty militare;
    private StringProperty tipo;
    private StringProperty dose;
    private ObjectProperty<DataOraria> data;

    public StoriaVaccinale(){
        this("",false,false,"","","","",null);
    }
    
    public StoriaVaccinale(String vaccino, Boolean pregressa, Boolean pregressaDocumentata, String civile, String militare, String tipo, String dose, DataOraria data) {
        this.vaccino = new SimpleStringProperty(vaccino);
        this.pregressa = new SimpleBooleanProperty(pregressa);
        this.pregressaDocumentata = new SimpleBooleanProperty(pregressaDocumentata);
        this.civile = new SimpleStringProperty(civile);
        this.militare = new SimpleStringProperty(militare);
        this.tipo = new SimpleStringProperty(tipo);
        this.dose = new SimpleStringProperty(dose);
        this.data = new SimpleObjectProperty<>(data);
    }

    public void ultimaVaccinazioneCivile(boolean civile){
        this.civile = new SimpleStringProperty(civile ? "D" : "");
        this.militare = new SimpleStringProperty(!civile ? "D" : "");
    }
    
    public void setData(DataOraria data){
        this.data = new SimpleObjectProperty<>(data);
    }
    
    public void setDose(String dose){
        this.dose = new SimpleStringProperty(dose);
    }
    
    public String getVaccino() {
        return vaccino.get();
    }

    public char getPregressa() {
        return pregressa.get()==true ? 'X' : ' ';
    }

    public char getPregressaDocumentata() {
        return pregressaDocumentata.get() == true ? 'X' : ' ';
    }

    public String getCivile() {
        return civile.get();
    }

    public String getMilitare() {
        return militare.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getDose() {
        return dose.get();
    }

    public DataOraria getData() {
        return data.get();
    }
    
    
    public boolean inputVaccinazioneCorretto(){
        return data.get() != null && (militare.get().length() == 1 || civile.get().length() == 1) ;
    }

    @Override
    public String toString() {
        String s = String.format("%s [%s] %s militare:%s civile:%s  data:%s ", vaccino.get(),tipo.get(),dose.get(),militare.get(),civile.get(),data.get());
        return s;
    }
    
    
    
    public static StoriaVaccinale[] crea(int vaccinazione) {
        switch(vaccinazione){
            case Risorse.Vaccinazioni.TETANO_DIFTERITE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.TETANO),
                    creaStoria(vaccinazione,Risorse.Profilassi.DIFTERITE)
                };
            case Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.TETANO),
                    creaStoria(vaccinazione,Risorse.Profilassi.DIFTERITE),
                    creaStoria(vaccinazione,Risorse.Profilassi.POLIO)
                };
            case Risorse.Vaccinazioni.TETANO:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.TETANO)
                };
            case Risorse.Vaccinazioni.DIFTERITE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.DIFTERITE)
                };
            case Risorse.Vaccinazioni.POLIO:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.POLIO)
                };
            case Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.MORBILLO),
                    creaStoria(vaccinazione,Risorse.Profilassi.ROSOLIA),
                    creaStoria(vaccinazione,Risorse.Profilassi.PAROTITE)
                };  
            case Risorse.Vaccinazioni.MORBILLO:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.MORBILLO)
                };  
            case Risorse.Vaccinazioni.ROSOLIA:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.ROSOLIA)
                };  
            case Risorse.Vaccinazioni.PAROTITE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.PAROTITE)
                };
            case Risorse.Vaccinazioni.VARICELLA:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.VARICELLA)
                };
            case Risorse.Vaccinazioni.MENINGITE_CONIUGATO:
            case Risorse.Vaccinazioni.MENINGITE_POLISACC:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.MENINGITE)
                };
            case Risorse.Vaccinazioni.EPATITE_A:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.EPATITE_A)
                };
            case Risorse.Vaccinazioni.EPATITE_B:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.EPATITE_B)
                };
            case Risorse.Vaccinazioni.EPATITE_A_B:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.EPATITE_A),
                    creaStoria(vaccinazione,Risorse.Profilassi.EPATITE_B)
                };
            case Risorse.Vaccinazioni.COLERA:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.COLERA)
                };
            case Risorse.Vaccinazioni.ENCEFALITE_GIAPP:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.ENC_GIAP)
                };
            case Risorse.Vaccinazioni.ENCEFALITE_ZECCHE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.ENC_ZECC)
                };
            case Risorse.Vaccinazioni.FEBB_GIALLA:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.FEBB_GIALLA)
                };
            case Risorse.Vaccinazioni.INFLUENZA:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.INFLUENZA)
                };
            case Risorse.Vaccinazioni.RABBIA:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.RABBIA)
                };
            case Risorse.Vaccinazioni.TIFO_EPATITE_A:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.TIFO),
                    creaStoria(vaccinazione,Risorse.Profilassi.EPATITE_A)
                };
            case Risorse.Vaccinazioni.TIFO_ORALE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.TIFO)
                };  
            case Risorse.Vaccinazioni.TIFO_PARENTALE:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.TIFO)
                };
            case Risorse.Vaccinazioni.VAIOLO:
                return new StoriaVaccinale[]{
                    creaStoria(vaccinazione,Risorse.Profilassi.VAIOLO)
                };
            default: return null; 
        }
    }
    
    private static StoriaVaccinale creaStoria(int vaccino, String profilassi){
        StoriaVaccinale vaccinazione = new StoriaVaccinale();
        vaccinazione.vaccino = new SimpleStringProperty(profilassi); 
        vaccinazione.tipo = new SimpleStringProperty(Risorse.SIGLA_VACCINAZIONI[vaccino]);
        return vaccinazione;
    }
    
}
