package it.quasar_x7.infermeria.controllo;

import it.quasar_x7.infermeria.modello.Rifiuti;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse.Impostazioni;
import it.quasar_x7.infermeria.programma.Risorse.Input;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.Errore;
import it.quasar_x7.javafx.CampoTesto;

import static it.quasar_x7.infermeria.programma.Risorse.FXML.FINESTRA_RIFIUTI;
import static it.quasar_x7.infermeria.programma.Risorse.Messaggi.ERRORE_SALVATAGGIO_DATI;
import static it.quasar_x7.infermeria.programma.Risorse.Messaggi.INFO_CAMPI_NON_COMPLETI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dr. Domenico della Peruta
 */
public class VoceRifiutiController implements Initializable {

    @FXML
    private TextField campoContenitore;

    @FXML
    private TextField campoVerbale;

    @FXML
    private ChoiceBox<String> selettoreCodice;

    @FXML
    private TextField campoVolume;

    @FXML
    private DatePicker dataVersamento;

    @FXML
    private TextField campoProtocollo;

    @FXML
    private TextField campoQuantità;

    @FXML
    private DatePicker dataProduzione;
    
    
    @FXML
    private ComboBox<String> menuMedico;
    
    
    @FXML
    private ComboBox<String> menuResponsabile;
    
    public static Rifiuti rifiuti;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(selettoreCodice.getItems().isEmpty()){
            selettoreCodice.getItems().add(Input.RIFIUTI_PERICOLOSI);
            selettoreCodice.getItems().add(Input.MEDICINALI_SCADUTI);
        }
        if(menuMedico.getItems().isEmpty()){
            menuMedico.getItems().add(
                    Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_RESPONSABILE_PRODUZIONE_RIFIUTI
                    )
            );
             menuMedico.getItems().add(
                    Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_VICE_RESPONSABILE_PRODUZIONE_RIFIUTI
                    )
            );
        }
        if(menuResponsabile.getItems().isEmpty()){
            menuResponsabile.getItems().add(
                    Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_RESPONSABILE_STOCCAGGIO_RIFIUTI
                    )
            );
             menuResponsabile.getItems().add(
                    Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_VICE_RESPONSABILE_STOCCAGGIO_RIFIUTI
                    )
            );
        }
        
        Programma.campoData(dataProduzione);
        Programma.campoData(dataVersamento);
        CampoTesto.soloNumeri(campoContenitore, 3);
        CampoTesto.soloNumeri(campoQuantità, 4);
        CampoTesto.soloNumeri(campoVolume, 4);
        CampoTesto.aggiungiLimiteTesto(campoVerbale, 20);
        if(rifiuti != null)
            carica();
    }    
    
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                            FXMLLoader.load(getClass().getResource(FINESTRA_RIFIUTI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(VoceRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rifiuti = null;//azzera valori caricati
    }
    
    
    @FXML
    private void salva(ActionEvent event){
        if(campoProtocollo.getText().length() > 0 && CampoTesto.controllaData(dataProduzione.getEditor())){
            DataOraria produzione,versamento;
            try {
                produzione = new DataOraria(dataProduzione.getEditor().getText());
            } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                produzione = null;
            }
            try {
                versamento = new DataOraria(dataVersamento.getEditor().getText());
            } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                versamento = null;
            }
            Long quantità, volume, contenitore;
            try{
                quantità = new Long(campoQuantità.getText());
            }catch(java.lang.NumberFormatException ex){
                quantità = 0L;
            }
            try{
                contenitore = new Long(campoContenitore.getText());
            }catch(java.lang.NumberFormatException ex){
                contenitore = 0L;
            }
            try{
                volume = new Long(campoVolume.getText());
            }catch(java.lang.NumberFormatException ex){
                volume = 0L;
            }  
            if(rifiuti != null)
                Programma.datiRifiuti.elimina(rifiuti.getProtocollo());
            boolean ok = Programma.datiRifiuti.aggiungi(
                    campoProtocollo.getText(),
                    produzione,
                    selettoreCodice.getValue(),
                    quantità,
                    contenitore,
                    volume,
                    menuMedico.getEditor().getText(),
                    menuResponsabile.getEditor().getText(),
                    versamento,
                    campoVerbale.getText()
            );
            if(ok)
                chiusuraSenzaSalvare(event);//chiudi salvando ;)
            else
                try {
                    Programma.finestraAvviso(this, ERRORE_SALVATAGGIO_DATI);
                } catch (IOException ex) {
                    Logger.getLogger(VoceRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
        }else{
           try {
                Programma.finestraAvviso(this, INFO_CAMPI_NON_COMPLETI);
            } catch (IOException ex) {
                Logger.getLogger(VoceRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    private void carica() {
        campoProtocollo.setText(rifiuti.getProtocollo());
        
        DataOraria produzione = rifiuti.getDataProduzione();
        if(produzione != null)
            dataProduzione.getEditor().setText(produzione.stampaGiorno());
        
        selettoreCodice.setValue(rifiuti.getCodice());
        
        Long quantità = (rifiuti.getQuantità() != null) ? rifiuti.getQuantità() : 0L;
        if(quantità > 0L)
            campoQuantità.setText(quantità.toString());
        
        Long contenitori = (rifiuti.getContenitori() != null) ? rifiuti.getContenitori(): 0L;
        if(contenitori > 0L)
            campoContenitore.setText(contenitori.toString());
        
        Long volume = (rifiuti.getVolume()!= null) ? rifiuti.getVolume(): 0L;
        if(volume > 0L)
            campoVolume.setText(volume.toString());
        
        menuMedico.getEditor().setText(rifiuti.getMedico());
        menuResponsabile.getEditor().setText(rifiuti.getResponsabile());
        
        DataOraria versamento = rifiuti.getDataVerbale();
        if(versamento != null)
            dataVersamento.getEditor().setText(versamento.stampaGiorno());
        
        campoVerbale.setText(rifiuti.getVerbale());
        
    }
    
}
