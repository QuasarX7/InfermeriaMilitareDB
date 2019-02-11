package it.quasar_x7.infermeria.controllo;

import static it.quasar_x7.infermeria.controllo.FinestraRicercaDataController.listaMilitari;
import static it.quasar_x7.infermeria.programma.Programma.datiVaccinazioni;

import it.quasar_x7.infermeria.modello.AttivitàVaccinale;
import it.quasar_x7.infermeria.modello.SituazioneVaccini;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class AttivitàVaccinaleController implements Initializable {

    public static ObservableList<AttivitàVaccinale> listaAttività = FXCollections.observableArrayList();
    public static ObservableList<SituazioneVaccini> listaVaccini = FXCollections.observableArrayList();
    
    @FXML
    private DatePicker dataInizio;

    @FXML
    private DatePicker dataFine;

    @FXML
    private TableView<AttivitàVaccinale> tabellaAnamnesiVaccinale;

    @FXML
    private TableColumn<AttivitàVaccinale, DataOraria> colonnaData;

    @FXML
    private TableColumn<AttivitàVaccinale, String> colonnaCp;

    @FXML
    private TableColumn<AttivitàVaccinale, Long> colonnaMilitari;
    
      
    
    
    @FXML
    private TableView<SituazioneVaccini> tabellaVaccini;
    
    @FXML
    private TableColumn<SituazioneVaccini, String> colonnaSesso;

    @FXML
    private TableColumn<SituazioneVaccini, Long> colonnaNumero;
    
    @FXML
    private TableColumn<SituazioneVaccini, String> colonnaProfilassi;

    @FXML
    private TableColumn<SituazioneVaccini, String> colonnaEtà;

    @FXML
    private TableColumn<SituazioneVaccini, String> colonnaDose;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inizializzazioneTabellaAttività();
        inizializzazioneTabellaVaccini();
    }   
    
    private void inizializzazioneTabellaAttività(){
        colonnaData.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DATA));
        colonnaCp.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.CP));
        colonnaMilitari.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.MILITARI));
        tabellaAnamnesiVaccinale.setItems(listaAttività);
    }
    
    private void inizializzazioneTabellaVaccini(){
        colonnaProfilassi.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.PROFILASSI));
        colonnaDose.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO_DOSE));
        colonnaSesso.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.SESSO));
        colonnaEtà.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.ETA));
        colonnaNumero.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.N_VACCINI));
        tabellaVaccini.setItems(listaVaccini);
    }
    /**
     * Chiudi finestra
     * @param event 
     */
    @FXML
    private void chiusura(ActionEvent event) {
        listaAttività.clear();
        listaVaccini.clear();
        listaMilitari.clear();
        Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
        
    }
    
    /**
     * Riduci la finestra ad icona.
     * @param event 
     */
    @FXML
    private void riduciFinestra(ActionEvent event) {
        Programma.riduciFinestra();
    }

    @FXML
    void cerca(ActionEvent event) {
        listaAttività.clear();
        listaVaccini.clear();
        DataOraria inizio = campoData(dataInizio.getValue());
        DataOraria fine = campoData(dataFine.getValue());
        if(inizio != null && fine != null){
            ArrayList<Object[]> listaA = datiVaccinazioni.situazioneVaccinati(inizio, fine);
            if(listaA != null)
                for(Object[] visita:listaA){
                    listaAttività.add(
                            new AttivitàVaccinale(
                                    (DataOraria)visita[0],
                                    (String)visita[1],
                                    (Long)visita[2]
                            )
                    );
                }
            ArrayList<Object[]> listaV = datiVaccinazioni.situazioneVaccini(inizio, fine);
            if(listaV != null)
                for(Object[] situazione:listaV){
                    Long età = 0L;
                    if(situazione[3] != null)
                        età= (Long)situazione[3];
                    listaVaccini.add(
                            new SituazioneVaccini(
                                    (String)situazione[0],
                                    (String)situazione[1],
                                    (String)situazione[2],
                                    età > 0 ? SituazioneVaccini.MAGGIORE_25 : SituazioneVaccini.MINORE_25,
                                    (Long)situazione[4]
                            )
                    );
                }
            
        }
    }
    
    private DataOraria campoData(LocalDate data){
        DataOraria dataOraria = null;
        if(data != null){
            dataOraria = new DataOraria(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
        }
        return dataOraria;
    }

    

     
    
}
