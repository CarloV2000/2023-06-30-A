/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.exam;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.exam.model.CoppiaA;
import it.polito.tdp.exam.model.Model;
import it.polito.tdp.exam.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnDettagli"
    private Button btnDettagli; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbAnno"
    private ComboBox<Integer> cmbAnno; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSquadra"
    private ComboBox<String> cmbSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtTifosi"
    private TextField txtTifosi; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	String nomeTeam = this.cmbSquadra.getValue();
    	if(nomeTeam == null) {
    		this.txtResult.setText("Inserire un numero nel box Squadra");
    		return;
    	}
    	String s = model.creaGrafo(nomeTeam);
    	this.txtResult.setText(s);
    	
    }

    @FXML
    void handleDettagli(ActionEvent event) {
    	Integer anno = this.cmbAnno.getValue();
    	String s = "Archi partenti dall'anno "+anno+": \n";
    	if(anno == null) {
    		this.txtResult.setText("Inserire un numero nel box Anno");
    		return;
    	}
    	List<CoppiaA> res = model.getAllArchiAdiacenti(anno);
    	for(CoppiaA x : res) {
    		s += x.getA1()+"<->"+x.getA2()+" ("+x.getPeso()+")\n";
    	}
    	this.txtResult.setText(s);
    }

    @FXML
    void handleSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDettagli != null : "fx:id=\"btnDettagli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSquadra != null : "fx:id=\"cmbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTifosi != null : "fx:id=\"txtTifosi\" was not injected: check your FXML file 'Scene.fxml'.";
        for(int i = 1871; i <= 2019; i++) {
        	this.cmbAnno.getItems().add(i);
        }

    }

    public void setModel(Model model) {
        this.model = model;
        for(Team t : model.getAllTeams()) {
        	this.cmbSquadra.getItems().add(t.getName());
        }
    }

}
