package it.quasar_x7.infermeria.controllo;

import it.quasar_x7.infermeria.modello.Medico;
import it.quasar_x7.infermeria.programma.Programma;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class FinestraMediciController implements Initializable {


    public static final ObservableList<Medico> lista = FXCollections.observableArrayList();
    
        
    @FXML
    private TextField campo;

    @FXML
    private Label messaggio;

    @FXML
    private TableColumn<Medico, String> colonna;

    @FXML
    private TableView<Medico> tabella;

    
    /**
     * Inizializza la classe Controller.
     * @param rb
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campo.setText("");
        messaggio.setText("");
        colonna.setCellValueFactory(new PropertyValueFactory<>(it.quasar_x7.infermeria.programma.Risorse.Modello.MEDICO));
        tabella.setItems(lista);
        inizializzaLista();
        
    }    
    
    
    /**
     * Metodo che permette di ritornare alla finestra principale, cliccando sul
     * pulsante "Annulla".
     * 
     * @param event 
     */
    @FXML
    private void ritornoFinestraPrincipale(ActionEvent event){
        Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void aggiungiMedico(ActionEvent event) {
        String nuovoMedico = campo.getText();
        if(nuovoMedico != null){
            if(nuovoMedico.length() > 0){
                Programma.datiMedico.aggiungiMedico(nuovoMedico);
                lista.add(new Medico(nuovoMedico));
                Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
                Programma.adattaFinestra();
                Programma.vistaCorrente.show();
            }else{
                messaggio.setText(it.quasar_x7.infermeria.programma.Risorse.Messaggi.NESSUN_VALORE_INSERITO);
            }
        }else{
            messaggio.setText(it.quasar_x7.infermeria.programma.Risorse.Messaggi.NESSUN_VALORE_INSERITO);
        }
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void rimuoviMedico(ActionEvent event) {
        int numeroElementi = Programma.datiMedico.tuttiMedici().size();
        int id = tabella.getSelectionModel().getSelectedIndex();
        Medico _medico = tabella.getSelectionModel().getSelectedItem();
            
        if(id >= 0 && id < numeroElementi && _medico != null){
            
            //elimina elemento in 
            lista.remove(id);
            Programma.datiMedico.eliminaMedico(_medico.getMedico());
            
        }else{
            messaggio.setText(it.quasar_x7.infermeria.programma.Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
        }
        
    }
   
    
    public static void inizializzaLista() {
        lista.clear();
        ArrayList<String> tuttiRecord = Programma.datiMedico.tuttiMedici();
        if(tuttiRecord != null){
            for(String x: tuttiRecord){
                lista.add(new Medico(x));
            }
        }
    }
    
}
