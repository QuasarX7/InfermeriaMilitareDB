package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.modello.Corso;
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
 * Classe che gestisce la finestra lista corsi.
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.2 ultima modifica 15/04/2016
 */
public class ListaCorsoController implements Initializable {

    public static final ObservableList<Corso> lista = FXCollections.observableArrayList();
    
    @FXML
    private TextField campo;

    @FXML
    private Label messaggio;

    @FXML
    private TableColumn<Corso, String> colonna;

    @FXML
    private TableView<Corso> tabella;

    
    
    /**
     * Inizializza la classe Controller.
     * @param rb
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campo.setText("");
        messaggio.setText("");
        colonna.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.CORSO));
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
     * Metodo che permette aggiungere un nuovo corso, tramite il pulsante Aggiungi.
     * @param event 
     */
    @FXML
    private void aggiungiCorso(ActionEvent event) {
        String nuovoCorso = campo.getText();
        if(nuovoCorso != null){
            if(nuovoCorso.length() > 0){
                datiImpostazioni.valore(Risorse.Impostazioni.KEY_CORSO + datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_CORSO), 
                        Risorse.Impostazioni.TIPO_CORSO, 
                        nuovoCorso
                );
                lista.add(new Corso(nuovoCorso));
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
     * Metodo che elimina un corso dalla lista della tabella, associato al 
     * menu di contesto della tabella.
     * 
     * @param event 
     */
    @FXML
    private void rimuoviCorso(ActionEvent event) {
        int numeroElementi = datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_CORSO);
        int id = tabella.getSelectionModel().getSelectedIndex();
        
        if(id >= 0 && id < numeroElementi){
            //elimina elemento in 
            lista.remove(id);

            //cancella tutti gli elementi dalla base di dati
            for(int i=0; i < numeroElementi; i++){
                datiImpostazioni.elimina(Risorse.Impostazioni.KEY_CORSO+i);
            }

            //aggiungi gli elementi della lista nella base di dati
            for(int i=0; i < numeroElementi-1; i++){
                datiImpostazioni.valore(
                        Risorse.Impostazioni.KEY_CORSO+i,
                        Risorse.Impostazioni.TIPO_CORSO, 
                        lista.get(i).getCorso()
                );
            }
        }else{
            messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
        }
        
    }
   
    
    public static void inizializzaLista() {
        lista.clear();
        if(datiImpostazioni != null){
            int nCorsi =datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_CORSO);
            for(int id=0; id < nCorsi ;id++){
                lista.add(new Corso(
                            datiImpostazioni.valore(Risorse.Impostazioni.KEY_CORSO+id)
                    )
                );
            }
        }
    }
    
    
}
