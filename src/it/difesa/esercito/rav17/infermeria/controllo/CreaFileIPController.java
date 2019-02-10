package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.programma.Programma;
import it.difesa.esercito.rav17.infermeria.programma.Risorse;
import it.difesa.esercito.rav17.infermeria.DaseDati.Dati;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe che gestisce il comportamento della finestra per la creazione del file 
 * esterno XML contenente l'IP del server MySQL.
 *
 * @author Dr. Domenico della Peruta
 * @version 1.0.1 ultima modifica 05/03/2016
 */
public class CreaFileIPController implements Initializable {

    @FXML
    private Label messaggio;
    
    @FXML
    private Button pulsanteAnnulla;
    
    @FXML
    private Button pulsanteSalva;
    
    @FXML
    private TextField campoIP;
    
    /**
     * Metodo che inizializza la classe controller.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messaggio.setText("");
        if(Dati.getHost() != null){ 
            //la finestra è stata avviata dopo quella di login
            this.campoIP.setText(Dati.getHost());
        }
    }  
    
    /**
     * Funzione che chiude la finestra ed arresta il programma.
     * @param event
     */
    @FXML
    public void annulla(ActionEvent event){
        Platform.exit();
    }
    
    /**
     * Funzione che gestisce l'evento di click del pulsante "Salva", 
     * permettendo di creare il file XML in cui è contenuto l'indirizzo IP del server MySQL.
     * @param event
     */
    @FXML
    public void salvaIP(ActionEvent event){
        String ip = campoIP.getText();
        if(ip != null){
            if(ip.length() > 0){
                Properties lista = new Properties();
                lista.setProperty(Risorse.KeyXML.IP, ip);
                
                try {
                    FileOutputStream file = new FileOutputStream(Risorse.FileEsterno.IP_SERVER_MYSQL);
                    lista.storeToXML(file, Risorse.KeyXML.INFO);
                    file.close();
                    //se tutto va bene, prima di aprire il login, viene salvato il nuovo indirizzo IP
                    Dati.setHost(ip);  // e nella gestore della base di dati
                    Programma.vistaCorrente.setScene(
                        new Scene(
                                FXMLLoader.load(getClass().getResource(Risorse.FXML.LOGIN))
                            )
                    );
                    Programma.adattaFinestra();
                    Programma.vistaCorrente.show();
                        
                } catch (IOException ex) {
                    messaggio.setText(Risorse.Messaggi.ERRORE_SALVATAGGIO_FILE);
                }
                
            }else{
                messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
            }
        }else{
            messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
        }
        
    }
    
}
