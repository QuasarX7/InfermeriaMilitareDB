package it.quasar_x7.infermeria.controllo;

import static it.quasar_x7.infermeria.controllo.FinestraPrincipaleController.listaStoriaVaccinale;
import static it.quasar_x7.infermeria.controllo.FinestraPrincipaleController.militare;
import static it.quasar_x7.infermeria.programma.Risorse.SIGLA_VACCINAZIONI;
import static it.quasar_x7.infermeria.programma.Risorse.VACCINAZIONI;

import it.quasar_x7.infermeria.DaseDati.DatiStoriaVaccinale;
import it.quasar_x7.infermeria.modello.StoriaVaccinale;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.infermeria.programma.Risorse.Vaccinazioni;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.Errore;
import it.quasar_x7.javafx.CampoTesto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * 
 *
 * @author Dr. Domenico della Peruta
 * @version 1.0.0 ultima modifica 10/04/2016
 */
public class ModificaVoceStoriaVaccinaleController implements Initializable {

    

        
    @FXML
    private ChoiceBox<String> profilassi;

    @FXML
    private ComboBox<String> dose;

    @FXML
    private TextField data;

    @FXML
    private RadioButton vaccCivile;

    @FXML
    private RadioButton vaccMilitare;
    
    
    @FXML
    private Label messaggio;
    
    @FXML
    private CheckBox pregressa;

    @FXML
    private CheckBox pregressaDocumentata;

    
    private StoriaVaccinale[] dati=null;

    private DatiStoriaVaccinale db;
    

    static private Scene finestra;
    
    static private StoriaVaccinale valoreIniziali;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messaggio.setText("");
        db = new DatiStoriaVaccinale();
        inizializzaUltimaVaccinazione();
        inizializzaProfilassi();
        inizializzaDose();
        inizializzaPregressa();
        CampoTesto.aggiungiMascheraInput(data, Risorse.MascheraCampi.DATA,Risorse.MascheraCampi.CARATTERE);
        caricaValoriDiSelezioneEsterna();
        
    }    
    
    
    private void inizializzaPregressa(){
        comportamentoCampoPregressa(pregressa,pregressaDocumentata);
        comportamentoCampoPregressa(pregressaDocumentata,pregressa);
    }
    
    private void comportamentoCampoPregressa(CheckBox campo, CheckBox controCampo) {
        campo.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean vecchiaSelezione, Boolean nuovaSelezione) -> {
                    if(nuovaSelezione){
                        controCampo.setSelected(false);
                        vaccCivile.setDisable(true);
                        vaccMilitare.setDisable(true);
                        data.setDisable(true);
                        dose.setDisable(true);
                    }else{
                        vaccCivile.setDisable(false);
                        vaccMilitare.setDisable(false);
                        data.setDisable(false);
                        dose.setDisable(false);
                    }
                }
        );
    }
    
    
    /**
     * Metodo che implementa il comportamento dei campi RadioButton: la selezione di uno dei due,
     * oltre ad venire memorizata nell'array dati, permette di preselezionare il valore più probabile
     * del menu del campo dose.
     */
    private void inizializzaUltimaVaccinazione(){
        final ToggleGroup ultimaVacc = new ToggleGroup();
        vaccCivile.setToggleGroup(ultimaVacc);
        vaccMilitare.setToggleGroup(ultimaVacc);
        vaccMilitare.setUserData(Risorse.Seleziona.ULTIMA_VACC_MILITARE);
        vaccCivile.setUserData(Risorse.Seleziona.ULTIMA_VACC_CIVILE);
        ultimaVacc.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            Toggle taggle = ultimaVacc.getSelectedToggle();
            if(taggle !=null){
                Integer sel = (Integer) taggle.getUserData();
                String selezione = this.profilassi.getValue();
                if(selezione != null){
                    if(sel == Risorse.Seleziona.ULTIMA_VACC_MILITARE){
                        if(dati != null)
                            for(StoriaVaccinale d:dati){
                                d.ultimaVaccinazioneCivile(false);
                            }
                        if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_R);
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_R);
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.POLIO])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_R);  
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D);
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB1);   
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_B])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_B);  
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A_B])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB2);   
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_CONIUGATO])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D);    
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_POLISACC])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D);  
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.VARICELLA])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB2); 
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.INFLUENZA])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D); 
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_ORALE])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB4); 
                        }else{
                            dose.getSelectionModel().select(-1);
                        }
                    }else if(sel == Risorse.Seleziona.ULTIMA_VACC_CIVILE){
                        if(dati != null)
                            for(StoriaVaccinale d:dati){
                                d.ultimaVaccinazioneCivile(true);
                            }
                        if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB4);
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_R);
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.POLIO])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB4);  
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB2);
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB2);   
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_B])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB3);  
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A_B])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB3);   
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_CONIUGATO])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D);    
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_POLISACC])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D);  
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.VARICELLA])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB2); 
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.INFLUENZA])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_1D); 
                        }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_ORALE])){
                            dose.getSelectionModel().select(Risorse.Input.DOSE_CB4); 
                        }else{
                            dose.getSelectionModel().select(-1);
                        }
                    }
                }
            }
        });
    }
    /**
     * Metodo che implementa il comportamento del campo a menu dose, permettendo di memorizzare il valore
     * nell'array dati.
     */
    private void inizializzaDose(){
        this.dose.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(this.dati != null)
                for(StoriaVaccinale vacc: dati){
                    vacc.setDose(newValue);
                }
        });
    }
    
    /**
     * Metodo che inizializza i valori del campo a menu della profilassi, e ne implementa il comportamento; 
     * selezionando la voce dal menu, vengono inizializzati i valori del campo a menu dose.
     */
    private void inizializzaProfilassi() {
        profilassi.getItems().addAll(Risorse.VACCINAZIONI);
        this.profilassi.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String selezione) -> {
            dose.getItems().clear();
            if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.TETANO_DIFTERITE);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_R,
                            Risorse.Input.DOSE_CB4,
                            Risorse.Input.DOSE_CB3,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1 
                        }
                );
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO);
                
                dose.getItems().addAll(
                        new String[] {Risorse.Input.DOSE_R }
                );

            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.POLIO])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.POLIO);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_R,
                             Risorse.Input.DOSE_CB4,
                             Risorse.Input.DOSE_CB3,
                             Risorse.Input.DOSE_CB2,
                             Risorse.Input.DOSE_CB1
                         }
                );

            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TETANO])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.TETANO);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_R,
                             Risorse.Input.DOSE_CB4,
                             Risorse.Input.DOSE_CB3,
                             Risorse.Input.DOSE_CB2,
                             Risorse.Input.DOSE_CB1
                         }
                );

            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.DIFTERITE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.DIFTERITE);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_R,
                             Risorse.Input.DOSE_CB4,
                             Risorse.Input.DOSE_CB3,
                             Risorse.Input.DOSE_CB2,
                             Risorse.Input.DOSE_CB1
                         }
                );

            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );
                    
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.MORBILLO);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.ROSOLIA])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.ROSOLIA);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.PAROTITE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.PAROTITE);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.EPATITE_A);
                
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_B,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_B])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.EPATITE_B);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_B,
                            Risorse.Input.DOSE_CB3,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A_B])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.EPATITE_A_B);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_B,
                            Risorse.Input.DOSE_CB3,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_CONIUGATO])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.MENINGITE_CONIUGATO);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_R
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_POLISACC])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.MENINGITE_POLISACC);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_R
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.VARICELLA])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.VARICELLA);
                    dose.getItems().addAll(
                            new String[] {
                                Risorse.Input.DOSE_CB1,
                                Risorse.Input.DOSE_CB2
                            }
                    );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.INFLUENZA])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.INFLUENZA);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.VAIOLO])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.VAIOLO);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_R
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_EPATITE_A])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.TIFO_EPATITE_A);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_ORALE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.TIFO_ORALE);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_CB4,
                            Risorse.Input.DOSE_CB3,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.TIFO_PARENTALE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.TIFO_PARENTALE);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_R
                        }
                );

                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.FEBB_GIALLA])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.FEBB_GIALLA);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_1D,
                            Risorse.Input.DOSE_R
                        }
                );

                    
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.COLERA])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.COLERA);
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
                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.RABBIA])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.RABBIA);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_CB3,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1,
                            Risorse.Input.DOSE_R
                        }
                );
                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.ENCEFALITE_GIAPP])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.ENCEFALITE_GIAPP);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_CB3,
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1,
                            Risorse.Input.DOSE_B,
                            Risorse.Input.DOSE_R
                        }
                );
                
            }else if(selezione.equals(Risorse.VACCINAZIONI[Risorse.Vaccinazioni.ENCEFALITE_ZECCHE])){
                dati = StoriaVaccinale.crea(Risorse.Vaccinazioni.ENCEFALITE_ZECCHE);
                dose.getItems().addAll(
                        new String[] {
                            Risorse.Input.DOSE_CB2,
                            Risorse.Input.DOSE_CB1,
                            Risorse.Input.DOSE_B,
                            Risorse.Input.DOSE_R
                        }
                );
            }
            
        });
    }
    
    /**
     * Chiusura senza salvare.
     * 
     * @param event 
     */
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.vistaCorrente.setScene(finestra != null ? finestra : Programma.finestraPrincipale);
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
        finestra = null;
    }
    
    /**
     * Metodo che permette di salvare i dati.
     * 
     * @param event pulsante Salva
     */
    @FXML
    void Salva(ActionEvent event) {
        // Salva solo i valori di pregressa e pregressa documentata eliminando il resto
        if(salvaPregressa())
            return;
        
        // Salva di dati quando non sono spuntate le caselle di 'pregressa' malattia
        if(dati != null){
            boolean erroreData = false;
            try{
                for(StoriaVaccinale vacc : dati)
                    vacc.setData(new DataOraria(data.getText()));
            }catch(Errore e){ // controlla ????????????????????????????????????
                erroreData = true;
                messaggio.setText(Risorse.Messaggi.ERRORE_DATA);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
            if(militare != null || !erroreData){
                boolean ok = true;
                for(StoriaVaccinale vaccinazione : dati){
                    if(vaccinazione.inputVaccinazioneCorretto()){
                        //System.out.println(vaccinazione);
                        Boolean vCivile = vaccCivile.getToggleGroup().getSelectedToggle().getUserData().equals(Risorse.Seleziona.ULTIMA_VACC_CIVILE);
                        
                        db.aggiungiStoriaVaccinale(
                                militare.getCognome(), 
                                militare.getNome(), 
                                militare.getNascita(), 
                                vaccinazione.getVaccino(), 
                                vaccinazione.getPregressa() == 'D' || vaccinazione.getPregressa() == 'R', 
                                vaccinazione.getPregressaDocumentata()== 'D' || vaccinazione.getPregressaDocumentata() == 'R', 
                                vCivile,
                                vaccinazione.getDose(), 
                                vaccinazione.getTipo(), 
                                vaccinazione.getData(), 
                                FinestraImpostazioniController.medico(),
                                Programma.utente
                        );
                        
                    }else{
                        ok = false;
                        messaggio.setText(Risorse.Messaggi.ERRORE_CAMPI_PRINCIPALI);
                        messaggio.setStyle(Risorse.Colore.ARANCIONE);
                    }
                }
                if(ok){
                    Programma.vistaCorrente.setScene(finestra != null ? finestra : Programma.finestraPrincipale);
                    Programma.adattaFinestra();
                    Programma.vistaCorrente.show();
                    aggiornaTabella();
                    finestra = null;
                }
            }else{
                if(militare == null){
                    messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }
        }else{
            messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
    }
    
    private boolean salvaPregressa(){
        if(pregressa.isSelected() || pregressaDocumentata.isSelected()){
            for(StoriaVaccinale _profilassi : dati){
                db.eliminaStoriaVaccinale(
                        militare.getCognome(), 
                        militare.getNome(), 
                        militare.getNascita(), 
                        _profilassi.getVaccino()
                );
                db.aggiungiStoriaVaccinale(
                        militare.getCognome(), 
                        militare.getNome(), 
                        militare.getNascita(), 
                        _profilassi.getVaccino(), 
                        pregressa.isSelected(), 
                        pregressaDocumentata.isSelected(), 
                        null,
                        "", 
                        "", 
                        DataOraria.NULL(), 
                        null,//medico .... 
                        Programma.utente
                );
            }
            Programma.vistaCorrente.setScene(finestra != null ? finestra : Programma.finestraPrincipale);
                    Programma.adattaFinestra();
                    Programma.vistaCorrente.show();
                    aggiornaTabella();
                    finestra = null;
            return true;
        }
        
        return false;
    }

    /**
     * Metodo che aggiora la tabella dello storico vaccinale presente nella finestra principale.
     */
    private void aggiornaTabella(){
        listaStoriaVaccinale.clear();
        if(militare != null){
            for(String _profilassi: db.profilassi()){
                Object[] record = db.trovaStoriaVaccinale(
                        militare.getCognome(), 
                        militare.getNome(), 
                        militare.getNascita(), 
                        _profilassi
                );
                if(record != null){
                    listaStoriaVaccinale.add(new StoriaVaccinale(
                                    (String)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.PROFILASSI], 
                                    (Boolean)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.PREGRESSA], 
                                    (Boolean)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.PREGRESSA_DOC], 
                                    (String)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.VACCINAZIONE_CIVILE], 
                                    (String)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.VACCINAZIONE_MILITARE], 
                                    (String)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.SIGLA_TIPO_VACCINO], 
                                    (String)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.DOSE], 
                                    (DataOraria)record[it.quasar_x7.infermeria.DaseDati.BASE_DATI.STORIA_VACCINALE.DATA_VACCINAZIONE]
                            )
                    );
                }
            }
            
        }
    }
    
    
    
    static void registraFinestraRitorno(Scene scenaCorrente) {
        finestra = scenaCorrente;
    }

    /**
     * Inizializza i campi selezionati esternamente dalla tabella della finestra chiamante.
     * 
     */
    private void caricaValoriDiSelezioneEsterna() {
        if(valoreIniziali != null){
            String valoreSigla = valoreIniziali.getTipo();
            if(valoreSigla != null){
                if(valoreSigla.equals("")){
                    profilassi.getSelectionModel().select(valoreIniziali.getVaccino());
                    if(profilassi.getValue().contains(Risorse.Profilassi.MENINGITE))
                        profilassi.getSelectionModel().select(Risorse.VACCINAZIONI[16]);
                }
                    
                for(int i=0; i < SIGLA_VACCINAZIONI.length; i++){
                    if(valoreSigla.equals(SIGLA_VACCINAZIONI[i])){
                        profilassi.getSelectionModel().select(VACCINAZIONI[i]);
                        break;
                    }
                }
            }else{
               profilassi.getSelectionModel().select(valoreIniziali.getVaccino()); 
               if(profilassi.getValue().contains(Risorse.Profilassi.MENINGITE))
                    profilassi.getSelectionModel().select(Risorse.VACCINAZIONI[16]);
            }
            
            profilassi.setDisable(true);
            if(valoreIniziali.getData() != null)
                data.setText(valoreIniziali.getData().stampaGiorno('/'));
            
            dose.getSelectionModel().select(valoreIniziali.getDose());
            String civile = valoreIniziali.getCivile();
            String militare = valoreIniziali.getMilitare();
            if(civile != null)
                vaccCivile.selectedProperty().setValue(civile.length() == 1);
            if(militare != null)
                vaccMilitare.selectedProperty().setValue(militare.length() == 1);
            
        }
        valoreIniziali = null;
    }
    
    public static void inizializzaCampi(StoriaVaccinale campi){
        if(campi != null){
            valoreIniziali = campi;
        }
    }

    
}
