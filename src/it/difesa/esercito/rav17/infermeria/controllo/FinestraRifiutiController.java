package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.modello.Rifiuti;
import it.difesa.esercito.rav17.infermeria.programma.Programma;
import static it.difesa.esercito.rav17.infermeria.programma.Programma.datiRifiuti;
import it.difesa.esercito.rav17.infermeria.programma.Risorse.ColoreTabella;
import static it.difesa.esercito.rav17.infermeria.programma.Risorse.FXML.FINESTRA_MENSILITA_RIFIUTI;
import it.difesa.esercito.rav17.infermeria.programma.Risorse.Modello;
import it.quasar_x7.java.utile.DataOraria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static it.difesa.esercito.rav17.infermeria.programma.Risorse.FXML.FINESTRA_VOCE_RIFIUTI;
import static it.difesa.esercito.rav17.infermeria.programma.Risorse.Input.MEDICINALI_SCADUTI;
import static it.difesa.esercito.rav17.infermeria.programma.Risorse.Input.RIFIUTI_PERICOLOSI;
import static it.difesa.esercito.rav17.infermeria.programma.Risorse.Messaggi.ELIMINA_VOCE;
import java.util.ArrayList;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Dr Domenico della Peruta
 */
public class FinestraRifiutiController implements Initializable {

    public static final ObservableList<Rifiuti> lista = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<Rifiuti, DataOraria> colonnaDataVersamento;

    @FXML
    private TableColumn<Rifiuti, DataOraria> colonnaDataProduzione;

    @FXML
    private TableColumn<Rifiuti, String> colonnaProtocollo;

    @FXML
    private TableColumn<Rifiuti, String> colonnaVerbaleVersamento;

    @FXML
    private TableView<Rifiuti> tabella;

    @FXML
    private TableColumn<Rifiuti, String> colonnaCodice;
    
    @FXML
    private TableColumn<Rifiuti, Long> colonnaQuantità;

    @FXML
    private TableColumn<Rifiuti, Long> colonnaVolume;
    
    @FXML
    private TableColumn<Rifiuti, Long> colonnaContenitori;

    @FXML
    private TextField campoVolumePericolosi;

    @FXML
    private TextField campoQuantitàPericolosi;

    @FXML
    private TextField campoContenitoriPericolosi;

    @FXML
    private TextField campoVolumeMedicinali;

    @FXML
    private TextField campoQuantitàMedicinali;

    @FXML
    private TextField campoContenitoriMedicinali;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colonnaProtocollo.setCellValueFactory(new PropertyValueFactory<>(Modello.PROTOCOLLO));
        colonnaCodice.setCellValueFactory(new PropertyValueFactory<>(Modello.CODICE));
        colonnaDataProduzione.setCellValueFactory(new PropertyValueFactory<>(Modello.PRODUZIONE));
        colonnaQuantità.setCellValueFactory(new PropertyValueFactory<>(Modello.QUANTITA));
        colonnaContenitori.setCellValueFactory(new PropertyValueFactory<>(Modello.CONTENITORE));
        colonnaVolume.setCellValueFactory(new PropertyValueFactory<>(Modello.VOLUME));
        colonnaDataVersamento.setCellValueFactory(new PropertyValueFactory<>(Modello.VERSAMENTO));
        colonnaVerbaleVersamento.setCellValueFactory(new PropertyValueFactory<>(Modello.VERB_VERSAMENTO));
        
        tabella.setItems(lista);
        tabella.setRowFactory((TableView<Rifiuti> param) -> new TableRow<Rifiuti>() {
            @Override
            protected void updateItem(Rifiuti rifiuti, boolean empty) {
                if (!empty && rifiuti != null) {
                    
                    DataOraria versamento = rifiuti.getDataVerbale();
                    if(versamento != null){
                        setStyle(ColoreTabella.GRIGIOCHIARO);
                    }else{
                        String codice = rifiuti.getCodice();
                        if(codice != null){
                            if(codice.equals(RIFIUTI_PERICOLOSI)){
                                setStyle(ColoreTabella.ROSSOCHIARO);
                            }else{
                                setStyle(ColoreTabella.VERDECHIARO);

                            }
                        }else{
                            setStyle(ColoreTabella.BIANCO);
                        }
                    }
                }
                super.updateItem(rifiuti, empty);
                
            }
            
        });
        caricaCampi();
        caricaTabella();
        
    }   
    
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event){
        Programma.vistaCorrente.setScene(
                Programma.finestraPrincipale
        );
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
    }
    
  
    void caricaTabella(){
        lista.clear();
        ArrayList<Object[]> rifiuti = datiRifiuti.registroRifiuti();
        if(rifiuti != null)
            for(Object[] voce: rifiuti){
                DataOraria produzione=null, versamento=null;
                try{
                    produzione = (DataOraria)voce[1];
                }catch(java.lang.ClassCastException e){
                }
                try{
                    versamento = (DataOraria)voce[8];
                }catch(java.lang.ClassCastException e){
                }
                lista.add(new Rifiuti(
                                (String)voce[0],
                                produzione,
                                (String)voce[2],
                                (Long)voce[4],
                                (Long)voce[3],
                                (Long)voce[5],
                                versamento,
                                (String)voce[9],
                                (String)voce[6],
                                (String)voce[7]
                        )
                );
            }
    }
    
    @FXML
    private void aggiungi(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(FINESTRA_VOCE_RIFIUTI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(FinestraRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    @FXML
    private void attivitàMensile(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(FINESTRA_MENSILITA_RIFIUTI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(FinestraRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    @FXML
    private void aggiungiDati(ActionEvent event) {
        aggiungi(event);
    }
    
    @FXML
    private void selezionaDati(ActionEvent event) {
        VoceRifiutiController.rifiuti  = tabella.getSelectionModel().getSelectedItem();
        if(VoceRifiutiController.rifiuti != null)
            aggiungi(event);
        
    }
    
    @FXML
    private void eliminaDati(ActionEvent event) {
        Rifiuti rifiuti = tabella.getSelectionModel().getSelectedItem();
        if(rifiuti != null)
            try {
                Programma.finestraConferma(this, ELIMINA_VOCE, () -> {
                    datiRifiuti.elimina(rifiuti.getProtocollo());
                });
                lista.remove(rifiuti);
            } catch (IOException ex) {
                Logger.getLogger(FinestraRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void caricaCampi() {
        ArrayList<Object[]> rifiutiPericolosi = datiRifiuti.giacenzaMagazzino(RIFIUTI_PERICOLOSI, DataOraria.creaDataOggi());
        if(rifiutiPericolosi != null)
            if(rifiutiPericolosi.size() > 0){
                Object[] record = rifiutiPericolosi.get(0);
                campoContenitoriPericolosi.setText(record[0].toString());
                campoQuantitàPericolosi.setText(record[1].toString());
                campoVolumePericolosi.setText(record[2].toString());
            }
        
        ArrayList<Object[]> rifiutiMedicinali = datiRifiuti.giacenzaMagazzino(MEDICINALI_SCADUTI, DataOraria.creaDataOggi());
        if(rifiutiMedicinali != null)
            if(rifiutiMedicinali.size() > 0){
                Object[] record = rifiutiMedicinali.get(0);
                campoContenitoriMedicinali.setText(record[0].toString());
                campoQuantitàMedicinali.setText(record[1].toString());
                campoVolumeMedicinali.setText(record[2].toString());
            }
    
    }
    
    
    
}
