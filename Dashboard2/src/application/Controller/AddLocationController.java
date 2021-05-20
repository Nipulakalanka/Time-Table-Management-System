package application.Controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Model.Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddLocationController implements Initializable {
	@FXML
	private TextField tfBname;
	@FXML
	private TextField tfRname;
	
	@FXML
	private TextField tfCap;
	@FXML
	private Button savebtn;
	@FXML
	private AnchorPane tab;
	
	@FXML
    private RadioButton lechall;

    @FXML
    private ToggleGroup mygroup;

    @FXML
    private RadioButton lab;
	
	String query =null;
	/*
	 * ResultSet resultsset =null; PreparedStatement preparedStatement;
	 * 
	 */
	

	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement preparedStatement =null;
	Location location;
	

	/*
	 * public final static String cloudURL =
	 * "jdbc:mysql://43.250.240.213:3306/student_ms?autoReconnect=true&useSSL=false&verifyServerCertificate=false";
	 * public final static String cloudUser = "itpmUser"; public final static String
	 * cloudUserPW = "itpm@1234";
	 */
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	//database connection
	public Connection getConnection() {
		Connection con;
		try{
			con =DriverManager.getConnection("jdbc:mysql://itpmserver2021.mysql.database.azure.com:3306/itpmnew?autoReconnect=true&useSSL=false&verifyServerCertificate=false", "itpmUser@itpmserver2021","itpm@1234");
			return con;
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
			return null;
			
			
		}
	}
	
	//Click the save button for insert the values
	@FXML
	public void Save(MouseEvent event) {
		String bname = tfBname.getText();
		String rname =tfRname.getText();
		String rtype =getRadioButton();
		String cap=tfCap.getText();
		
		if(bname.isEmpty() ||rname.isEmpty()||rtype.isEmpty()||cap.isEmpty()) {
			Alert alert =new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Fill All Data");
			alert.showAndWait();
		}
		else {
			InserLocation(); //call data insert method
			
		}
		
		
		
		
		
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/application/View/ManageLocation.fxml")); /*after the save button clicked it 
			                                                                                   will be redirect ManageLocation.fxml */
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
			
			
			
			
						
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	//Data Insert Method
	private void InserLocation() {
		
		/*String query =" INSERT INTO location" + " (bname, rname, rtype, cap) VALUES " + " (?,?,?,?);";
		
		try {
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setString(1, tfBname.getText());
			preparedStatement.setString(2, tfRname.getText());
			preparedStatement.setString(3, tfRtype.getText());
			preparedStatement.setString(4, tfCap.getText());
		}catch(SQLException ex) {
			ex.printStackTrace();
		}*/
		
		Connection con =getConnection();
		String query ="insert into location (bname,rname,rtype,cap) values(?,?,?,?)";
		try {
			
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, tfBname.getText());
		preparedStatement.setString(2, tfRname.getText());
		preparedStatement.setString(3, getRadioButton());
		preparedStatement.setString(4, tfCap.getText());
		preparedStatement.execute();
		
		JOptionPane.showMessageDialog(null, "Insert Successfully");
	}catch(SQLException ex) {
		JOptionPane.showMessageDialog(null, ex);
	}
			
		}
	
	 @FXML
	    void RadioSelect(ActionEvent event) {
		 
		 getRadioButton();

	    }

	 public String getRadioButton() {
	    	String message ="";
			if(lechall.isSelected()) {
				message += lechall.getText() +"\n";
			}
			
			if(lab.isSelected()) {
				message += lab.getText() +"\n";
			}
			
			System.out.println(message);
			return message;
	    	
	    }
		
		
		
		
	}
