package it.quasar_x7.infermeria.controllo;

import static it.quasar_x7.infermeria.controllo.FinestraPrincipaleController.listaStoriaVaccinale;
import static it.quasar_x7.infermeria.controllo.FinestraPrincipaleController.militare;
import static it.quasar_x7.infermeria.programma.Programma.datiImpostazioni;
import static it.quasar_x7.infermeria.programma.Programma.datiMilitari;
import static it.quasar_x7.infermeria.programma.Programma.datiStoricoVaccinale;
import static it.quasar_x7.infermeria.programma.Programma.datiVaccinazioni;

import it.quasar_x7.infermeria.DaseDati.BASE_DATI;
import it.quasar_x7.infermeria.controllo.Report;
import it.quasar_x7.infermeria.modello.StoriaVaccinale;
import it.quasar_x7.infermeria.modello.Vaccinazione;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.Errore;
import it.quasar_x7.javafx.CampoTesto;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.1 ultima modifica 04/06/2017
 */
public class AnamnesiVaccinaleController implements Initializable {

    
    
    
    public static ObservableList<Vaccinazione> listaVaccinazione;
    
    public static DataOraria data;
    
    @FXML
    private TableView<StoriaVaccinale> tabellaStoricoVaccinale;
    
    @FXML
    private TableColumn<StoriaVaccinale, String> colonnaVaccino;
    
    @FXML
    private TableColumn<StoriaVaccinale, Boolean> colonnaPregressa;
    
    @FXML
    private TableColumn<StoriaVaccinale, Boolean> colonnaPregressaDoc;
    
    @FXML
    private TableColumn<StoriaVaccinale, String> colonnaVaccCivile;
    
    @FXML
    private TableColumn<StoriaVaccinale, String> colonnaVaccMilitare;
    
    @FXML
    private TableColumn<StoriaVaccinale, String> colonnaTipoDose;
    
    @FXML
    private TableColumn<StoriaVaccinale, String> colonnaTipoVaccino;
    
    @FXML
    private TableColumn<StoriaVaccinale, DataOraria> colonnaDataVaccinazione;
    
    
    @FXML
    private DatePicker campoData;
     
    @FXML
    private Label messaggio;
    
    @FXML
    private Label etichettaMilitare;
    
    @FXML
    private Label campoMedico;
    
    @FXML
    private TableView<Vaccinazione> tabellaVaccinazioni;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaProfilassi;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaDose;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaSomministrazione;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaNomeVaccino;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaDitta;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaLotto;
    
    @FXML
    private TableColumn<Vaccinazione, DataOraria> colonnaScadenza;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaInadempienza;
    
    
    @FXML
    private TextField campoVaccinoReazione1;

    @FXML
    private TextField campoVaccinoReazione2;

    @FXML
    private TextField campoVaccinoReazione3;

    @FXML
    private TextField campoVaccinoReazione4;

    @FXML
    private TextField dataVaccinoReazione1;

    @FXML
    private TextField dataVaccinoReazione2;
    
    
    @FXML
    private TextField dataVaccinoReazione3;
    
    
    @FXML
    private TextField dataVaccinoReazione4;

    @FXML
    private ChoiceBox<String> menuGeneraliLievi1; //
    
    @FXML
    private ChoiceBox<String> menuGeneraliLievi2;
    
   @FXML
    private ChoiceBox<String> menuGeneraliLievi3;

    @FXML
    private ChoiceBox<String> menuGeneraliLievi4;

    @FXML
    private ChoiceBox<String> menuGeneraliGravi1;

    @FXML
    private ChoiceBox<String> menuGeneraliGravi2;

    @FXML
    private ChoiceBox<String> menuGeneraliGravi3;

    @FXML
    private ChoiceBox<String> menuGeneraliGravi4;
    
    @FXML
    private ChoiceBox<String> menuLocaliGravi1;

    @FXML
    private ChoiceBox<String> menuLocaliGravi2;

    @FXML
    private ChoiceBox<String> menuLocaliGravi3;

    @FXML
    private ChoiceBox<String> menuLocaliGravi4;

    @FXML
    private ChoiceBox<String> menuAllergiaPollo;
    
    @FXML
    private ChoiceBox<String> menuAllergiaAnatra;
    
    @FXML
    private ChoiceBox<String> menuAllergiaBovina;
    
    @FXML
    private ChoiceBox<String> menuAllergiaFormaldeide;
    
    @FXML
    private ChoiceBox<String> menuAllergiaNeomicina;
    
    @FXML
    private ChoiceBox<String> menuAllergiaStreptomicina;
    
    @FXML
    private ChoiceBox<String> menuAllergiaKanamicina;
    
    @FXML
    private ChoiceBox<String> menuAllergiaPolimixin;
    
    @FXML
    private ChoiceBox<String> menuAllergiaCompMercuriali;
    
    @FXML
    private ChoiceBox<String> menuAllergiaAltro;
    
    @FXML
    private ChoiceBox<String> menuConvivenzaImmunodepressioni;
    
    @FXML
    private ChoiceBox<String> menuConvivenzaTumoriSangue;
    
    @FXML
    private ChoiceBox<String> menuConvivenzaGravidanza;
    
    
    @FXML
    private ChoiceBox<String> menuAnamnesiFebbre;
    
    @FXML
    private ChoiceBox<String> menuAnamnesiVieAeree;
    
    @FXML
    private ChoiceBox<String> menuAnamnesiDiarrea;
    
    @FXML
    private ChoiceBox<String> menuAnamnesiTerapia;
    
    @FXML
    private ChoiceBox<String> menuAnamnesiEmotrafusioni;
    
    
    @FXML
    private ChoiceBox<String> menuPeriodicitaMestruale;
    
    @FXML
    private ChoiceBox<String> menuStatoGravido;
    
    @FXML
    private ChoiceBox<String> menuTestGravidanza;
    
    @FXML
    private TextField dataUltimaMestruazione;
    
    @FXML
    private TextField dataTestGravidanza;
    
    static private Scene finestra;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(listaVaccinazione == null)
            listaVaccinazione = FXCollections.observableArrayList();
        inizializzaPannelloStoricoVaccinale();
        inizializzaPannelloVaccinazioni();
        caricaTabellaStoriaVaccinale();
        inizailizzaData();
        caricaTabellaVaccinazioni();
        inizializzaReazioniAiVaccini();
        inizializzaAnamnesiVaccinale();
        
        if(FinestraImpostazioniController.medico() != null)
                    campoMedico.setText(FinestraImpostazioniController.medico());
        
        etichettaMilitare.setText(String.format("%s %s %s %s",militare.getGrado(),militare.getCognome(),militare.getNome(),militare.getNascita().stampaGiorno()));
    }    
    
    static void registraFinestraRitorno(Scene scenaCorrente) {
        finestra = scenaCorrente;
    }
     
    private void inizializzaReazioniAiVaccini() {
        CampoTesto.aggiungiLimiteTesto(campoVaccinoReazione1, 40);
        CampoTesto.aggiungiLimiteTesto(campoVaccinoReazione2, 40);
        CampoTesto.aggiungiLimiteTesto(campoVaccinoReazione3, 40);
        CampoTesto.aggiungiLimiteTesto(campoVaccinoReazione4, 40);
        menuReazioneVaccino(menuGeneraliLievi1);
        menuReazioneVaccino(menuGeneraliLievi2);
        menuReazioneVaccino(menuGeneraliLievi3);
        menuReazioneVaccino(menuGeneraliLievi4);
        menuReazioneVaccino(menuGeneraliGravi1);
        menuReazioneVaccino(menuGeneraliGravi2);
        menuReazioneVaccino(menuGeneraliGravi3);
        menuReazioneVaccino(menuGeneraliGravi4);
        menuReazioneVaccino(menuLocaliGravi1);
        menuReazioneVaccino(menuLocaliGravi2);
        menuReazioneVaccino(menuLocaliGravi3);
        menuReazioneVaccino(menuLocaliGravi4);
        CampoTesto.aggiungiMascheraInput(dataVaccinoReazione1, Risorse.MascheraCampi.DATA, Risorse.MascheraCampi.CARATTERE);
        CampoTesto.aggiungiMascheraInput(dataVaccinoReazione2, Risorse.MascheraCampi.DATA, Risorse.MascheraCampi.CARATTERE);
        CampoTesto.aggiungiMascheraInput(dataVaccinoReazione3, Risorse.MascheraCampi.DATA, Risorse.MascheraCampi.CARATTERE);
        CampoTesto.aggiungiMascheraInput(dataVaccinoReazione4, Risorse.MascheraCampi.DATA, Risorse.MascheraCampi.CARATTERE);
        caricaValoriReazioneAiVaccini();
    }
    
    
        
    private void inizializzaAnamnesiVaccinale() {
        menuAllergia(menuAllergiaPollo);
        menuAllergia(menuAllergiaAnatra);
        menuAllergia(menuAllergiaBovina);
        menuAllergia(menuAllergiaFormaldeide);
        menuAllergia(menuAllergiaNeomicina);
        menuAllergia(menuAllergiaStreptomicina);
        menuAllergia(menuAllergiaKanamicina);
        menuAllergia(menuAllergiaPolimixin);
        menuAllergia(menuAllergiaCompMercuriali);
        menuAllergia(menuAllergiaAltro);
       
        menuAnamnesi(menuConvivenzaGravidanza);
        menuAnamnesi(menuConvivenzaTumoriSangue);
        menuAnamnesi(menuConvivenzaImmunodepressioni);
        
        menuAnamnesi(menuAnamnesiDiarrea);
        menuAnamnesi(menuAnamnesiEmotrafusioni);
        menuAnamnesi(menuAnamnesiFebbre);
        menuAnamnesi(menuAnamnesiTerapia);
        menuAnamnesi(menuAnamnesiVieAeree);
        
        menuPeriodicitaMestruale.getItems().addAll(
                new String[]{"",Risorse.Input.REGOLARE,Risorse.Input.IRREGOLARE}
        );
        menuStatoGravido.getItems().addAll(
                new String[]{"",Risorse.Input.NON_INCORSO,Risorse.Input.INCORSO,Risorse.Input.NON_NOTO}
        );
        menuTestGravidanza.getItems().addAll(
                new String[]{"",Risorse.Input.NEGATIVO,Risorse.Input.POSITIVO}
        );
        CampoTesto.aggiungiMascheraInput(dataTestGravidanza, Risorse.MascheraCampi.DATA, Risorse.MascheraCampi.CARATTERE);
        CampoTesto.aggiungiMascheraInput(dataUltimaMestruazione, Risorse.MascheraCampi.DATA, Risorse.MascheraCampi.CARATTERE);
        
        
        caricaAnamnesiVaccinale();
    }
    
    private void menuReazioneVaccino(ChoiceBox<String> menu) {
        menu.getItems().addAll(new String[]{"",Risorse.Input.SI,Risorse.Input.NO});
    }
    
    private void menuAllergia(ChoiceBox<String> menu) {
        menu.getItems().addAll(new String[]{"",Risorse.Input.D,Risorse.Input.S});
    }
    
    private void menuAnamnesi(ChoiceBox<String> menu) {
        menu.getItems().addAll(new String[]{"",Risorse.Input.X});
    }
    
    private void inizailizzaData(){
        campoData.setEditable(false);
        if(data != null){
            campoData.setValue(LocalDate.parse(data.stampaGiornoInverso()));
            campoData.setStyle(Risorse.Colore.ARANCIONE);
        }else{
            campoData.setValue(LocalDate.now());
            campoData.setDisable(true);
            campoData.setStyle(Risorse.Colore.NERO);
            data = new DataOraria(
                    campoData.getValue().getDayOfMonth(),
                    campoData.getValue().getMonthValue(),
                    campoData.getValue().getYear()
            );
        }
            
    }
    /**
     * Inizializza dati nel pannello secondario della storia vaccinale.
     * 
     */
    private void inizializzaPannelloStoricoVaccinale(){
        colonnaVaccino.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.VACCINO));
        colonnaPregressa.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.MALATTIA_PREGRESSA));
        colonnaPregressaDoc.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.MALATIA_PREGRESSA_DOC));
        colonnaVaccCivile.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.ULTIMA_VACC_CIVILE));
        colonnaVaccMilitare.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.ULTIMA_VACC_MILITARE));
        colonnaTipoDose.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO_DOSE));
        colonnaTipoVaccino.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO));
        colonnaDataVaccinazione.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DATA));
        tabellaStoricoVaccinale.setItems(listaStoriaVaccinale);
    }
    
    
    private void inizializzaPannelloVaccinazioni() {
        colonnaProfilassi.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.PROFILASSI));
        colonnaDose.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO_DOSE));
        colonnaSomministrazione.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.VIA_SOMM));
        colonnaNomeVaccino.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.VACCINO));
        colonnaDitta.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DITTA_VACC));
        colonnaLotto.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.LOTTO_VACC));
        colonnaScadenza.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.SCADENZA));
        colonnaInadempienza.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.INADEMPIENZA));
        tabellaVaccinazioni.setItems(listaVaccinazione);
        
    }
    
    @FXML
    private void modificaStoriaVaccinale(ActionEvent event) {
        try {
            ModificaVoceStoriaVaccinaleController.registraFinestraRitorno(Programma.scenaCorrente());
            ModificaVoceStoriaVaccinaleController.inizializzaCampi(tabellaStoricoVaccinale.getSelectionModel().getSelectedItem());
            Programma.vistaCorrente.setScene(
                    new Scene(
                            FXMLLoader.load(getClass().getResource(Risorse.FXML.MODIFICA_VACCINAZIONE_PASS))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
            messaggio.setStyle(Risorse.Colore.ROSSO);
            Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void eliminaStoriaVaccinale(ActionEvent event) {
        String profilassi = tabellaStoricoVaccinale.getSelectionModel().getSelectedItem().getVaccino();
        datiStoricoVaccinale.eliminaStoriaVaccinale(
                militare.getCognome(), militare.getNome(), militare.getNascita(), 
                profilassi
        );
        datiStoricoVaccinale.aggiungiStoriaVaccinale(
                militare.getCognome(), militare.getNome(), militare.getNascita(), 
                profilassi, 
                false, false, // pregressa..
                null, // vaccinazione di origine civile (true), militare (false) o nulla
                "",//dose
                "",//vaccino
                null,//data
                null, // medicoVaccinatore
                Programma.utente // utente
        );
        caricaTabellaStoriaVaccinale();
    }
    
        
    private void caricaTabellaVaccinazioni(){
        listaVaccinazione.clear();
        if(militare != null){
            ArrayList<Object[]> vaccini = datiVaccinazioni.trovaVaccinazioni(
                    militare.getCognome(), 
                    militare.getNome(), 
                    militare.getNascita(),
                    data == null ? DataOraria.creaDataOggi() : data
            );
            if(vaccini != null)
                for(Object[] vaccino: vaccini){
                    listaVaccinazione.add(new Vaccinazione(
                                (DataOraria)vaccino[BASE_DATI.VACCINAZIONI.DATA_SEDUTA],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.TIPO_PROFILASSI],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.DOSE],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.VIA_SOMMINISTRAZIONE],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.NOME_VACCINO],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.DITTA],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.LOTTO],
                                (DataOraria)vaccino[BASE_DATI.VACCINAZIONI.SCADENZA],
                                (String)vaccino[BASE_DATI.VACCINAZIONI.INADEMPIENZA]
                            )
                    );
                }
        }
    }
    
    private boolean caricaTabellaStoriaVaccinale(){
        listaStoriaVaccinale.clear();
        boolean trovato=false;
        if(militare != null){
            for(String profilassi: datiStoricoVaccinale.profilassi()){
                Object[] record = datiStoricoVaccinale.trovaStoriaVaccinale(
                        militare.getCognome(), 
                        militare.getNome(), 
                        militare.getNascita(), 
                        profilassi
                );
                if(record != null){
                    listaStoriaVaccinale.add(
                            new StoriaVaccinale(
                                    (String)record[BASE_DATI.STORIA_VACCINALE.PROFILASSI], 
                                    (Boolean)record[BASE_DATI.STORIA_VACCINALE.PREGRESSA], 
                                    (Boolean)record[BASE_DATI.STORIA_VACCINALE.PREGRESSA_DOC], 
                                    (String)record[BASE_DATI.STORIA_VACCINALE.VACCINAZIONE_CIVILE], 
                                    (String)record[BASE_DATI.STORIA_VACCINALE.VACCINAZIONE_MILITARE], 
                                    (String)record[BASE_DATI.STORIA_VACCINALE.SIGLA_TIPO_VACCINO], 
                                    (String)record[BASE_DATI.STORIA_VACCINALE.DOSE], 
                                    (DataOraria)record[BASE_DATI.STORIA_VACCINALE.DATA_VACCINAZIONE]
                            )
                    );
                    trovato=true;
                }else{
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
                    messaggio.setStyle(Risorse.Colore.ARANCIONE);
                }
            }
            
        }
        return trovato;
    }
    
    @FXML
    private void aggiungiVaccino(ActionEvent event) {
        try {
            salva();
            AggiungiVacciniController.finestraAnamnesi = this;
            
            Programma.vistaCorrente.setScene(
                    
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_AGGIUNGI_VACCINO))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }
    
    @FXML
    private void eliminaVaccino(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Vaccinazione vaccinazione = tabellaVaccinazioni.getSelectionModel().getSelectedItem();
                if(vaccinazione != null){
                    try {
                        Programma.finestraConferma(
                                this,
                                String.format(
                                        Risorse.Messaggi.DOMANDA_ELIMINA_VACCINO,
                                        vaccinazione.getProfilassi() + " " + vaccinazione.getDose()
                                ),
                                () -> {
                                    boolean ok = Programma.datiVaccinazioni.eliminaVaccinazione(
                                            vaccinazione.getProfilassi(),vaccinazione.getData(),
                                            militare.getCognome(),militare.getNome(), militare.getNascita()
                                    );
                                    if(ok){
                                        FinestraPrincipaleController.listaVaccinazione.remove(vaccinazione);
                                        listaVaccinazione.remove(vaccinazione);
                                    }else{
                                        try {
                                            Programma.finestraAvviso(this,Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VACCINO);
                                        } catch (IOException ex1) {
                                            Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex1);
                                        }
                                        messaggio.setText(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VACCINO);
                                        messaggio.setStyle(Risorse.Colore.ROSSO);
                                    }
                                }
                        );
                    } catch (IOException ex) {
                        messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
                        messaggio.setStyle(Risorse.Colore.ROSSO);
                        Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }else{
                    try {
                        Programma.finestraAvviso(this,Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    } catch (IOException ex1) {
                        Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }else{
                try {
                    Programma.finestraAvviso(this,Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                } catch (IOException ex1) {
                    Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    public  void inadempienza(Vaccinazione vaccinazione){
        if(vaccinazione != null){
            java.awt.EventQueue.invokeLater(
                    () -> {
                        String sInadempienza= (String)JOptionPane.showInputDialog(
                                null,
                                "Motivo inadempienza", "tipo di inadempienza",
                                JOptionPane.QUESTION_MESSAGE,
                                new javax.swing.ImageIcon(getClass().getResource(Risorse.Immagine.ATTESA)),
                                Risorse.INADEMPIENZA_VACCINAZIONE,
                                Risorse.INADEMPIENZA_VACCINAZIONE[0]
                        );

                        if(sInadempienza != null){
                            Object[] record = new Object[]{
                                vaccinazione.getData(),
                                "(NO) "+vaccinazione.getProfilassi(),
                                militare.getCognome(),
                                militare.getNome(), 
                                militare.getNascita(),
                                "--",
                                "--",
                                "------------",
                                "------------",
                                "------------",
                                "------",
                                DataOraria.NULL(),
                                sInadempienza,
                            };

                            Programma.datiVaccinazioni.modificaVaccinazione(
                                    vaccinazione.getProfilassi(),vaccinazione.getData(),
                                    militare.getCognome(),militare.getNome(), militare.getNascita(),
                                    record
                            );

                            FinestraPrincipaleController.listaVaccinazione.remove(vaccinazione);
                            listaVaccinazione.remove(vaccinazione);

                            Vaccinazione vacc = new Vaccinazione(record);
                            listaVaccinazione.add(vacc);
                            FinestraPrincipaleController.listaVaccinazione.add(vacc);
                        }
                    }
            );

        }
    }
    
    @FXML
    private void inadempienzaVaccinazione(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Vaccinazione vaccinazione = tabellaVaccinazioni.getSelectionModel().getSelectedItem();
                inadempienza(vaccinazione);
            }else{
                try {
                    Programma.finestraAvviso(this,Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                } catch (IOException ex1) {
                    Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }

    @FXML
    private void stampaStoriaVaccinale(ActionEvent event) {
        Object[] datiM = datiMilitari.trovaMilitare(militare.getCognome(), militare.getNome(), militare.getNascita());
        String scuola = "";
        if(datiM != null)
          if(datiM[BASE_DATI.MILITARE.SCUOLA] != null)
              scuola =  datiM[BASE_DATI.MILITARE.SCUOLA].toString();
        String[][] tabella = FinestraPrincipaleController.tabellaStoricoVaccinale();
        if(tabella != null){
            Report.stampaStoriaVaccinale(
                    militare.vettore(scuola),
                    datiImpostazioni.valore(Risorse.Impostazioni.TITOLO_REPARTO),
                    datiImpostazioni.valore(Risorse.Impostazioni.TITOLO_UFFICIO),
                    datiImpostazioni.valore(Risorse.Impostazioni.SEDE), 
                    tabella,
                    FinestraImpostazioniController.medico()//datiImpostazioni.valore(Risorse.Impostazioni.KEY_MEDICO_VACCINATORE)
            );
        }else{
            messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
    }
    
    
    
    
    @FXML
    private void salvaChiudi(ActionEvent event){
        if(salva()){
//            try {
//                Programma.finestraConferma(
//                        this,
//                        Risorse.Conferma.SALVA_DATI, 
//                        () -> {
                            Programma.vistaCorrente.setScene(
                                    Programma.finestraPrincipale
                            );
                            Programma.adattaFinestra();
                            Programma.vistaCorrente.show();
                            data = null;
//                        }
//                );
//            } catch (IOException ex) {
//                Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            
        }
    }
    
    @FXML
    public void stampaAnamnesi(ActionEvent event) {
        
        if(salva()){
            Programma.vistaCorrente.setScene(
                    finestra != null ? finestra : Programma.finestraPrincipale
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
            data = null;
            finestra = null;
            
        }
        
        Object[] reazioni = new Object[]{
            campoVaccinoReazione1.getText(),menuLocaliGravi1.getValue(),menuGeneraliLievi1.getValue(),menuGeneraliGravi1.getValue(),dataVaccinoReazione1.getText(),
            campoVaccinoReazione2.getText(),menuLocaliGravi2.getValue(),menuGeneraliLievi2.getValue(),menuGeneraliGravi2.getValue(),dataVaccinoReazione2.getText(),
            campoVaccinoReazione3.getText(),menuLocaliGravi3.getValue(),menuGeneraliLievi3.getValue(),menuGeneraliGravi3.getValue(),dataVaccinoReazione3.getText(),
            campoVaccinoReazione4.getText(),menuLocaliGravi4.getValue(),menuGeneraliLievi4.getValue(),menuGeneraliGravi4.getValue(),dataVaccinoReazione4.getText()
            
        };
        
        Object[] allergie = new Object[]{
            menuAllergiaPollo.getValue(),
            menuAllergiaAnatra.getValue(),
            menuAllergiaBovina.getValue(),
            menuAllergiaFormaldeide.getValue(),
            menuAllergiaNeomicina.getValue(),
            menuAllergiaStreptomicina.getValue(),
            menuAllergiaKanamicina.getValue(),
            menuAllergiaPolimixin.getValue(),
            menuAllergiaCompMercuriali.getValue(),
            menuAllergiaAltro.getValue()
        };
        
        Object[] convivenza = new Object[]{
            menuConvivenzaImmunodepressioni.getValue(),
            menuConvivenzaTumoriSangue.getValue(),
            menuConvivenzaGravidanza.getValue()
        };
        
        Object[] anamnesiRecente = new Object[]{
            menuAnamnesiFebbre.getValue(),
            menuAnamnesiVieAeree.getValue(),
            menuAnamnesiDiarrea.getValue(),
            menuAnamnesiTerapia.getValue(),
            menuAnamnesiEmotrafusioni.getValue()
        };
        
        //
        String regolare=null,irregolare=null,
                non_incorso=null,incorso=null,non_noto=null,
                positivo=null,negativo=null;
        String dataMestruazione=null,dataTest=null;
        if(menuPeriodicitaMestruale.getValue() != null){
            if(menuPeriodicitaMestruale.getValue().equals(Risorse.Input.REGOLARE))
                regolare = Risorse.Input.X;
            else if(menuPeriodicitaMestruale.getValue().equals(Risorse.Input.IRREGOLARE))
                irregolare = Risorse.Input.X;
        }
        if(menuStatoGravido.getValue() != null){
            if(menuStatoGravido.getValue().equals(Risorse.Input.NON_INCORSO))
                non_incorso = Risorse.Input.X;
            else if(menuStatoGravido.getValue().equals(Risorse.Input.INCORSO))
                incorso = Risorse.Input.X;
            else if(menuStatoGravido.getValue().equals(Risorse.Input.NON_NOTO))
                non_noto = Risorse.Input.X;
        }
        if(menuTestGravidanza.getValue() != null){
            if(menuTestGravidanza.getValue().equals(Risorse.Input.NEGATIVO))
                negativo = Risorse.Input.X;
            else if(menuTestGravidanza.getValue().equals(Risorse.Input.POSITIVO))
                positivo = Risorse.Input.X;
        }
 
        if(!dataUltimaMestruazione.equals(Risorse.MascheraCampi.DATA)){
            dataMestruazione = dataUltimaMestruazione.getText();
        }
        
        if(!dataTestGravidanza.equals(Risorse.MascheraCampi.DATA)){
            dataTest = dataTestGravidanza.getText();
        }
        
        Object[] ginecologica = new Object[]{
            regolare,
            irregolare,
            dataMestruazione,
            non_incorso,
            incorso,
            non_noto,
            positivo,
            negativo,
            dataTest
        };
        
        ArrayList<Object[]> vaccini = new ArrayList<>();
        for(Vaccinazione vaccino: listaVaccinazione){
            vaccini.add(
                    new Object[]{
                        vaccino.getProfilassi() != null ? vaccino.getProfilassi() : "",
                        vaccino.getDose() != null ? vaccino.getDose() : "",
                        vaccino.getSomministrazione() != null ? vaccino.getSomministrazione() : "",
                        vaccino.getVaccino() != null ? vaccino.getVaccino() : "",
                        vaccino.getDitta()!= null ? vaccino.getDitta() : "",
                        vaccino.getLotto() != null ? vaccino.getLotto() : "",
                        "",
                        vaccino.getScadenza() != null ? vaccino.getScadenza().stampaGiornoRidotto('-') : "",
                        vaccino.getInadempienza()!= null ? vaccino.getInadempienza() : ""
                    }
            );
        }
        
        Object[] dati = new Object[]{reazioni,allergie,convivenza,anamnesiRecente,ginecologica,vaccini.toArray()};
        
        Report.stampaVaccinazioni(
                    militare.vettore(),
                    dati,
                    datiImpostazioni.valore(Risorse.Impostazioni.TITOLO_REPARTO),
                    datiImpostazioni.valore(Risorse.Impostazioni.TITOLO_UFFICIO),
                    datiImpostazioni.valore(Risorse.Impostazioni.SEDE),
                    campoData.getEditor().getText(),
                    FinestraImpostazioniController.medico()//datiImpostazioni.valore(Risorse.Impostazioni.KEY_MEDICO_VACCINATORE)
            );
        
        
        
        
    }
    
    private boolean salva(){
        if(militare != null && data != null){
            reazioneVaccino(campoVaccinoReazione1, dataVaccinoReazione1, menuLocaliGravi1, menuGeneraliLievi1, menuGeneraliGravi1);
            reazioneVaccino(campoVaccinoReazione2, dataVaccinoReazione2, menuLocaliGravi2, menuGeneraliLievi2, menuGeneraliGravi2);
            reazioneVaccino(campoVaccinoReazione3, dataVaccinoReazione3, menuLocaliGravi3, menuGeneraliLievi3, menuGeneraliGravi3);
            reazioneVaccino(campoVaccinoReazione4, dataVaccinoReazione4, menuLocaliGravi4, menuGeneraliLievi4, menuGeneraliGravi4);
            
            Object[] anamnesi = new Object[datiVaccinazioni.tabella(BASE_DATI.TABELLA.ANAMNESI_VACCINALE).vediTuttiAttributi().size()];
            
        
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_ALTRO] = menuAllergiaAltro.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_ANATRA] = menuAllergiaAnatra.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_POLLO] = menuAllergiaPollo.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_BOVINO] = menuAllergiaBovina.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_FOLMALDEIDE] = menuAllergiaFormaldeide.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_KANAMICINA] = menuAllergiaKanamicina.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_MERCURIALI] = menuAllergiaCompMercuriali.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_NEOMICINA] = menuAllergiaNeomicina.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_POLIMIXIN_B] = menuAllergiaPolimixin.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_STRETTOMICINA] = menuAllergiaStreptomicina.getValue();
            
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.CONVINVENTI_GRAVIDANZA] = valoreMenuAnamnesi(menuConvivenzaGravidanza);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.CONVINVENTI_IMMUNODEPRESSIVI] = valoreMenuAnamnesi(menuConvivenzaImmunodepressioni);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.CONVINVENTI_TUMORI_SANGUE] = valoreMenuAnamnesi(menuConvivenzaTumoriSangue);
            
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.DIARREA] = valoreMenuAnamnesi(menuAnamnesiDiarrea);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.TRASFUSIONE] = valoreMenuAnamnesi(menuAnamnesiEmotrafusioni);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.FEBBRE] = valoreMenuAnamnesi(menuAnamnesiFebbre);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.TERAPIA_RECENTE] = valoreMenuAnamnesi(menuAnamnesiTerapia);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.DISTURBI_VIE_AEREE] = valoreMenuAnamnesi(menuAnamnesiVieAeree);
            
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.PERIODICITA_MESTRUALE] = menuPeriodicitaMestruale.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.STATO_GRAVIDANZA] = menuStatoGravido.getValue();
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.TEST_GRAVIDANZA] = menuTestGravidanza.getValue();
            
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.DATA_TEST_GRAVIDANZA] = caricaCampoData(dataTestGravidanza);
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ULTIMA_MESTRUAZIONE] = caricaCampoData(dataUltimaMestruazione);
            
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.UTENTE] = Programma.utente;
            anamnesi[BASE_DATI.ANAMNESI_VACCINALE.MEDICO] = FinestraImpostazioniController.medico();
            
            if(!datiVaccinazioni.aggiungiAnamnesiVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita(),data, anamnesi)){
                messaggio.setText(Risorse.Messaggi.ERRORE_SALVATAGGIO_DATI);
                messaggio.setStyle(Risorse.Colore.ROSSO);
                return false;
            }
            salvaModificheVaccinazioni();
            return true;
            
        }else{
            if(data == null)
                messaggio.setText(Risorse.Messaggi.ERRORE_DATA);
            else
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setStyle(Risorse.Colore.ROSSO);
            return false;
        }
        
    }
    
    /**
     * Salva le vaccinazioni nel caso di modifica della data di seduta.
     */
    private void salvaModificheVaccinazioni(){
        for(Vaccinazione vaccino: listaVaccinazione){
            if(data.compareTo(vaccino.getData()) != 0){
                Object[] record = Programma.datiVaccinazioni.trovaVaccinazione(
                        vaccino.getProfilassi(), vaccino.getData(), 
                        militare.getCognome(), militare.getNome(), militare.getNascita()
                );
                if(record != null){
                    record[BASE_DATI.VACCINAZIONI.DATA_SEDUTA] = data;
                    Programma.datiVaccinazioni.modificaVaccinazione(
                            vaccino.getProfilassi(), vaccino.getData(), 
                            militare.getCognome(), militare.getNome(), militare.getNascita(), record
                    );

                }else{
                    messaggio.setText(Risorse.Messaggi.ERRORE_MODIFICA_DATI);
                    messaggio.setStyle(Risorse.Colore.ROSSO);

                }
            }
        }
    }

    private boolean valoreMenuAnamnesi(ChoiceBox<String> menu){
        String valore = menu.getValue();
        if(valore != null){
            return menu.getValue().equals(Risorse.Input.X);
        }
        return false;
    }
    
    private DataOraria caricaCampoData(TextField campo){
        DataOraria _data = null;
        try {
            _data = new DataOraria(campo.getText());
        } catch (Errore ex) {
        }
        return _data;
    }
    
    private void reazioneVaccino(TextField campoVaccinoReazione,TextField dataVaccinoReazione, ChoiceBox<String> menuLocaliGravi, ChoiceBox<String> menuGeneraliLievi, ChoiceBox<String> menuGeneraliGravi){
        Object[] record = new Object[BASE_DATI.REAZIONE_VACCINALE.DIMENSIONE_TABELLA];
        DataOraria data1 = null;
        try {
            data1 = new DataOraria(dataVaccinoReazione.getText());
            record[BASE_DATI.REAZIONE_VACCINALE.DATA_EVENTO] = data1;
        } catch (Errore ex) {
        }
        if(campoVaccinoReazione.getText().length() > 0  && militare != null){
            record[BASE_DATI.REAZIONE_VACCINALE.COGNOME] = militare.getCognome();
            record[BASE_DATI.REAZIONE_VACCINALE.NOME] = militare.getNome();
            record[BASE_DATI.REAZIONE_VACCINALE.DATA_NASCITA] = militare.getNascita();
            record[BASE_DATI.REAZIONE_VACCINALE.VACCINO] = campoVaccinoReazione.getText();
            record[BASE_DATI.REAZIONE_VACCINALE.DATA_VACCINAZIONE] = DataOraria.creaDataOggi();
            
            if(menuLocaliGravi.getValue() != null)
                record[BASE_DATI.REAZIONE_VACCINALE.LOCALI_GRAVI  ] = menuLocaliGravi.getValue().equals(Risorse.Input.SI);
            if(menuGeneraliGravi.getValue() != null)
                record[BASE_DATI.REAZIONE_VACCINALE.GENERALI_GRAVI] = menuGeneraliGravi.getValue().equals(Risorse.Input.SI);
            if(menuGeneraliLievi.getValue() != null)
                record[BASE_DATI.REAZIONE_VACCINALE.GENERALI_LIEVI] = menuGeneraliLievi.getValue().equals(Risorse.Input.SI);
            
            datiVaccinazioni.aggiungiReazioneVaccino(record);
        }
        
    }

    private void caricaValoriReazioneAiVaccini() {
        if(militare != null){
            ArrayList<Object[]> reazioni = datiVaccinazioni.trovaAnamnesiReazioneVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita());
            HashSet<String> listaVaccini = new HashSet<>();
            int i=0;
            if(reazioni != null)
                for(Object[] evento:reazioni){
                    String vaccino = (String) evento[BASE_DATI.REAZIONE_VACCINALE.VACCINO];
                    DataOraria dataEvento = (DataOraria) evento[BASE_DATI.REAZIONE_VACCINALE.DATA_EVENTO];
                    String localeGravi = "";
                    if(evento[BASE_DATI.REAZIONE_VACCINALE.LOCALI_GRAVI] != null){
                        localeGravi = (Boolean)evento[BASE_DATI.REAZIONE_VACCINALE.LOCALI_GRAVI] ? Risorse.Input.SI : Risorse.Input.NO;
                    }
                    String generaliGravi = "";
                    if(evento[BASE_DATI.REAZIONE_VACCINALE.GENERALI_GRAVI] != null){
                        generaliGravi = (Boolean)evento[BASE_DATI.REAZIONE_VACCINALE.GENERALI_GRAVI] ? Risorse.Input.SI : Risorse.Input.NO;
                    }
                     String generaliLievi = "";
                    if(evento[BASE_DATI.REAZIONE_VACCINALE.GENERALI_LIEVI] != null){
                        generaliLievi = (Boolean)evento[BASE_DATI.REAZIONE_VACCINALE.GENERALI_LIEVI] ? Risorse.Input.SI : Risorse.Input.NO;
                    }       
                    if(!listaVaccini.contains(vaccino+dataEvento+localeGravi+generaliGravi+generaliLievi)){

                        caricaValoriReazioneAiVaccini(i++,vaccino,dataEvento,localeGravi,generaliLievi,generaliGravi);
                        listaVaccini.add(vaccino+dataEvento+localeGravi+generaliGravi+generaliLievi);
                   }
                }
        }
    }
    
    
    private void caricaAnamnesiVaccinale(){
        if(militare != null ){
            Object[] anamnesi = datiVaccinazioni.trovaAnamnesiVaccinale(
                    militare.getCognome(),
                    militare.getNome(), 
                    militare.getNascita(),
                    data
            );
            
            if(anamnesi != null){
                // allergie
                caricaCampoAnamnesi(menuAllergiaPollo,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_POLLO]);
                caricaCampoAnamnesi(menuAllergiaAnatra,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_ANATRA]);
                caricaCampoAnamnesi(menuAllergiaAltro,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_ALTRO]);
                caricaCampoAnamnesi(menuAllergiaBovina,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_BOVINO]);
                caricaCampoAnamnesi(menuAllergiaCompMercuriali,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_MERCURIALI]);
                caricaCampoAnamnesi(menuAllergiaFormaldeide,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_FOLMALDEIDE]);
                caricaCampoAnamnesi(menuAllergiaKanamicina,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_KANAMICINA]);
                caricaCampoAnamnesi(menuAllergiaNeomicina,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_NEOMICINA]);
                caricaCampoAnamnesi(menuAllergiaPolimixin,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_POLIMIXIN_B]);
                caricaCampoAnamnesi(menuAllergiaStreptomicina,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ALLERGIA_STRETTOMICINA]);
                
                // convivenza
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.CONVINVENTI_GRAVIDANZA] == true)
                    menuConvivenzaGravidanza.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.CONVINVENTI_TUMORI_SANGUE] == true)
                    menuConvivenzaTumoriSangue.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.CONVINVENTI_IMMUNODEPRESSIVI] == true)
                    menuConvivenzaImmunodepressioni.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                // anamnesi recente
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.DIARREA] == true)
                    menuAnamnesiDiarrea.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.TRASFUSIONE] == true)
                    menuAnamnesiEmotrafusioni.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.FEBBRE] == true)
                    menuAnamnesiFebbre.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.TERAPIA_RECENTE] == true)
                    menuAnamnesiTerapia.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                if((Boolean)anamnesi[BASE_DATI.ANAMNESI_VACCINALE.DISTURBI_VIE_AEREE] == true)
                    menuAnamnesiVieAeree.selectionModelProperty().getValue().select(Risorse.Input.X);
                
                // anamnesi ginecologica
                caricaCampoAnamnesi(menuPeriodicitaMestruale,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.PERIODICITA_MESTRUALE]);
                caricaCampoAnamnesi(menuStatoGravido,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.STATO_GRAVIDANZA]);
                caricaCampoAnamnesi(menuTestGravidanza,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.TEST_GRAVIDANZA]);
                
                caricaCampoDataAnamnesi(dataUltimaMestruazione,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.ULTIMA_MESTRUAZIONE]);
                caricaCampoDataAnamnesi(dataTestGravidanza,anamnesi[BASE_DATI.ANAMNESI_VACCINALE.DATA_TEST_GRAVIDANZA]);
                
                
            }
            
        }
    }
    
    private void caricaCampoAnamnesi(ChoiceBox<String> menu, Object input){
        if(input != null)
            menu.selectionModelProperty().getValue().select(input.toString());
    }
    
    private void caricaCampoDataAnamnesi(TextField campo, Object input){
        if(input instanceof DataOraria) {
            DataOraria dataOraria = (DataOraria) input;
            campo.setText(dataOraria.stampaGiorno('/'));
        }
    }

    private void caricaValoriReazioneAiVaccini(int i, String vaccino, DataOraria dataEvento, String localeGravi, String generaliLievi, String generaliGravi) {
        switch(i){
            case 0: {
                campoVaccinoReazione1.setText(vaccino);
                if(dataEvento != null)
                    dataVaccinoReazione1.setText(dataEvento.stampaGiorno('/'));
                menuGeneraliLievi1.selectionModelProperty().getValue().select(generaliLievi);
                menuGeneraliGravi1.selectionModelProperty().getValue().select(generaliGravi);
                menuLocaliGravi1.selectionModelProperty().getValue().select(localeGravi);
            }
            break;
            case 1: {
                campoVaccinoReazione2.setText(vaccino);
                if(dataEvento != null)
                    dataVaccinoReazione2.setText(dataEvento.stampaGiorno('/'));
                menuGeneraliLievi2.selectionModelProperty().getValue().select(generaliLievi);
                menuGeneraliGravi2.selectionModelProperty().getValue().select(generaliGravi);
                menuLocaliGravi2.selectionModelProperty().getValue().select(localeGravi);
            }
            break;
            case 3: {
                campoVaccinoReazione3.setText(vaccino);
                if(dataEvento != null)
                    dataVaccinoReazione3.setText(dataEvento.stampaGiorno('/'));
                menuGeneraliLievi3.selectionModelProperty().getValue().select(generaliLievi);
                menuGeneraliGravi3.selectionModelProperty().getValue().select(generaliGravi);
                menuLocaliGravi3.selectionModelProperty().getValue().select(localeGravi);
            }
            break;
            case 4: {
                campoVaccinoReazione4.setText(vaccino);
                if(dataEvento != null)
                    dataVaccinoReazione4.setText(dataEvento.stampaGiorno('/'));
                menuGeneraliLievi4.selectionModelProperty().getValue().select(generaliLievi);
                menuGeneraliGravi4.selectionModelProperty().getValue().select(generaliGravi);
                menuLocaliGravi4.selectionModelProperty().getValue().select(localeGravi);
            }
            break;
        }
    }
    
    
    @FXML
    private void eliminaReazioneVaccino1(ActionEvent event) {
        eliminaReazioneVaccino(campoVaccinoReazione1,dataVaccinoReazione1,menuGeneraliLievi1,menuGeneraliGravi1,menuLocaliGravi1);
         
    }

    @FXML
    private void eliminaReazioneVaccino2(ActionEvent event) {
        eliminaReazioneVaccino(campoVaccinoReazione2,dataVaccinoReazione2,menuGeneraliLievi2,menuGeneraliGravi2,menuLocaliGravi2);
         
    }
    @FXML
    private void eliminaReazioneVaccino3(ActionEvent event) {
        eliminaReazioneVaccino(campoVaccinoReazione3,dataVaccinoReazione3,menuGeneraliLievi3,menuGeneraliGravi3,menuLocaliGravi3);
         
    }
    
    @FXML
    private void eliminaReazioneVaccino4(ActionEvent event) {
        eliminaReazioneVaccino(campoVaccinoReazione4,dataVaccinoReazione4,menuGeneraliLievi4,menuGeneraliGravi4,menuLocaliGravi4);
         
    }
    
    private void eliminaReazioneVaccino(TextField campoVaccinoReazione, TextField dataVaccinoReazione, 
            ChoiceBox<String> menuGeneraliLievi, ChoiceBox<String> menuGeneraliGravi, 
            ChoiceBox<String> menuLocaliGravi) {
        
        campoVaccinoReazione.setText("");
        dataVaccinoReazione.setText(Risorse.MascheraCampi.DATA);
        menuGeneraliLievi.selectionModelProperty().getValue().select(0);
        menuGeneraliGravi.selectionModelProperty().getValue().select(0);
        menuLocaliGravi.selectionModelProperty().getValue().select(0);
    }
    
    
    /**
     * Metodo che gestisce il pulsante "X", in alto a destra, per la chiusura della finestra.
     * @param event 
     */
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event){
        
//        try {
//            Programma.finestraConferma(
//                    this,
//                    Risorse.Conferma.CHIUSURA_FINESTRA_ANAMNESI_VACCINALE, 
//                    () -> {
                        Programma.vistaCorrente.setScene(finestra != null ? finestra : Programma.finestraPrincipale);
                        Programma.adattaFinestra();
                        Programma.vistaCorrente.show();
                        data = null;
                        finestra = null;
//                    }
//            );
//        } catch (IOException ex) {
//            Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

     
    /**
     * Riduci la finestra ad icona.
     * @param event 
     */
    @FXML
    private void riduciFinestra(ActionEvent event) {
        Programma.riduciFinestra();
    }
    
    /**
     * Metodo che gestisce il cambio della data della seduta vaccinale
     * @param event 
     */
    @FXML
    private void cambiaData(ActionEvent event){
        LocalDate valoreData = this.campoData.getValue();
        data = new DataOraria(valoreData.getDayOfMonth(),valoreData.getMonthValue(),valoreData.getYear());
        System.out.println("cambio data -> "+data.stampaGiorno());
        
    }

    
}
