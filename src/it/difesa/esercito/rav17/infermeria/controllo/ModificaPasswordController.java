package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.programma.Programma;
import it.difesa.esercito.rav17.infermeria.programma.Risorse;
import it.difesa.esercito.rav17.infermeria.DaseDati.DatiUtente;
import it.quasar_x7.java.utile.CifrarioVigeneve;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Classe che gestisce la finestra di modifica della password.
 *
 * @author Dr. Domenico della Peruta
 * @version 1.0.2 ultima modifica 10/03/16
 */
public class ModificaPasswordController implements Initializable {

    
    @FXML
    private PasswordField campoConfermaPassword;

    @FXML
    private PasswordField campoNuovaPassword;

    @FXML
    private PasswordField campoVecchiaPassword;
    
    @FXML
    private Label messaggio;
    
    @FXML
    private Label utente;

    private DatiUtente db;
    
    
    /**
     * Metodo di inizializzazione della classe.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DatiUtente();
        utente.setText(Programma.utente);
        messaggio.setText("");
    }    
    
    /**
     * Metodo che salva le modifiche della password, se corrette.
     * 
     * @param event 
     */
    @FXML
    public void salvaModifiche(ActionEvent event) {
        String passVecchia = campoVecchiaPassword.getText(); System.out.println(passVecchia);
        char[] passNuova = campoNuovaPassword.getText().toCharArray();
        char[] conferma = campoConfermaPassword.getText().toCharArray();
        if(controlloPassword(passVecchia)){
             if(confrontoPassword(passNuova,conferma)){
                 
                 db.eliminaUtente(Programma.utente);
                 db.aggiungiUtente(
                         Programma.utente, 
                         new CifrarioVigeneve(Risorse.Codifica.KEY).cifra(String.valueOf(passNuova)),
                         Programma.livello
                 );
                 
                 try {
                    Programma.finestraPrincipale = new Scene(
                             FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_PRINCIPALE))
                    );
                    Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
                    Programma.adattaFinestra();
                    Programma.vistaCorrente.show();
                 } catch (IOException ex) {
                     messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
                 }
                 
             }else{
                 messaggio.setText(Risorse.Messaggi.ERRORE_NUOVA_PASSWORD);

             }

         }else{
             messaggio.setText(Risorse.Messaggi.ERRORE_VECCHIA_PASSWORD);
             campoVecchiaPassword.setText("");
         } 
    }
    
    private boolean controlloPassword(String passVecchia) {
        if(db.trovaUtene(Programma.utente)!=null){
            String pass = (String) db.trovaUtene(Programma.utente)[1];
            CifrarioVigeneve codifica = new CifrarioVigeneve(Risorse.Codifica.KEY);
            pass=codifica.decifra(pass);
            if(pass.compareTo(passVecchia)==0){
                return true;
            }
        }
        return false;
    }

    private boolean confrontoPassword(char[] passNuova, char[] conferma) {
        if(passNuova.length == conferma.length){
            for(int i=0; i<passNuova.length;i++){
                if(passNuova[i]!=conferma[i]){
                    return false;
                }
            }
        }else
            return false;
        return true;

    }

    /**
     * Metodo che ripristina la finestra di login.
     * @param event 
     */
    @FXML
    public void annulla(ActionEvent event) {
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                            FXMLLoader.load(getClass().getResource(Risorse.FXML.LOGIN))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            messaggio.setText(Risorse.Messaggi.ERRORE_CARICAMENTO_FINESTRA);
        }
    }
    
}
