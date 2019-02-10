package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.programma.Programma;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

/**
 * Gestore finestra di conferma
 *
 * @author Dott. Domenico della Peruta
 */
public class FinestraConfermaController implements Initializable {
    
    public interface Codice{
        public void esegui();
    }
    
    public static Scene scenaCorrente;
    
    private static Codice azione;
    private static String testo;
    
    @FXML
    private TextArea domanda;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(testo != null)
            domanda.setText(testo);
        else
            domanda.setText("Conferma l'azione?");
    }    
    
    public static void conferma(String testoDomanda,Codice codice){
        azione = codice;
        testo = testoDomanda;
    } 
    
    @FXML
    private void chiudiSenzaSalvare(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            chiudi();
        }
    }

    @FXML
    private void chiudiSalva(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            chiudi();
            azione.esegui();
            azione = null;
        }
    }
    
    private void chiudi(){
        if(scenaCorrente != null)
            Programma.vistaCorrente.setScene(scenaCorrente);
        else
            Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
            
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
        scenaCorrente = null;
    }
    
    
}
