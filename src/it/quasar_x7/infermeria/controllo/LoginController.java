package it.quasar_x7.infermeria.controllo;

import it.quasar_x7.infermeria.DaseDati.DatiUtente;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.CifrarioVigeneve;

import static it.quasar_x7.infermeria.programma.Programma.datiUtente;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Classe che gestisce la finestra di login.
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.4 ultima modifica 22/09/16
 */
public class LoginController implements Initializable {


    
    @FXML
    private Button pulsanteModifica;
    
    @FXML
    private Button pulsantAccedi;
    
    @FXML
    private ChoiceBox menuUtenti;
    
    @FXML
    private PasswordField campoPassword;
    
    @FXML
    private Label messaggio;
    
    @FXML
    private Button pulsanteIP;
    
    
    /**
     * Metodo di inizializzazione della classe.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messaggio.setText("");
        this.pulsanteIP.setText("IP="+DatiUtente.getHost());
        ArrayList<String> listaUtenti = datiUtente.tuttiUtenti();
        if(listaUtenti != null){
            if(listaUtenti.size() > 0){
                Object[] utenti = datiUtente.tuttiUtenti().toArray();
                if(utenti !=null) {
                     menuUtenti.getItems().addAll(utenti);
                }
            }else{
                messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE);
            }
        }else{
            messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE);
        }
    }

    
    
    /**
     * Metodo che gestisce il pulsante "Accedi" che permette di verificare il login
     * ed accedere alla finestra principale.
     * @param event
     */
    @FXML
    public void accedi(ActionEvent event){
        Object utente = menuUtenti.getValue();
        if(utente != null){
            if(utente.toString().length() > 0){
                Programma.utente=utente.toString();
                Programma.livello=datiUtente.livello(utente.toString());
                String password = campoPassword.getText();
                if(controlloPassword(password,Programma.utente)){
                    try {
                        //inizializza finestra principale
                        Programma.finestraPrincipale = new Scene(
                                FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_PRINCIPALE))
                        );
                        Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
                        //Programma.finestraPrincipale.getStylesheets().add(Risorse.FXML.CSS);
                        Programma.adattaFinestra();
                        Programma.vistaCorrente.show();
                        messaggio.setText(Risorse.Messaggi.OK);
                    } catch (IOException ex) {
                        messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
                        ex.printStackTrace();
                    }
                }else{
                    messaggio.setText(Risorse.Messaggi.ERRORE_PASSWORD);
                }
            }else{
                messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE_SELEZIONATO);
            }
            
        }else{
            messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE_SELEZIONATO);
        }
        campoPassword.setText("");
        
    }
    

    /**
     * Metodo che effettua la verifica della password di un utente.
     * 
     * @param password
     * @param utente
     * @return 
     */
    private boolean controlloPassword(String password, String utente) {
        if(datiUtente.trovaUtene(utente)!=null){
            CifrarioVigeneve codifica=new CifrarioVigeneve(Risorse.Codifica.KEY);

            String pass = (String) datiUtente.trovaUtene(utente)[1];
            pass=codifica.decifra(pass);
            if(pass.compareTo(password)==0){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodo che apre la finestra per la modifica della password.
     * 
     * @param event
     */
    @FXML
    public void modifica(ActionEvent event){
        Object utente = menuUtenti.getValue();
                
        if(utente != null){
            if(utente.toString().length() > 0){
                Programma.utente=utente.toString();
                Programma.livello=datiUtente.livello(utente.toString());
        
                try {
                    Programma.vistaCorrente.setScene(
                            new Scene(
                                    FXMLLoader.load(getClass().getResource(Risorse.FXML.MODIFICA_PASSWORD))
                            )
                    );
                    Programma.adattaFinestra();
                    Programma.vistaCorrente.show();
                } catch (IOException ex) {
                    messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
                }
            }else{
                messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE_SELEZIONATO);
            }
        }else{
            messaggio.setText(Risorse.Messaggi.ERRORE_NESSUN_UTENTE_SELEZIONATO);
        }
       
    }
    
    /**
     * Metodo che apre la finestra per reimpostare l'IP del server MySQL.
     * @param event 
     */
    @FXML
    void impostaIP(ActionEvent event) {
                        
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                            FXMLLoader.load(getClass().getResource(Risorse.FXML.CREA_FILE_IP))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
        }
    }
    
    
    /**
     * Chiusura programma.
     * 
     * @param event 
     */
    @FXML
    void chiusuraSenzaSalvare(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    
}


