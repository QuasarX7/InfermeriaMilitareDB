package it.quasar_x7.infermeria.controllo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.Errore;
import it.quasar_x7.java.utile.Testo;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * Questa classe implementa le funzionalità della finestra impostazioni 
 * temporanee delle vaccinazioni. Questa finestra è stata introdotta per rendere
 * i dati di ciascuna applicazione client in rete indipendenti, evitando di utilizzare 
 * direttamente i dati delle impostazioni vaccinali salvati nel DB. I dati 
 * modificati, verranno comunque salvati nel DB di condivisione senza interferire
 * sulle impostazioni attuali delle altre applicazioni client (come il nome del 
 * medico), se non su espliciata richiera di aggiornamento forzato.
 *
 * @author Dott. Domenico della Peruta
 */
public class FinestraImpostazioniController implements Initializable {

    public final static String CHIAVE_VACCINO = "dati_vaccinazione_";
    public final static String INFO_CHIAVE = "vaccinazione";
    public static Object[] impostazioni;
    
    @FXML
    private Label messaggio;
    
    @FXML
    private DatePicker campoData;
    
    @FXML
    private DatePicker campoData1;
    
    @FXML
    private DatePicker campoData2;
    
    @FXML
    private DatePicker campoData3;
    
    @FXML
    private DatePicker campoData4;
    
    @FXML
    private DatePicker campoData5;
    
    @FXML
    private DatePicker campoData6;
    
    @FXML
    private DatePicker campoData7;
    
    @FXML
    private DatePicker campoData8;

    @FXML
    private TextField campoLotto;
    
    @FXML
    private TextField campoLotto1;
    
    @FXML
    private TextField campoLotto2;
    
    @FXML
    private TextField campoLotto3;
    
    @FXML
    private TextField campoLotto4;
    
    @FXML
    private TextField campoLotto5;
    
    @FXML
    private TextField campoLotto6;
    
    @FXML
    private TextField campoLotto7;
    
    @FXML
    private TextField campoLotto8;

    @FXML
    private TextField campoDitta;
    
    @FXML
    private TextField campoDitta1;

    
    @FXML
    private TextField campoDitta2;
    
    @FXML
    private TextField campoDitta3;
    
    @FXML
    private TextField campoDitta4;
    
    @FXML
    private TextField campoDitta5;
    
    @FXML
    private TextField campoDitta6;
    
    @FXML
    private TextField campoDitta7;
    
    @FXML
    private TextField campoDitta8;
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextField campoNome1;
    
    @FXML
    private TextField campoNome2;
    
    @FXML
    private TextField campoNome3;

    @FXML
    private TextField campoNome4;
    
    @FXML
    private TextField campoNome5;
    
    @FXML
    private TextField campoNome6;
    
    @FXML
    private TextField campoNome7;

    @FXML
    private TextField campoNome8;





    @FXML
    private ChoiceBox<String> selezionaSom;

    @FXML
    private ChoiceBox<String> selezionaSom1;

    @FXML
    private ChoiceBox<String> selezionaSom2;

    @FXML
    private ChoiceBox<String> selezionaSom3;
    
    @FXML
    private ChoiceBox<String> selezionaSom4;

    @FXML
    private ChoiceBox<String> selezionaSom5;

    @FXML
    private ChoiceBox<String> selezionaSom6;

    @FXML
    private ChoiceBox<String> selezionaSom7;

    @FXML
    private ChoiceBox<String> selezionaSom8;

    @FXML
    private ComboBox<String> selezionaMedico;


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inizializzaCampi();
        if(impostazioni == null){
            caricaDB();
        }
        caricaImpostazioni();
    } 
    
    public void inizializzaCampi(){
        
        Programma.campoData(campoData);
        Programma.campoData(campoData1);
        Programma.campoData(campoData2);
        Programma.campoData(campoData3);
        Programma.campoData(campoData4);
        Programma.campoData(campoData5);
        Programma.campoData(campoData6);
        Programma.campoData(campoData7);
        Programma.campoData(campoData8);
        
        selezionaMedico.getItems().addAll(Programma.datiMedico.tuttiMedici());
        
        selezionaSom.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom1.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom2.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom3.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom4.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom5.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom6.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom7.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        selezionaSom8.getItems().addAll(
                new String[] {
                        Risorse.Input.VIA_SOMM_SC,
                        Risorse.Input.VIA_SOMM_IM,
                        Risorse.Input.VIA_SOMM_OS
                    }
        );
        
    }
    
    @FXML
    void chiusura(ActionEvent event) {
        Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
    }
    
    @FXML
    void pulsanteOK(ActionEvent event) {
        chiusura(event);
        caricaModifiche();
    }
    
    
    public void caricaDB(){
       if(impostazioni==null)
                impostazioni = new Object[46+9];//+9
        //scanzione da 0 a 45
        for(int i=0; i< impostazioni.length-9; i++){
            String s = Programma.datiImpostazioni.valore(CHIAVE_VACCINO+i);

            if(s != null){
                Testo formato = new Testo(s);

                if(formato.formatoDataSemplice()){//se è una data
                    try {
                        impostazioni[i] = new DataOraria(s);
                    } catch (Errore ex) {
                        Logger.getLogger(FinestraImpostazioniController.class.getName()).log(Level.SEVERE, null, ex);
                        messaggio.setText(Risorse.Messaggi.ERRORE_DATA+ " '"+ s +"'");
                        messaggio.setStyle(Risorse.Colore.ROSSO);
                        
                    }
                }else{
                    impostazioni[i] = s; 
                }
            }

        }
        //scanzopne da 100 a 140
        for(int i=100,k=46; i <= 140; i+=5,k++){
            String s = Programma.datiImpostazioni.valore(CHIAVE_VACCINO+i);

            if(s != null){
                impostazioni[k] = s; 
            }

        }
        
           
    }
    
    
    private void caricaImpostazioni(){
        DataOraria data = null;
        
        
        if(impostazioni!=null){
           
                //inizializza
            campoNome.setText((String) impostazioni[0]);
            campoDitta.setText((String)impostazioni[1]);
            campoLotto.setText((String)impostazioni[2]);
            //campoSerie.setText((String)impostazioni[3]);
            if(impostazioni[4] instanceof DataOraria){
                data = (DataOraria) impostazioni[4];
                campoData.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome1.setText((String) impostazioni[5]);
            campoDitta1.setText((String) impostazioni[6]);
            campoLotto1.setText((String) impostazioni[7]);
            //finestra.campoSerie1.setText((String) impostazioni[8]);
            if(impostazioni[9] instanceof DataOraria){
                data = (DataOraria)impostazioni[9];
                campoData1.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome2.setText((String) impostazioni[10]);
            campoDitta2.setText((String) impostazioni[11]);
            campoLotto2.setText((String) impostazioni[12]);
            //campoSerie2.setText((String) impostazioni[13]);
            if(impostazioni[14] instanceof DataOraria){
                    data = (DataOraria)impostazioni[14];
                    campoData2.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
                
            }

            campoNome3.setText((String) impostazioni[15]);
            campoDitta3.setText((String) impostazioni[16]);
            campoLotto3.setText((String) impostazioni[17]);
            //campoSerie3.setText((String) impostazioni[18]);
            if(impostazioni[19] instanceof DataOraria){
                data = (DataOraria)impostazioni[19];
                campoData3.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome4.setText((String) impostazioni[20]);
            campoDitta4.setText((String) impostazioni[21]);
            campoLotto4.setText((String) impostazioni[22]);
            //campoSerie4.setText((String) impostazioni[23]);
            if(impostazioni[24] instanceof DataOraria){
                data = (DataOraria)impostazioni[24];
                campoData4.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome5.setText((String) impostazioni[25]);
            campoDitta5.setText((String) impostazioni[26]);
            campoLotto5.setText((String) impostazioni[27]);
            //campoSerie5.setText((String) impostazioni[28]);
            if(impostazioni[29] instanceof DataOraria){
                data = (DataOraria)impostazioni[29];
                campoData5.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome6.setText((String) impostazioni[30]);
            campoDitta6.setText((String) impostazioni[31]);
            campoLotto6.setText((String) impostazioni[32]);
            //campoSerie6.setText((String) impostazioni[33]);
            if(impostazioni[34] instanceof DataOraria){
                data = (DataOraria)impostazioni[34];
                campoData6.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome7.setText((String) impostazioni[35]);
            campoDitta7.setText((String) impostazioni[36]);
            campoLotto7.setText((String) impostazioni[37]);
            //campoSerie7.setText((String) impostazioni[38]);
            if(impostazioni[39] instanceof DataOraria){
                data = (DataOraria)impostazioni[39];
                campoData7.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }

            campoNome8.setText((String) impostazioni[40]);
            campoDitta8.setText((String) impostazioni[41]);
            campoLotto8.setText((String) impostazioni[42]);
            //campoSerie8.setText((String) impostazioni[43]);
            if(impostazioni[44] instanceof DataOraria){
                data = (DataOraria)impostazioni[44];
                campoData8.setValue(LocalDate.of(data.anno(), data.mese(), data.giornoMese()));
            }            
            selezionaMedico.getSelectionModel().select((String) impostazioni[45]);
            
            //campi via somministrazione
            selezionaSom.getSelectionModel().select((String) impostazioni[46]);
            selezionaSom1.getSelectionModel().select((String) impostazioni[47]);
            selezionaSom2.getSelectionModel().select((String) impostazioni[48]);
            selezionaSom3.getSelectionModel().select((String) impostazioni[49]);
            selezionaSom4.getSelectionModel().select((String) impostazioni[50]);
            selezionaSom5.getSelectionModel().select((String) impostazioni[51]);
            selezionaSom6.getSelectionModel().select((String) impostazioni[52]);
            selezionaSom7.getSelectionModel().select((String) impostazioni[53]);
            selezionaSom8.getSelectionModel().select((String) impostazioni[54]);
            
            
            
        }else{
            caricaDB();
        }
    }
    
    
    public void caricaModifiche() {
        if(impostazioni == null){
                impostazioni = new Object[46+9];
            }
            //inizializza
            impostazioni[0]=campoNome.getText();
            impostazioni[1]=campoDitta.getText();
            impostazioni[2]=campoLotto.getText();
            //impostazioni[3]=campoSerie.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[4]=new DataOraria(campoData.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[4]=null;
                }

            impostazioni[5]=campoNome1.getText();
            impostazioni[6]=campoDitta1.getText();
            impostazioni[7]=campoLotto1.getText();
            //impostazioni[8]=finestra.campoSerie1.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[9]=new DataOraria(campoData1.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[9]=null;
                }
            impostazioni[10]=campoNome2.getText();
            impostazioni[11]=campoDitta2.getText();
            impostazioni[12]=campoLotto2.getText();
            //impostazioni[13]=finestra.campoSerie2.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[14]=new DataOraria(campoData2.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[14]=null;
                }
            impostazioni[15]=campoNome3.getText();
            impostazioni[16]=campoDitta3.getText();
            impostazioni[17]=campoLotto3.getText();
            //impostazioni[18]=finestra.campoSerie3.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[19]=new DataOraria(campoData3.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[19]=null;
                }
            impostazioni[20]=campoNome4.getText();
            impostazioni[21]=campoDitta4.getText();
            impostazioni[22]=campoLotto4.getText();
            //impostazioni[23]=finestra.campoSerie4.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[24]=new DataOraria(campoData4.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[24]=null;
                }
            impostazioni[25]=campoNome5.getText();
            impostazioni[26]=campoDitta5.getText();
            impostazioni[27]=campoLotto5.getText();
            //impostazioni[28]=finestra.campoSerie5.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[29]=new DataOraria(campoData5.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[29]=null;
                }
            impostazioni[30]=campoNome6.getText();
            impostazioni[31]=campoDitta6.getText();
            impostazioni[32]=campoLotto6.getText();
            //impostazioni[33]=finestra.campoSerie6.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[34]=new DataOraria(campoData6.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[34]=null;
                }
            impostazioni[35]=campoNome7.getText();
            impostazioni[36]=campoDitta7.getText();
            impostazioni[37]=campoLotto7.getText();
            //impostazioni[38]=finestra.campoSerie7.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[39]=new DataOraria(campoData7.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[39]=null;
                }
            impostazioni[40]=campoNome8.getText();
            impostazioni[41]=campoDitta8.getText();
            impostazioni[42]=campoLotto8.getText();
            //impostazioni[43]=finestra.campoSerie8.getText();
            //if(campoData.getValue() != null)
                try {
                    impostazioni[44]=new DataOraria(campoData8.getEditor().getText());
                } catch (Errore | java.lang.StringIndexOutOfBoundsException ex) {
                    impostazioni[44]=null;
                }
            impostazioni[45]=selezionaMedico.getValue();

            impostazioni[46]=selezionaSom.getValue();
            impostazioni[47]=selezionaSom1.getValue();
            impostazioni[48]=selezionaSom2.getValue();
            impostazioni[49]=selezionaSom3.getValue();
            impostazioni[50]=selezionaSom4.getValue();
            impostazioni[51]=selezionaSom5.getValue();
            impostazioni[52]=selezionaSom6.getValue();
            impostazioni[53]=selezionaSom7.getValue();
            impostazioni[54]=selezionaSom8.getValue();
            
            salvaDB();
            
    }
    
    public void salvaDB(){
        for(int i=0; i< impostazioni.length-9; i++){
            //if(impostazioni[i]!=null){
                if(impostazioni[i] instanceof String){
                    Programma.datiImpostazioni.valore(CHIAVE_VACCINO+i, INFO_CHIAVE, (String)impostazioni[i]);
                }else if(impostazioni[i] instanceof DataOraria){
                    Programma.datiImpostazioni.valore(CHIAVE_VACCINO+i, INFO_CHIAVE, ((DataOraria)impostazioni[i]).stampaGiorno());
                }else {
                    Programma.datiImpostazioni.valore(CHIAVE_VACCINO+i, INFO_CHIAVE, "");
                }
            //}
        }
        for(int i=100,k=46; i <= 140; i+=5,k++){
            if(impostazioni[k]!=null){
                if(impostazioni[k] instanceof String){
                    Programma.datiImpostazioni.valore(CHIAVE_VACCINO+i, INFO_CHIAVE, (String)impostazioni[k]);
                }
            }
        }
    }
    
    public static String medico(){
        if(impostazioni != null)
            if(impostazioni[45] != null)
                return impostazioni[45].toString();
        return null;
    }
    
    @FXML
    void forzaAggiornamento(ActionEvent event) {
        caricaDB();
        caricaImpostazioni();
    }
    
}
