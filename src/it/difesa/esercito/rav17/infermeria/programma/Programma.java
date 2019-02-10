package it.difesa.esercito.rav17.infermeria.programma;

import it.difesa.esercito.rav17.infermeria.DaseDati.DatiGenerici;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiImpostazioni;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiMedico;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiMilitare;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiModelloGL;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiRicovero;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiRifiuti;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiStoriaVaccinale;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiUtente;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiVaccinazioni;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiVerbaleVisita;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiVisita;
import it.difesa.esercito.rav17.infermeria.controllo.FinestraAvvisoController;
import it.difesa.esercito.rav17.infermeria.controllo.FinestraConfermaController;
import it.difesa.esercito.rav17.infermeria.controllo.FinestraInputMenuController;
import it.quasar_x7.javafx.Schermo;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

/**
 * Classe che gestisce l'avvio e lo stato del programma.
 * 
 * @author Dr Domenico della Peruta
 * @version 1.3.0 ultima modifica 03/10/2016
 */
public class Programma extends Application {

    public static Stage vistaCorrente;
    public static Scene finestraPrincipale;
    public static String utente;
    public static String livello;
    public static DatiImpostazioni datiImpostazioni = new DatiImpostazioni();
    public static DatiMedico datiMedico = new DatiMedico();
    public static DatiModelloGL datiVerbaleModelloGL = new DatiModelloGL();
    public static DatiStoriaVaccinale datiStoricoVaccinale = new DatiStoriaVaccinale();
    public static DatiVaccinazioni datiVaccinazioni = new DatiVaccinazioni();
    public static DatiMilitare datiMilitari = new DatiMilitare();
    public static DatiVerbaleVisita datiVerbaleModelloML = new DatiVerbaleVisita();
    public static DatiGenerici datiGenerici = new DatiGenerici();
    public static DatiVisita datiVisita = new DatiVisita();
    public static DatiUtente datiUtente= new DatiUtente();
    public static DatiRicovero datiRicoveri = new DatiRicovero();
    public static DatiRifiuti datiRifiuti = new DatiRifiuti();
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Programma.vistaCorrente=stage;
        Programma.vistaCorrente.setResizable(false);
        Programma.vistaCorrente.initStyle(StageStyle.TRANSPARENT);
        Programma.vistaCorrente.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        login();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
    
    
    
    
    /**
     * Metodo che permette il caricamento della finestra di login, o della finestra per la 
     * creazione del file esterno XML contenente l'indirizzo IP del server MySQL.
     * 
     * @throws IOException si verifica quando il file FXML (delle finestre Dialogo) 
     * non è caricato correttamente.
     */
    private void login() throws IOException{
        String fileFXML = this.cercaFileIP() ? Risorse.FXML.LOGIN : Risorse.FXML.CREA_FILE_IP;
        
        Programma.vistaCorrente.setScene(
            new Scene(
                FXMLLoader.load(getClass().getResource(fileFXML))
            )
        );
        Programma.vistaCorrente.getIcons().add(new Image(Risorse.Immagine.SIRINGA));
        Programma.vistaCorrente.getScene().setFill(null);
        Programma.vistaCorrente.show();
    }
    
    
    
    /**
     * Metodo che controlla l'esistenza dell'indirizzo IP del server MySQL in un file XML esterno.
     * @return <b>true</b> se esiste un IP oppure <b>false</b>.
     * @throws IOException si verifica quando il file XML è inesistente oppure dannegiato.
     */
    private boolean cercaFileIP(){
        
        try {
            Properties lista = new Properties();
            FileInputStream file = new FileInputStream(Risorse.FileEsterno.IP_SERVER_MYSQL);
            lista.loadFromXML(file);
            file.close();
            String ip = lista.getProperty(Risorse.KeyXML.IP);
            return ip != null;
        } catch (IOException ex) {
            return false;
        }
    }
    
    
    public static Scene scenaCorrente(){
        if(Programma.vistaCorrente != null)
            return Programma.vistaCorrente.getScene();
        return null;
    }
    
    
    public static void riduciFinestra(){
        if(Programma.vistaCorrente != null)
            Programma.vistaCorrente.setIconified(true);
    }
    
    /**
     * Metodo che fa visualizzare la finestra di conferma.
     * 
     * @param controller        classe corrente in cui viene chiamato il metodo
     * @param domanda           
     * @param ok                classe astratta che gestisce il metodo 'esegui' legato 
     *                          all'evento click sul pulsante Sì
     * @throws IOException 
     */
    public static void finestraConferma(Object controller, String domanda, FinestraConfermaController.Codice ok) throws IOException{
        FinestraConfermaController.scenaCorrente = Programma.scenaCorrente();
        FinestraConfermaController.conferma(domanda, ok);
        
        Programma.vistaCorrente.setScene(
                new Scene(
                            FXMLLoader.load(controller.getClass().getResource(Risorse.FXML.FINESTRA_CONFERMA))
                    )
        );
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
        
    }
    
    /**
     * 
     * @param controller
     * @param domanda
     * @param risposte
     * @param ok
     * @throws IOException 
     */
    public static void finestraInputMenu(Object controller, String domanda,  ArrayList<String> risposte, FinestraInputMenuController.Codice ok) throws IOException{
        FinestraInputMenuController.scenaCorrente = Programma.scenaCorrente();
        FinestraInputMenuController.input(domanda, risposte.toArray(new String[risposte.size()]), ok);
        
        Programma.vistaCorrente.setScene(
                new Scene(
                            FXMLLoader.load(controller.getClass().getResource(Risorse.FXML.FINESTRA_INPUT_MENU))
                    )
        );
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
        
    }
    
    
    public static void finestraAvviso(Object controller, String domanda) throws IOException {
        FinestraAvvisoController.scenaCorrente = Programma.scenaCorrente();
        FinestraAvvisoController.conferma(domanda);
        
        Programma.vistaCorrente.setScene(
                new Scene(
                            FXMLLoader.load(controller.getClass().getResource(Risorse.FXML.FINESTRA_AVVISO))
                    )
        );
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
    }
    
    /**
     * Metodo che accentra la finestra e la rende trasparente..
     */
    public static void adattaFinestra(){
        Schermo.accentraFinestra(Programma.vistaCorrente);
        Programma.vistaCorrente.getScene().setFill(null);
    }
    

    public static void campoData(DatePicker datePicker){
        String pattern = "dd/MM/yyyy";

        datePicker.setPromptText(pattern.toLowerCase());

        datePicker.setConverter(new StringConverter<LocalDate>() {
             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

             @Override 
             public String toString(LocalDate date) {
                 if (date != null) {
                     return dateFormatter.format(date);
                 } else {
                     return "";
                 }
             }

             @Override 
             public LocalDate fromString(String string) {
                 if (string != null && !string.isEmpty()) {
                     return LocalDate.parse(string, dateFormatter);
                 } else {
                     return null;
                 }
             }
         });
    }
 }
