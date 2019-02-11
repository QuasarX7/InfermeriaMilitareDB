package it.quasar_x7.infermeria.controllo;

import it.quasar_x7.infermeria.programma.Programma;
import it.quasar_x7.infermeria.programma.Risorse.Impostazioni;
import it.quasar_x7.infermeria.programma.Risorse.Messaggi;
import it.quasar_x7.infermeria.programma.Risorse.TabellaRifiuti;
import it.quasar_x7.java.utile.DataOraria;
import it.quasar_x7.java.utile.FilePDF;

import static it.quasar_x7.infermeria.controllo.VoceRifiutiController.rifiuti;
import static it.quasar_x7.infermeria.programma.Risorse.MESI;
import static it.quasar_x7.infermeria.programma.Risorse.FXML.FINESTRA_RIFIUTI;
import static it.quasar_x7.infermeria.programma.Risorse.Input.MEDICINALI_SCADUTI;
import static it.quasar_x7.infermeria.programma.Risorse.Input.RIFIUTI_PERICOLOSI;
import static it.quasar_x7.infermeria.programma.Risorse.Messaggi.INFO_SELEZIONA_DATA;
import static it.quasar_x7.java.utile.FilePDF.NERO;
import it.quasar_x7.javafx.CampoTesto;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Dr Domenico della Peruta
 */
public class FinestraAttivitaMensileRifiutiController implements Initializable {

    @FXML
    private TextField campoLtMedicinaliSmaltiti;

    @FXML
    private TextField campoNrMedicinaliProdotti;

    @FXML
    private TextField campoLtPericolosiPrecedenti;

    @FXML
    private TextField campoNrMedicinaliPrecedenti;

    @FXML
    private TextField campoLtPericolosiProdotti;

    @FXML
    private TextField campoLtPericolosiTotale;

    @FXML
    private ChoiceBox<String> menuMese;

    @FXML
    private TextField campoKgPericolosiSmaltiti;

    @FXML
    private TextField campoNrPericolosiPrecedenti;

    @FXML
    private TextField campoNrMedicinaliSmaltiti;

    @FXML
    private TextField campoLtMedicinaliTotale;

    @FXML
    private TextField campoNrPericolosiProdotti;

    @FXML
    private TextField campoNrMedicinaliTotale;

    @FXML
    private TextField campoLtPericolosiSmaltiti;

    @FXML
    private TextField campoKgPericolosiProdotti;

    @FXML
    private TextField campoKgPericolosiPrecedenti;

    @FXML
    private TextField campoNrPericolosiTotale;

    @FXML
    private TextField campoKgMedicinaliPrecedenti;

    @FXML
    private TextField campoKgMedicinaliProdotti;

    @FXML
    private TextField campoLtMedicinaliPrecedenti;

    @FXML
    private TextField campoKgPericolosiTotale;

    @FXML
    private TextField campoAnno;

    @FXML
    private TextField campoNrPericolosiSmaltiti;

    @FXML
    private TextField campoLtMedicinaliProdotti;

    @FXML
    private TextField campoKgMedicinaliTotale;

    @FXML
    private TextField campoKgMedicinaliSmaltiti;
    
    private Integer precedentiNrP;
    
    private Integer precedentiKgP;
    
    private Integer precedentiLtP;
    
    
    private Integer precedentiNrM;
    
    private Integer precedentiKgM;
    
    private Integer precedentiLtM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CampoTesto.soloNumeri(campoAnno, 4);
        campoAnno.setText(DataOraria.creaDataOggi().anno()+"");
        if(menuMese.getItems().isEmpty()){
            menuMese.getItems().add(MESI[0]);
            menuMese.getItems().add(MESI[1]);
            menuMese.getItems().add(MESI[2]);
            menuMese.getItems().add(MESI[3]);
            menuMese.getItems().add(MESI[4]);
            menuMese.getItems().add(MESI[5]);
            menuMese.getItems().add(MESI[6]);
            menuMese.getItems().add(MESI[7]);
            menuMese.getItems().add(MESI[8]);
            menuMese.getItems().add(MESI[9]);
            menuMese.getItems().add(MESI[10]);
            menuMese.getItems().add(MESI[11]);
            
        }
    }  
    
    @FXML
    private void chiusuraSenzaSalvare(ActionEvent event){
        try {
            Programma.vistaCorrente.setScene(
                    new Scene(
                            FXMLLoader.load(getClass().getResource(FINESTRA_RIFIUTI))
                    )
            );
            Programma.adattaFinestra();
            Programma.vistaCorrente.show();
        } catch (IOException ex) {
            Logger.getLogger(VoceRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rifiuti = null;//azzera valori caricati
    }
    
    @FXML
    private void cerca(ActionEvent event) {
        int anno = 0;
        try{
            anno = Integer.parseInt(campoAnno.getText());
        }catch(java.lang.NumberFormatException ex){}
        int mese = menuMese.getSelectionModel().getSelectedIndex()+1;
        if(mese > 0 && anno > 2000){
            int mesePrec = mese == 1 ? 12 : mese-1;
            int annoPrec = mese == 1 ? anno-1 : anno;
            DataOraria dataFineMesePrecedente = new DataOraria(DataOraria.giorniMese(mesePrec, annoPrec), mesePrec ,annoPrec);
            caricaCampiGiacenza(campoNrPericolosiPrecedenti, campoKgPericolosiPrecedenti, campoLtPericolosiPrecedenti, RIFIUTI_PERICOLOSI, dataFineMesePrecedente);
            caricaCampiGiacenza(campoNrMedicinaliPrecedenti, campoKgMedicinaliPrecedenti, campoLtMedicinaliPrecedenti, MEDICINALI_SCADUTI, dataFineMesePrecedente);
           
            DataOraria dataInizioMese = new DataOraria(1,mese,anno);
            DataOraria dataFineMese = new DataOraria(DataOraria.giorniMese(mese, anno),mese,anno);
            
            caricaCampiVersati(campoNrPericolosiSmaltiti, campoKgPericolosiSmaltiti, campoLtPericolosiSmaltiti, RIFIUTI_PERICOLOSI, dataInizioMese, dataFineMese);
            caricaCampiVersati(campoNrMedicinaliSmaltiti, campoKgMedicinaliSmaltiti, campoLtMedicinaliSmaltiti, MEDICINALI_SCADUTI, dataInizioMese, dataFineMese);
            
            caricaCampiGiacenza(campoNrPericolosiTotale, campoKgPericolosiTotale, campoLtPericolosiTotale, RIFIUTI_PERICOLOSI, dataFineMese);
            caricaCampiGiacenza(campoNrMedicinaliTotale, campoKgMedicinaliTotale, campoLtMedicinaliTotale, MEDICINALI_SCADUTI, dataFineMese);
           
            precedentiNrP = Integer.parseInt(campoNrPericolosiTotale.getText()) - 
                    Integer.parseInt(campoNrPericolosiPrecedenti.getText()) + 
                    Integer.parseInt(campoNrPericolosiSmaltiti.getText());
            campoNrPericolosiProdotti.setText(precedentiNrP.toString());
            
            precedentiKgP = Integer.parseInt(campoKgPericolosiTotale.getText()) - 
                    Integer.parseInt(campoKgPericolosiPrecedenti.getText()) + 
                    Integer.parseInt(campoKgPericolosiSmaltiti.getText());
            campoKgPericolosiProdotti.setText(precedentiKgP.toString());
            
            precedentiLtP = Integer.parseInt(campoLtPericolosiTotale.getText()) - 
                    Integer.parseInt(campoLtPericolosiPrecedenti.getText()) + 
                    Integer.parseInt(campoLtPericolosiSmaltiti.getText());
            campoLtPericolosiProdotti.setText(precedentiLtP.toString());
            
            precedentiNrM = Integer.parseInt(campoNrMedicinaliTotale.getText()) - 
                    Integer.parseInt(campoNrMedicinaliPrecedenti.getText()) + 
                    Integer.parseInt(campoNrMedicinaliSmaltiti.getText());
            campoNrMedicinaliProdotti.setText(precedentiNrM.toString());
            
            precedentiKgM = Integer.parseInt(campoKgMedicinaliTotale.getText()) - 
                    Integer.parseInt(campoKgMedicinaliPrecedenti.getText()) + 
                    Integer.parseInt(campoKgMedicinaliSmaltiti.getText());
            campoKgMedicinaliProdotti.setText(precedentiKgM.toString());
            
            precedentiLtM = Integer.parseInt(campoLtMedicinaliTotale.getText()) - 
                    Integer.parseInt(campoLtMedicinaliPrecedenti.getText()) + 
                    Integer.parseInt(campoLtMedicinaliSmaltiti.getText());
            campoLtMedicinaliProdotti.setText(precedentiLtM.toString());
            
        }else{
            try {
                Programma.finestraAvviso(this, INFO_SELEZIONA_DATA);
            } catch (IOException ex) {
                Logger.getLogger(FinestraAttivitaMensileRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void caricaCampiGiacenza(TextField nr,TextField kg, TextField lt, String codice,DataOraria data){
        ArrayList<Object[]> rifiuti = Programma.datiRifiuti.giacenzaMagazzino(codice, data);
            if(rifiuti != null)
                if(rifiuti.size() > 0){
                    nr.setText(rifiuti.get(0)[0].toString());
                    kg.setText(rifiuti.get(0)[1].toString());
                    lt.setText(rifiuti.get(0)[2].toString());
                }
    }
    
    private void caricaCampiVersati(TextField nr,TextField kg, TextField lt, String codice,DataOraria inizio,DataOraria fine){
        ArrayList<Object[]> rifiuti = Programma.datiRifiuti.versamentoRifiuti(codice, inizio,fine);
            if(rifiuti != null)
                if(rifiuti.size() > 0){
                    nr.setText(rifiuti.get(0)[0].toString());
                    kg.setText(rifiuti.get(0)[1].toString());
                    lt.setText(rifiuti.get(0)[2].toString());
                }
    }
    
    
    @FXML
    private void creaStampato(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(Programma.vistaCorrente);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf","*.PDF");
        fileChooser.getExtensionFilters().add(extFilter);
        
        if (file != null) {
            if(!file.getPath().contains(".pdf"))
                creaFilePDF(file.getPath()+".pdf");
            else
                creaFilePDF(file.getPath());
        }
    }
    
    private void creaFilePDF(String nomeFile) {
        try {
            ArrayList<String> listaResponsabili = new ArrayList<>();
            listaResponsabili.add(Programma.datiImpostazioni.valore(Impostazioni.KEY_RESPONSABILE_STOCCAGGIO_RIFIUTI));
            listaResponsabili.add(Programma.datiImpostazioni.valore(Impostazioni.KEY_VICE_RESPONSABILE_STOCCAGGIO_RIFIUTI));
             
            Programma.finestraInputMenu(
                    this, 
                    Messaggi.RESPONSABILE_RIFIUTI, 
                    listaResponsabili, 
                    (String risposta) -> {
                        FilePDF file = new FilePDF(nomeFile,FilePDF.Orientamento.ORIZZONTALE);
                        file.aggiungi(Programma.datiImpostazioni.valore(Impostazioni.TITOLO_REPARTO), FilePDF.TIMES_ROMAN, 16, FilePDF.GROSSETTO, FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
                        file.aggiungi(Programma.datiImpostazioni.valore(Impostazioni.TITOLO_UFFICIO), FilePDF.TIMES_ROMAN, 14, FilePDF.GROSSETTO, FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
                        file.aggiungi(" ", FilePDF.TIMES_ROMAN, 14, FilePDF.GROSSETTO, FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
                        file.aggiungi(
                                String.format(
                                        TabellaRifiuti.INTESTAZIONE_TABELLA,
                                        menuMese.getSelectionModel().getSelectedItem(),
                                        campoAnno.getText()
                                )
                                , FilePDF.TIMES_ROMAN, 12, FilePDF.GROSSETTO, FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);
                        file.aggiungi(" ", FilePDF.TIMES_ROMAN, 14, FilePDF.GROSSETTO, FilePDF.ALLINEAMENTO_CENTRO,FilePDF.NERO);

                        ArrayList<String> testo = new ArrayList<>();
                        testo.add(" ");
                        testo.add(TabellaRifiuti.INTESTAZIONE_CODICE_180103);
                        testo.add(TabellaRifiuti.INTESTAZIONE_CODICE_180109);
                        testo.add(TabellaRifiuti.INTESTAZIONE_STATO);
                        testo.add(TabellaRifiuti.STATO);
                        testo.add(" /// ");
                        testo.add(TabellaRifiuti.INTESTAZIONE_PERICOLOSITA);
                        testo.add(TabellaRifiuti.PERICOLOSITA);
                        testo.add(" /// ");

                        int indice = menuMese.getSelectionModel().getSelectedIndex();
                        String mesePrecedente = "???";
                        if(indice > 0){
                            mesePrecedente = MESI[indice - 1];
                        }else if(indice == 0){
                            mesePrecedente = MESI[11];
                        }



                        testo.add(String.format(TabellaRifiuti.INTESTAZIONE_MESE_PRECEDENTE,mesePrecedente));
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                campoNrPericolosiPrecedenti.getText(),
                                campoLtPericolosiPrecedenti.getText(),
                                campoKgPericolosiPrecedenti.getText()
                        )
                        );
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                campoNrMedicinaliPrecedenti.getText(),
                                campoLtMedicinaliPrecedenti.getText(),
                                campoKgMedicinaliPrecedenti.getText()
                        )
                        );

                        testo.add(String.format(TabellaRifiuti.INTESTAZIONE_SMALTIMENTO,menuMese.getSelectionModel().getSelectedItem()));
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                campoNrPericolosiSmaltiti.getText(),
                                campoLtPericolosiSmaltiti.getText(),
                                campoKgPericolosiSmaltiti.getText()
                        ));
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                campoNrMedicinaliSmaltiti.getText(),
                                campoLtMedicinaliSmaltiti.getText(),
                                campoKgMedicinaliSmaltiti.getText()
                        ));

                        testo.add(String.format(TabellaRifiuti.INTESTAZIONE_PRODOTTI,menuMese.getSelectionModel().getSelectedItem()));
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                precedentiNrP,precedentiLtP,precedentiKgP
                        ));
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                precedentiNrM,precedentiLtM,precedentiKgM
                        ));

                        testo.add(TabellaRifiuti.INTESTAZIONE_TOTALE);
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                campoNrPericolosiTotale.getText(),
                                campoLtPericolosiTotale.getText(),
                                campoKgPericolosiTotale.getText()
                        ));
                        testo.add(String.format(
                                TabellaRifiuti.QUANTITATIVO,
                                campoNrMedicinaliTotale.getText(),
                                campoLtMedicinaliTotale.getText(),
                                campoKgMedicinaliTotale.getText()
                        ));
                        file.aggiungiTabella(testo, new float[]{20,60,60}, FilePDF.TIMES_ROMAN, 10, FilePDF.NORMALE,true);

                        file.aggiungi(TabellaRifiuti.INTESTAZIONE_NOTE, FilePDF.TIMES_ROMAN, 12, FilePDF.NORMALE, FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
                        file.aggiungi(TabellaRifiuti.NOTE_1, FilePDF.TIMES_ROMAN, 10, FilePDF.NORMALE, FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.NERO);
                        int totalePericolosi = 0;
                        if(campoLtPericolosiTotale.getText().length() > 0){
                            totalePericolosi = Integer.parseInt(campoLtPericolosiTotale.getText());
                            if(totalePericolosi >= 200)
                                file.aggiungi(TabellaRifiuti.NOTE_2, FilePDF.TIMES_ROMAN, 10, FilePDF.GROSSETTO, FilePDF.ALLINEAMENTO_SINISTRA,FilePDF.ROSSO);
                        }
                        ArrayList<String> timbro = new ArrayList<>();
                        timbro.add(" ");
                        if(Programma.datiImpostazioni.valore(Impostazioni.KEY_RESPONSABILE_STOCCAGGIO_RIFIUTI).equals(risposta))
                            timbro.add("IL CAPO NUCLEO GESTIONE\nRIFIUTI SPECIALI INFERMERIA");
                        else
                            timbro.add("IL VICE CAPO NUCLEO GESTIONE\nRIFIUTI SPECIALI INFERMERIA");
                        timbro.add(" ");
                        timbro.add(risposta);
                        timbro.add(" ");
                        timbro.add("ORIGINALE FIRMATO AGLI ATTI");


                        file.aggiungiTabella(timbro, new float[]{200,100}, FilePDF.TIMES_ROMAN, 10, FilePDF.NORMALE,false);

                        timbro = new ArrayList<>();
                        timbro.add(" ");
                        timbro.add("D.Lgs. 07-03-2005, n.82\n"
                                + "Codice dell'amministrazione digitale\n"
                                + "Pubblicato nella Gazzetta Ufficiale 16 maggio 2005, n.112, S.O.\n"
                                + "Capo IV-trasmissione informatica dei documenti\n"
                                + "Art.45. Valore giuridico della trasmissione");

                        file.aggiungiTabella(timbro, new float[]{200,100}, FilePDF.TIMES_ROMAN, 6, FilePDF.NORMALE,false);

                        file.chiudi();
                    }
            );
            
        } catch (IOException ex) {
            Logger.getLogger(FinestraAttivitaMensileRifiutiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
