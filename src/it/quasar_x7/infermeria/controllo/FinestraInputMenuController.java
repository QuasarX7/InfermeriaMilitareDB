package it.quasar_x7.infermeria.controllo;

import java.net.URL;
import java.util.ResourceBundle;

import it.quasar_x7.infermeria.programma.Programma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * Gestore della finestra di dialoga a input menu.
 *
 * @author Dott. Domenico della Peruta
 */
public class FinestraInputMenuController implements Initializable {

    
    public interface Codice{
        public void esegui(String risposta);
    }
    
    private static Codice azione;

    public static Scene scenaCorrente;
    
    private static String testo;
    
    private static final  ObservableList<String> listaInput = FXCollections.observableArrayList();

    
    @FXML
    private ComboBox<String> input;

    @FXML
    private TextArea domanda;
    
   

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        input.setItems(listaInput);
        if(testo != null)
            domanda.setText(testo);
        else
            domanda.setText("Seleziona input");
    }  
    
    public static void input(String testoDomanda,String[] risposte, Codice ok){
        listaInput.clear();
        testo = testoDomanda;
        listaInput.addAll(risposte);
        azione = ok;
    } 
    
    @FXML
    private void pulsanteOK(ActionEvent event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            azione.esegui(input.getValue());
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
