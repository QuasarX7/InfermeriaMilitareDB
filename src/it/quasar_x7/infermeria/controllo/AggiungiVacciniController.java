package it.quasar_x7.infermeria.controllo;

import static it.quasar_x7.infermeria.controllo.AnamnesiVaccinaleController.data;
import static it.quasar_x7.infermeria.controllo.AnamnesiVaccinaleController.listaVaccinazione;
import static it.quasar_x7.infermeria.controllo.FinestraImpostazioniController.impostazioni;
import static it.quasar_x7.infermeria.controllo.FinestraPrincipaleController.militare;
import static it.quasar_x7.infermeria.programma.Programma.datiVaccinazioni;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import it.quasar_x7.infermeria.DaseDati.BASE_DATI;
import it.quasar_x7.infermeria.modello.Vaccinazione;
import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse;
import it.quasar_x7.java.utile.DataOraria;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;




/**
 * FXML Controller class
 *
 * @author Ninja
 */
public class AggiungiVacciniController implements Initializable {

    public static AnamnesiVaccinaleController finestraAnamnesi;
   
    @FXML
    private TableView<Vaccinazione> tabellaVaccinazioni;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaProfilassi;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaDose;
    @FXML
    private TableColumn<Vaccinazione, String> colonnaNomeVaccino;
    
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaLotto;
    
    @FXML
    private TableColumn<Vaccinazione, DataOraria> colonnaScadenza;
    
    @FXML
    private TableColumn<Vaccinazione, String> colonnaInadempienza;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(listaVaccinazione == null)
            listaVaccinazione = FXCollections.observableArrayList();
        colonnaProfilassi.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.PROFILASSI));
        colonnaDose.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.TIPO_DOSE));
        colonnaNomeVaccino.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.VACCINO));
        colonnaLotto.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.LOTTO_VACC));
        colonnaScadenza.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.SCADENZA));
        colonnaInadempienza.setCellValueFactory(new PropertyValueFactory<>(Risorse.Modello.INADEMPIENZA));
        tabellaVaccinazioni.setItems(listaVaccinazione);
        
    }    
    
    
    @FXML
    private void aggiungiMeningite1D(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.MENINGITE);
    }
    
    @FXML
    private void aggiungiEpatiteA_CB1(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_A);
    }
    
    @FXML
    private void aggiungiEpatiteA_CB2(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_A_CB2);
    }
    
    @FXML
    private void aggiungiEpatiteB_CB1(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_B);
    }
    
    @FXML
    private void aggiungiEpatiteB_CB2(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_B_CB2);
    }
    
    @FXML
    private void aggiungiEpatiteB_CB3(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_B_CB3);
    }
    
    @FXML
    private void aggiungiEpatiteB_B(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_B_B);
    }
    
    @FXML
    private void aggiungiEpatiteAB_CB1(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_AB);
    }
    
    @FXML
    private void aggiungiEpatiteAB_CB2(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_AB_CB2);
    }
    
    @FXML
    private void aggiungiEpatiteAB_CB3(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.EPATITE_AB_CB3);
    }
    
    @FXML
    private void aggiungiMorRoPar1D(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.MPR);
    }
    
    @FXML
    private void aggiungiMorRoParCB2(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.MPR_CB2);
    }
    
    @FXML
    private void aggiungiVaricellaCB1(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.VARICELLA);
    }
    
    @FXML
    private void aggiungiVaricellaCB2(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.VARICELLA_CB2);
    }
    
    @FXML
    private void aggiungiTetanoDiferitePolioR(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.TDP);
    }
    
    @FXML
    private void aggiungiTetanoDiferiteR(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.TD);
    }
    
    @FXML
    private void aggiungiPolioR(ActionEvent event) {
        creaRecordVaccino(Risorse.DoseVaccino.POLIO);
    }
    
    @FXML
    private void aggiungiAltroVaccino(ActionEvent event) {
        try {
            AggiungiVaccinoGenericoController.gestore = this;
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.FINESTRA_AGGIUNGI_VACCINO_GENERICO))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(AggiungiVaccinoGenericoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /****************************************************************************
    * Metodo che genera il record relativo alla nuova vaccinazione.
    *
    * @param _vaccino
    ***************************************************************************/
   public void creaRecordVaccino(String _vaccino){
       //salvaDatiControindicazioni();
       Object[] vaccino;
       
       if(data == null){
            vaccino = datiVaccinazioni.trovaVaccinazioneOggi(
                                  _vaccino,
                                  militare.getCognome(),
                                  militare.getNome(),
                                  militare.getNascita()
            );
       }else{
           vaccino = datiVaccinazioni.trovaVaccinazione(
                   _vaccino,
                    data,
                    militare.getCognome(),
                    militare.getNome(),
                    militare.getNascita()
           );
       }
       if(vaccino == null){
           Object[] record = caricaDatiVaccino(_vaccino);
           if(datiVaccinazioni.esisteVaccinazione(_vaccino,militare.getCognome(),militare.getNome(), militare.getNascita())){
               SwingUtilities.invokeLater(() -> {
                   int opzione = javax.swing.JOptionPane.showConfirmDialog(
                           null,
                           "Il militare è già stato sottoposto a questa vaccinazione,\nvuoi effettuare la vaccinazione?",
                           "avviso!",
                           javax.swing.JOptionPane.YES_NO_OPTION,
                           javax.swing.JOptionPane.WARNING_MESSAGE,
                           new ImageIcon(
                                getClass().getResource(Risorse.Immagine.ATTESA)
                           )
                   );
                   if (opzione == javax.swing.JOptionPane.YES_OPTION) {
                       salvaRecordVaccinazioni(_vaccino, record);
                   }
               });
           }else{//nuova vaccinazione
               salvaRecordVaccinazioni(_vaccino, record);
           }
      }else{ 
           SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                        null,
                        String.format("Il vaccino %s è già aggiunto!",_vaccino)
                        , "avviso!", JOptionPane.QUESTION_MESSAGE);
           });
      }
   }
   
   
   public Object[] caricaDatiVaccino(String _vaccino){
        Object[] campiVaccinazione = new Object[datiVaccinazioni.dimensioneVaccinazioni()];
        //--------------------------------------------------
        //                   MENINGITE 1D
        if(_vaccino.compareTo(Risorse.DoseVaccino.MENINGITE)==0 ||
               _vaccino.compareTo(Risorse.DoseVaccino.MPR)==0){
                campiVaccinazione[5]="1D";
                if(_vaccino.compareTo(Risorse.DoseVaccino.MENINGITE)==0){
                    if(impostazioni!=null){
                         campiVaccinazione[7]=impostazioni[35];
                         campiVaccinazione[8]=impostazioni[36];
                         campiVaccinazione[9]=impostazioni[37];
                         campiVaccinazione[10]=impostazioni[38];
                         campiVaccinazione[11]=impostazioni[39];
                         campiVaccinazione[6]=impostazioni[53];
                    }
                }else if(_vaccino.compareTo(Risorse.DoseVaccino.MPR)==0){
                    if(impostazioni!=null){
                         campiVaccinazione[7]=impostazioni[40];
                         campiVaccinazione[8]=impostazioni[41];
                         campiVaccinazione[9]=impostazioni[42];
                         campiVaccinazione[10]=impostazioni[43];
                         campiVaccinazione[11]=impostazioni[44];
                         campiVaccinazione[6]=impostazioni[54];
                    }
                }
        //---------------------------------------------------
        //                 EPATITE A,B,A+B   CB1
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_A)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.EPATITE_AB)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B)==0){
            campiVaccinazione[5]="CB1";

            if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_A)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[0];                  
                     campiVaccinazione[8]=impostazioni[1];
                     campiVaccinazione[9]=impostazioni[2];
                     campiVaccinazione[10]=impostazioni[3];
                     campiVaccinazione[11]=impostazioni[4];
                     campiVaccinazione[6]=impostazioni[46];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[5];
                     campiVaccinazione[8]=impostazioni[6];
                     campiVaccinazione[9]=impostazioni[7];
                     campiVaccinazione[10]=impostazioni[8];
                     campiVaccinazione[11]=impostazioni[9];
                     campiVaccinazione[6]=impostazioni[47];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_AB)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[10];
                     campiVaccinazione[8]=impostazioni[11];
                     campiVaccinazione[9]=impostazioni[12];
                     campiVaccinazione[10]=impostazioni[13];
                     campiVaccinazione[11]=impostazioni[14];
                     campiVaccinazione[6]=impostazioni[48];
                }
            }
        //----------------------------------------------
        //               VARICELLA CB1
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.VARICELLA)==0){
            campiVaccinazione[5]="CB1";             
            if(impostazioni!=null){
                 campiVaccinazione[7]=impostazioni[30];
                 campiVaccinazione[8]=impostazioni[31];
                 campiVaccinazione[9]=impostazioni[32];
                 campiVaccinazione[10]=impostazioni[33];
                 campiVaccinazione[11]=impostazioni[34]; 
                 campiVaccinazione[6]=impostazioni[52];
            }
        //----------------------------------------------
        //               TETANO/DIFTERICO    R
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.TD)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.TDP)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.POLIO)==0){
            campiVaccinazione[5]="R";
            if(_vaccino.compareTo(Risorse.DoseVaccino.TD)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[15];
                     campiVaccinazione[8]=impostazioni[16];
                     campiVaccinazione[9]=impostazioni[17];
                     campiVaccinazione[10]=impostazioni[18];
                     campiVaccinazione[11]=impostazioni[19];
                     campiVaccinazione[6]=impostazioni[49];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.TDP)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[20];
                     campiVaccinazione[8]=impostazioni[21];
                     campiVaccinazione[9]=impostazioni[22];
                     campiVaccinazione[10]=impostazioni[23];
                     campiVaccinazione[11]=impostazioni[24];
                     campiVaccinazione[6]=impostazioni[50];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.POLIO)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[25];
                     campiVaccinazione[8]=impostazioni[26];
                     campiVaccinazione[9]=impostazioni[27];
                     campiVaccinazione[10]=impostazioni[28];
                     campiVaccinazione[11]=impostazioni[29];
                     campiVaccinazione[6]=impostazioni[51];
                }
            }
        //---------------------------------------------------
        //            EPATITE A,B,A+B    CB2
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_A_CB2)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.EPATITE_AB_CB2)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B_CB2)==0){
            campiVaccinazione[5]="CB2";
            if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_A_CB2)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[0];
                     campiVaccinazione[8]=impostazioni[1];
                     campiVaccinazione[9]=impostazioni[2];
                     campiVaccinazione[10]=impostazioni[3];
                     campiVaccinazione[11]=impostazioni[4];
                     campiVaccinazione[6]=impostazioni[46];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B_CB2)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[5];
                     campiVaccinazione[8]=impostazioni[6];
                     campiVaccinazione[9]=impostazioni[7];
                     campiVaccinazione[10]=impostazioni[8];
                     campiVaccinazione[11]=impostazioni[9];
                     campiVaccinazione[6]=impostazioni[47];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_AB_CB2)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[10];
                     campiVaccinazione[8]=impostazioni[11];
                     campiVaccinazione[9]=impostazioni[12];
                     campiVaccinazione[10]=impostazioni[13];
                     campiVaccinazione[11]=impostazioni[14];
                     campiVaccinazione[6]=impostazioni[48];
                }
            }
        //---------------------------------------------------
        //            EPATITE A,B,A+B    CB3
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_AB_CB3)==0 ||
                _vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B_CB3)==0){

            campiVaccinazione[5]="CB3";

            if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B_CB3)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[5];
                     campiVaccinazione[8]=impostazioni[6];
                     campiVaccinazione[9]=impostazioni[7];
                     campiVaccinazione[10]=impostazioni[8];
                     campiVaccinazione[11]=impostazioni[9];
                     campiVaccinazione[6]=impostazioni[47];
                }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_AB_CB3)==0){
                if(impostazioni!=null){
                     campiVaccinazione[7]=impostazioni[10];
                     campiVaccinazione[8]=impostazioni[11];
                     campiVaccinazione[9]=impostazioni[12];
                     campiVaccinazione[10]=impostazioni[13];
                     campiVaccinazione[11]=impostazioni[14];
                     campiVaccinazione[6]=impostazioni[48];
                }
            }
        //----------------------------------------------
        //               VARICELLA CB2
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.VARICELLA_CB2)==0 || _vaccino.compareTo(Risorse.DoseVaccino.MPR_CB2)==0){
            campiVaccinazione[5]="CB2";
            if(_vaccino.compareTo(Risorse.DoseVaccino.VARICELLA_CB2)==0 ){
                 if(impostazioni!=null){
                      campiVaccinazione[7]=impostazioni[30];
                      campiVaccinazione[8]=impostazioni[31];
                      campiVaccinazione[9]=impostazioni[32];
                      campiVaccinazione[10]=impostazioni[33];
                      campiVaccinazione[11]=impostazioni[34];
                      campiVaccinazione[6]=impostazioni[52];
                 }
            }else if(_vaccino.compareTo(Risorse.DoseVaccino.MPR_CB2)==0 ){
                 if(impostazioni!=null){
                         campiVaccinazione[7]=impostazioni[40];
                         campiVaccinazione[8]=impostazioni[41];
                         campiVaccinazione[9]=impostazioni[42];
                         campiVaccinazione[10]=impostazioni[43];
                         campiVaccinazione[11]=impostazioni[44];
                         campiVaccinazione[6]=impostazioni[54];
                    }
            }
        //----------------------------------------------
        // EPATITE B   B
        }else if(_vaccino.compareTo(Risorse.DoseVaccino.EPATITE_B_B)==0){
            campiVaccinazione[5]="B";
            if(impostazioni!=null){
                 campiVaccinazione[7]=impostazioni[5];
                 campiVaccinazione[8]=impostazioni[6];
                 campiVaccinazione[9]=impostazioni[7];
                 campiVaccinazione[10]=impostazioni[8];
                 campiVaccinazione[11]=impostazioni[9];
                 campiVaccinazione[6]=impostazioni[47];
            }
        }
        return campiVaccinazione;
    }
    
   
   
   
   /**
    * Permette di salvare il dati della vaccinazione appena creato.
    * 
    * @param _vaccino
    * @param record 
    */ 
   public void salvaRecordVaccinazioni(String _vaccino, Object[] record){
        
        Vaccinazione vaccinazione = null;
                         //se non trovo un record lo crea
        if(data == null){
            DataOraria oggi = new DataOraria();
            oggi.oggi();
           boolean ok = datiVaccinazioni.aggiungiVaccinazioneOggi(
                   _vaccino,
                   militare.getCognome(),
                   militare.getNome(),
                   militare.getNascita(),
                   record
           );
            if(ok){
                 vaccinazione = new Vaccinazione(
                     oggi,
                     _vaccino,
                     (String)record[BASE_DATI.VACCINAZIONI.DOSE],
                     (String)record[BASE_DATI.VACCINAZIONI.VIA_SOMMINISTRAZIONE],
                     (String)record[BASE_DATI.VACCINAZIONI.NOME_VACCINO],
                     (String)record[BASE_DATI.VACCINAZIONI.DITTA],
                     (String)record[BASE_DATI.VACCINAZIONI.LOTTO],
                     (DataOraria)record[BASE_DATI.VACCINAZIONI.SCADENZA],
                     (String)record[BASE_DATI.VACCINAZIONI.INADEMPIENZA]
                 );
            }

        }else{
           boolean ok = datiVaccinazioni.aggiungiVaccinazione(
                   _vaccino,
                   data,
                   militare.getCognome(),
                   militare.getNome(),
                   militare.getNascita(),
                   record
           );
            if(ok){
                vaccinazione = new Vaccinazione(
                         data,
                         _vaccino,
                         (String)record[BASE_DATI.VACCINAZIONI.DOSE],
                         (String)record[BASE_DATI.VACCINAZIONI.VIA_SOMMINISTRAZIONE],
                         (String)record[BASE_DATI.VACCINAZIONI.NOME_VACCINO],
                         (String)record[BASE_DATI.VACCINAZIONI.DITTA],
                         (String)record[BASE_DATI.VACCINAZIONI.LOTTO],
                         (DataOraria)record[BASE_DATI.VACCINAZIONI.SCADENZA],
                         (String)record[BASE_DATI.VACCINAZIONI.INADEMPIENZA]
                 );
            }

        }
        if(vaccinazione != null){
            listaVaccinazione.add(vaccinazione);
            FinestraPrincipaleController.listaVaccinazione.add(vaccinazione);
        }
    }
       
    
    
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                        FXMLLoader.load(getClass().getResource(Risorse.FXML.ANAMNESI_VACCINALE))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(AggiungiVacciniController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @FXML
    void aggiungiVaccino(ActionEvent event) {
        chiusuraSenzaSalvare(event);
    }

    
    @FXML
    void stampa(ActionEvent event) {
        if(finestraAnamnesi != null){
            finestraAnamnesi.stampaAnamnesi(event);
        }
    }
    
    @FXML
    private void inadempienzaVaccinazione(ActionEvent event) {
        if(finestraAnamnesi != null){
            Vaccinazione vaccinazione = tabellaVaccinazioni.getSelectionModel().getSelectedItem();
            finestraAnamnesi.inadempienza(vaccinazione);
        }
    }
    
    @FXML
    private void eliminaVaccino(ActionEvent event){
        Vaccinazione vaccinazione = tabellaVaccinazioni.getSelectionModel().getSelectedItem();
        if(vaccinazione != null){
            try {
                Programma.finestraConferma(
                        this,
                        String.format(
                                Risorse.Messaggi.DOMANDA_ELIMINA_VACCINO,
                                vaccinazione.getProfilassi() + " " + vaccinazione.getDose()
                        ),
                        () -> {
                            boolean ok = Programma.datiVaccinazioni.eliminaVaccinazione(
                                    vaccinazione.getProfilassi(),vaccinazione.getData(),
                                    militare.getCognome(),militare.getNome(), militare.getNascita()
                            );
                            if(ok){
                                FinestraPrincipaleController.listaVaccinazione.remove(vaccinazione);
                                listaVaccinazione.remove(vaccinazione);
                            }else{
                                try {
                                    Programma.finestraAvviso(this,Risorse.Messaggi.IMPOSSIBILE_ELIMINARE_VACCINO);
                                } catch (IOException ex1) {
                                    Logger.getLogger(AggiungiVacciniController.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        }
                );
            } catch (IOException ex) {
                Logger.getLogger(AggiungiVacciniController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    
    }
}
