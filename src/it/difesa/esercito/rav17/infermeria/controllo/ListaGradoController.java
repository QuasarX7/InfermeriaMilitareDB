package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.modello.Grado;
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
 * Metodo che gestisce la finestra della lista gradi.
 *
 * @author Dr Domenico della Peruta
 * @version 1.0.2 ultima modifica 15/04/16
 */
public class ListaGradoController implements Initializable {

    public static final ObservableList<Grado> lista = FXCollections.observableArrayList();
    
    @FXML
    private TextField campo;

    @FXML
    private Label messaggio;

    @FXML
    private TableColumn<Grado, String> colonna;

    @FXML
    private TableView<Grado> tabella;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campo.setText("");
        messaggio.setText("");
        colonna.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.GRADO));
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
     * Metodo che permette aggiungere un nuovo grado, tramite il pulsante Aggiungi.
     * @param event 
     */
    @FXML
    private void aggiungiGrado(ActionEvent event) {
        String nuovoGrado = campo.getText();
        if(nuovoGrado != null){
            if(nuovoGrado.length() > 0){
                datiImpostazioni.valore(Risorse.Impostazioni.KEY_GRADO + datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_GRADO), 
                        Risorse.Impostazioni.TIPO_GRADO, 
                        nuovoGrado
                );
                lista.add(new Grado(nuovoGrado));
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
    private void rimuoviGrado(ActionEvent event) {
        int numeroElementi = datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_GRADO);
        int id = tabella.getSelectionModel().getSelectedIndex();
        
        if(id >= 0 && id < numeroElementi){
            //elimina elemento in 
            lista.remove(id);

            //cancella tutti gli elementi dalla base di dati
            for(int i=0; i < numeroElementi; i++){
                datiImpostazioni.elimina(Risorse.Impostazioni.KEY_GRADO+i);
            }

            //aggiungi gli elementi della lista nella base di dati
            for(int i=0; i < numeroElementi-1; i++){
                datiImpostazioni.valore(
                        Risorse.Impostazioni.KEY_GRADO+i,
                        Risorse.Impostazioni.TIPO_GRADO, 
                        lista.get(i).getGrado()
                );
            }
        }else{
            messaggio.setText(Risorse.Messaggi.NESSUN_VALORE_SELEZIONATO);
        }
        
    }
   
    
    public static void inizializzaLista() {
        lista.clear();
        if(datiImpostazioni != null){
            int nGradi =datiImpostazioni.tipo(Risorse.Impostazioni.TIPO_GRADO);
            for(int id=0; id < nGradi ;id++){
                lista.add(new Grado(
                            datiImpostazioni.valore(Risorse.Impostazioni.KEY_GRADO+id)
                    )
                );
            }
        }
    }
    
}
