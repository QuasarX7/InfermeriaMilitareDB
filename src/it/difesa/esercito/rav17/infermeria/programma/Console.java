package it.difesa.esercito.rav17.infermeria.programma;

import it.quasar_x7.java.utile.DataOraria;
import java.io.IOException;
import java.io.OutputStream;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Dott. Domenico della Peruta
 */
public class Console extends OutputStream{
    private final TextArea areaDebug;

    public Console(TextArea areaDebug) {
        this.areaDebug = areaDebug;
    }

    @Override
    public void write(int b) throws IOException {
        Platform.runLater(
                () -> {
                    if(areaDebug.getText().length() > 30000){
                        areaDebug.setText(
                                String.format(
                                        "### [clear %s] ##############\n",
                                        DataOraria.creaDataOraAdesso().stampaOrario()
                                )
                        );
                    }
                    areaDebug.appendText((char)b+"");
                }
        );
    }
    
    
}
