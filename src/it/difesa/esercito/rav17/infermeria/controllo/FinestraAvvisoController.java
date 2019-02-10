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
 * Gestore della finestra di avviso.
 *
 * @author Dott. Domenico della Peruta
 */
public class FinestraAvvisoController implements Initializable {

    
    public static Scene scenaCorrente;
    
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
    
    public static void conferma(String testoDomanda){
        testo = testoDomanda;
    } 
    
    @FXML
    private void pulsanteOK(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            chiudi();
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
