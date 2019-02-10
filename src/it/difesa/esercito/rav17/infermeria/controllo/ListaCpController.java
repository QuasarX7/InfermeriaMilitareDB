package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.modello.Compagnia;
import it.difesa.esercito.rav17.infermeria.programma.Programma;
import it.difesa.esercito.rav17.infermeria.programma.Risorse;
import static it.difesa.esercito.rav17.infermeria.programma.Programma.datiImpostazioni;
import java.net.URL;
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
 * Classe che implementa il comportamento della finestra di gestione della lista delle Cp.
 *
 * @author Dr. Domenico della Peruta
 * @version 1.0.2 ultima modifica 15/04/2016
 */
public class ListaCpController implements Initializable {

    public static final ObservableList<Compagnia> lista = FXCollections.observableArrayList();
    
    @FXML
    private TextField campo;

    @FXML
    private Label messaggio;

    @FXML
    private TableColumn<Compagnia, String> colonna;

    @FXML
    private TableView<Compagnia> tabella;

    
    /**
     * Inizializza la classe Controller.
     * @param rb
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campo.setText("");
        messaggio.setText("");
        colonna.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.CP));
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
     * Metodo che permette aggiungere una nuova Cp, tramite il pulsante Aggiungi.
     * @param event 
     */
    @FXML
    private void aggiungiCp(ActionEvent event) {
        String nuovaCp = campo.getText();
        if(nuovaCp != null){
            if(nuovaCp.length() > 0){
                datiImpostazioni.valore(Risorse.Impostazioni.KEY_CP + datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_CP), 
                        Risorse.Impostazioni.TIPO_CP, 
                        nuovaCp
                );
                lista.add(new Compagnia(nuovaCp));
                Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
                Programma.adattaFinestra();
                Programma.vistaCorrente.show();
            }else{
                messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
            }
        }else{
            messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_INSERITO);
        }
    }
    
    /**
     * Metodo che elimina una compagnia dalla lista della tabella, associato al 
     * menu di contesto della tabella.
     * 
     * @param event 
     */
    @FXML
    private void rimuoviCp(ActionEvent event) {
        int numeroElementi = datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_CP);
        int id = tabella.getSelectionModel().getSelectedIndex();
        
        if(id >= 0 && id < numeroElementi){
            //elimina elemento in 
            lista.remove(id);

            //cancella tutti gli elementi dalla base di dati
            for(int i=0; i < numeroElementi; i++){
                datiImpostazioni.elimina(Risorse.Impostazioni.KEY_CP+i);
            }

            //aggiungi gli elementi della lista nella base di dati
            for(int i=0; i < numeroElementi-1; i++){
                datiImpostazioni.valore(
                        Risorse.Impostazioni.KEY_CP+i,
                        Risorse.Impostazioni.TIPO_CP, 
                        lista.get(i).getCp()
                );
            }
        }else{
            messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
        }
        
    }
   
    
    public static void inizializzaLista() {
        lista.clear();
        if(datiImpostazioni != null){
            int nCp =datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_CP);
            for(int id=0; id < nCp ;id++){
                lista.add(new Compagnia(
                            datiImpostazioni.valore(Risorse.Impostazioni.KEY_CP+id)
                    )
                );
            }
        }
    }
    
}
