/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class VentanaController implements Initializable {
    
    
    double numeroRedondeado;
    double porcentajeTeorico;
    double porcentajePractico;
    double notaPrimerParcial;
    double notaSegundoParcial;
    double notaPractico;
    double notaMejoramiento;

    @FXML
    private Button ButtonCalcular;
    @FXML
    private Button buttonReset;
    @FXML
    private TextField contenedorTeorico;
    @FXML
    private TextField contenedorP;
    @FXML
    private TextField contenedorS;
    @FXML
    private TextField contenedorPractico;
    @FXML
    private TextField contenedorM;
    
    @FXML
    private Label labelPractico;
    @FXML
    private Label labelMejora;
    @FXML
    private Label labelAlertTeorico;
    @FXML
    private Label labelPrimerP;
    @FXML
    private Label labelSegundoP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 
     
    @FXML
    private void calcular(ActionEvent event) {
        double porcentajeTeorico = validar(labelAlertTeorico, contenedorTeorico)/100;
        double notaPrimerParcial = validar(labelPrimerP, contenedorP);
        double notaSegundoParcial = validar(labelSegundoP, contenedorS);
        double notaPractico = validar(labelPractico, contenedorPractico);
        double notaMejoramiento = validar(labelMejora, contenedorM);
        double porcentajePractico = 1 - porcentajeTeorico;
        
        List<Double> parciales = new ArrayList<>();
        parciales.add(notaPrimerParcial);
        parciales.add(notaSegundoParcial);
        
        double menorParcial = Collections.min(parciales);
        
        if(notaMejoramiento > menorParcial){
            parciales.remove(menorParcial);
            parciales.add(notaMejoramiento);
            double primerP = parciales.get(0);
            double segundoP = parciales.get(1);
            notaPrimerParcial = primerP;
            notaSegundoParcial = segundoP; 
        }
        
        
        double puntajeTotal = puntajeFinal(notaPrimerParcial, notaSegundoParcial, porcentajeTeorico, notaPractico, porcentajePractico);
        
        
        labelPractico.setText("");
        labelMejora.setText("");
        labelAlertTeorico.setText("");
        labelPrimerP.setText("");
        labelSegundoP.setText("");
        
        if(puntajeTotal>=60){
            Alert windowAlert = new Alert(AlertType.INFORMATION);
            windowAlert.setTitle("Calculadora ESPOL");
            windowAlert.setHeaderText("Calificaciones");
            windowAlert.setContentText("PUNTAJE: "+puntajeTotal+"\nHAS APROBADO!\n"+"BUEN TRABAJO!");
            windowAlert.showAndWait();
        }
        else{
            Alert windowAlert = new Alert(AlertType.INFORMATION);
            windowAlert.setTitle("Calculadora ESPOL");
            windowAlert.setHeaderText("Calificaciones");
            windowAlert.setContentText("PUNTAJE: "+puntajeTotal+"\nHAS REPROBADO");
            windowAlert.showAndWait();
        
        }
    }
    
    
    
    public double puntajeFinal(double primerP, double segundoP, double percenTeorico, double practico, double percenPractico){
    
        double puntajeTeorico = ((primerP + segundoP)/2)*percenTeorico;  
        double puntajePractico= practico*percenPractico;
        double puntajeTotal= puntajeTeorico + puntajePractico;
        return puntajeTotal;
    }

    public double validar(Label label, TextField contenedor){   
         try{
            String contenido = contenedor.getText();
            double num =Double.parseDouble(contenido);
            int parteEntera = (int) num; 
            //para redondear
            double numeroRedondeado= Math.round(num * 100.0) / 100.0;
            if (parteEntera >= 0 && parteEntera <= 100 && numeroRedondeado<=100.00) {
                label.setText(null);
                return numeroRedondeado;
                
            }else {
                label.setText("Numero fuera de rango");
                return 0;
                    
            }
        }catch(NumberFormatException ex){
            label.setText("El contenido no es un numero");
            return 0;
        
        }
    
    }
    
  
    
    @FXML
    private void limpiar(ActionEvent event) {
        contenedorTeorico.clear();
        contenedorP.clear();
        contenedorS.clear();
        contenedorPractico.clear();
        contenedorM.clear();
        labelPractico.setText("");
        labelMejora.setText("");
        labelAlertTeorico.setText("");
        labelPrimerP.setText("");
        labelSegundoP.setText("");
    }

    @FXML
    private void validarTeorico(KeyEvent event) {
        double porcentajeTeorico = validar(labelAlertTeorico, contenedorTeorico)/100;
    }

    @FXML
    private void validarPrimerP(KeyEvent event) {
        double notaPrimerParcial = validar(labelPrimerP, contenedorP);
    }

    @FXML
    private void validarSegundoP(KeyEvent event) {
        double notaSegundoParcial = validar(labelSegundoP, contenedorS);
    }

    @FXML
    private void validarPractico(KeyEvent event) {
        double notaPractico = validar(labelPractico, contenedorPractico);
    }

    @FXML
    private void validarMejora(KeyEvent event) {
        double notaMejoramiento = validar(labelMejora, contenedorM);
    }

    
}
