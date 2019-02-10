package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.programma.Programma;
import it.difesa.esercito.rav17.infermeria.programma.Risorse;
import static it.difesa.esercito.rav17.infermeria.programma.Programma.datiImpostazioni;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Classe che implementa la finestra per l'impostazione dei valori predefiniti 
 * dell'anamnesi iniziale.
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.2 ultima modifica 15/04/2016
 */
public class ImpostazioneAnamnesiNuovoMilitareController implements Initializable {


    
    @FXML
    private TextArea campoAnamnesiFamiliare;

    @FXML
    private TextArea campoAnamnesiRemota;

    @FXML
    private TextArea campoAnamnesiProssima;

    @FXML
    private Label messaggio;

    /**
     * Inizializzazione della finestra
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoAnamnesiFamiliare.setText(datiImpostazioni.valore(Risorse.Impostazioni.KEY_ANAMNESI_FAMILIARE));
        campoAnamnesiProssima.setText(datiImpostazioni.valore(Risorse.Impostazioni.KEY_ANAMNESI_PROSSIMA));
        campoAnamnesiRemota.setText(datiImpostazioni.valore(Risorse.Impostazioni.KEY_ANAMNESI_REMOTA));
        messaggio.setText("");
    } 
    
    @FXML
    void salva(ActionEvent event) {
        boolean ok1 = inputAnamnesi(campoAnamnesiFamiliare, Risorse.Impostazioni.KEY_ANAMNESI_FAMILIARE);
        boolean ok2 = inputAnamnesi(campoAnamnesiProssima, Risorse.Impostazioni.KEY_ANAMNESI_PROSSIMA);
        boolean ok3 = inputAnamnesi(campoAnamnesiRemota, Risorse.Impostazioni.KEY_ANAMNESI_REMOTA);
        if(ok1 && ok2 && ok3){
            Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        }
    }

    private boolean inputAnamnesi(TextArea campo,String key){
        if(campo.getText().length() < 200){
            datiImpostazioni.valore(key, Risorse.Impostazioni.TIPO_ANAMNESI, campo.getText());
            campo.setStyle(Risorse.Colore.NERO);
            return true;
        }else{
            messaggio.setText(Risorse.Messaggi.ERRORE_INPUT_OVERFLOW);
            campo.setStyle(Risorse.Colore.ROSSO);
            return false;
        }
    }
    @FXML
    void annulla(ActionEvent event) {
        Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
    }

       
    
}
