package it.quasar_x7.infermeria.controllo;

import it.quasar_x7.java.utile.DataOraria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import it.quasar_x7.infermeria.controllo.FinestraStampatoRegistroIncorporamento;
import it.quasar_x7.infermeria.controllo.FinestraStampatoRegistroVaccinazioni;
import it.quasar_x7.infermeria.controllo.Report;
import it.quasar_x7.infermeria.modello.Militare;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.infermeria.vista.FinestraModelloGL;
import it.quasar_x7.infermeria.vista.FinestraModelloML;
import it.quasar_x7.infermeria.vista.FinestraRicovero;
import it.quasar_x7.infermeria.vista.FinestraVisita;
import it.quasar_x7.java.swing.finestra.Finestra;
import it.quasar_x7.java.utile.Errore;

import static it.quasar_x7.infermeria.programma.Programma.datiMedico;
import static it.quasar_x7.infermeria.programma.Programma.datiMilitari;
import static it.quasar_x7.infermeria.programma.Programma.datiRicoveri;
import static it.quasar_x7.infermeria.programma.Programma.datiVaccinazioni;
import static it.quasar_x7.infermeria.programma.Programma.datiVerbaleModelloGL;
import static it.quasar_x7.infermeria.programma.Programma.datiVerbaleModelloML;
import static it.quasar_x7.infermeria.programma.Programma.datiVisita;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class FinestraRicercaDataController implements Initializable {

    public static ObservableList<Militare> listaMilitari = FXCollections.observableArrayList();
    
       
    
    @FXML
    private DatePicker dataGiorno;

    @FXML
    private TableView<Militare> tabella;
    
    @FXML
    private TableColumn<Militare, String> colonnaGrado;
    
    @FXML
    private TableColumn<Militare, String> colonnaCognome;

    @FXML
    private TableColumn<Militare, String> colonnaNome;

    @FXML
    private TableColumn<Militare, DataOraria> colonnaNascita;

    @FXML
    private TableColumn<Militare, String> colonnaInfo;

    @FXML
    private TableColumn<Militare, String> colonnaCp;

    @FXML
    private ChoiceBox<String> selezionaAttività;
    
    @FXML
    private Label messaggio;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inizializzazioneTabella();
        selezionaAttività.getItems().addAll(
                Risorse.Attività.INCORPORATI,
                Risorse.Attività.VACCINAZIONI,
                Risorse.Attività.CHIEDENTI_VISITA,
                Risorse.Attività.PRONTO_SOCCORSO,
                Risorse.Attività.RICOVERI,
                Risorse.Attività.VERBALI_GL,
                Risorse.Attività.VERBALI_ML
        );
        selezionaAttività.getSelectionModel().select(-1);
        dataGiorno.getEditor().setText(DataOraria.creaDataOggi().stampaGiorno('/'));
    } 
    
    private void inizializzazioneTabella(){
        colonnaGrado.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.GRADO));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.COGNOME));
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.NOME));
        colonnaNascita.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.NASCITA));
        colonnaCp.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.CP));
        colonnaInfo.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.INFO));
        tabella.setItems(listaMilitari);
    }
    
    private DataOraria dataCampo(){
        LocalDate data = dataGiorno.getValue();
        DataOraria dataOraria = null;
        if(data != null){
            dataOraria = new DataOraria(
                    data.getDayOfMonth(),
                    data.getMonthValue(),
                    data.getYear()
            );
        }
        return dataOraria;
    }
    
    @FXML
    void cerca(ActionEvent event) {
        listaMilitari.clear();
        DataOraria dataOraria = dataCampo();
        if(dataOraria != null && selezionaAttività.getValue() != null)
            switch(selezionaAttività.getValue()){
                case Risorse.Attività.VACCINAZIONI: 
                    caricaVaccinazioni(dataOraria);
                break;
                case Risorse.Attività.CHIEDENTI_VISITA: 
                    caricaVisita(dataOraria,"CHIEDENTI VISITA");
                break;
                case Risorse.Attività.PRONTO_SOCCORSO: 
                    caricaVisita(dataOraria,"PRONTO SOCCORSO");
                break;
                case Risorse.Attività.RICOVERI: 
                    caricaRicoveri(dataOraria);
                break;
                case Risorse.Attività.INCORPORATI: 
                    caricaIncorporamento(dataOraria);
                break;
                case Risorse.Attività.VERBALI_GL: 
                    caricaGL(dataOraria);
                break;
                case Risorse.Attività.VERBALI_ML: 
                    caricaML(dataOraria);
                break;
            }

    }
    
    private void caricaGL(DataOraria data){
        ArrayList<Object[]> elenco = datiVerbaleModelloGL.trovaVerbaliGL(data);
        if(elenco != null){
            for(Object[] record: elenco){
                String diagnosi = record[5] != null ? record[5].toString() : "";
                listaMilitari.add(
                            new Militare(
                                    record[0].toString(), // grado
                                    record[1].toString(), // cognome
                                    record[2].toString(), // nome
                                    (DataOraria)record[3],// nascita
                                    "",// luogo di nascita
                                    record[4].toString(), // cp
                                    "", // corso
                                    // info
                                    diagnosi
                            )
                );
            }  
        }
    }
    
    private void caricaML(DataOraria data){
        ArrayList<Object[]> elenco = datiVerbaleModelloML.trovaVerbaliML(data);
        if(elenco != null){
            for(Object[] record: elenco){
                listaMilitari.add(
                            new Militare(
                                    record[0].toString(), // grado
                                    record[1].toString(), // cognome
                                    record[2].toString(), // nome
                                    (DataOraria)record[3],// nascita
                                    "",// luogo di nascita
                                    record[4].toString(), // cp
                                    "", // corso
                                    // info
                                    record[5].toString()
                            )
                );
            }  
        }
    }
    
    private void caricaIncorporamento(DataOraria data){
        ArrayList<Object[]> elenco = datiMilitari.elencoIncorporati(data);
        if(elenco != null){
            for(Object[] record: elenco){
                listaMilitari.add(
                            new Militare(
                                    record[0].toString(), // grado
                                    record[1].toString(), // cognome
                                    record[2].toString(), // nome
                                    (DataOraria)record[3],// nascita
                                    "",// luogo di nascita
                                    record[4].toString(), // cp
                                    "", // corso
                                    // info
                                    record[6].toString()
                            )
                );
            }  
        }
    }
    
    private void caricaRicoveri(DataOraria data){
        ArrayList<Object[]> elenco = datiRicoveri.trovaRicoveri(data);
        if(elenco != null){
            for(Object[] record: elenco){
                DataOraria uscita = (DataOraria)record[6];
                String sUscita = uscita != null ? uscita.stampaGiorno('/') +" "+ uscita.stampaOrario() : "?";
                listaMilitari.add(
                            new Militare(
                                    (String)record[0], // grado
                                    (String)record[1], // cognome
                                    (String)record[2], // nome
                                    (DataOraria)record[3],// nascita
                                    "",// luogo di nascita
                                    (String)record[4], // cp
                                    "", // corso
                                    // info
                                    ((DataOraria)record[5]).stampaGiorno('/')+" "+((DataOraria)record[5]).stampaOrario()+
                                    " ⤇ "+sUscita+"\n"+record[7]
                            )
                );
            }  
        }
    }
    
    private void caricaVisita(DataOraria data,String tipo){
        ArrayList<Object[]> elenco = datiVisita.trovaVisiteMediche(data, tipo);
        if(elenco != null){
            for(Object[] record: elenco){
                listaMilitari.add(
                            new Militare(
                                    (String)record[0], // grado
                                    (String)record[1], // cognome
                                    (String)record[2], // nome
                                    (DataOraria)record[3],// nascita
                                    "",// luogo di nascita
                                    (String)record[4], // cp
                                    "", // corso
                                    // info
                                    record[8]+"\n"+record[5]+" \n"+record[6]+""

                            )
                    );
            }
        }
    }

    private void caricaVaccinazioni(DataOraria data){
        ArrayList<Object[]> elenco = datiVaccinazioni.trovaVaccinazioni(data);
        if(elenco != null){
            
                for(Object[] record: elenco){
                    listaMilitari.add(
                            new Militare(
                                    record[1].toString(), // grado
                                    record[2].toString(), // cognome
                                    record[3].toString(), // nome
                                    (DataOraria)record[4],// nascita
                                    "",// luogo di nascita
                                    record[5].toString(), // cp
                                    "", // corso
                                    // info
                                    "vaccino: "+record[6]+" (dose:"+record[9]+") lotto:"+record[7]+ "\nmedico: "+record[10]
                            )
                    );
                }
        }
    }
    
    @FXML
    void selezionaRigaTabella(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            Militare militare = tabella.getSelectionModel().getSelectedItem();
            DataOraria data = dataCampo();
            Object[] infoMilitare=null;
            if(militare != null && data != null){
                switch(selezionaAttività.getValue()){
                    
                    case Risorse.Attività.VACCINAZIONI: 
                        try {
                            Programma.finestraConferma(
                                    this,
                                    Risorse.Conferma.APERTURA_FINESTRA_MODIFICA,
                                    () -> {
                                            try {
                                                AnamnesiVaccinaleController.data = data;
                                                AnamnesiVaccinaleController.registraFinestraRitorno(Programma.vistaCorrente.getScene());
                                                Programma.vistaCorrente.setScene(
                                                        new Scene(
                                                                FXMLLoader.load(getClass().getResource(Risorse.FXML.ANAMNESI_VACCINALE))
                                                        )
                                                );

                                                Programma.adattaFinestra();
                                                Programma.vistaCorrente.show();

                                            } catch (IOException ex) {
                                                Logger.getLogger(FinestraRicercaDataController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                    }
                            );
                        } catch (IOException ex) {
                            Logger.getLogger(FinestraRicercaDataController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    break;
                    
                    case Risorse.Attività.CHIEDENTI_VISITA: 
                    case Risorse.Attività.PRONTO_SOCCORSO:
                            
                        infoMilitare = Programma.datiMilitari.trovaMilitare(
                                militare.getCognome(),
                                militare.getNome(),
                                militare.getNascita()
                        );
                        DataOraria visita = estraiDataOra(militare.getInfo());
                        if(visita != null)
                            Finestra.creaFinestra(new FinestraVisita(infoMilitare,visita));   
                    break;
                    
                    case Risorse.Attività.RICOVERI: 
                        infoMilitare = new Object[]{
                            militare.getCognome(),
                            militare.getNome(),
                            militare.getNascita(),
                            militare.getLuogo(),
                            militare.getGrado(),
                            null,
                            militare.getCp()
                        };
                        DataOraria inizio = estraiDataOra(militare.getInfo());
                        if(inizio != null)
                            Finestra.creaFinestra(new FinestraRicovero(infoMilitare,inizio));
                    break;
                    
                    case Risorse.Attività.INCORPORATI: 
                    break;
                    
                    case Risorse.Attività.VERBALI_GL: 
                        infoMilitare = Programma.datiMilitari.trovaMilitare(
                            militare.getCognome(),
                            militare.getNome(),
                            militare.getNascita()
                        );
                        Finestra.creaFinestra(new FinestraModelloGL(infoMilitare,data,false));
                    break;
                    
                    case Risorse.Attività.VERBALI_ML: 
                        
                        infoMilitare = new Object[]{
                            militare.getCognome(),
                            militare.getNome(),
                            militare.getNascita(),
                            militare.getLuogo(),
                            militare.getGrado(),
                            null,
                            militare.getCp()
                        };

                        Finestra.creaFinestra(new FinestraModelloML(infoMilitare,data));
                        
                    break;
                }
            }
        }
    }
    
    private DataOraria estraiDataOra(String info){
        try {
            return new DataOraria(info.substring(0, 19));
        } catch (Errore ex) {
            return null;
        }
    }
    
    @FXML
    void stampaDati(ActionEvent event) {
        DataOraria dataOraria = dataCampo();
        if(dataOraria != null && selezionaAttività.getValue() != null)
            switch(selezionaAttività.getValue()){
                case Risorse.Attività.VACCINAZIONI: 
                    Finestra.creaFinestra(new FinestraStampatoRegistroVaccinazioni(dataOraria));
                break;
                case Risorse.Attività.CHIEDENTI_VISITA: 
                    visitaMedica(dataOraria,Risorse.Attività.CHIEDENTI_VISITA);
                break;
                case Risorse.Attività.PRONTO_SOCCORSO: 
                    visitaMedica(dataOraria,Risorse.Attività.PRONTO_SOCCORSO);
                break;
                case Risorse.Attività.RICOVERI: 
                break;
                case Risorse.Attività.INCORPORATI: 
                    Finestra.creaFinestra(new FinestraStampatoRegistroIncorporamento(dataOraria));
                break;
                case Risorse.Attività.VERBALI_GL: 
                break;
                case Risorse.Attività.VERBALI_ML: 
                break;
            }
    }
    
    private void visitaMedica(DataOraria dataOraria, String tipo){
        java.awt.EventQueue.invokeLater(
                () -> {
                    String medico = (String) JOptionPane.showInputDialog(
                            null,
                            "seleziona medico responsabile", "medico",
                            JOptionPane.QUESTION_MESSAGE, null,
                            datiMedico.tuttiMedici().toArray()," "
                    );
                     
                    if(medico==null){
                        medico="";
                    }
                    ArrayList<Object[]> listaVisite = datiVisita.trovaVisite(dataOraria,tipo);
                    if(listaVisite != null)
                        Report.stampaVisiteMediche(listaVisite, medico, datiMilitari);
                }
        );
    }
    
    @FXML
    private void chiusura(ActionEvent event) {
        listaMilitari.clear();
        Programma.vistaCorrente.setScene(Programma.finestraPrincipale);
        Programma.adattaFinestra();
        Programma.vistaCorrente.show();
        
    }
    
    /**
     * Riduci la finestra ad icona.
     * @param event 
     */
    @FXML
    private void riduciFinestra(ActionEvent event) {
        Programma.riduciFinestra();
    }
    
}
