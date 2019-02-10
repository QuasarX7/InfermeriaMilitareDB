package it.difesa.esercito.rav17.infermeria.controllo;

import it.difesa.esercito.rav17.infermeria.programma.Programma;
import it.difesa.esercito.rav17.infermeria.programma.Risorse.Impostazioni;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class FinestraResponsabiliRifiutiController implements Initializable {

    @FXML
    private TextField campoResponsabileProduzione;

    @FXML
    private TextField campoViceResponsabileProduzione;

    @FXML
    private TextField campoResponsabileStoccaggio;

    @FXML
    private TextField campoViceResponsabileStoccaggio;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoResponsabileProduzione.setText(
                Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_RESPONSABILE_PRODUZIONE_RIFIUTI
                )
        );
        campoViceResponsabileProduzione.setText(
                Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_VICE_RESPONSABILE_PRODUZIONE_RIFIUTI
                )
        );
        
        campoResponsabileStoccaggio.setText(
                Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_RESPONSABILE_STOCCAGGIO_RIFIUTI
                )
        );
        
        campoViceResponsabileStoccaggio.setText(
                Programma.datiImpostazioni.valore(
                        Impostazioni.KEY_VICE_RESPONSABILE_STOCCAGGIO_RIFIUTI
                )
        );
    
    }    
    
    @FXML
    public void chiusuraSenzaSalvare(ActionEvent event) {
        Programma.vistaCorrente.setScene(
                Programma.finestraPrincipale
        );
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
    }
    
    @FXML
    public void salva(ActionEvent event) {
        Programma.datiImpostazioni.valore(
                Impostazioni.KEY_RESPONSABILE_PRODUZIONE_RIFIUTI, 
                Impostazioni.TIPO_RIFIUTI, 
                campoResponsabileProduzione.getText()
        );
        Programma.datiImpostazioni.valore(
                Impostazioni.KEY_VICE_RESPONSABILE_PRODUZIONE_RIFIUTI, 
                Impostazioni.TIPO_RIFIUTI, 
                campoViceResponsabileProduzione.getText()
        );
        Programma.datiImpostazioni.valore(
                Impostazioni.KEY_RESPONSABILE_STOCCAGGIO_RIFIUTI, 
                Impostazioni.TIPO_RIFIUTI, 
                campoResponsabileStoccaggio.getText()
        );
        Programma.datiImpostazioni.valore(
                Impostazioni.KEY_VICE_RESPONSABILE_STOCCAGGIO_RIFIUTI, 
                Impostazioni.TIPO_RIFIUTI, 
                campoViceResponsabileStoccaggio.getText()
        );
        chiusuraSenzaSalvare(event);
    }
    
    
}
