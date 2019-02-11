package it.quasar_x7.infermeria.controllo;

import static it.quasar_x7.infermeria.programma.Programma.datiVaccinazioni;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import it.quasar_x7.infermeria.DaseDati.BASE_DATI;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import java.time.LocalDate;
import javafx.beans.value.ObservableValue;

/**
 * FXML Controller class
 *
 * @author Dott. Domenico della Peruta
 */
public class AggiungiVaccinoGenericoController implements Initializable {

    @FXML
    private ChoiceBox<String> profilassi;

    @FXML
    private ComboBox<String> viaSomministazione;

    @FXML
    private ComboBox<String> dose;

    @FXML
    private Label messaggio;

    @FXML
    private DatePicker scadenza;

    @FXML
    private TextField vaccino;

    @FXML
    private TextField lotto;

    @FXML
    private TextField ditta;
    
    public static AggiungiVacciniController gestore;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inizializzaCampi();
        messaggio.setText("");
    }    
    
    
    

    @FXML
    void chiusuraSenzaSalvare(ActionEvent event) {
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_AGGIUNGI_VACCINO))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(AggiungiVaccinoGenericoController.class.getName()).log(Level.SEVERE, null, ex);
            messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
    }

   

    @FXML
    void Salva(ActionEvent event) {
        if(gestore != null){
            String vaccinazione = profilassi.getValue();
            boolean ok = true;
            if(vaccinazione == null)
                ok = false;
            else if(vaccinazione.length() == 0)
                ok = false;
            
            Object[] datiVaccino = new Object[datiVaccinazioni.dimensioneVaccinazioni()];
            
            if(dose.getSelectionModel().getSelectedIndex() > -1)
                datiVaccino[BASE_DATI.VACCINAZIONI.DOSE] = dose.getValue();
            else
                ok = false;
            
            datiVaccino[BASE_DATI.VACCINAZIONI.VIA_SOMMINISTRAZIONE] = viaSomministazione.getValue();
            datiVaccino[BASE_DATI.VACCINAZIONI.NOME_VACCINO] = vaccino.getText();
            datiVaccino[BASE_DATI.VACCINAZIONI.DITTA] = ditta.getText();
            datiVaccino[BASE_DATI.VACCINAZIONI.LOTTO] = lotto.getText();
            LocalDate d = scadenza.getValue();
            if(d != null){
                DataOraria scadenzaVaccino = new DataOraria(d.getDayOfMonth(),d.getMonthValue(),d.getYear());
                datiVaccino[BASE_DATI.VACCINAZIONI.SCADENZA] = scadenzaVaccino;
            }
            
            if(ok){
                gestore.salvaRecordVaccinazioni(
                        vaccinazione, 
                        datiVaccino
                );
                chiusuraSenzaSalvare(event);//chiusura della finestra (salvando i dati ^_^ )
            
            }else{
                messaggio.setText(Risorse.Messaggi.ERRORE_CAMPI_PRINCIPALI);
                messaggio.setStyle(Risorse.Colore.ROSSO);
            }
        }
    }
    
    private void caricaCampi(String selezione){
        if(gestore != null){
            Object[] campi = gestore.caricaDatiVaccino(selezione);
            vaccino.setText((String)campi[BASE_DATI.VACCINAZIONI.NOME_VACCINO]);
            dose.getSelectionModel().select((String)campi[BASE_DATI.VACCINAZIONI.DOSE]);
            viaSomministazione.getSelectionModel().select((String)campi[BASE_DATI.VACCINAZIONI.VIA_SOMMINISTRAZIONE]);
            ditta.setText((String)campi[BASE_DATI.VACCINAZIONI.DITTA]);
            lotto.setText((String)campi[BASE_DATI.VACCINAZIONI.LOTTO]);
            DataOraria scad = (DataOraria) campi[BASE_DATI.VACCINAZIONI.SCADENZA];
            if(scad != null)
                scadenza.setValue(LocalDate.of(scad.anno(),scad.mese(),scad.giornoMese()));
            
        }
    }
    
    private void svuotaCampi(){
        
            vaccino.setText("");
            dose.getSelectionModel().select(-1);
            viaSomministazione.getSelectionModel().select(-1);
            ditta.setText("");
            lotto.setText("");
            scadenza.setValue(LocalDate.now());
            
        
    }
    public void inizializzaCampi(){
        viaSomministazione.getItems().addAll(
                        new String[] {
                            Risorse.Input.VIA_SOMM_SC,
                            Risorse.Input.VIA_SOMM_IM,
                            Risorse.Input.VIA_SOMM_OS
                        }
                );
        profilassi.getItems().addAll(Risorse.VACCINAZIONI);
        
        profilassi.valueProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String selezione) -> {
                    dose.getItems().clear();
                    if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_R,
                                    Risorse.Input.DOSE_CB4,
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1 
                                }
                        );
                        caricaCampi(Risorse.DoseVaccino.TD);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO])){

                        dose.getItems().addAll(
                                new String[] {Risorse.Input.DOSE_R }
                        );
                        caricaCampi(Risorse.DoseVaccino.TDP);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.POLIO])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_R,
                                     Risorse.Input.DOSE_CB4,
                                     Risorse.Input.DOSE_CB3,
                                     Risorse.Input.DOSE_CB2,
                                     Risorse.Input.DOSE_CB1
                                 }
                        );

                        caricaCampi(Risorse.DoseVaccino.POLIO);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_R,
                                     Risorse.Input.DOSE_CB4,
                                     Risorse.Input.DOSE_CB3,
                                     Risorse.Input.DOSE_CB2,
                                     Risorse.Input.DOSE_CB1
                                 }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.DIFTERITE])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_R,
                                     Risorse.Input.DOSE_CB4,
                                     Risorse.Input.DOSE_CB3,
                                     Risorse.Input.DOSE_CB2,
                                     Risorse.Input.DOSE_CB1
                                 }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        caricaCampi(Risorse.DoseVaccino.MPR);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        svuotaCampi();


                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.ROSOLIA])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        svuotaCampi();


                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.PAROTITE])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        svuotaCampi();


                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A])){

                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_B,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        caricaCampi(Risorse.DoseVaccino.EPATITE_A);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_B])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_B,
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A_B])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_B,
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );

                        caricaCampi(Risorse.DoseVaccino.EPATITE_AB);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_CONIUGATO])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_R
                                }
                        );

                        caricaCampi(Risorse.DoseVaccino.MENINGITE);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_POLISACC])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_R
                                }
                        );

                        caricaCampi(Risorse.DoseVaccino.MENINGITE);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.VARICELLA])){
                            dose.getItems().addAll(
                                    new String[] {
                                        Risorse.Input.DOSE_CB1,
                                        Risorse.Input.DOSE_CB2
                                    }
                            );

                            caricaCampi(Risorse.DoseVaccino.VARICELLA);

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.INFLUENZA])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.VAIOLO])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_R
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_EPATITE_A])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_ORALE])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_CB4,
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_PARENTALE])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_R
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.FEBB_GIALLA])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_1D,
                                    Risorse.Input.DOSE_R
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.COLERA])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_CB4,
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1,
                                    Risorse.Input.DOSE_R,
                                    Risorse.Input.DOSE_1D
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.RABBIA])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1,
                                    Risorse.Input.DOSE_R
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.ENCEFALITE_GIAPP])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_CB3,
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1,
                                    Risorse.Input.DOSE_B,
                                    Risorse.Input.DOSE_R
                                }
                        );
                        svuotaCampi();

                    }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.ENCEFALITE_ZECCHE])){
                        dose.getItems().addAll(
                                new String[] {
                                    Risorse.Input.DOSE_CB2,
                                    Risorse.Input.DOSE_CB1,
                                    Risorse.Input.DOSE_B,
                                    Risorse.Input.DOSE_R
                                }
                        );
                        svuotaCampi();
                    }else{
                        svuotaCampi();
                    }

                }
        );
    }
    
}
