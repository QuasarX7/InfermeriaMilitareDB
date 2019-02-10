package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.DaseDati.BASE_DATI;
import it.difesa.esercito.rav17.infermeria.DaseDati.Dati;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiUtente;
import it.difesa.esercito.rav17.infermeria.modello.Militare;
import it.difesa.esercito.rav17.infermeria.programma.Programma;
import it.difesa.esercito.rav17.infermeria.programma.Risorse;
import it.difesa.esercito.rav17.infermeria.modello.Ricovero;
import it.difesa.esercito.rav17.infermeria.modello.StoriaVaccinale;
import it.difesa.esercito.rav17.infermeria.modello.Vaccinazione;
import it.difesa.esercito.rav17.infermeria.modello.Verbale;
import it.difesa.esercito.rav17.infermeria.modello.VisitaMedica;
import it.difesa.esercito.rav17.infermeria.programma.Console;
import static it.difesa.esercito.rav17.infermeria.programma.Programma.datiUtente;
import it.difesa.esercito.rav17.infermeria.vista.FinestraDatiGMLMedici;
import it.difesa.esercito.rav17.infermeria.vista.FinestraDatiIncorporamento;
import it.difesa.esercito.rav17.infermeria.vista.FinestraElencoMilitari;
import it.difesa.esercito.rav17.infermeria.vista.FinestraGestioneUtenti;
import it.difesa.esercito.rav17.infermeria.vista.FinestraImpostazioneStampa;
import it.difesa.esercito.rav17.infermeria.vista.FinestraModelloGL;
import it.difesa.esercito.rav17.infermeria.vista.FinestraModelloML;
import it.difesa.esercito.rav17.infermeria.vista.FinestraModificaMilitare;
import it.difesa.esercito.rav17.infermeria.vista.FinestraRapportino;
import it.difesa.esercito.rav17.infermeria.vista.FinestraRicovero;
import it.difesa.esercito.rav17.infermeria.vista.FinestraSituazioneVaccinazioni;
import it.difesa.esercito.rav17.infermeria.vista.FinestraTabella;
import it.difesa.esercito.rav17.infermeria.vista.FinestraVisita;
import it.quasar_x7.java.BaseDati.EccezioneBaseDati;
import it.quasar_x7.java.Sistema.AdattatoreDiRete;
import it.quasar_x7.java.Sistema.Hardware;
import it.quasar_x7.java.Sistema.Java;
import it.quasar_x7.java.Sistema.SO;
import it.quasar_x7.java.swing.finestra.Finestra;
import it.quasar_x7.java.swing.stampa.FinestraStampatoSemplice;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.Errore;
import it.quasar_x7.javafx.CampoTesto;
import it.quasar_x7.javafx.Orologio;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Classe che gestisce la finestra principale.
 *
 * @author Dr Domenico della Peruta
 * @version 0.1.0 ultima modifica 15/04/2016
 */
public class FinestraPrincipaleController implements Initializable {


    public static ObservableList<Militare> listaMilitari = FXCollections.observableArrayList();
    public static ObservableList<StoriaVaccinale> listaStoriaVaccinale = FXCollections.observableArrayList();
    public static ObservableList<Vaccinazione> listaVaccinazione = FXCollections.observableArrayList();
    public static ObservableList<VisitaMedica> listaVisitaMedica= FXCollections.observableArrayList();
    public static ObservableList<Verbale> listaVerbaleML= FXCollections.observableArrayList();
    public static ObservableList<Verbale> listaVerbaleGL= FXCollections.observableArrayList();
    public static ObservableList<Ricovero> listaRicoveri= FXCollections.observableArrayList();
    
    
    /**
     * Contiene i dati principali del militare selezionato.
     */
    public static Militare militare;
    
    //public static String medicoVaccinatore;
    
    private static int stato;
    
    @FXML 
    private Label messaggio;
    
    
    
    /**
     * Metodo di inizializzazione della classe.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // per la compatibilita alle vecchie finestre swing
        Finestra.utente = Programma.utente;
        
        
        etichettaUtente.setText(Risorse.Etichette.BENVENUTO +Programma.utente);
        
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        messaggio.setText("");
        messaggio.setStyle(Risorse.Colore.ROSSO);
        inizializzaPannelloLaterale();
        inizializzaPannelloRicerca();
        inizializzaPannelloMilitare();
        inizializzaPannelloNuovoMilitare();
        //pennelli secondari
        rinizializzaGML();
        comportamentoCampiGML();
        diagrammaAttivita();
        inizializzaPannelloStoricoVaccinale();
        inizializzaPannelloInputStoriaVaccinale();
        inizializzaPannelloVaccinazioni();
        inizializzaPanelloVisitaMedica();
        inizializzaPanelloVerbaleML();
        inizializzaPanelloVerbaleGL();
        inizializzaPanelloRicoveri();
        inizializzaAreaDebug();
    }  
    
      
    private void inizializzaMenuDocumento(ChoiceBox menu){
        if(menu.getItems().isEmpty()){
            menu.getItems().add(Risorse.Input.DOC_CARTA_IDENTITA);
            menu.getItems().add(Risorse.Input.DOC_PATENTE_GUIDA);
            menu.getItems().add(Risorse.Input.DOC_PATENTE_NAUTICA);
            menu.getItems().add(Risorse.Input.DOC_PASSAPORTO);
            menu.getItems().add(Risorse.Input.DOC_PORTO_ARMI);
            menu.getItems().add(Risorse.Input.DOC_LIBRETTO_PENSIONE);
            menu.getItems().add(Risorse.Input.DOC_TESSERA_AT);
            menu.getItems().add(Risorse.Input.DOC_TESSERA_BT);
        }
    }
    
    private void inizializzaScuola(ChoiceBox menu){
        if(menu.getItems().isEmpty()){
            menu.getItems().add(Risorse.Input.SCUOLA_OBBLIGO);
            menu.getItems().add(Risorse.Input.SCUOLA_SUPERIORI);
            menu.getItems().add(Risorse.Input.SCUOLA_UNIVERSITA);
        }
    }
    
    private void inizializzaSesso(ChoiceBox menu){
        if(menu.getItems().isEmpty()){
            menu.getItems().add(Risorse.Input.SESSO_F);
            menu.getItems().add(Risorse.Input.SESSO_M);
        }
    }
    
    private void inizializzaMedico(ComboBox menu){
        if(menu.getItems().size() < 2){
            ArrayList<String> medici = Programma.datiMedico.tuttiMedici();
            if(medici != null)
                menu.getItems().addAll(medici);
        }
    }

    private void erroreCaricamentoFinestra(IOException ex){
        messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
        messaggio.setStyle(Risorse.Colore.ROSSO);
        Logger.getLogger(AnamnesiVaccinaleController.class.getName()).log(Level.SEVERE, null, ex);
        visualizzaAreaDebug();
    }
    
//--------------------------------------------------------------------------------------
                        /*********************
                         * PANNELLO LATERALE *
                         *********************/   
    
    @FXML
    private Pane orologio;
    
    @FXML
    private ImageView immagine;
    
    @FXML
    private TitledPane schedaOrologio;
    
    @FXML
    private ListView<String> listaInfo;
    
    @FXML
    private Accordion schedeLaterali;
    
    private void inizializzaPannelloLaterale(){
        new Orologio(orologio);
        schedeLaterali.setExpandedPane(schedaOrologio);
        String[] IP = AdattatoreDiRete.indizizziIP();
        String ipLocale = "";
        if(IP!=null) {
                for(String s:IP){                
                    ipLocale += "\n"+s;
                }
            }
        listaInfo.setItems(
                FXCollections.observableArrayList(
                        Risorse.Info.AUTORE,
                        Risorse.Info.VERSIONE,
                        Risorse.Info.DATA_MODIFICA,
                        "IP server: "+Dati.getHost(),
                        "IP locale: "+ipLocale,
                        "S.O.: "+SO.nome()+" v. "+SO.versione(),
                        "CPU: "+Hardware.processore(),
                        "Java v. "+Java.versione()
                        
                )
        );
        
    }

//--------------------------------------------------------------------------------------
                        /*********************
                         *  MENU PRINCIPALE  *
                         *********************/   
    
    @FXML
    private StackPane pannelloPrincipale;

    @FXML
    private Pane  pannelloInfo;
    
    @FXML
    private Pane pannelloMilitare;
    
    @FXML
    private Pane pannelloRicerca;
    
    @FXML
    private Pane pannelloNuovoMilitare;
    
    @FXML
    private StackPane pannelloSecondario;
   
    
    @FXML
    private Pane pannelloGML;
    
    @FXML
    private Pane pannelloStoriaVaccinale;
    
    @FXML
    private Pane pannelloInputStoriaVaccinale;
    
    @FXML
    private Pane pannelloNuoveVaccinazioni;
    
    @FXML
    private Pane pannelloVisitaMedica;
    
    @FXML
    private Pane pannelloVerbaleML;
    
    @FXML
    private Pane pannelloVerbaleGL;
    
    @FXML
    private Pane pannelloRicovero;
    
    @FXML
    private Pane pannelloDebug;
    
    
    
    
    
    /**
     * Chiusura programma.
     * 
     * @param event 
     */
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event) {
        try {
            Programma.finestraConferma(
                    this,
                    Risorse.Conferma.CHIUSURA_PROGRAMMA, 
                    () -> {
                        Platform.exit();
                        System.exit(0);
                    }
            );
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
        
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
    private void visualizzaAreaDebug(ActionEvent event){
        visualizzaAreaDebug();
    }
    
    private void visualizzaAreaDebug(){
        pannelloVisibile(pannelloPrincipale,pannelloDebug);
        stato = Risorse.Stato.DEBUG;
        messaggio.setText("");
    }
    
    @FXML
    private void visualizzaGraficoIniziale(ActionEvent event){
        pannelloVisibile(pannelloPrincipale,pannelloInfo);
        stato = Risorse.Stato.INFO;
        messaggio.setText("");
    }
    
    @FXML
    private void visualizzaCampiNuovoMilitare(ActionEvent event){
        pannelloVisibile(pannelloPrincipale,pannelloNuovoMilitare);
        stato = Risorse.Stato.NUOVO_MILITARE;
        messaggio.setText("");
    }
    
    
    
    @FXML
    private void visualizzaCampiMisure(ActionEvent event){
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloGML);
        visualizzaSchedaMisure();
        stato = Risorse.Stato.MISURE;
        caricaDatiMilitare();
    }
    
    
    @FXML
    private void visualizzaCampiGML(ActionEvent event){
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloGML);
        visualizzaSchedaGML();
        stato = Risorse.Stato.GML;
        caricaDatiMilitare();
    }
    
    @FXML
    private void visualizzaRegistroIncorporamento(ActionEvent event){
        creaFinestra(new FinestraStampatoRegistroIncorporamento());
    }
    
    
    @FXML
    private void visualizzaFiltroDati(ActionEvent event){
        SwingUtilities.invokeLater(
                () -> {
                    creaFinestra(new FinestraTabella());
                }
        );
        
    }
            
    @FXML
    private void visualizzaStoriaVaccinale(ActionEvent event){
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloStoriaVaccinale);
        stato = Risorse.Stato.STORIA_VACCINALE;
        caricaDatiMilitare();
    }
    
    @FXML
    private void visualizzaNuoveVaccinazioni(ActionEvent event){
        listaVaccinazione.clear();
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloNuoveVaccinazioni);
        stato = Risorse.Stato.VACCINAZIONI;
        caricaDatiMilitare();
        
    }
    
    
     
    @FXML
    private void visualizzaStamatoLibVaccinaleMilitare(ActionEvent event){
        SwingUtilities.invokeLater(
                () -> {
                    Report.creaFinestraStampaReportAllegatoLibSingolo(null,Programma.datiMilitari);
                }
        );
        
    }
    
    @FXML
    private void visualizzaStamatoLibVaccinaleCp(ActionEvent event){
        SwingUtilities.invokeLater(
                () -> {
                    Report.creaFinestraStampaReportAllegatoLibMilitare(null,Programma.datiMilitari);
                }
        );
    }
    
    @FXML
    private void visualizzaStamatoLibVaccinaleCorso(ActionEvent event){
        SwingUtilities.invokeLater(
                () -> {
                    Report.creaFinestraStampaReportAllegatoLibCorso(null,Programma.datiMilitari);
                }
        );
        
    }
    
    @FXML
    private void visualizzaStamatoStralcioVaccinale(ActionEvent event){
        SwingUtilities.invokeLater(
                () -> {
                    Report.creaFinestraStampaReportStralcioVaccinale(null,Programma.datiMilitari);
                }
        );
        
    }
    
    @FXML
    private void visualizzaVisitaMedica(ActionEvent event){
        listaVaccinazione.clear();
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloVisitaMedica);
        stato = Risorse.Stato.VISITA_MEDICA;
        caricaDatiMilitare();
    }
    
    @FXML
    private void visualizzaVerbaleModelloML(ActionEvent event){
        listaVerbaleML.clear();
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloVerbaleML);
        stato = Risorse.Stato.VERBALE_MOD_ML;
        caricaDatiMilitare();
    }
    
    @FXML
    private void visualizzaVerbaleModelloGL(ActionEvent event){
        listaVerbaleGL.clear();
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloVerbaleGL);
        stato = Risorse.Stato.VERBALE_MOD_GL;
        caricaDatiMilitare();
    }
    
    @FXML
    private void visualizzaRicoveri(ActionEvent event){
        listaRicoveri.clear();
        pannelloVisibile(pannelloPrincipale,pannelloMilitare);
        //pannelloVisibile(pannelloSecondario,pannelloRicovero);
        stato = Risorse.Stato.RICOVERO;
        caricaDatiMilitare();
    }
    
    @FXML
    private void visualizzaRapportino(ActionEvent event){
        creaFinestra(new FinestraRapportino());
    }
    
    @FXML
    private void visualizzaRicercaData(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_RICERCA))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    @FXML
    private void gestioneUtenti(ActionEvent event){
        creaFinestra(new FinestraGestioneUtenti());
    }
    
    @FXML
    private void gestioneMedici(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_IMPOSTAZIONI_MEDICO))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
   
    
    
    @FXML
    private void modificaDatiPrincipaliMilitare(ActionEvent event){
        creaFinestra(new FinestraElencoMilitari());
    }
    
    
    @FXML
    private void visualizzaRegistroVaccinale(ActionEvent event){
        creaFinestra(new FinestraStampatoRegistroVaccinazioni());
    }
    
    @FXML
    private void visualizzaCopertura(ActionEvent event){
        creaFinestra(new FinestraSituazioneVaccinazioni());
    }
    
    @FXML
    private void visualizzaAttivitaIncorporamento(ActionEvent event){
        creaFinestra(new FinestraDatiIncorporamento());
    }
    
    @FXML
    private void visualizzaAttivitaMedici(ActionEvent event){
        creaFinestra(new FinestraDatiGMLMedici());
    }
    
    /**
     * Voce menu Vaccinazioni > Attività.
     * 
     * @param event 
     */
    @FXML
    private void visualizzaAttività(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_ATTIVITA_VACCINALE))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    /**
     * Voce menu Rifiuti > Registro
     * @param event 
     */
    @FXML
    private void gestioneRifiuti(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_RIFIUTI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    /**
     * Voce menu Rifiuti > Impostazioni
     * @param event 
     */
    @FXML
    private void impostazioniRifiuti(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_IMPOSTAZIONI_RIFIUTI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    /**
     * Metodo che permette di aprire la finestra impostazioni di stampa, tramite la voce del menu 
     * visite mediche.
     * 
     * @param event 
     */
    @FXML
    private void impostazioniStampaVisite(ActionEvent event){
        creaFinestra(new FinestraImpostazioneStampa());
    }
    
    /*
    @FXML
    private void impostazioniVaccinazioni(ActionEvent event){
        creaFinestra(new FinestraImpostazioniVaccinazioni());
    }
    */
    @FXML
    private void impostazioneDatiVaccinali(ActionEvent event){
        try {
            /*
                Programma.finestraInputMenu(
                        this,
                        "Seleziona il Medico vaccinatore",
                        Programma.datiMedico.tuttiMedici(),
                        (String input)->{ 
                            // evento pulsante ok
                            medicoVaccinatore = input;
                        }

                );
                
             */                   
                // apri pinestra impostazioni vaccinali
                Programma.vistaCorrente.setScene(
                        new Scene(
                                FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_IMPOSTAZIONI_VACCINALI))
                        )
                );
                Programma.adattaFinestra();
                Programma.vistaCorrente.show();
                
                
        } catch (IOException ex) {
                erroreCaricamentoFinestra(ex);
        }
    }
    
    private void creaFinestra(final javax.swing.JFrame finestra){
        Finestra.spegniAltreFinestre(finestra);
        
    }
    
    
    /**
     * Metodo che permette di visualizzare solo uno dei pannelli contenuta in un StackPane.
     * 
     * @param areaPannelli area destinata a visualizzare i pannelli.
     * @param pannelloSelezionato pannello da visualizzare.
     */
    private void pannelloVisibile(StackPane areaPannelli, Pane pannelloSelezionato){
        for (Node pannello : areaPannelli.getChildren()) {
            if(pannello instanceof Pane){
                if(pannello != pannelloSelezionato){
                    pannello.setVisible(false);
                }else{
                    pannello.setVisible(true);
                }
            }
        }
    }
//-------------------------------------------------------------------------------------
                       /***********************
                        *   PANNELLO DEBUG    *
                        ***********************/
                        
    @FXML
    private TextArea areaDebug;

    
    private void inizializzaAreaDebug(){
        if(!Dati.getHost().equals("localhost") && !Dati.getHost().equals("127.0.0.1")){ 
            System.out.print(Dati.getHost());
        // in modalità reale (fuori compilazione e debug)
            Console debug = new Console(areaDebug);
            
            PrintStream stream = new PrintStream(debug, true);
            System.setOut(stream);
            System.setErr(stream);
        }else{
            areaDebug.setText("Non attivo in modalità di programmazione (host = 127.0.0.1)");
        }
    }
    
    
//-------------------------------------------------------------------------------------
                       /***********************
                        *  PANNELLO INIZIALE  *
                        ***********************/
    
    @FXML
    private BarChart<String,Number> diagrammaAttivita;
    
    @FXML
    private NumberAxis numeroAttivita;
    
    @FXML
    private CategoryAxis dataAttivita;
    
    @FXML
    private Label etichettaUtente;
   
    
    private void diagrammaAttivita(){
        
        XYChart.Series incorporati = new XYChart.Series();
        XYChart.Series chiedentiVisita = new XYChart.Series();
        XYChart.Series prontoSoccorso = new XYChart.Series();
        XYChart.Series vaccinazioni = new XYChart.Series();
        
        
        incorporati.setName(Risorse.Grafico.Attivita.INCORPORAMENTO);
        chiedentiVisita.setName(Risorse.Grafico.Attivita.CHIEDENTI_VISITA);
        prontoSoccorso.setName(Risorse.Grafico.Attivita.PRONTO_SOCCORSO);
        vaccinazioni.setName(Risorse.Grafico.Attivita.VACCINAZIONI);
        
        DataOraria giorno = new DataOraria();
        
        giorno.oggi();
        int i=0;
        while(i < 15){
            try {
                incorporati.getData().add(new XYChart.Data(
                                giorno.stampaGiorno(), 
                                Programma.datiMilitari.numeroVisiteIncorporati(giorno)
                        )
                );
                chiedentiVisita.getData().add(new XYChart.Data(
                                giorno.stampaGiorno(), 
                                Programma.datiVisita.numeroVisite(Risorse.Visite.CHIEDENTE_VISITA,giorno)
                        )
                );
                prontoSoccorso.getData().add(new XYChart.Data(
                                giorno.stampaGiorno(), 
                                Programma.datiVisita.numeroVisite(Risorse.Visite.PRONTO_SOCCORSO,giorno)
                        )
                );
                vaccinazioni.getData().add(new XYChart.Data(
                                giorno.stampaGiorno(), 
                                Programma.datiVaccinazioni.numeroVaccinati(giorno)
                        )
                );
                
            } catch (EccezioneBaseDati ex) {
            }
            i++;
            giorno.decrementaGiorno(1);
            
        }
        diagrammaAttivita.getData().addAll(incorporati,chiedentiVisita,prontoSoccorso,vaccinazioni);
        
        
        
    }
    
//-------------------------------------------------------------------------------------
                        /*********************
                         * PANNELLO MILITARE *
                         *********************/
    
    @FXML
    private TextField campoGrado;
    
    @FXML
    private TextField campoCognome;
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextField campoNascita;

    @FXML
    private TextField campoCp;
    
    @FXML
    private TextField campoCorso;
    
    private void inizializzaPannelloMilitare(){
        bordoEffetto(pannelloIdentificativo);
        
        campoCognome.setText("");
        campoCorso.setText("");
        campoCp.setText("");
        campoNascita.setText("");
        campoNome.setText("");
        campoGrado.setText("");
        pannelloVisibile(pannelloPrincipale,pannelloInfo);
    } 
    
    /**
     * Da un effetto speciale al bordo dell'oggetto graffico (pannello, campo o etichetta)
     * @param oggetto 
     */
    private void bordoEffetto(Region oggetto){
        DropShadow bordo= new DropShadow();
        int depth = 70;
        bordo.setOffsetY(0f);
        bordo.setOffsetX(0f);
        bordo.setColor(Color.CHARTREUSE);
        bordo.setWidth(depth);
        bordo.setHeight(depth);
        oggetto.setEffect(bordo);
        oggetto.setBorder(
                new Border(
                        new BorderStroke(
                                Color.CHARTREUSE,
                                BorderStrokeStyle.SOLID, 
                                null, 
                                null
                        )
                )
        );
    }
    
    /**
     * Metodo che mostra il pannello Ricerca, con il campo di ricerca per cognome e la tabella militari,
     * implementato dal pulsante Cerca del pannello Militare.
     * 
     * @param event Pulsante "Cerca" pannello principale Militare.
     */
    @FXML
    private void visualizzaPannelloRicerca(ActionEvent event) {
        visualizzaPannelloRicerca();
    }
    
    /**
     * Metodo che mostra il pannello Ricerca, con il campo di ricerca per cognome e la tabella militari.
     * 
     */
    private void visualizzaPannelloRicerca() {
        pannelloVisibile(pannelloPrincipale,pannelloRicerca);
        campoRicercaCognome.clear();//azzera campi
        
    }
    
//----------------------------------------------------------------------------------------------
                        /********************
                         * PANNELLO RICERCA *
                         ********************/
    
    @FXML
    private Pane pannelloIdentificativo;
    
    @FXML
    private TextField campoRicercaCognome;
    
    @FXML
    private ChoiceBox menuCorso;
   
    @FXML
    private TableView<Militare> tabellaMilitari;
    
    @FXML
    private TableColumn<Militare, String> colonnaCognome;
    
    @FXML
    private TableColumn<Militare, String> colonnaNome;
    
    @FXML
    private TableColumn<Militare, DataOraria> colonnaNascita;
    
    @FXML
    private TableColumn<Militare, String> colonnaCp;
    
    @FXML
    private TableColumn<Militare, String> colonnaCorso;
    
    
    

   
    private void inizializzaPannelloRicerca(){
        bordoEffetto(campoRicercaCognome);
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.COGNOME));
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.NOME));
        colonnaNascita.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.NASCITA));
        colonnaCp.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.CP));
        colonnaCorso.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.CORSO));
        tabellaMilitari.setItems(listaMilitari);
        menuCorso.getItems().add("");
        Object[] corsi = Programma.datiMilitari.corsiPresenti();
        if(corsi != null)
        	menuCorso.getItems().addAll(corsi);
    }
    
    
    
    /**
     * Metodo che popola la tabella militare, al variare dei caratteri immessi nel 
     * campo di ricerca cognomi.
     */
    @FXML
    private void ricercaMilitare(KeyEvent event){
        if(event.getSource() instanceof TextField){
            String cognome = campoRicercaCognome.getText();
            Object _corso = menuCorso.getValue();
            String corso = "";
            listaMilitari.clear();
            if(_corso != null){
                if(_corso.toString().length() > 0){
                    corso = _corso.toString();
                }
            }
            if(cognome != null){
                if(cognome.length() > 1){
                    ArrayList<Object[]> militari = Programma.datiMilitari.tuttiMilitariSimili(cognome,corso);
                    if(militari != null)
                        for(Object[] record: militari){
                            listaMilitari.add(new Militare(
                                            (String)record[BASE_DATI.MILITARE.GRADO],
                                            (String)record[BASE_DATI.MILITARE.COGNOME],
                                            (String)record[BASE_DATI.MILITARE.NOME],
                                            (DataOraria)record[BASE_DATI.MILITARE.DATA_NASCITA],
                                            (String)record[BASE_DATI.MILITARE.LUOGO_NASCITA],
                                            (String)record[BASE_DATI.MILITARE.COMPAGNIA],
                                            (String)record[BASE_DATI.MILITARE.CORSO]
                                    )
                            );
                        }
                }
            }
        }
    }
    
    
    
    /**
     * Metodo che permette di visualizzare il pannello Militare inizializzando
     * i campi.
     * 
     * @param event 
     */
    @FXML
    private void selezionaMilitareTabella(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            militare=tabellaMilitari.getSelectionModel().getSelectedItem();
            campoGrado.setText(militare.getGrado());
            campoCognome.setText(militare.getCognome());
            campoCorso.setText(militare.getCorso());
            campoCp.setText(militare.getCp());
            campoNascita.setText(militare.getNascita().stampaGiorno('/'));
            campoNome.setText(militare.getNome());
            pannelloVisibile(pannelloPrincipale,pannelloMilitare);
            caricaDatiMilitare();
        }
    }


    
    /**
     * Metodo che permette di eliminare il militare in tabella, selezionando la voce del menu
     * di contesto della tabella.
     * 
     * @param event mouse destro su tabella
     */
    @FXML
    private void eliminaMilitare(ActionEvent event) {
        militare=tabellaMilitari.getSelectionModel().getSelectedItem();
        SwingUtilities.invokeLater(() -> {
            Object idoneo = Programma.datiMilitari.trovaMilitare(militare.getCognome(),militare.getNome(), militare.getNascita())[it.difesa.esercito.rav17.infermeria.DaseDati.BASE_DATI.MILITARE.GML];
            if(idoneo == null){ //se non è ancora idoneo
                if(JOptionPane.showConfirmDialog(
                            null,
                            String.format(
                                    Risorse.Messaggi.DOMANDA_ELIMINA_MILITARE,
                                    militare.getCognome(),
                                    militare.getNome(),
                                    militare.getNascita().stampaGiorno()
                            ),
                            "Attenzione!",
                            JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION){
                    
                    Programma.datiMilitari.eliminaMilitare(
                            militare.getCognome(),
                            militare.getNome(), 
                            militare.getNascita().stampaGiorno('/')
                    );
                    listaMilitari.remove(militare);
                }
            }else{
                JOptionPane.showConfirmDialog(
                        null, 
                        Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_MILITARE, 
                        Risorse.Messaggi.TITOLO_AVVISO, 
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        
    }

    /**
     * Metodo che permette di modificare i dati anagrafici del militare, selezionando la voce del menu
     * di contesto della tabella.
     * 
     * @param event mouse destro su tabella
     */
    @FXML
    private void modificaMilitare(ActionEvent event) {
        militare=tabellaMilitari.getSelectionModel().getSelectedItem();
        Finestra.creaFinestra(
                new FinestraModificaMilitare(
                        militare.getCognome(),
                        militare.getNome(),
                        militare.getNascita()
                )
        );
    }
    
//-------------------------------------------------------------------------------------------------
                                    /******************
                                     *  DATI MILITARI *
                                     ******************/
    
    @FXML
    private Label etichettaStato;
    
    @FXML
    private HBox barraPulsanti;
    
    @FXML
    private Pane pulsantiSpeciali;
    
    @FXML
    private Button pulsanteStampa;
    
    @FXML
    private Button pulsanteSalva;
    
    @FXML
    private Button pulsanteAggiungi;
    
    @FXML
    private Button pulsanteSelezione;
    
    @FXML
    private Button pulsanteElimina;
    
    @FXML
    private Button pulsanteEliminaTutto;
    
    
    
    @FXML
    private ContextMenu menuDati;
    
    private void vediPulsantiSelezione(boolean vedi){
        barraPulsanti.setVisible(vedi);
        pulsanteAggiungi.setVisible(vedi);
        pulsanteSelezione.setVisible(vedi);
        pulsanteElimina.setVisible(vedi);
        pulsantiSpeciali.setVisible(false);
    }
    
    
    
    private void vediPulsanteSpecialiStampaEdEliminaTutto(){
        pulsantiSpeciali.setVisible(true);
        pulsanteStampa.setVisible(true);
        pulsanteSalva.setVisible(false);
        pulsanteEliminaTutto.setVisible(true);
        
    }
    
    
    
    private void vediPulsanteSalva(){
        barraPulsanti.setVisible(true);
        pulsantiSpeciali.setVisible(true);
        pulsanteStampa.setVisible(false);
        pulsanteSalva.setVisible(true);
        pulsanteEliminaTutto.setVisible(false);
        
        pulsanteAggiungi.setVisible(false);
        pulsanteSelezione.setVisible(false);
        pulsanteElimina.setVisible(false);
    }
    
    
    
    /**
     * Metodo che permette di caricare in base allo stato i dati relativi nel pannello secondario.
     */
    private void caricaDatiMilitare(){
        messaggio.setText("");
        etichettaUltimaModificaMilirare.setText("");
        vediPulsantiSelezione(false);
        
        switch(stato){
            case Risorse.Stato.GML:
                immagine.setImage(new Image(Risorse.Immagine.ATTESA));
                
                vediPulsanteSalva();
                etichettaStato.setText(Risorse.Etichette.GML);
                caricaCampiGML();
                break;
            case Risorse.Stato.MISURE:
                immagine.setImage(new Image(Risorse.Immagine.SCRIVERE));
                vediPulsanteSalva();
                etichettaStato.setText(Risorse.Etichette.MISURE);
                caricaCampiGML();
                break;
            case Risorse.Stato.STORIA_VACCINALE:
                immagine.setImage(new Image(Risorse.Immagine.SCRIVERE));
                etichettaStato.setText(Risorse.Etichette.STORIA_VACCINALE);
                if(caricaTabellaStoriaVaccinale()){
                    vediPulsantiSelezione(true);
                    vediPulsanteSpecialiStampaEdEliminaTutto();
                }else{
                    if(militare != null)
                        vediPulsanteSalva();
                    inizializzaPannelloInputStoriaVaccinale();
                }
                break;
            case Risorse.Stato.VACCINAZIONI:
                immagine.setImage(new Image(Risorse.Immagine.VACCINAZIONE));
                vediPulsantiSelezione(true);
                etichettaStato.setText(Risorse.Etichette.NUOVE_VACCINAZIONI);
                caricaTabellaVaccinazioni();
                break;
            case Risorse.Stato.VISITA_MEDICA:
                immagine.setImage(new Image(Risorse.Immagine.ATTESA));
                etichettaStato.setText(Risorse.Etichette.MISURE);
                
                vediPulsantiSelezione(true);
                etichettaStato.setText(Risorse.Etichette.VISITA_AMBULATORIALE);
                caricaTabellaVisiteMediche();
                break;
            case Risorse.Stato.VERBALE_MOD_ML:
                immagine.setImage(new Image(Risorse.Immagine.SCRIVERE));
                vediPulsantiSelezione(true);
                etichettaStato.setText(Risorse.Etichette.VERBALE_ML);
                caricaTabellaVerbaleML();
                break;
            case Risorse.Stato.VERBALE_MOD_GL:
                immagine.setImage(new Image(Risorse.Immagine.SCRIVERE));
                vediPulsantiSelezione(true);
                etichettaStato.setText(Risorse.Etichette.VERBALE_GL);
                caricaTabellaVerbaleGL();
                break;
            case Risorse.Stato.RICOVERO:
                immagine.setImage(new Image(Risorse.Immagine.ATTESA));
                vediPulsantiSelezione(true);
                etichettaStato.setText(Risorse.Etichette.RICOVERO);
                caricaTabellaRicoveri();
                break;
        }
    }
    
    /**
     * Apertura del menu a tentina sulle tabelle dei dati del militare associate
     * al clic secondario.
     * 
     * @param event click area tabella (associata)
     */
    @FXML
    private void aperturaMunuDati(MouseEvent event) {
        if(event.getButton() == MouseButton.SECONDARY){
            if(event.getSource() instanceof TableView){
                TableView tabella = (TableView) event.getSource();
                menuDati.show(tabella, event.getScreenX(), event.getScreenY());
            }
        }
    }
    
    @FXML
    private void stampaDati(ActionEvent event) {
        switch(stato){
            case Risorse.Stato.VERBALE_MOD_GL:
                break;
                
            case Risorse.Stato.VERBALE_MOD_ML:
                break;
                
            case Risorse.Stato.RICOVERO:
                break;
                
            case Risorse.Stato.VISITA_MEDICA:
                break;
                
            case Risorse.Stato.VACCINAZIONI:
                break;
                
            case Risorse.Stato.STORIA_VACCINALE:
                stampaStoriaVaccinale(event);
                break;
        }
    }
    
    @FXML
    private void salvaDati(ActionEvent event) {
        switch(stato){
            case Risorse.Stato.GML:
            case Risorse.Stato.MISURE:
                salvaModificheGML();
                break;
            case Risorse.Stato.STORIA_VACCINALE:
                salvaInputStoriaVaccinale(event);
                break;
        }
    }
    
    /**
     * 
     * @param event voce "Aggiungi" menu della tabella o dal pulsante aggiungi.
     */
    @FXML
    private void aggiungiDati(ActionEvent event) {
        switch(stato){
            case Risorse.Stato.VERBALE_MOD_GL:
                aggiungiVerbaleGL(event);
                break;
                
            case Risorse.Stato.VERBALE_MOD_ML:
                aggiungiVerbaleML(event);
                break;
                
            case Risorse.Stato.RICOVERO:
                aggiungiRicovero(event);
                break;
                
            case Risorse.Stato.VISITA_MEDICA:
                aggiungiVisitaMedica(event);
                break;
                
            case Risorse.Stato.VACCINAZIONI:
                creaNuovaVaccinazione(event);
                break;
                
            case Risorse.Stato.STORIA_VACCINALE:
                 aggiungiAltroVaccino(event);
                break;
        }
    }
    
    @FXML
    private void eliminaTuttiDati(ActionEvent event){
        if(Programma.utente != null){
            String livello = datiUtente.livello(Programma.utente);
            if(
                    (livello.equals(DatiUtente.AMMINISTRATORE) || livello.equals(DatiUtente.MEDICO) || livello.equals(DatiUtente.INFERMIERE)) ||
                    stato == Risorse.Stato.STORIA_VACCINALE) {
                
                switch(stato){
                    case Risorse.Stato.VERBALE_MOD_GL:
                    case Risorse.Stato.VERBALE_MOD_ML:
                    case Risorse.Stato.RICOVERO:
                    case Risorse.Stato.VISITA_MEDICA:
                    case Risorse.Stato.VACCINAZIONI:
                        break;

                    case Risorse.Stato.STORIA_VACCINALE:
                        eliminaStralcioVaccinale(event);
                        break;
                }
                
            }else{
                
                try {
                    Programma.finestraAvviso(this, Risorse.Messaggi.ERRORE_AUTORIZZAZIONE);
                } catch (IOException ex) {
                    erroreCaricamentoFinestra(ex);
                }
                
            }
        }
    }
    
    /**
     * 
     * @param event voce "Seleziona" menu della tabella oppure pulsante di 
     * Selezione
     */
    @FXML
    private void selezionaDati(ActionEvent event) {
        switch(stato){
            case Risorse.Stato.VERBALE_MOD_GL:
                selezionaVerbaleGLDaTabella(event);
                break;
                
            case Risorse.Stato.VERBALE_MOD_ML:
                selezionaVerbaleMLDaTabella(event);
                break;
                
            case Risorse.Stato.RICOVERO:
                selezionaRicoveroDaTabella(event);
                break;
                
            case Risorse.Stato.VISITA_MEDICA:
                selezionaVisitaDaTabella(event);
                break;
                
            case Risorse.Stato.VACCINAZIONI:
                selezionaSedutaVaccinale(event);
                break;
                
            case Risorse.Stato.STORIA_VACCINALE:
                modificaStoriaVaccinale(event);
                break;
        }
    }
    
    /**
     * 
     * @param event voce "Elimina" menu della tabella
     */
    @FXML
    private void eliminaDati(ActionEvent event) {
        if(Programma.utente != null){
            String livello = datiUtente.livello(Programma.utente);
            if(
                    (livello.equals(DatiUtente.AMMINISTRATORE) || livello.equals(DatiUtente.MEDICO) || livello.equals(DatiUtente.INFERMIERE)) ||
                    stato == Risorse.Stato.STORIA_VACCINALE) {
                
                switch(stato){
                    case Risorse.Stato.VERBALE_MOD_GL:
                        eliminaVerbaleGL(event);
                        break;

                    case Risorse.Stato.VERBALE_MOD_ML:
                        eliminaVerbaleML(event);
                        break;

                    case Risorse.Stato.RICOVERO:
                        eliminaRicovero(event);
                        break;

                    case Risorse.Stato.VISITA_MEDICA:
                        eliminaVisitaMedica(event);
                        break;

                    case Risorse.Stato.VACCINAZIONI:
                        eliminaVaccinazioni(event);
                        break;

                    case Risorse.Stato.STORIA_VACCINALE:
                        eliminaStoriaVaccinale(event);
                        break;
                }
            }else{
                
                try {
                    Programma.finestraAvviso(this, Risorse.Messaggi.ERRORE_AUTORIZZAZIONE);
                } catch (IOException ex) {
                    erroreCaricamentoFinestra(ex);
                }
                
            }
        }
    }

//-------------------------------------------------------------------------------------------------
                                    /******************
                                     * NUOVO MILITARE *
                                     ******************/

    @FXML
    private ChoiceBox menuInputCorso;
    
    @FXML
    private ChoiceBox menuInputCp;
    
    @FXML
    private ChoiceBox menuInputGrado;
    
    @FXML
    private ChoiceBox menuInputSesso;
    
    @FXML
    private ChoiceBox menuInputScuola;
    
    @FXML
    private ChoiceBox menuInputDocumento;
    
    @FXML
    private TextField campoInputLuogo;
    
    @FXML
    private TextField campoInputCognome;
    
    @FXML
    private TextField campoInputNome;
    
    @FXML
    private TextField campoInputNascita;
    
    @FXML
    private TextArea campoInputAnamnesiFamiliare;

    @FXML
    private TextArea campoInputAnamnesiRemota;

    @FXML
    private TextArea campoInputAnamnesiProssima;

    @FXML
    private TextField campoInputAnno;
    
    @FXML
    private TextField campoInputNrDocumento;
    
    
    @FXML
    private CheckBox casellaInputAssenzaDocG6PDH;

    @FXML
    private CheckBox casellaInputAssenzaG6PDH;
    
  
    
    private void inizializzaPannelloNuovoMilitare() {
        campoInputAnno.setText(new DataOraria().anno()+"");
        inizializzaMenuDocumento(menuInputDocumento);
        inizializzaScuola(menuInputScuola);
        inizializzaSesso(menuInputSesso);
        
        ListaCorsoController.inizializzaLista();
        menuInputCorso.setItems(ListaCorsoController.lista);
        
        ListaGradoController.inizializzaLista();
        menuInputGrado.setItems(ListaGradoController.lista);
        
        ListaCpController.inizializzaLista();
        menuInputCp.setItems(ListaCpController.lista);
        
        
        rinizializzaCampiVariabiliNuovoMilitare();
        comportamentoCampiNuovoMilitare();
    }
    
    /**
     * Metodo che inizializza solo i campi personali del militare, che variano
     * da individuo ad individuo.
     */
    private void rinizializzaCampiVariabiliNuovoMilitare(){
        campoInputCognome.setText("");
        campoInputNome.setText("");
        campoInputNascita.setText(Risorse.MascheraCampi.DATA);
        campoInputLuogo.setText("");
        campoInputNrDocumento.setText("");
        menuInputSesso.getSelectionModel().select(-1);
        menuInputDocumento.getSelectionModel().select(-1);
        menuInputScuola.getSelectionModel().select(-1);
        campoInputAnamnesiFamiliare.setText(Programma.datiImpostazioni.valore(Risorse.Impostazioni.KEY_ANAMNESI_FAMILIARE));
        campoInputAnamnesiRemota.setText(Programma.datiImpostazioni.valore(Risorse.Impostazioni.KEY_ANAMNESI_REMOTA));
        campoInputAnamnesiProssima.setText(Programma.datiImpostazioni.valore(Risorse.Impostazioni.KEY_ANAMNESI_PROSSIMA));
        campoInputNascita.setStyle(Risorse.Colore.NERO);
        casellaInputAssenzaG6PDH.setSelected(false);
        casellaInputAssenzaG6PDH.setIndeterminate(false);
        casellaInputAssenzaDocG6PDH.setSelected(false);
        casellaInputAssenzaDocG6PDH.setIndeterminate(false);
        
        
    }
    

    
    /**
     * Metodo che assegna un comportamento ad alcuni campi di input della finestra
     * per creare nuovi militari.
     */
    private void comportamentoCampiNuovoMilitare() {
        CampoTesto.aggiungiMascheraInput(campoInputNascita, Risorse.MascheraCampi.DATA,Risorse.MascheraCampi.CARATTERE);
        CampoTesto.soloCaratteri(campoInputCognome, 40," '");
        CampoTesto.maiuscolo(campoInputCognome);
        
        CampoTesto.soloCaratteri(campoInputNome, 40," ',*");
        CampoTesto.maiuscolo(campoInputNome);
        ArrayList<String> comuni = Programma.datiGenerici.tuttiComuni();
        if(comuni != null)
        	CampoTesto.autoCompletamento(campoInputLuogo, comuni);
        CampoTesto.maiuscolo(campoInputLuogo);
        
        CampoTesto.maiuscolo(campoInputAnamnesiFamiliare);
        CampoTesto.maiuscolo(campoInputAnamnesiRemota);
        CampoTesto.maiuscolo(campoInputAnamnesiProssima);
        CampoTesto.aggiungiLimiteTesto(campoInputAnamnesiFamiliare, 100);
        CampoTesto.aggiungiLimiteTesto(campoInputAnamnesiRemota, 100);
        CampoTesto.aggiungiLimiteTesto(campoInputAnamnesiProssima, 100);
        
        CampoTesto.soloNumeri(campoInputAnno,4);
        CampoTesto.maiuscolo(campoInputNrDocumento);
        CampoTesto.aggiungiLimiteTesto(campoInputNrDocumento, 30);
    }
    
    /**
     * Metodo che implementa il pulsante "Salva" del pannello di creazione del nuovo militare.
     * 
     * @param event 
     */
    @FXML
    private void creaNuovoMilitare(ActionEvent event){
        boolean errore = !CampoTesto.controllaData(campoInputNascita);
        if(errore){
            avviso(Risorse.Messaggi.ERRORE_DATA);
            messaggio.setText(Risorse.Messaggi.ERRORE_DATA);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
        if(this.campoInputCognome.getText().isEmpty() || this.campoInputNome.getText().isEmpty()){
            errore = true;
            avviso(Risorse.Messaggi.ERRORE_CAMPI_PRINCIPALI);
            messaggio.setText(Risorse.Messaggi.ERRORE_CAMPI_PRINCIPALI);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
        if(!errore){
            String cognome =campoInputCognome.getText(); 
            String nome =campoInputNome.getText(); 
            DataOraria nascita;
                
            try {
                nascita = new DataOraria(campoInputNascita.getText());
                if(Programma.datiMilitari.aggiungiMilitare(cognome,nome,nascita,recordNuovoMilitare())){
                    messaggio.setText(
                            Risorse.Messaggi.AGGIUNTO_NUOVO_RECORD
                            + String.format(Risorse.FORMAT.STAMPA_MILITARE_S, cognome,nome,nascita.stampaGiorno())
                    );
                    messaggio.setStyle(Risorse.Colore.BLU);
                    rinizializzaCampiVariabiliNuovoMilitare();
                }else{
                    avviso(Risorse.Messaggi.ERRORE_MILITARE_MILITARE_GIA_PRESENTE);
                    messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_MILITARE_GIA_PRESENTE);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            } catch (Errore ex) {
                avviso(Risorse.Messaggi.ERRORE_DATA);
                messaggio.setText(Risorse.Messaggi.ERRORE_DATA);
                messaggio.setStyle(Risorse.Colore.ROSSO);
            }
                
            
        }
    }
    
    private void avviso(String messaggio){
        try {
            Programma.finestraAvviso(this,messaggio);
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    /**
     * Metodo che permette di generare il record militare, inizializzato con i valori
     * del pannello "Nuovo Militare".
     * 
     * @return Object[]
     */
    private Object[] recordNuovoMilitare() {
        Object[] record = new Object[Programma.datiMilitari.dimensioneMilitate()];
        
        Object grado = menuInputGrado.getSelectionModel().getSelectedItem();
        if(grado != null)
            record[BASE_DATI.MILITARE.GRADO] = grado.toString();
        else
            record[BASE_DATI.MILITARE.GRADO] = "?";
        
        Object cp = menuInputCp.getSelectionModel().getSelectedItem();
        if(cp != null)
            record[BASE_DATI.MILITARE.COMPAGNIA] = cp.toString();
        else
            record[BASE_DATI.MILITARE.COMPAGNIA] = "?";
            
        Object nrCorso = menuInputCorso.getSelectionModel().getSelectedItem();
        int anno = Integer.parseInt(campoInputAnno.getText());
        record[BASE_DATI.MILITARE.CORSO] = 
                menuInputCorso.getSelectionModel().getSelectedIndex() != -1 
                    ? nrCorso+Risorse.Input.CORSO_SEPARATORE+ anno
                    : Risorse.Input.CORSO_QP;
        
        record[BASE_DATI.MILITARE.LUOGO_NASCITA] = campoInputLuogo.getText();
        record[BASE_DATI.MILITARE.SCUOLA] = menuInputScuola.getSelectionModel().getSelectedItem();
        record[BASE_DATI.MILITARE.SESSO] = menuInputSesso.getSelectionModel().getSelectedItem();
        record[BASE_DATI.MILITARE.ANAMNESI_FAMILIARE] = campoInputAnamnesiFamiliare.getText();
        record[BASE_DATI.MILITARE.ANAMNESI_REMOTA] = campoInputAnamnesiRemota.getText();
        record[BASE_DATI.MILITARE.ANAMNESI_PROSSIMA] = campoInputAnamnesiProssima.getText();
        record[BASE_DATI.MILITARE.DOCUMENTO] = menuInputDocumento.getSelectionModel().getSelectedItem();
        record[BASE_DATI.MILITARE.NR_DOCUMENTO] = campoInputNrDocumento.getText();
        record[BASE_DATI.MILITARE.ASSENZA_DOC_FAVISMO] = casellaInputAssenzaDocG6PDH.selectedProperty().getValue();
        record[BASE_DATI.MILITARE.FAVISMO] = casellaInputAssenzaG6PDH.selectedProperty().getValue();
        //registrazione delle modifiche fatte dall'utente
        DataOraria ora = new DataOraria();ora.adesso();
        record[BASE_DATI.MILITARE.MODIFICA] = ora;
        record[BASE_DATI.MILITARE.UTENTE] = Programma.utente;
        return record;
    }
    
    
    /**
     * Metodo che apre la finestra che gestisce i valori predefiniti dei campi di anamnesi.
     * 
     * @param event 
     */
    @FXML
    private void apriFinestraImpostazioniAnamnesi(ActionEvent event) {
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_IMPOSTAZIONI_ANAMNESI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    
    /**
     * Metodo che evidenzia l'etichetta "pulsante".
     * 
     * @param event 
     */
    @FXML
    private void attivaEtichetta(MouseEvent event){
        Object etichetta = event.getSource();
        if(etichetta instanceof Label){
            ((Label) etichetta).setTextFill(Color.CHARTREUSE);
            
            bordoEffetto((Label)etichetta);
        }
    }
    
    
    /**
     * Metodo che fa ritornare normale l'etichetta "pulsante".
     * 
     * @param event 
     */
    @FXML
    private void ripristinaEtichetta(MouseEvent event){
        Object etichetta = event.getSource();
        if(etichetta instanceof Label){
            ((Label) etichetta).setTextFill(Color.BLACK);
            ((Label) etichetta).setEffect(null);
            ((Label) etichetta).setBorder(Border.EMPTY);
        }
    }
    
    /**
     * Metodo che permette di aprire la finestra per la creazione di una nuova Cp, tramite
     * l'etichetta pulsante "cp/btg/rgt".
     * 
     * @param event 
     */
    @FXML
    private void aggiungiNuovaCp(MouseEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_LISTA_CP))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    /**
     * Metodo che permette di aprire la finestra per la creazione di un nuovo grado, tramite
     * l'etichetta pulsante "Grado".
     * 
     * @param event 
     */
    @FXML
    private void aggiungiNuovoGrado(MouseEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_LISTA_GRADO))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    /**
     * Metodo che permette di aprire la finestra per la creazione di un nuovo grado, tramite
     * l'etichetta pulsante "Grado".
     * 
     * @param event 
     */
    @FXML
    private void aggiungiNuovoCorso(MouseEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_LISTA_CORSO))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
//-------------------------------------------------------------------------------------------------
                                    /******************
                                     *  GML / Misure  *
                                     ******************/

    @FXML
    private Accordion raccoglitoreGML;
    
    @FXML
    private TitledPane schedaGML;
    
    @FXML
    private TitledPane schedaMisure;
    
    @FXML
    private TitledPane schedaAnamnesi;
    
    @FXML
    private TextField campoOutputLuogo;
    
    @FXML
    private ChoiceBox menuOutputSesso;
    
    @FXML
    private ChoiceBox menuOutputScuola;
    
    @FXML
    private ChoiceBox menuOutputDocumento;
    
    @FXML
    private TextField campoOutputNrDocumento;
    
    @FXML
    private TextField campoOutputAnamnesiFamiliare;
    
    @FXML
    private TextField campoOutputAnamnesiRemota;
    
    @FXML
    private TextField campoOutputAnamnesiProssima;
    
    
    @FXML
    private CheckBox casellaAssenzaDocG6PDH;
    
    @FXML
    private CheckBox casellaAssenzaG6PDH;
    
    @FXML
    private TextField campoAltezza;
    
    @FXML
    private TextField campoPeso;
    
    @FXML
    private TextField campoTorace;
    
    @FXML
    private Label etichettaIMC;
    
    @FXML
    private TextField campoVisusOD;
    
    @FXML
    private TextField campoVisusODcorretto;
    
    @FXML
    private TextField campoVisusOS;
    
    @FXML
    private TextField campoVisusOScorretto;
    
    @FXML
    private TextField campoPressioneMin;
    
    @FXML
    private TextField campoPressioneMax;
    
    @FXML
    private TextField campoFrequenza;
    
    @FXML
    private DatePicker dataGML;
    
    @FXML
    private TextArea campoGML;
    
    @FXML
    private ComboBox menuMedicoGML;
    
    @FXML
    private Label etichettaUltimaModificaMilirare;
    
    @FXML
    private ComboBox menuIdoneità; 

    
    
    class GML{
        public String giudizio=null;
        public String note=null; 
        
        static final String SEPARATORE = "\n\r";
        
        GML(String testo){
            if(testo != null){
                giudizio="";
                note="";
                int id = testo.indexOf(SEPARATORE);
                if(id > 0){
                    if(id <= testo.length()){
                        giudizio = testo.substring(0,id);
                    }
                    if(id+2 < testo.length()){
                        note = testo.substring(id+2);
                    }
                }else{
                    note = testo;
                }
                 
            }
        }
    }
            
            
    
    /**
     * Metodo che permette la stampa dei dati della verbale di incorporamento e del GML.
     * 
     * @param event riferito al pulsante stampa del pannello secondario GML
     */
    @FXML
    private void stampaGML(ActionEvent event){
        final Object[] datiMilitare = salvaModificheGML();
        if(datiMilitare != null)
            SwingUtilities.invokeLater(() -> {
                FinestraStampatoSemplice finestra =new FinestraStampatoSemplice();
                finestra.setVisible(true);
                Report.aggiungiReportGML(finestra, datiMilitare);
            });
    }
    
    

    
    /**
     * Metodo privato che effettua il salvataggio dei campi del GML nel BASE_DATI.
     * 
     * @return restituisce null se il militare non è stato selezionato altrimenti 
 il record della tabella (BASE_DATI) militare
     */
    private Object[] salvaModificheGML(){
        if(militare != null){
            Object[] record = new Object[Programma.datiMilitari.dimensioneMilitate()];
            record[BASE_DATI.MILITARE.GRADO] = campoGrado.getText();
            record[BASE_DATI.MILITARE.COGNOME] = campoCognome.getText();
            record[BASE_DATI.MILITARE.NOME] = campoNome.getText();
            try {
                record[BASE_DATI.MILITARE.DATA_NASCITA] = new DataOraria(campoNascita.getText());
            } catch (Errore ex) {
                avviso(Risorse.Messaggi.ERRORE_DATA);
                messaggio.setText(Risorse.Messaggi.ERRORE_DATA);
                CampoTesto.controllaData(campoNascita);
                messaggio.setStyle(Risorse.Colore.ROSSO);
                return null;
            }
            record[BASE_DATI.MILITARE.COMPAGNIA] = campoCp.getText();
            record[BASE_DATI.MILITARE.CORSO] = campoCorso.getText();
            record[BASE_DATI.MILITARE.LUOGO_NASCITA] = campoOutputLuogo.getText();
            record[BASE_DATI.MILITARE.SCUOLA] = menuOutputScuola.getSelectionModel().getSelectedItem();
            record[BASE_DATI.MILITARE.SESSO] = menuOutputSesso.getSelectionModel().getSelectedItem();
            record[BASE_DATI.MILITARE.ANAMNESI_FAMILIARE] = campoOutputAnamnesiFamiliare.getText();
            record[BASE_DATI.MILITARE.ANAMNESI_REMOTA] = campoOutputAnamnesiRemota.getText();
            record[BASE_DATI.MILITARE.ANAMNESI_PROSSIMA] = campoOutputAnamnesiProssima.getText();
            record[BASE_DATI.MILITARE.DOCUMENTO] = menuOutputDocumento.getSelectionModel().getSelectedItem();
            record[BASE_DATI.MILITARE.NR_DOCUMENTO] = campoOutputNrDocumento.getText();
            record[BASE_DATI.MILITARE.ASSENZA_DOC_FAVISMO] = casellaAssenzaDocG6PDH.selectedProperty().getValue();
            record[BASE_DATI.MILITARE.FAVISMO] = casellaAssenzaG6PDH.selectedProperty().getValue();
            
            try{
                record[BASE_DATI.MILITARE.ALTEZZA]= Long.parseLong(campoAltezza.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.ALTEZZA]=0;
            }
            try{
                record[BASE_DATI.MILITARE.PESO] = Long.parseLong(campoPeso.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.PESO]=0;
            }
            try{
                record[BASE_DATI.MILITARE.TORACE] = Long.parseLong(campoTorace.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.TORACE]=0;
            }
            try{
                record[BASE_DATI.MILITARE.VISUS_OD] = Long.parseLong(campoVisusOD.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.VISUS_OD]=0;
            }
            try{
                record[BASE_DATI.MILITARE.VISUS_OS] = Long.parseLong(campoVisusOS.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.VISUS_OS]=0;
            }
            try{
                record[BASE_DATI.MILITARE.VISUS_OD_CORRETTO] = Long.parseLong(campoVisusODcorretto.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.VISUS_OD_CORRETTO]=0;
            }
            try{
                record[BASE_DATI.MILITARE.VISUS_OS_CORRETTO] = Long.parseLong(campoVisusOScorretto.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.VISUS_OS_CORRETTO]=0;
            }
            try{
                record[BASE_DATI.MILITARE.PRESSIONE_MAX] = Long.parseLong(campoPressioneMax.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.PRESSIONE_MAX]=0;
            }
            try{
                record[BASE_DATI.MILITARE.PRESSIONE_MIN] = Long.parseLong(campoPressioneMin.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.PRESSIONE_MIN]=0;
            }
            try{
                record[BASE_DATI.MILITARE.FREQUENZA] = Long.parseLong(campoFrequenza.getText());
            }catch(NumberFormatException e){
                record[BASE_DATI.MILITARE.FREQUENZA]=0;
            }
            LocalDate data = dataGML.getValue();
            if(data != null)
                record[BASE_DATI.MILITARE.DATA_GML] = new DataOraria(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
            
            Object giudizio = menuIdoneità.getSelectionModel().getSelectedItem();
            
            record[BASE_DATI.MILITARE.GML] = (giudizio != null ? giudizio+GML.SEPARATORE : "")+campoGML.getText();
            
            record[BASE_DATI.MILITARE.MEDICO] = menuMedicoGML.getSelectionModel().getSelectedItem();
            
            //registrazione delle modifiche fatte dall'utente
            DataOraria ora = new DataOraria();ora.adesso();
            record[BASE_DATI.MILITARE.MODIFICA] = ora;
            record[BASE_DATI.MILITARE.UTENTE] = Programma.utente;
            if(Programma.datiMilitari.modificaMilitare(militare.getCognome(), militare.getNome(), militare.getNascita(), record)){
                
                visualizzaPannelloRicerca(); // <- N.B.: ripristino finestra ricerca
            
                messaggio.setText(Risorse.Messaggi.RECORD_MODIFICATO+militare);
                messaggio.setStyle(Risorse.Colore.BLU);
                return record;
            }else{
                avviso(Risorse.Messaggi.ERRORE_SALVATAGGIO_DATI);
                messaggio.setText(Risorse.Messaggi.ERRORE_SALVATAGGIO_DATI);
                messaggio.setStyle(Risorse.Colore.ROSSO);

                return null;
            }
             
            
        }else{
            avviso(Risorse.Messaggi.ERRORE_NESSUN_UTENTE_SELEZIONATO);
            messaggoErroreDatiMilitare();
            return null;
        }
    }
    
    /**
     * Metodo che inizializza i campi del pannello secondario GML.
     */
    private void rinizializzaGML(){
        inizializzaMenuDocumento(menuOutputDocumento);
        inizializzaScuola(menuOutputScuola);
        inizializzaSesso(menuOutputSesso);
        campoOutputLuogo.setText("");
        menuOutputSesso.getSelectionModel().select(-1);
        menuOutputScuola.getSelectionModel().select(-1);
        menuOutputDocumento.getSelectionModel().select(-1);
        campoOutputNrDocumento.setText("");
        campoOutputAnamnesiFamiliare.setText("");
        campoOutputAnamnesiRemota.setText("");
        campoOutputAnamnesiProssima.setText("");
        casellaAssenzaDocG6PDH.setSelected(false);
        casellaAssenzaG6PDH.setSelected(false);
        etichettaIMC.setText("");
        campoTorace.setText("");
        campoPeso.setText("");
        campoAltezza.setText("");
        campoVisusOD.setText("");
        campoVisusODcorretto.setText("");
        campoVisusOS.setText("");
        campoVisusOScorretto.setText("");
        campoPressioneMin.setText("");
        campoPressioneMax.setText("");
        campoFrequenza.setText("");
        dataGML.setValue(LocalDate.now());
        campoGML.setText("");
        menuMedicoGML.getSelectionModel().clearSelection();
        messaggio.setText("");
        messaggio.setStyle(Risorse.Colore.ROSSO);
        etichettaUltimaModificaMilirare.setText("");
        inizializzaMedico(this.menuMedicoGML);
        if(menuIdoneità.getItems().isEmpty()){
            menuIdoneità.getItems().addAll(
                    Risorse.Input.IDONEO,
                    Risorse.Input.IDONEA,
                    Risorse.Input.NON_IDONEO,
                    Risorse.Input.NON_IDONEA,
                    Risorse.Input.TEMPORANEAMENTE_NON_IDONEO,
                    Risorse.Input.TEMPORANEAMENTE_NON_IDONEA
            );
        }
    }

    private void comportamentoCampiGML(){
        //Programma.autoCompletamento(campoOutputLuogo, datiGenerici.tuttiComuni());
        CampoTesto.maiuscolo(campoOutputLuogo);
        
        CampoTesto.maiuscolo(campoOutputAnamnesiFamiliare);
        CampoTesto.maiuscolo(campoOutputAnamnesiRemota);
        CampoTesto.maiuscolo(campoOutputAnamnesiProssima);
        CampoTesto.aggiungiLimiteTesto(campoOutputAnamnesiFamiliare, 100);
        CampoTesto.aggiungiLimiteTesto(campoOutputAnamnesiRemota, 100);
        CampoTesto.aggiungiLimiteTesto(campoOutputAnamnesiProssima, 100);
        
        CampoTesto.maiuscolo(campoOutputNrDocumento);
        CampoTesto.aggiungiLimiteTesto(campoOutputNrDocumento, 30);
        CampoTesto.soloNumeri(campoAltezza, 3);
        CampoTesto.soloNumeri(campoPeso, 3);
        CampoTesto.soloNumeri(campoTorace, 3);
        CampoTesto.soloNumeri(campoVisusOD, 2);
        CampoTesto.soloNumeri(campoVisusODcorretto, 2);
        CampoTesto.soloNumeri(campoVisusOS, 2);
        CampoTesto.soloNumeri(campoVisusOScorretto, 2);
        CampoTesto.soloNumeri(campoPressioneMin, 3);
        CampoTesto.soloNumeri(campoPressioneMax, 3);
        CampoTesto.soloNumeri(campoFrequenza, 3);
        
        CampoTesto.cancellaAlFocus(campoAltezza);
        CampoTesto.cancellaAlFocus(campoPeso);
        CampoTesto.cancellaAlFocus(campoTorace);
        CampoTesto.cancellaAlFocus(campoVisusOD);
        CampoTesto.cancellaAlFocus(campoVisusODcorretto);
        CampoTesto.cancellaAlFocus(campoVisusOS);
        CampoTesto.cancellaAlFocus(campoVisusOScorretto);
        CampoTesto.cancellaAlFocus(campoPressioneMin);
        CampoTesto.cancellaAlFocus(campoPressioneMax);
        CampoTesto.cancellaAlFocus(campoFrequenza);
    }
    
    /**
     * Metodo che caria tutti i campi del pannello secondario GML del militare, interrogando la base di dati.
     */
    private void caricaCampiGML() {
        rinizializzaGML();
        if(militare != null){
            pannelloVisibile(pannelloSecondario, pannelloGML);
            Object[] record = Programma.datiMilitari.trovaMilitare(
                    militare.getCognome(), 
                    militare.getNome(), 
                    militare.getNascita()
            );
            if(record != null){
                if(record[BASE_DATI.MILITARE.LUOGO_NASCITA] != null)
                    campoOutputLuogo.setText(record[BASE_DATI.MILITARE.LUOGO_NASCITA].toString());
                if(record[BASE_DATI.MILITARE.SESSO] != null)
                    menuOutputSesso.setValue(record[BASE_DATI.MILITARE.SESSO].toString());
                if(record[BASE_DATI.MILITARE.SCUOLA] != null)
                    menuOutputScuola.setValue(record[BASE_DATI.MILITARE.SCUOLA].toString());
                if(record[BASE_DATI.MILITARE.DOCUMENTO] != null)
                    menuOutputDocumento.setValue(record[BASE_DATI.MILITARE.DOCUMENTO].toString());
                if(record[BASE_DATI.MILITARE.NR_DOCUMENTO] != null)
                    campoOutputNrDocumento.setText(record[BASE_DATI.MILITARE.NR_DOCUMENTO].toString());
                
                if(record[BASE_DATI.MILITARE.ANAMNESI_FAMILIARE] != null)
                    campoOutputAnamnesiFamiliare.setText(record[BASE_DATI.MILITARE.ANAMNESI_FAMILIARE].toString());
                if(record[BASE_DATI.MILITARE.ANAMNESI_REMOTA] != null)
                    campoOutputAnamnesiRemota.setText(record[BASE_DATI.MILITARE.ANAMNESI_REMOTA].toString());
                if(record[BASE_DATI.MILITARE.ANAMNESI_PROSSIMA] != null)
                    campoOutputAnamnesiProssima.setText(record[BASE_DATI.MILITARE.ANAMNESI_PROSSIMA].toString());
                
                if(record[BASE_DATI.MILITARE.ASSENZA_DOC_FAVISMO] != null)
                    casellaAssenzaDocG6PDH.setSelected((Boolean)record[BASE_DATI.MILITARE.ASSENZA_DOC_FAVISMO]);
                if(record[BASE_DATI.MILITARE.FAVISMO] != null)
                    casellaAssenzaG6PDH.setSelected((Boolean)record[BASE_DATI.MILITARE.FAVISMO]);
                
                if(record[BASE_DATI.MILITARE.ALTEZZA] != null)
                    campoAltezza.setText(record[BASE_DATI.MILITARE.ALTEZZA].toString());
                if(record[BASE_DATI.MILITARE.PESO] != null)
                    campoPeso.setText(record[BASE_DATI.MILITARE.PESO].toString());
                if(record[BASE_DATI.MILITARE.TORACE] != null)
                    campoTorace.setText(record[BASE_DATI.MILITARE.TORACE].toString());
                if(record[BASE_DATI.MILITARE.VISUS_OD] != null)
                    campoVisusOD.setText(record[BASE_DATI.MILITARE.VISUS_OD].toString());
                if(record[BASE_DATI.MILITARE.VISUS_OD_CORRETTO] != null)
                    campoVisusODcorretto.setText(record[BASE_DATI.MILITARE.VISUS_OD_CORRETTO].toString());
                if(record[BASE_DATI.MILITARE.VISUS_OS] != null)
                    campoVisusOS.setText(record[BASE_DATI.MILITARE.VISUS_OS].toString());
                if(record[BASE_DATI.MILITARE.VISUS_OS_CORRETTO] != null)
                    campoVisusOScorretto.setText(record[BASE_DATI.MILITARE.VISUS_OS_CORRETTO].toString());
                if(record[BASE_DATI.MILITARE.PRESSIONE_MIN] != null)
                    campoPressioneMin.setText(record[BASE_DATI.MILITARE.PRESSIONE_MIN].toString());
                if(record[BASE_DATI.MILITARE.PRESSIONE_MAX] != null)
                    campoPressioneMax.setText(record[BASE_DATI.MILITARE.PRESSIONE_MAX].toString());
                if(record[BASE_DATI.MILITARE.FREQUENZA] != null)
                    campoFrequenza.setText(record[BASE_DATI.MILITARE.FREQUENZA].toString());
                DataOraria data = (DataOraria)record[BASE_DATI.MILITARE.DATA_GML];
                if(data != null)
                    dataGML.setValue(
                            LocalDate.parse(data.stampaGiornoInverso())
                    );
                if(record[BASE_DATI.MILITARE.GML] != null){
                    GML gml = new GML(record[BASE_DATI.MILITARE.GML].toString());
                    campoGML.setText(gml.note);
                    this.menuIdoneità.setValue(gml.giudizio);
                }
                if(record[BASE_DATI.MILITARE.MEDICO] != null)
                    menuMedicoGML.setValue(record[BASE_DATI.MILITARE.MEDICO].toString());
                IMC();
                if(record[BASE_DATI.MILITARE.MODIFICA] !=null && record[BASE_DATI.MILITARE.UTENTE] != null)
                    ultimaModifica(
                            (DataOraria)record[BASE_DATI.MILITARE.MODIFICA], 
                            (String) record[BASE_DATI.MILITARE.UTENTE]
                    ); 
            }
        }else{
            messaggoErroreDatiMilitare();
        }
    }
    
    
    private void messaggoErroreDatiMilitare(){
        messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE_SELEZIONATO);
        messaggio.setStyle(Risorse.Colore.ROSSO);
        pannelloVisibile(pannelloSecondario, null);
    }
    
    private void ultimaModifica(DataOraria data,String utente){
        String modifica = 
                data != null 
                ? String.format(Risorse.FORMAT.STAMPA_DATA_ORA,data.stampaGiorno(),data.stampaOra())
                :
                Risorse.Messaggi.INFO_MODIFICA_SCONOSCIUTO;
        String _utente = 
                utente != null 
                ? 
                utente
                : 
                Risorse.Messaggi.INFO_UTENTE_SCONOSCIUTO;

        etichettaUltimaModificaMilirare.setText(
                Risorse.Messaggi.INFO_ULTIMA_MODIFICA_DATI +_utente+ modifica
        );
    }
    /**
     * Metodo per il calcolo dell' IMC (indice di massa corporea).
     */
    private void IMC(){
        try{
            int p =Integer.parseInt(campoPeso.getText());
            int h =Integer.parseInt(campoAltezza.getText());
            float IMC = (float) (p*10000/(double)(h*h));
            etichettaIMC.setText(Risorse.Etichette.IMC+IMC);
        }catch(NumberFormatException e){
            etichettaIMC.setText(Risorse.Etichette.IGNOTO);
        }
    }
    
    /**
     * Metodo che permette di visualizzare tutte le shede del pannello secondario GML.
     */
    private void visualizzaSchedaGML(){
        raccoglitoreGML.setExpandedPane(schedaGML);
        schedaAnamnesi.setVisible(true);
        schedaMisure.setVisible(true);
        schedaGML.setVisible(true);
    }
    
    /**
     * Metodo che permette di visualizzare solo la sheda delle misurazioni del pannello GML.
     */
    private void visualizzaSchedaMisure(){
        raccoglitoreGML.setExpandedPane(schedaMisure);
        schedaAnamnesi.setVisible(false);
        schedaMisure.setVisible(true);
        schedaGML.setVisible(false);
    }

//-------------------------------------------------------------------------------------------------
                                /********************
                                 * STORIA VACCINALE *
                                 ********************/


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
    
    /**
     * Metodo che popola la tabella dello storico vaccinale.
     * 
     * @return restituisce true se la tabellta è stata caricata correttamente, altrimenti false.
     */
    private boolean caricaTabellaStoriaVaccinale(){
        listaStoriaVaccinale.clear();
        boolean trovato=false;
        if(militare != null){
            String utenteModifica ="";
            DataOraria ultimaData = null;
            for(String profilassi: Programma.datiStoricoVaccinale.profilassi()){
                Object[] record = Programma.datiStoricoVaccinale.trovaStoriaVaccinale(
                        militare.getCognome(), 
                        militare.getNome(), 
                        militare.getNascita(), 
                        profilassi
                );
                if(record != null){
                    listaStoriaVaccinale.add(new StoriaVaccinale(
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
                    DataOraria dataModifica = (DataOraria)record[BASE_DATI.STORIA_VACCINALE.MODIFICA];
                    
                    if(dataModifica != null){
                        if(ultimaData == null){
                            ultimaData = dataModifica;
                            utenteModifica = (String)record[BASE_DATI.STORIA_VACCINALE.UTENTE];
                        }else if(dataModifica.compareTo(ultimaData) > 0){
                            ultimaData = dataModifica;
                            utenteModifica = (String)record[BASE_DATI.STORIA_VACCINALE.UTENTE];
                        }
                    }
                }else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
                    messaggio.setStyle(Risorse.Colore.ARANCIONE);
                    break;
                }
            }
            if(ultimaData != null)
                ultimaModifica(ultimaData,utenteModifica);
             if(trovato)
                pannelloVisibile(pannelloSecondario,pannelloStoriaVaccinale);
            else
                pannelloVisibile(pannelloSecondario,pannelloInputStoriaVaccinale);
        }else{
            messaggoErroreDatiMilitare();
        }
        
        return trovato;
    }
    
    
      
    @FXML
    private void eliminaStoriaVaccinale(ActionEvent event){
        
        try{
            String profilassi = tabellaStoricoVaccinale.getSelectionModel().getSelectedItem().getVaccino();
            Programma.finestraConferma(
                        this,
                        String.format(Risorse.Messaggi.DOMANDA_ELIMINA_VACCINO,profilassi),
                        ()->{
                            Programma.datiStoricoVaccinale.eliminaStoriaVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita(), profilassi);
                            Programma.datiStoricoVaccinale.aggiungiStoriaVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita(), profilassi, false, false,null, "", "", null, null, Programma.utente);
                            pannelloVisibile(pannelloSecondario,pannelloStoriaVaccinale);
                            caricaTabellaStoriaVaccinale();
                        }
                );
        }catch(java.lang.NullPointerException e){
            try {
                Programma.finestraAvviso(this, Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
            } catch (IOException ex) {
                erroreCaricamentoFinestra(ex);
            }
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
        
    }
    
    private void eliminaStralcioVaccinale(ActionEvent event){
        try {
            Programma.finestraConferma(
                    this,
                    String.format(Risorse.Messaggi.DOMANDA_ELIMINA_STRALCIO,militare.toString()),
                    ()->{
                        try {
                            Programma.datiStoricoVaccinale.eliminaStoriaVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita().stampaGiorno());
                        } catch (Errore ex) {
                            Logger.getLogger(FinestraPrincipaleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pannelloVisibile(pannelloSecondario,pannelloInputStoriaVaccinale);
                        vediPulsanteSalva();
                    }
            );
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
    }
    
    public static String[][] tabellaStoricoVaccinale(){
        String[][] tabella = new String[listaStoriaVaccinale.size()][8];
        int r =0;
        for (StoriaVaccinale record : listaStoriaVaccinale) {
            tabella[r][0] = record.getVaccino();
            tabella[r][1] = record.getPregressa() + "";
            tabella[r][2] = record.getPregressaDocumentata() + "";
            if (record.getCivile() != null) {
                tabella[r][3] = record.getCivile();
            } else {
                tabella[r][3] = "";
            }
            if (record.getMilitare() != null) {
                tabella[r][4] = record.getMilitare();
            } else {
                tabella[r][4] = "";
            }
            if (record.getDose() != null) {
                tabella[r][5] = record.getDose();
            } else {
                tabella[r][5] = "";
            }
            if (record.getTipo() != null) {
                tabella[r][6] = record.getTipo();
            } else {
                tabella[r][6] = "";
            }
            if (record.getData() != null) {
                tabella[r][7] = record.getData().stampaGiorno('/');
            } else {
                tabella[r][7] = "";
            }
            r++;
        }
        return tabella;
    }
    
    @FXML
    private void stampaStoriaVaccinale(ActionEvent event){
        Object[] datiM = Programma.datiMilitari.trovaMilitare(militare.getCognome(), militare.getNome(), militare.getNascita());
       String scuola = datiM != null ? datiM[BASE_DATI.MILITARE.SCUOLA].toString() : "";
        Object[][] tabella = tabellaStoricoVaccinale();
        if(tabella != null){
            Report.stampaStoriaVaccinale(
                    militare.vettore(scuola),
                    Programma.datiImpostazioni.valore(Risorse.Impostazioni.TITOLO_REPARTO),
                    Programma.datiImpostazioni.valore(Risorse.Impostazioni.TITOLO_UFFICIO),
                    Programma.datiImpostazioni.valore(Risorse.Impostazioni.SEDE), 
                    (String[][]) tabella,
                    null
            );
        }else{
            avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
    }
    
    
    
             
    
    private void modificaStoriaVaccinale(ActionEvent event){
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
            erroreCaricamentoFinestra(ex);
        }
       
    }
    
    
    
//-------------------------------------------------------------------------------------------------
                           /**************************
                            * INPUT STORIA VACCINALE *
                            **************************/
    
    @FXML
    private TextField dataInputTD;
    
    @FXML
    private ComboBox  menuInputDoseTD;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareTD;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileTD;
    
    @FXML
    private CheckBox casellaPregressaDocInputTD;

    @FXML
    private TextField dataInputTDP;
    
    @FXML
    private ComboBox  menuInputDoseTDP;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareTDP;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileTDP;
    
    @FXML
    private CheckBox casellaPregressaDocInputTDP;
    
     @FXML
    private TextField dataInputPolio;
    
    @FXML
    private ComboBox  menuInputDosePolio;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitarePolio;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivilePolio;
    
    @FXML
    private CheckBox casellaPregressaDocInputPolio;
    
     
    @FXML
    private TextField dataInputEpB;
    
    @FXML
    private ComboBox  menuInputDoseEpB;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareEpB;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileEpB;
    
    @FXML
    private CheckBox casellaPregressaDocInputEpB;
    
    @FXML
    private TextField dataInputEpA;
    
    @FXML
    private ComboBox  menuInputDoseEpA;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareEpA;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileEpA;
    
    @FXML
    private CheckBox casellaPregressaDocInputEpA;
    
    @FXML
    private TextField dataInputEpAB;
    
    @FXML
    private ComboBox  menuInputDoseEpAB;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareEpAB;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileEpAB;
    
    @FXML
    private CheckBox casellaPregressaDocInputEpAB;
    
    
    @FXML
    private TextField dataInputMPR;
    
    @FXML
    private ComboBox  menuInputDoseMPR;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareMPR;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileMPR;
    
    @FXML
    private CheckBox casellaPregressaDocInputMPR;
    
    @FXML
    private TextField dataInputVar;
    
    @FXML
    private ComboBox  menuInputDoseVar;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareVar;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileVar;
    
    @FXML
    private CheckBox casellaPregressaDocInputVar;
    
    @FXML
    private TextField dataInputMen;
    
    @FXML
    private ComboBox  menuInputDoseMen;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareMen;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileMen;
    
    @FXML
    private CheckBox casellaPregressaDocInputMen;
    
    @FXML
    private TextField dataInputCol;
    
    @FXML
    private ComboBox  menuInputDoseCol;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareCol;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileCol;
    
    @FXML
    private CheckBox casellaPregressaDocInputCol;
    
    @FXML
    private TextField dataInputTifo;
    
    @FXML
    private ComboBox  menuInputDoseTifo;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareTifo;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileTifo;
    
    @FXML
    private CheckBox casellaPregressaDocInputTifo;
    
    @FXML
    private TextField dataInputFG;
    
    @FXML
    private ComboBox  menuInputDoseFG;
    
    @FXML
    private RadioButton selettoreUltimaVaccMilitareFG;
    
    @FXML
    private RadioButton selettoreUltimaVaccCivileFG;
    
    @FXML
    private CheckBox casellaPregressaDocInputFG;
 
    @FXML
    private Accordion schedeInputStoriaVaccinale;
    
    
    private void inizializzaPannelloInputStoriaVaccinale(){
        for(TitledPane pannello : schedeInputStoriaVaccinale.getPanes()){
            pannello.setStyle(Risorse.Colore.NERO);
        }
        inizializzaInputStoriaVacc(//Tetano-Difterite
                casellaPregressaDocInputTD,
                dataInputTD, menuInputDoseTD, 
                selettoreUltimaVaccMilitareTD, selettoreUltimaVaccCivileTD,
                new String[]{
                    Risorse.Input.DOSE_R,Risorse.Input.DOSE_CB4,Risorse.Input.DOSE_CB3,
                    Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_R,
                Risorse.Input.DOSE_R
        );

        inizializzaInputStoriaVacc(//Tetano-Difterite-Polio
                casellaPregressaDocInputTDP,
                dataInputTDP, menuInputDoseTDP, 
                selettoreUltimaVaccMilitareTDP, selettoreUltimaVaccCivileTDP,
                new String[]{
                    Risorse.Input.DOSE_R
                },
                Risorse.Input.DOSE_R,
                Risorse.Input.DOSE_R
        );
        inizializzaInputStoriaVacc(//Polio
                casellaPregressaDocInputPolio,
                dataInputPolio, menuInputDosePolio, 
                selettoreUltimaVaccMilitarePolio, selettoreUltimaVaccCivilePolio,
                new String[]{
                    Risorse.Input.DOSE_R,Risorse.Input.DOSE_CB4,Risorse.Input.DOSE_CB3,
                    Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_R,
                Risorse.Input.DOSE_CB4
        );
        inizializzaInputStoriaVacc(//Epatite B
                casellaPregressaDocInputEpB,
                dataInputEpB, menuInputDoseEpB, 
                selettoreUltimaVaccMilitareEpB, selettoreUltimaVaccCivileEpB,
                new String[]{
                    Risorse.Input.DOSE_B,Risorse.Input.DOSE_CB3,Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_B,
                Risorse.Input.DOSE_CB3
        );
        inizializzaInputStoriaVacc(//Epatite A
                casellaPregressaDocInputEpA,
                dataInputEpA, menuInputDoseEpA, 
                selettoreUltimaVaccMilitareEpA, selettoreUltimaVaccCivileEpA,
                new String[]{
                    Risorse.Input.DOSE_B,Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_CB1,
                Risorse.Input.DOSE_CB2
        );
        inizializzaInputStoriaVacc(//Epatite A+B
                casellaPregressaDocInputEpAB,
                dataInputEpAB, menuInputDoseEpAB, 
                selettoreUltimaVaccMilitareEpAB, selettoreUltimaVaccCivileEpAB,
                new String[]{
                    Risorse.Input.DOSE_B,Risorse.Input.DOSE_CB3,Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_B,
                Risorse.Input.DOSE_CB3
        );
        inizializzaInputStoriaVacc(//Morbilo-Parotite-Rosolia
                casellaPregressaDocInputMPR,
                dataInputMPR, menuInputDoseMPR, 
                selettoreUltimaVaccMilitareMPR, selettoreUltimaVaccCivileMPR,
                new String[]{
                    Risorse.Input.DOSE_1D,Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_1D,
                Risorse.Input.DOSE_CB2
        );
        inizializzaInputStoriaVacc(//Varicella
                casellaPregressaDocInputVar,
                dataInputVar, menuInputDoseVar, 
                selettoreUltimaVaccMilitareVar, selettoreUltimaVaccCivileVar,
                new String[]{
                    Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_CB1,
                Risorse.Input.DOSE_CB2
        );
        inizializzaInputStoriaVacc(//Meningite
                casellaPregressaDocInputMen,
                dataInputMen, menuInputDoseMen, 
                selettoreUltimaVaccMilitareMen, selettoreUltimaVaccCivileMen,
                new String[]{
                    Risorse.Input.DOSE_R,Risorse.Input.DOSE_1D
                },
                Risorse.Input.DOSE_1D,
                Risorse.Input.DOSE_1D
        );
        inizializzaInputStoriaVacc(//Tifo
                casellaPregressaDocInputTifo,
                dataInputTifo, menuInputDoseTifo, 
                selettoreUltimaVaccMilitareTifo, selettoreUltimaVaccCivileTifo,
                new String[]{
                    Risorse.Input.DOSE_R,Risorse.Input.DOSE_1D,Risorse.Input.DOSE_CB4,Risorse.Input.DOSE_CB3,Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_CB4,
                Risorse.Input.DOSE_CB4
        );
        inizializzaInputStoriaVacc(//Colera
                casellaPregressaDocInputCol,
                dataInputCol, menuInputDoseCol, 
                selettoreUltimaVaccMilitareCol, selettoreUltimaVaccCivileCol,
                new String[]{
                    Risorse.Input.DOSE_B,Risorse.Input.DOSE_CB2,Risorse.Input.DOSE_CB1
                },
                Risorse.Input.DOSE_CB2,
                Risorse.Input.DOSE_CB2
        );
        inizializzaInputStoriaVacc(//Febbre Gialla
                casellaPregressaDocInputFG,
                dataInputFG, menuInputDoseFG, 
                selettoreUltimaVaccMilitareFG, selettoreUltimaVaccCivileFG,
                new String[]{
                    Risorse.Input.DOSE_R,Risorse.Input.DOSE_1D
                },
                Risorse.Input.DOSE_1D,
                Risorse.Input.DOSE_1D
        );
   
    }
    
    private void inizializzaInputStoriaVacc(CheckBox pregressaDoc,TextField data,ComboBox  dose,RadioButton ultimaMilitare,RadioButton ultimaCivile,String[] tipoDose,String doseMilitare,String doseCivile){
        pregressaDoc.setSelected(false);
        CampoTesto.aggiungiMascheraInput(data, Risorse.MascheraCampi.DATA,Risorse.MascheraCampi.CARATTERE);
        if(dose.getItems().isEmpty())
            dose.getItems().addAll(Arrays.asList(tipoDose));
        dose.setDisable(true);
        data.setDisable(true);
        final ToggleGroup selettore = new ToggleGroup();
        ultimaMilitare.setSelected(false);
        ultimaMilitare.setToggleGroup(selettore);
        ultimaMilitare.setUserData(Risorse.Seleziona.ULTIMA_VACC_MILITARE);
        ultimaCivile.setSelected(false);
        ultimaCivile.setToggleGroup(selettore);
        ultimaCivile.setUserData(Risorse.Seleziona.ULTIMA_VACC_CIVILE);
        
        selettore.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            Toggle taggle = selettore.getSelectedToggle();
            if(taggle !=null){
                Parent scheda = dose.getParent().getParent().getParent().getParent();
                scheda.setStyle(Risorse.Colore.BLU);
                dose.setDisable(false);
                data.setDisable(false);
                Integer sel = (Integer) taggle.getUserData();
                if(sel == Risorse.Seleziona.ULTIMA_VACC_MILITARE){
                    dose.getSelectionModel().select(doseMilitare);
                }else if(sel == Risorse.Seleziona.ULTIMA_VACC_CIVILE){
                    dose.getSelectionModel().select(doseCivile);
                }
            }
        });
    }
    
    
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void aggiungiAltroVaccino(ActionEvent event){
       ModificaVoceStoriaVaccinaleController.registraFinestraRitorno(Programma.scenaCorrente());
            
       try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                            FXMLLoader.load(getClass().getResource(Risorse.FXML.MODIFICA_VACCINAZIONE_PASS))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            erroreCaricamentoFinestra(ex);
        }
       
    }
    
    
    
    /**
     * Metodo che permette di salvare i dati input della storia vaccinale pregressa.
     * 
     * @param event 
     */
    private void salvaInputStoriaVaccinale(ActionEvent event){
        salvaInputStoriaVaccinale();
        pannelloVisibile(pannelloSecondario,pannelloStoriaVaccinale);
        vediPulsantiSelezione(true);
        vediPulsanteSpecialiStampaEdEliminaTutto();
        caricaTabellaStoriaVaccinale();
    }
    
    private void salvaInputStoriaVaccinale(){
        
        boolean tdp = salvaVaccStoria(Risorse.Profilassi.TETANO, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO], menuInputDoseTDP, selettoreUltimaVaccCivileTDP, casellaPregressaDocInputTDP, dataInputTDP);
        salvaVaccStoria(Risorse.Profilassi.DIFTERITE, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO], menuInputDoseTDP, selettoreUltimaVaccCivileTDP, casellaPregressaDocInputTDP, dataInputTDP);
        salvaVaccStoria(Risorse.Profilassi.POLIO,  Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE_POLIO], menuInputDoseTDP, selettoreUltimaVaccCivileTDP, casellaPregressaDocInputTDP, dataInputTDP);
        
        if(!tdp){
            salvaVaccStoria(Risorse.Profilassi.TETANO, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE], menuInputDoseTD, selettoreUltimaVaccCivileTD, casellaPregressaDocInputTD, dataInputTD);
            salvaVaccStoria(Risorse.Profilassi.DIFTERITE, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.TETANO_DIFTERITE], menuInputDoseTD, selettoreUltimaVaccCivileTD, casellaPregressaDocInputTD, dataInputTD);

            salvaVaccStoria(Risorse.Profilassi.POLIO, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.POLIO], menuInputDosePolio, selettoreUltimaVaccCivilePolio, casellaPregressaDocInputPolio, dataInputPolio);
        }
        salvaVaccStoria(Risorse.Profilassi.COLERA,  Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.COLERA], menuInputDoseCol, selettoreUltimaVaccCivileCol, casellaPregressaDocInputCol, dataInputCol);
        salvaVaccStoria(Risorse.Profilassi.FEBB_GIALLA, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.FEBB_GIALLA], menuInputDoseFG, selettoreUltimaVaccCivileFG, casellaPregressaDocInputFG, dataInputFG);
        
        boolean ab = salvaVaccStoria(Risorse.Profilassi.EPATITE_A, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A_B], menuInputDoseEpAB, selettoreUltimaVaccCivileEpAB, casellaPregressaDocInputEpAB, dataInputEpAB);
        salvaVaccStoria(Risorse.Profilassi.EPATITE_B,  Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A_B], menuInputDoseEpAB, selettoreUltimaVaccCivileEpAB, casellaPregressaDocInputEpAB, dataInputEpAB);
        
        if(!ab){
            salvaVaccStoria(Risorse.Profilassi.EPATITE_A,  Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_A], menuInputDoseEpA, selettoreUltimaVaccCivileEpA, casellaPregressaDocInputEpA, dataInputEpA);
            salvaVaccStoria(Risorse.Profilassi.EPATITE_B,  Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.EPATITE_B], menuInputDoseEpB, selettoreUltimaVaccCivileEpB, casellaPregressaDocInputEpB, dataInputEpB);
        }
        salvaVaccStoria(Risorse.Profilassi.MORBILLO,  Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE], menuInputDoseMPR, selettoreUltimaVaccCivileMPR, casellaPregressaDocInputMPR, dataInputMPR);
        salvaVaccStoria(Risorse.Profilassi.ROSOLIA, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE], menuInputDoseMPR, selettoreUltimaVaccCivileMPR, casellaPregressaDocInputMPR, dataInputMPR);
        salvaVaccStoria(Risorse.Profilassi.PAROTITE, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.MORBILLO_ROSOLIA_PAROTITE], menuInputDoseMPR, selettoreUltimaVaccCivileMPR, casellaPregressaDocInputMPR, dataInputMPR);
        
        salvaVaccStoria(Risorse.Profilassi.VARICELLA, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.VARICELLA], menuInputDoseVar, selettoreUltimaVaccCivileVar, casellaPregressaDocInputVar, dataInputVar);
        
        salvaVaccStoria(Risorse.Profilassi.MENINGITE, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.MENINGITE_CONIUGATO], menuInputDoseMen, selettoreUltimaVaccCivileMen, casellaPregressaDocInputMen, dataInputMen);
        
        salvaVaccStoria(Risorse.Profilassi.TIFO, Risorse.SIGLA_VACCINAZIONI[Risorse.Vaccinazioni.TIFO_ORALE], menuInputDoseTifo, selettoreUltimaVaccCivileTifo, casellaPregressaDocInputTifo, dataInputTifo);
        salvaVaccStoria(Risorse.Profilassi.VAIOLO);
        salvaVaccStoria(Risorse.Profilassi.INFLUENZA);
        salvaVaccStoria(Risorse.Profilassi.ENC_GIAP);
        salvaVaccStoria(Risorse.Profilassi.ENC_ZECC);
        
        salvaVaccStoria(Risorse.Profilassi.RABBIA);
        
    }
    
    private void salvaVaccStoria(String profilassi){
        if(militare != null){
            Programma.datiStoricoVaccinale.aggiungiStoriaVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita(), 
                profilassi, 
                false, 
                false, 
                null,
                null,
                null,
                null,
                null, 
                Programma.utente
            );
        }else{
            avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setStyle(Risorse.Colore.ROSSO);
        }
    }
    
    public boolean salvaVaccStoria(String profilassi,String vaccino,ComboBox  dose,RadioButton ultivaVaccCivile,CheckBox pregressaDoc,TextField data){
        if(militare != null){
            boolean selezionato = true;
            DataOraria _data = null;
            try {
                _data = new DataOraria(data.getText());
            } catch (Errore ex) {
                this.messaggio.setText(Risorse.Messaggi.ERRORE_DATA);
                this.messaggio.setStyle(Risorse.Colore.ARANCIONE);
                selezionato = false;
            }

            Object ultima = null;
            try{
                if(_data != null || pregressaDoc.selectedProperty().get())
                // la data nulla equivale a una voce non selezionata se non vi è 
                // il flag sulla pregressa malattia
                    ultima = ultivaVaccCivile.getToggleGroup().getSelectedToggle().getUserData();
                
            }catch(java.lang.NullPointerException ex){
                 selezionato = false;// non è stata selezionata "ultima verbale"
            }
            Programma.datiStoricoVaccinale.aggiungiStoriaVaccinale(militare.getCognome(), militare.getNome(), militare.getNascita(), 
                    profilassi, 
                    false, 
                    pregressaDoc.selectedProperty().get(), 
                    ultima != null ? (((int)ultima == Risorse.Seleziona.ULTIMA_VACC_CIVILE))  : null,  
                    ultima != null ? dose.getValue().toString() : null,
                    ultima != null ? vaccino : null,
                    ultima != null ? _data : null,
                    null, //nessun medicoVaccinatore (fase iniziale)
                    Programma.utente
            );

            return selezionato;
        }
        avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
        messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
        messaggio.setStyle(Risorse.Colore.ROSSO);
        return false;
    }
    
    
    
    
    
//-------------------------------------------------------------------------------------------------
                            /**********************
                             * NUOVE VACCINAZIONI *
                             **********************/


    @FXML
    private TableView<Vaccinazione> tabellaVaccinazioni;
    
    @FXML
    private TableColumn<Vaccinazione, DataOraria> colonnaDataNuovaVaccinazione;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaProfilassi;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaNomeVaccino;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaDose;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaInadempienza;

    private void inizializzaPannelloVaccinazioni() {
        colonnaDataNuovaVaccinazione.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DATA));
        colonnaProfilassi.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.PROFILASSI));
        colonnaNomeVaccino.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.VACCINO));
        colonnaDose.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO_DOSE));
        colonnaInadempienza.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.INADEMPIENZA));
        tabellaVaccinazioni.setItems(listaVaccinazione);
    }
    
    private void caricaTabellaVaccinazioni(){
        listaVaccinazione.clear();
        pannelloVisibile(pannelloSecondario,pannelloNuoveVaccinazioni);
        if(militare != null){
            ArrayList<Object[]> lista = Programma.datiVaccinazioni.trovaVaccinazioni(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
            );
            if(lista != null)
                for(Object[] record : lista)
                    listaVaccinazione.add(new Vaccinazione(
                                    (DataOraria)record[BASE_DATI.VACCINAZIONI.DATA_SEDUTA],
                                    (String)record[BASE_DATI.VACCINAZIONI.TIPO_PROFILASSI], 
                                    (String)record[BASE_DATI.VACCINAZIONI.NOME_VACCINO], 
                                    (String)record[BASE_DATI.VACCINAZIONI.DOSE], 
                                    (String)record[BASE_DATI.VACCINAZIONI.INADEMPIENZA] 
                            )
                    );
        }else{
            messaggoErroreDatiMilitare();
        }
    }
    
    
    
    private void creaNuovaVaccinazione(ActionEvent event){
        if(militare != null){
            try {
                if(FinestraImpostazioniController.impostazioni == null){
                     impostazioneDatiVaccinali(event);
                }else{
                    Programma.vistaCorrente.setScene(
                            new Scene(
                                    FXMLLoader.load(getClass().getResource(Risorse.FXML.ANAMNESI_VACCINALE))
                            )
                    );
                    Programma.adattaFinestra();
                    Programma.vistaCorrente.show();
                }
                
            } catch (IOException ex) {
                erroreCaricamentoFinestra(ex);
            }
        }else{
            avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setStyle(Risorse.Colore.ARANCIONE);
        }
    } 
    
    
    private void selezionaSedutaVaccinale(ActionEvent event){
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                try {
                    Programma.finestraConferma(
                            this,
                            Risorse.Conferma.APERTURA_FINESTRA_MODIFICA,
                            () -> {
                                Vaccinazione vaccinazione = tabellaVaccinazioni.getSelectionModel().getSelectedItem();
                                if(vaccinazione != null){
                                    try {
                                        AnamnesiVaccinaleController.data = vaccinazione.getData();
                                        Programma.vistaCorrente.setScene(
                                                new Scene(
                                                        FXMLLoader.load(getClass().getResource(Risorse.FXML.ANAMNESI_VACCINALE))
                                                )
                                        );
                                        
                                        Programma.adattaFinestra();
                                        Programma.vistaCorrente.show();
                                        
                                    } catch (IOException ex) {
                                        erroreCaricamentoFinestra(ex);
                                    }
                                    
                                }else{
                                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                                    messaggio.setStyle(Risorse.Colore.ROSSO);
                                }
                            }
                    );
                } catch (IOException ex) {
                    erroreCaricamentoFinestra(ex);
                }
                
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    
    private void eliminaVaccinazioni(ActionEvent event){
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
                                       listaVaccinazione.remove(vaccinazione);
                                    }else{
                                        avviso(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VACCINO);
                                        messaggio.setText(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VACCINO);
                                        messaggio.setStyle(Risorse.Colore.ROSSO);
                                    }
                                }
                        );
                    } catch (IOException ex) {
                        erroreCaricamentoFinestra(ex);
                    }
                    
                    
                }else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }

//-------------------------------------------------------------------------------------------------
                            /********************
                             *  VISITA MEDICA   *
                             ********************/


    @FXML
    private TableView<VisitaMedica> tabellaVisitaMedica;
    
    @FXML
    private TableColumn<Vaccinazione, DataOraria> colonnaDataVisitaMedica;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaTipoVisitaMedica;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaDiagnosiVisitaMedica;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaPMLVisitaMedica;
    
    
    private void inizializzaPanelloVisitaMedica() {
        colonnaDataVisitaMedica.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DATA));
        colonnaTipoVisitaMedica.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO));
        colonnaDiagnosiVisitaMedica.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DIAGNOSI));
        colonnaPMLVisitaMedica.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.PML));
        tabellaVisitaMedica.setItems(listaVisitaMedica);
    }
 
    private void caricaTabellaVisiteMediche() {
        listaVisitaMedica.clear();
        pannelloVisibile(pannelloSecondario,pannelloVisitaMedica);
        if(militare != null){
            ArrayList<Object[]> lista = Programma.datiVisita.trovaVisiteMediche(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
            );
            if(lista != null)
                for(Object[] record : lista)
                    listaVisitaMedica.add(new VisitaMedica(
                                    (DataOraria)record[BASE_DATI.VISITA.DATA],
                                    (String)record[BASE_DATI.VISITA.TIPO], 
                                    (String)record[BASE_DATI.VISITA.DIAGNOSI], 
                                    (String)record[BASE_DATI.VISITA.PML]
                            )
                    );
        }else{
            pannelloVisibile(pannelloSecondario,null);
            messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
            messaggio.setStyle(Risorse.Colore.ARANCIONE);
        }
    }
    
    private void aggiungiVisitaMedica(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = Programma.datiMilitari.trovaMilitare(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
                );
                try {
                    Programma.finestraInputMenu(
                            this,
                            "Seleziona Medico visita", 
                            Programma.datiMedico.tuttiMedici(),
                            (String input)->{ // evento pulsante ok
                                Finestra.creaFinestra(
                                        new FinestraVisita(
                                                infoMilitare,
                                                input // medico selezionato
                                        ));
                            }
                    );
                    visualizzaPannelloRicerca(event);
                    messaggio.setText(Risorse.Messaggi.AGGIUNTO_NUOVO_RECORD);
                    messaggio.setStyle(Risorse.Colore.BLU);
                    
                } catch (IOException ex) {
                    erroreCaricamentoFinestra(ex);
                }
                

            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    private void eliminaVisitaMedica(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                VisitaMedica visita = tabellaVisitaMedica.getSelectionModel().getSelectedItem();
                if(visita != null){
                    try {
                        Programma.finestraConferma(
                                this,
                                String.format(Risorse.Messaggi.DOMANDA_ELIMINA_VISITA_MEDICA,
                                        visita.getData().stampaGiorno()
                                ),
                                () -> {
                                    boolean ok = Programma.datiVisita.eliminaVisita(
                                            visita.getData(),
                                            militare.getCognome(),militare.getNome(), militare.getNascita()
                                    );
                                    if(ok){
                                       listaVisitaMedica.remove(visita);
                                    }else{
                                        avviso(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VISITA_MEDICA);
                                        messaggio.setText(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VISITA_MEDICA);
                                        messaggio.setStyle(Risorse.Colore.ROSSO);
                                    }
                                }
                        );
                    } catch (IOException ex) {
                        erroreCaricamentoFinestra(ex);
                    }
                    
                    
                }else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    private void selezionaVisitaDaTabella(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = Programma.datiMilitari.trovaMilitare(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
                );

                VisitaMedica visita = tabellaVisitaMedica.getSelectionModel().getSelectedItem();
                if(visita != null)
                    Finestra.creaFinestra(new FinestraVisita(infoMilitare,visita.getData()));
                else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ARANCIONE);
                }

            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
        
    }
    
//-------------------------------------------------------------------------------------------------
                        /********************
                         *    VERBALE ML    *
                         ********************/


    @FXML
    private TableView<Verbale> tabellaVerbaleML;
    
    @FXML
    private TableColumn<Verbale, DataOraria> colonnaDataVerbaleML;
    
    @FXML
    private TableColumn<Verbale, String> colonnaDiagnosiML;
    
    
    private void inizializzaPanelloVerbaleML() {
        colonnaDataVerbaleML.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DATA));
        colonnaDiagnosiML.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DIAGNOSI));
        tabellaVerbaleML.setItems(listaVerbaleML);
  
    }
 
    private void caricaTabellaVerbaleML() {
        listaVerbaleML.clear();
        pannelloVisibile(pannelloSecondario,pannelloVerbaleML);
        
        if(militare != null){
            ArrayList<Object[]> lista = Programma.datiVerbaleModelloML.trovaVerbali(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
            );
            if(lista != null)
                for(Object[] record : lista)
                    listaVerbaleML.add(new Verbale(
                                    (DataOraria)record[BASE_DATI.MODELLO_ML.DATA_VERBALE],
                                    (String)record[BASE_DATI.MODELLO_ML.DIAGNOSI]
                            )
                    );
            
            
        }else{
            messaggoErroreDatiMilitare();
        }
    }
    
    private void aggiungiVerbaleML(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = Programma.datiMilitari.trovaMilitare(
                        militare.getCognome(),
                        militare.getNome(),
                        militare.getNascita()
                );
                Object[] verbale = Programma.datiVerbaleModelloML.vediVerbale(DataOraria.creaDataOggi(), militare.getCognome(), militare.getNome(), militare.getNascita());
                
                if(verbale != null)
                    try {
                        Programma.finestraAvviso(this, Risorse.Messaggi.ESISTE_VISITA_MEDICA);
                    } catch (IOException ex) {
                        erroreCaricamentoFinestra(ex);
                    }
                else
                    Finestra.creaFinestra(new FinestraModelloML(infoMilitare,DataOraria.creaDataOggi()));
                
                visualizzaPannelloRicerca(event);
                messaggio.setText(Risorse.Messaggi.AGGIUNTO_NUOVO_RECORD);
                messaggio.setStyle(Risorse.Colore.BLU);
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
        
    }
    
    private void eliminaVerbaleML(ActionEvent event){
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Verbale verbale = tabellaVerbaleML.getSelectionModel().getSelectedItem();
                if(verbale != null){
                    try {
                        Programma.finestraConferma(this,
                                String.format(Risorse.Messaggi.DOMANDA_ELIMINA_VERBALE_ML,
                                        verbale.getData().stampaGiorno()
                                ),
                                () -> {
                                    boolean ok = Programma.datiVerbaleModelloML.eliminaVerbale(verbale.getData(),
                                            militare.getCognome(),militare.getNome(), militare.getNascita()
                                    );
                                    if(ok){
                                       listaVerbaleML.remove(verbale);
                                    }else{
                                        avviso(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VERBALE_ML);
                                        messaggio.setText(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VERBALE_ML);
                                        messaggio.setStyle(Risorse.Colore.ROSSO);
                                    }
                                }
                        );
                    } catch (IOException ex) {
                        erroreCaricamentoFinestra(ex);
                    }
                    
                    
                }else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    /**
     * Attivazione della voce selezione dal menu della tabella ML.
     * 
     * @param event 
     */
    private void selezionaVerbaleMLDaTabella(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = new Object[]{
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita(),
                    militare.getLuogo(),
                    militare.getGrado(),
                    null,
                    militare.getCp()
                };

                Verbale verbaleML = tabellaVerbaleML.getSelectionModel().getSelectedItem();
                if(verbaleML != null)
                    Finestra.creaFinestra(new FinestraModelloML(infoMilitare,verbaleML.getData()));

            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
        
    }
    
    //-------------------------------------------------------------------------------------------------
                        /********************
                         *    VERBALE GL    *
                         ********************/


    @FXML
    private TableView<Verbale> tabellaVerbaleGL;
    
    @FXML
    private TableColumn<Verbale, DataOraria> colonnaDataVerbaleGL;
    
    @FXML
    private TableColumn<Verbale, String> colonnaDiagnosiGL;
    
   
    
    
    private void inizializzaPanelloVerbaleGL() {
        colonnaDataVerbaleGL.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DATA));
        colonnaDiagnosiGL.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DIAGNOSI));
        tabellaVerbaleGL.setItems(listaVerbaleGL);
    }
 
    private void caricaTabellaVerbaleGL() {
        listaVerbaleGL.clear();
        pannelloVisibile(pannelloSecondario,pannelloVerbaleGL);
        if(militare != null){
            ArrayList<Object[]> lista = Programma.datiVerbaleModelloGL.trovaModelloGL(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
            );
            if(lista != null)
                for(Object[] record : lista)
                    listaVerbaleGL.add(new Verbale(
                                    (DataOraria)record[BASE_DATI.MODELLO_GL.DATA_VERBALE],
                                    (String)record[BASE_DATI.MODELLO_GL.DIAGNOSI]
                            )
                    );
        }else{
            messaggoErroreDatiMilitare();
        }
    }
    
    private void aggiungiVerbaleGL(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = Programma.datiMilitari.trovaMilitare(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
                );
                SwingUtilities.invokeLater(
                        () -> {
                            Finestra.creaFinestra(new FinestraModelloGL(infoMilitare,DataOraria.creaDataOggi(),true));
                        }
                );
                visualizzaPannelloRicerca(event);
                messaggio.setText(Risorse.Messaggi.AGGIUNTO_NUOVO_RECORD);
                messaggio.setStyle(Risorse.Colore.BLU);
                
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    
    private void eliminaVerbaleGL(ActionEvent event){
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Verbale verbale = tabellaVerbaleGL.getSelectionModel().getSelectedItem();
                if(verbale != null){
                    try {
                        Programma.finestraConferma(this,
                                String.format(Risorse.Messaggi.DOMANDA_ELIMINA_VERBALE_GL,
                                        verbale.getData().stampaGiorno()
                                ),
                                () -> {
                                    boolean ok = Programma.datiVerbaleModelloGL.eliminaModelloGL(verbale.getData(),
                                            militare.getCognome(),militare.getNome(), militare.getNascita()
                                    );
                                    if(ok){
                                       listaVerbaleML.remove(verbale);
                                    }else{
                                        avviso(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VERBALE_GL);
                                        messaggio.setText(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VERBALE_GL);
                                        messaggio.setStyle(Risorse.Colore.ROSSO);
                                    }
                                }
                        );
                    } catch (IOException ex) {
                        erroreCaricamentoFinestra(ex);
                    }
                    
                    
                }else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    /**
     * Attivazione della voce selezione dal menu della tabella GL o pulsante Seleziona.
     * 
     * @param event 
     */
    private void selezionaVerbaleGLDaTabella(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = Programma.datiMilitari.trovaMilitare(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
                );

                Verbale verbaleGL = tabellaVerbaleGL.getSelectionModel().getSelectedItem();
                if(verbaleGL != null)
                    Finestra.creaFinestra(new FinestraModelloGL(infoMilitare,verbaleGL.getData(),false));

            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
//-------------------------------------------------------------------------------------------------
                        /********************
                         *     RICOVERO     *
                         ********************/
    
    
    @FXML
    private TableView<Ricovero> tabellaRicoveri;
    
    @FXML
    private TableColumn<Ricovero, DataOraria> colonnaDataOraInizioRicovero;
    
    @FXML
    private TableColumn<Ricovero, DataOraria> colonnaDataOraFineRicovero;
    
    @FXML
    private TableColumn<Ricovero, String> colonnaDiagnosiRicovero;
    
    
    private void inizializzaPanelloRicoveri() {
        colonnaDataOraInizioRicovero.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.INIZIO));
        colonnaDataOraFineRicovero.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.FINE));
        colonnaDiagnosiRicovero.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.DIAGNOSI));
        tabellaRicoveri.setItems(listaRicoveri);
    }

   
    
    private void caricaTabellaRicoveri() {
        listaRicoveri.clear();
        pannelloVisibile(pannelloSecondario,pannelloRicovero);
        if(militare != null){
            ArrayList<Object[]> lista = Programma.datiRicoveri.trovaRicoveri(
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
            );
            if(lista != null)
                for(Object[] record : lista)
                    listaRicoveri.add(
                            new Ricovero(
                                    (DataOraria)record[3],
                                    (DataOraria)record[4],
                                    (String)record[8]
                            )
                    );
        }else{
            messaggoErroreDatiMilitare();
        }
    }
    
    private void aggiungiRicovero(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = new Object[]{
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita(),
                    militare.getLuogo(),
                    militare.getGrado(),
                    null,
                    militare.getCp()
                };

                Finestra.creaFinestra(new FinestraRicovero(infoMilitare));
                
                visualizzaPannelloRicerca(event);
                messaggio.setText(Risorse.Messaggi.AGGIUNTO_NUOVO_RECORD);
                messaggio.setStyle(Risorse.Colore.BLU);

            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
    
    /**
     * 
     * @param event 
     */
    private void selezionaRicoveroDaTabella(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Object[] infoMilitare = new Object[]{
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita(),
                    militare.getLuogo(),
                    militare.getGrado(),
                    null,
                    militare.getCp()
                };

                Ricovero ricovero = tabellaRicoveri.getSelectionModel().getSelectedItem();
                if(ricovero != null)
                    Finestra.creaFinestra(new FinestraRicovero(infoMilitare,ricovero.getInizio()));

            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
        
    }
    
    
    private void eliminaRicovero(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            if(militare != null){
                Ricovero ricovero = tabellaRicoveri.getSelectionModel().getSelectedItem();
                if(ricovero != null){
                    try {
                        Programma.finestraConferma(this,
                                String.format(Risorse.Messaggi.DOMANDA_ELIMINA_RICOVERO,
                                        ricovero.getInizio().stampaGiorno()+" ⇔ "+
                                        ricovero.getFine().stampaGiorno()
                                ),
                                () -> {
                                    boolean ok = Programma.datiRicoveri.eliminaRicovero(ricovero.getInizio(),
                                            militare.getCognome(),militare.getNome(), militare.getNascita()
                                    );
                                    if(ok){
                                       listaRicoveri.remove(ricovero);
                                    }else{
                                        avviso(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_RICOVERO);
                                        messaggio.setText(Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_RICOVERO);
                                        messaggio.setStyle(Risorse.Colore.ROSSO);
                                    }
                                }
                        );
                    } catch (IOException ex) {
                        erroreCaricamentoFinestra(ex);
                    }
                    
                    
                }else{
                    avviso(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
                    messaggio.setStyle(Risorse.Colore.ROSSO);
                }
            }else{
                avviso(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setText(Risorse.Messaggi.ERRORE_MILITARE_NON_DEFINITO);
                messaggio.setStyle(Risorse.Colore.ARANCIONE);
            }
        }
    }
    
}