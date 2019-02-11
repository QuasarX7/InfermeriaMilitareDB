package it.quasar_x7.infermeria.programma;

/**
 * Classe che contiene tutte le stringhe e le costanti del programma, organizzate per tipo.
 * 
 * @author Dr Domenico della Peruta
 * @version 1.6.1 ultima modifica 16/04/16
 */
public class Risorse {

    public class Info{
        public static final String AUTORE = "AUTORE:  Dott. Domenico della PERUTA";
        public static final String VERSIONE = "VERSIONE:  4.0.1";
        public static final String DATA_MODIFICA = "DATA AGG.:  04/09/2018";
    }
    
    public class Conferma {
        public static final String CHIUSURA_FINESTRA_ANAMNESI_VACCINALE  = 
                                                "Sei sicuro di voler chiudere la finestra di "
                                                + "\"Anamnesi Vaccinale\" senza salvare i dati?";
        
        public static final String CHIUSURA_PROGRAMMA  = 
                                                "Vuoi veramente chiudere il programma?";
        public static final String APERTURA_FINESTRA_MODIFICA = "Stai per aprire la finestra per la modifica dei dati, continua?";
        
        public static final String SALVA_DATI = "Salva le modifiche?";
        
        

    }

    public class Seleziona{
        public static final int ULTIMA_VACC_MILITARE = 3001;
        public static final int ULTIMA_VACC_CIVILE   = 3002;
    }

    
    public class Etichette{
        public static final String IMC     = "IMC=";
        public static final String IGNOTO  = "?";
        public static final String BENVENUTO = "Benvenuto ";
        public static final String NUOVE_VACCINAZIONI = "Nuove VACCINAZIONI";
        public static final String STORIA_VACCINALE = "Vaccinazioni precedenti";
        public static final String VISITA_AMBULATORIALE = "Visita AMBULATORIALE";
        public static final String RICOVERO= "Ricovero";
        public static final String VERBALE_ML = "Verbale ML";
        public static final String VERBALE_GL = "Verbale GL";
        public static final String GML = "Visita di Incorporamento";
        public static final String MISURE = "Inserimento misure antropometriche";
        
    }
    
    public class FORMAT{
        public static final String STAMPA_MILITARE       = " [ %s %s %s %s ] ";
        public static final String STAMPA_MILITARE_S     = " [ %s %s %s ] ";
        public static final String STAMPA_DATA_ORA       = " [%s %s] ";
        
    }
    
    public class Visite{
        public static final String PRONTO_SOCCORSO     = "PRONTO SOCCORSO";
        public static final String CHIEDENTE_VISITA    = "CHIEDENTI VISITA";
        
    }
    
    public class Grafico{
        public class Attivita{
            public static final String INCORPORAMENTO     = "incorporamento";
            public static final String PRONTO_SOCCORSO    = "pronto soccorso";
            public static final String CHIEDENTI_VISITA   = "chiedenti visita";
            public static final String VACCINAZIONI       = "vaccinazioni";
            public static final String PRETICHE_ML        = "verbali ML";
        
        }
    }
    
    

    public class Stato{

        public static final int NUOVO_MILITARE   = 10000;
        public static final int MISURE           = 10001;
        public static final int GML              = 10002;
        public static final int VACCINAZIONI     = 10003;
        public static final int STORIA_VACCINALE = 10004;
        public static final int INFO             = 10005;
        public static final int VISITA_MEDICA    = 10006;
        public static final int VERBALE_MOD_ML   = 10007;
        public static final int VERBALE_MOD_GL   = 10008;
        public static final int RICOVERO         = 10009;
        public static final int DEBUG            = 10010;
    }
    
    public class Attività{

        public static final String VACCINAZIONI      = "VACCINAZIONI";
        public static final String RICOVERI          = "RICOVERI";
        public static final String CHIEDENTI_VISITA  = "CHIEDENTI VISITA";
        public static final String PRONTO_SOCCORSO   = "PRONTO SOCCORSO";
        public static final String VERBALI_ML        = "VERBALI ML";
        public static final String VERBALI_GL        = "VERBALI GL";
        public static final String INCORPORATI       = "MILITARI INCORPORATI";
        
    }
    
    public class Colore{

        public static final String NERO         = "-fx-text-fill: black;";
        public static final String ARANCIONE    = "-fx-text-fill: orange;";
        public static final String ROSSO        = "-fx-text-fill: #FF0000;";
        public static final String BLU          = "-fx-text-fill: #00AFFF;";
        public static final String GRIGIO       = "-fx-text-fill: grey;";
        public static final String ROSSOSCURO   = "-fx-text-fill: darkred;";
        public static final String VERDESCURO   = "-fx-text-fill: darkgreen;";
        
    }
    
    public class ColoreTabella{
        public static final String GRIGIOCHIARO = "-fx-control-inner-background: #D3D3D3;";
        public static final String GIALLO = "-fx-control-inner-background:  yellow;";
        public static final String VERDEGIALLO = "-fx-control-inner-background: greenyellow;";
        public static final String BIANCO = "-fx-control-inner-background: #FFFFFF;";
        public static final String ROSSOCHIARO = "-fx-control-inner-background: #FFCCCC;";
        public static final String VERDECHIARO = "-fx-control-inner-background: #CCFFCC;";
    }
    
    public class MascheraCampi{
        public static final String  DATA        = "**/**/****";
        public static final char    CARATTERE   = '*';
    }
    public class Impostazioni{
        public static final String KEY_ANAMNESI_FAMILIARE   = "dati_anamnesi_familiare";
        public static final String KEY_ANAMNESI_REMOTA      = "dati_anamnesi_remota";
        public static final String KEY_ANAMNESI_PROSSIMA    = "dati_anamnesi_prossima";
        public static final String TIPO_ANAMNESI            = "ANAMNESI";
        
        public static final String KEY_MEDICO_VACCINATORE   = "dati_vaccinazione_45";
        
        public static final String KEY_CP       = "CP_";
        public static final String TIPO_CP      = "COMPAGNIA";
        public static final String KEY_GRADO    = "GRADO_";
        public static final String TIPO_GRADO   = "GRADI";
        public static final String KEY_CORSO    = "CORSO_";
        public static final String TIPO_CORSO   = "CORSI";
        public static final String TITOLO_REPARTO = "REGGIMENTO";
        public static final String TITOLO_UFFICIO = "UFFICIO";
        public static final String SEDE         = "LOCALITA";
        
        public static final String TIPO_RIFIUTI = "GESTIONE_RIFIUTI";
        
        public static final String KEY_RESPONSABILE_PRODUZIONE_RIFIUTI = "resp. prod. rifiuti";
        public static final String KEY_VICE_RESPONSABILE_PRODUZIONE_RIFIUTI = "vice resp. prod. rifiuti";

        public static final String KEY_RESPONSABILE_STOCCAGGIO_RIFIUTI = "resp. stocc. rif.";
        public static final String KEY_VICE_RESPONSABILE_STOCCAGGIO_RIFIUTI = "vice resp. stocc. rifiuti";

    }
    public class Modello{
        public static final String CP       = "cp";
        public static final String GRADO    = "grado";
        public static final String CORSO    = "corso";
        public static final String COGNOME  = "cognome";
        public static final String NOME     = "nome";
        public static final String NASCITA  = "nascita";
        public static final String VACCINO  = "vaccino";
        public static final String VIA_SOMM = "somministrazione";
        public static final String DITTA_VACC = "ditta";
        public static final String LOTTO_VACC = "lotto";
        public static final String SCADENZA   = "scadenza";
        public static final String MALATTIA_PREGRESSA       = "pregressa";
        public static final String MALATIA_PREGRESSA_DOC    = "pregressaDocumentata";
        public static final String ULTIMA_VACC_CIVILE       = "civile";
        public static final String ULTIMA_VACC_MILITARE     = "militare";
        public static final String TIPO             = "tipo";
        public static final String TIPO_DOSE        = "dose";
        public static final String DATA             = "data";
        public static final String PROFILASSI       = "profilassi";
        public static final String INADEMPIENZA     = "inadempienza";
        public static final String PML              = "PML";
        public static final String DIAGNOSI         = "diagnosi";
        public static final String INIZIO           = "inizio";
        public static final String FINE             = "fine";
        public static final String INFO             = "info";
        public static final String MILITARI         = "militari";
        public static final String SESSO            = "sesso";
        public static final String ETA              = "età";
        public static final String N_VACCINI        = "numero";
        
        public static final String PROTOCOLLO       = "protocollo";
        public static final String PRODUZIONE       = "dataProduzione";
        public static final String CODICE           = "codice";
        public static final String CONTENITORE      = "contenitori";
        public static final String QUANTITA         = "quantità";
        public static final String VOLUME           = "volume";
        public static final String MEDICO           = "medico";
        public static final String RESPONSABILE     = "responsabile";
        public static final String VERSAMENTO       = "dataVerbale";
        public static final String VERB_VERSAMENTO  = "verbale";
        
        
    }
    public class Input {
        public static final String SESSO_F = "F";
        public static final String SESSO_M = "M";
        
        public static final String SCUOLA_OBBLIGO       = "Obbligo";
        public static final String SCUOLA_SUPERIORI     = "Superiori";
        public static final String SCUOLA_UNIVERSITA    = "Università";
        
        public static final String DOC_CARTA_IDENTITA   = "Carta d'identità";
        public static final String DOC_PASSAPORTO       = "Passaporto";
        public static final String DOC_PATENTE_GUIDA    = "Patente di guida";
        public static final String DOC_PATENTE_NAUTICA  = "Patente nautica";
        public static final String DOC_LIBRETTO_PENSIONE = "Libretto di pensione";
        public static final String DOC_PORTO_ARMI       = "Licenza di porto d'armi";
        public static final String DOC_TESSERA_AT       = "Tessere di riconoscimento AT";
        public static final String DOC_TESSERA_BT       = "Tessere di riconoscimento BT";
        public static final String CORSO_QP             = "Q.P.";
        public static final String CORSO_SEPARATORE     = "/";
        
        public static final String DOSE_CB1             = "CB1";
        public static final String DOSE_CB2             = "CB2";
        public static final String DOSE_CB3             = "CB3";
        public static final String DOSE_CB4             = "CB4";
        public static final String DOSE_B               = "B";
        public static final String DOSE_R               = "R";
        public static final String DOSE_1D              = "1D";
        
        public static final String SUPERIORI            ="Superiori";
        public static final String OBBLIGO              ="Obbligo";
        public static final String UNIVERSITA           ="Università";
        public static final String SI = "SI";
        public static final String NO = "NO";
        
        public static final String D = "D";
        public static final String S = "S";
        
        public static final String X = "X";
        
        public static final String POSITIVO = "positivo";
        public static final String NEGATIVO = "negativo";
        
        public static final String REGOLARE = "regolare";
        public static final String IRREGOLARE = "irregolare";
        
        public static final String INCORSO = "incorso";
        public static final String NON_INCORSO = "non incorso";
        public static final String NON_NOTO = "non noto";
        
        public static final String IDONEO = "IDONEO";
        public static final String NON_IDONEO = "NON IDONEO";
        public static final String IDONEA = "IDONEA";
        public static final String NON_IDONEA = "NON IDONEA";
        public static final String TEMPORANEAMENTE_NON_IDONEO = "TEMPORANEAMENTE NON IDONEO";
        public static final String TEMPORANEAMENTE_NON_IDONEA = "TEMPORANEAMENTE NON IDONEA";
        public static final String VIA_SOMM_SC = "SC";
        public static final String VIA_SOMM_IM = "IM";
        public static final String VIA_SOMM_OS = "OS";
        
        public static final String RIFIUTI_PERICOLOSI = "180103*";
        public static final String MEDICINALI_SCADUTI = "180109";
        
    }
    public class Codifica {
        static public final String KEY="infermeria";
    }
    
    public class Immagine{
        static private final String PATH = "/it/difesa/esercito/rav17/infermeria/immagini/";
        
        static public final String ATTESA       = PATH + "infermiera_attesa.gif";
        static public final String MODIFICA     = PATH + "impostazioni.png";
        public static final String VACCINAZIONE = PATH + "infermiera_vaccinatrice.gif";
        public static final String SCRIVERE     = PATH + "scrivere.gif";
        public static final String SIRINGA      = PATH + "siringa.png";
    }
    
    public class FXML{
        static private final String PATH = "/it/difesa/esercito/rav17/infermeria/vista/";
        
        static public final String LOGIN                = PATH + "Login.fxml";
        static public final String CREA_FILE_IP         = PATH + "CreaFileIP.fxml";
        public static final String FINESTRA_PRINCIPALE  = PATH + "FinestraPrincipale.fxml";
        public static final String MODIFICA_PASSWORD    = PATH + "ModificaPassword.fxml";
        public static final String FINESTRA_LISTA_CP    = PATH + "ListaCp.fxml";
        public static final String FINESTRA_LISTA_GRADO = PATH + "ListaGrado.fxml";
        public static final String FINESTRA_LISTA_CORSO = PATH + "ListaCorso.fxml";
        public static final String FINESTRA_IMPOSTAZIONI_ANAMNESI = PATH + "ImpostazioneAnamnesiNuovoMilitare.fxml";
        public static final String CSS                  = PATH + "stile.css";
        public static final String MODIFICA_VACCINAZIONE_PASS = PATH + "ModificaVoceStoriaVaccinale.fxml";
        public static final String ANAMNESI_VACCINALE   = PATH + "AnamnesiVaccinale.fxml";
        public static final String FINESTRA_CONFERMA    = PATH + "FinestraConferma.fxml";
        public static final String FINESTRA_RICERCA     = PATH + "FinestraRicercaData.fxml";
        public static final String FINESTRA_AVVISO      = PATH + "FinestraAvviso.fxml";
        public static final String FINESTRA_ATTIVITA_VACCINALE    = PATH + "AttivitàVaccinale.fxml";
        public static final String FINESTRA_AGGIUNGI_VACCINO      = PATH + "AggiungiVaccini.fxml";
        public static final String FINESTRA_AGGIUNGI_VACCINO_GENERICO      = PATH + "AggiungiVaccinoGenerico.fxml";
        public static final String  FINESTRA_INPUT_MENU            = PATH + "FinestraInputMenu.fxml";
        public static final String FINESTRA_IMPOSTAZIONI_VACCINALI = PATH + "FinestraImpostazioni.fxml";
        public static final String FINESTRA_RIFIUTI = PATH + "FinestraRifiuti.fxml";
        public static final String FINESTRA_VOCE_RIFIUTI = PATH + "VoceRifiuti.fxml";
        public static final String FINESTRA_MENSILITA_RIFIUTI = PATH + "FinestraAttivitaMensileRifiuti.fxml";
        public static final String FINESTRA_IMPOSTAZIONI_RIFIUTI = PATH + "FinestraResponsabiliRifiuti.fxml";
        public static final String FINESTRA_IMPOSTAZIONI_MEDICO = PATH + "FinestraMedici.fxml";
    
    }
    public class FileEsterno{
        static public final String IP_SERVER_MYSQL = "serverIP.xml";
    }
    public class KeyXML{
        static public final String IP   = "IP";
        static public final String INFO = "ip o nome server host MySQL";
    }
    public class Messaggi{
        static public final String NESSUN_VALORE_INSERITO           = "Nessun valore inserito!";
        static public final String ERRORE_SALVATAGGIO_FILE          = "Errore salvataggio file '" + Risorse.FileEsterno.IP_SERVER_MYSQL + "'";
        static public final String ERRORE_NESSUN_UTENTE             = "Errore: nessun utente presente!";
        static public final String ERRORE_APERTURA_FILE             = "Errore apertura file '" + Risorse.FileEsterno.IP_SERVER_MYSQL + "'";
        static public final String ERRORE_NESSUN_UTENTE_SELEZIONATO = "Errore: nessun utente selezionato!";
        public static final String ERRORE_PASSWORD                  = "Password errata!";
        public static final String OK                               = "OK!";
        public static final String ERRORE_CARICAMENTO_FINESTRA      = "Errore caricamento finestra...";
        public static final String ERRORE_NUOVA_PASSWORD            = "Errore nuova password!";
        public static final String ERRORE_VECCHIA_PASSWORD          = "Errore vecchia password!";
        public static final String NESSUN_VALORE_SELEZIONATO        = "Nessun valore selezionato!";
        public static final String ERRORE_INPUT_OVERFLOW            = "Errore: testo di dimensioni eccessive ";
        public static final String ERRORE_DATA                      = "Data non valida!";
        public static final String ERRORE_CAMPI_PRINCIPALI          = "Errore: campi fondamentali vuoti!";
        public static final String AGGIUNTO_NUOVO_RECORD            = "Aggiunto nuovo record ";
        public static final String INFO_CAMPI_NON_COMPLETI          = "Campi non completi...";
        public static final String INFO_ULTIMA_MODIFICA_DATI        = "Ultima modifica: ";
        public static final String INFO_UTENTE_SCONOSCIUTO          = " utente sconosciuto ";
        public static final String INFO_MODIFICA_SCONOSCIUTO        = "";
        public static final String RECORD_MODIFICATO                = "Record modificato ";
        public static final String TITOLO_AVVISO                    = "Avviso";
        public static final String IMPOSSIBILE_ELIMINARE_MILITARE   = "Impossibile eliminare un militare \ngià incorporato e visitato da un medico!";
        public static final String DOMANDA_ELIMINA_MILITARE         = "Elimina '%s %s %s' ?";
        public static final String DOMANDA_ELIMINA_VACCINO          =  "Vuoi veramente eliminare la vaccinazione selezionata '%s' ?";
        public static final String DOMANDA_ELIMINA_VISITA_MEDICA    =  "Vuoi veramente eliminare la visita medica del '%s' ?";
                                
        public static final String ERRORE_MILITARE_NON_DEFINITO     = "Errore: militare non definito!";
        public static final String ERRORE_MILITARE_MILITARE_GIA_PRESENTE = "Imppossibile aggiungere il militare,\n controlla che non sia già presente!";
        public static final String ERRORE_SALVATAGGIO_DATI          = "Errore salvatagio dati...";
        public static final String ERRORE_MODIFICA_DATI             = "Errore: impossibile modificare i dati...";
        public static final String IMPOSSIBILE_ELIMINARE_VACCINO    = "Impossibile eliminare la vaccinazione...";
        public static final String IMPOSSIBILE_ELIMINARE_VISITA_MEDICA = "Impossibile eliminare la visita medica.";
        public static final String IMPOSSIBILE_ELIMINARE_VERBALE_ML = "Impossibile eliminare il verbale ML.";
        public static final String IMPOSSIBILE_ELIMINARE_VERBALE_GL = "Impossibile eliminare il verbale GL.";
        public static final String IMPOSSIBILE_ELIMINARE_RICOVERO   = "Impossibile eliminare il ricovero.";
        
        public static final String ERRORE_AUTORIZZAZIONE            = "Utente NON autorizzato!!!!";
        public static final String ESISTE_VISITA_MEDICA             = "Esiste già un verbale in data di oggi.";
    
        public static final String DOMANDA_ELIMINA_RICOVERO         = "Vuoi veramente eliminare il ricovero del '%s' ?";
        public static final String DOMANDA_ELIMINA_VERBALE_ML       = "Vuoi veramente eliminare il verbale ML del '%s' ?";
        public static final String DOMANDA_ELIMINA_VERBALE_GL       = "Vuoi veramente eliminare il verbale ML del '%s' ?";
        
        public static final String DOMANDA_ELIMINA_STRALCIO         = "Vuoi veramente eliminare lo stralcio vaccinale del %s ?";
        
        public static final String ELIMINA_VOCE = "Vuoi veramente eliminare la voce selezionata?";
        public static final String INFO_SELEZIONA_DATA = "Seleziona correttamente il mese e l'anno";
        
        public static final String RESPONSABILE_RIFIUTI = "Seleziona il responsabile della periodica.";
        
    }
    
    public static String[] INADEMPIENZA_VACCINAZIONE = new String[]{
        "ASSENZA DELL'INTERESSATO",
        "DINIEGO",
        "ASSENZA DOCUMENTAZIONE VACC.",
        "ASSENZA VACCINO",
        "TEMP. NON IDONEO",
        "NON IDONEO"
    };
    
    public class DoseVaccino{
        static public final String MENINGITE       ="MENINGITE";
        static public final String EPATITE_A       ="EPATITE A";
        static public final String EPATITE_A_CB2   ="EPATITE A ";
        static public final String EPATITE_B       ="EPATITE B";
        static public final String EPATITE_B_CB2   ="EPATITE B ";
        static public final String EPATITE_B_CB3   ="EPATITE B  ";
        static public final String EPATITE_B_B     ="EPATITE B   ";
        static public final String EPATITE_AB      ="EPATITE A+B";
        static public final String EPATITE_AB_CB2  ="EPATITE A+B ";
        static public final String EPATITE_AB_CB3  ="EPATITE A+B  ";
        static public final String MPR             ="Morbillo/Parotite/Rosolia";
        static public final String MPR_CB2         ="Morbillo/Parotite/Rosolia ";
        static public final String VARICELLA       ="VARICELLA";
        static public final String VARICELLA_CB2   ="VARICELLA ";
        static public final String TD              ="Tetano/Difterite";
        static public final String TDP             ="Tetano/Difterite/Polio";
        static public final String POLIO           ="POLIO";
    }
    
    public class Profilassi{
        static public final String VAIOLO       = "VAIOLO";
        static public final String TETANO       = "TETANO";
        static public final String DIFTERITE    = "DIFTERITE";
        static public final String POLIO        = "POLIO";
        static public final String MORBILLO     = "MORBILLO";
        static public final String ROSOLIA      = "ROSOLIA";
        static public final String PAROTITE     = "PAROTITE";
        static public final String EPATITE_B    = "EPATITE B";
        static public final String EPATITE_A    = "EPATITE A";
        static public final String VARICELLA    = "VARICELLA";
        static public final String INFLUENZA    = "INFLUENZA";
        static public final String MENINGITE    = "MENINGITE";
        static public final String TIFO         = "FEBB. TIFO";
        static public final String COLERA       = "COLERA";
        static public final String FEBB_GIALLA  = "FEBB. GIALLA";
        static public final String RABBIA       = "RABBIA";
        static public final String ENC_ZECC     = "ENC. ZECC.";
        static public final String ENC_GIAP     = "ENC. GIAP.";
    }
    
    
    
    public class Vaccinazioni{
        static public final int TETANO_DIFTERITE_POLIO  = 0;
        static public final int TETANO_DIFTERITE        = 1;
        static public final int TETANO                  = 2;
        static public final int DIFTERITE               = 3;
        static public final int POLIO                   = 4;
        static public final int VAIOLO                  = 5;
        static public final int MORBILLO_ROSOLIA_PAROTITE= 6;
        static public final int MORBILLO                = 7;
        static public final int ROSOLIA                 = 8;
        static public final int PAROTITE                = 9;
        static public final int EPATITE_A_B             = 10;
        static public final int EPATITE_A               = 11;
        static public final int EPATITE_B               = 12;
        static public final int VARICELLA               = 13;
        static public final int INFLUENZA               = 14;
        static public final int MENINGITE_POLISACC      = 15;
        static public final int MENINGITE_CONIUGATO     = 16;
        static public final int TIFO_ORALE              = 17;
        static public final int TIFO_PARENTALE          = 18;
        static public final int TIFO_EPATITE_A          = 19;
        static public final int COLERA                  = 20;
        static public final int FEBB_GIALLA             = 21;
        static public final int RABBIA                  = 22;
        static public final int ENCEFALITE_ZECCHE       = 23;
        static public final int ENCEFALITE_GIAPP        = 24;
    } 
    
    
    
    
    
    public static String[] VACCINAZIONI = new String[]{
        "TETANO-DIFTERICO-POLIO",
        "TETANO-DIFTERICO",
        "TETANO",
        "DIFTERITE",
        "POLIO",
        "VAIOLO",//5
        "MORBILLO-PAROTITE-ROSOLIA",
        "MORBILLO",
        "ROSOLIA",
        "PAROTITE",
        "EPATITE A+B",//10
        "EPATITE A",
        "EPATITE B",
        "VARICELLA",
        "INFLUENZA",
        "MENINGITE polisaccaridico",//15
        "MENINGITE coniugato",
        "FEBBRE TIFOIDE orale",
        "FEBBRE TIFOIDE parenterale",
        "EPATITE A + FEBBRE TIFOIDE",
        "COLERA",//20
        "FEBBRE GIALLA",
        "RABBIA",
        "ENCEFALITE DA ZECCHE",
        "ENCEFALITE GIAPPONESE",//24
    };
    
    public static String[] SIGLA_VACCINAZIONI = new String[]{
        "TDP",
        "TD",
        "T",
        "D",
        "PLV",
        "SPX",//5
        "MPR",
        "M",
        "R",
        "P",
        "HAB",//10
        "HAV",
        "HBV",
        "VZV",
        "FLU",
        "MEN",//15
        "MEC",
        "TFO",
        "TFP",
        "HAT",
        "   ",//20 colera???
        "YFV",
        "LYS",
        "TBE",
        "JEV",//24
    };
    
    public static String[] MESI = new String[]{
        "GENNAIO",
        "FEBBRAIO",
        "MARZO",
        "APRILE",
        "MAGGIO",
        "GIUGNO",
        "LUGLIO",
        "AGOSTO",
        "SETTEMBRE",
        "OTTOBRE",
        "NOVEMBRE",
        "DICEMBRE"
    };
    
    public class TabellaRifiuti{
        static public final String INTESTAZIONE_CODICE_180103  = 
                "Rifiuti che devono essere raccolti e smaltiti applicando precauzioni per evitare infezioni\nCodice 180103*";
        public static final String INTESTAZIONE_CODICE_180109 ="Medicinali diversi da quelli di cui alla voce 180108\nCodice 180109";
        public static final String INTESTAZIONE_STATO = "Stato fisico";
        public static final String STATO = "2\n(Slolido non polverulento)";
        public static final String INTESTAZIONE_PERICOLOSITA = "Pericolosità";
        public static final String PERICOLOSITA = "H9";
        public static final String INTESTAZIONE_MESE_PRECEDENTE = "Riporto mese precedente\n%s";
        public static final String QUANTITATIVO = "n. %s contenitori da litri 60 (tot. litri %s)\nper un peso complessivo di circa kg %s";
        public static final String INTESTAZIONE_SMALTIMENTO = "Rifiuti smaltiti\nnel mese di\n%s";
        public static final String INTESTAZIONE_PRODOTTI = "Rifiuti prodotti\nnel mese di\n%s";
        public static final String INTESTAZIONE_TOTALE = "Totale\nRimanenza";
        public static final String INTESTAZIONE_TABELLA = "CONSUNTIVO RIFIUTI SPECIALI PERICOLOSI E NON DELL'INFERMERIA MESE DI %s %s";
        public static final String INTESTAZIONE_NOTE = "\t\tNOTE:";
        public static final String NOTE_1 = "\t\t• Si precisa \"di circa\" in quanto vi è la presenza di liquidi volatili (es. alcool);";
        public static final String NOTE_2 = "\t\t• Sono stati superati i limiti di stoccaggio previsti dalla normativa vigente.";
    
    };
    
    public class Stampato{
        public class StoriaVaccinale{
            static public final String ANAMNESI_INDIVIDUALE         = "ANAMNESI INDIVIDUALE";
            public static final String DATI_INFETTIVOLOGICO_LOCALI  = "DATI INFETTIVOLOGICO-LOCALI";
            public static final String GRADO                        = "grado:";
            public static final String COGNOME                      = "cognome:";
            public static final String NOME                         = "nome:";
            public static final String DATA_NASCITA                 = "data di nascita:";
            public static final String LUOGO_NASCITA                = "luogo di nascita:";
            public static final String SCOLARITA                    = "1. SCOLARITA'";
            public static final String STATO_IMMUNITARIO            = "2. STATO IMMUNITARIO";
            public static final String VACCINO                      = "<b>vaccino</b>";
            public static final String PREGRESSA                    = "<b>pregressa</b>";
            public static final String PREGRESSA_DOC                = "<b>pregressa doc.</b>";
            public static final String ULTIMA_CIVILE                = "<b>ultima vacc. civile</b>";
            public static final String ULTIMA_MILITARE              = "<b>ultima vacc. militare</b>";
            public static final String DOSE                         = "<b>dose</b>";
            public static final String TIPO_VACC                    = "<b>tipo vacc.</b>";
            public static final String DATA_VACC                    = "<b>data</b>";
            public static final String FIRMA_INTERESSATO            = "<br><br>firma dell'interessato______________________________";
            public static final String FIRMA_MEDICO                 = "Sanitario Vaccinatore";
        }
    }

    
}
