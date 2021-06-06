package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Model.Tags;
import javafx.event.ActionEvent;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sessiongroupformController  implements Initializable{

    @FXML
    private TextField sgl1;

    @FXML
    private TextField sgl2;

    @FXML
    private TextField sgsc;

    @FXML
    private TextField sgs;

    @FXML
    private TextField sggid;

    @FXML
    private TextField sgt1;

    @FXML
    private TextField sgt2;


    
    String query =null;
  	ResultSet resultsset =null;
  	PreparedStatement preparedStatement;
  	Tags tags;
  	
public Connection getConnection() {
		
		Connection conn;
		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm","root","");
			return conn;
		}
		catch (Exception ex) {
			
			System.out.println("Error:"+ex.getMessage());
			return null;
		}
	}


@FXML
void addconsecetive(MouseEvent event)  {
	String l1 =sgl1.getText();
	String l2 =sgl2.getText();
	String sc = sgsc.getText();
	String s =sgs.getText();
	String sgid =sggid.getText();
	String t1 =sgt1.getText();
	String t2 =sgt2.getText();
	
	
	
	if(l1.isEmpty()||l2.isEmpty()||sc.isEmpty()||s.isEmpty()||sgid.isEmpty()||t1.isEmpty()||t2.isEmpty()) {
		Alert alert =new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Please Fill All Data");
		alert.showAndWait();
	}
	else {
		insertsessionform();
		
	}
	
	
	
	try {
		Parent root =FXMLLoader.load(getClass().getResource("/View/Sessiontab.fxml"));
		Scene scene = new Scene(root);
		Stage stage =new Stage();
		stage.setScene(scene);
		stage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
	

}
    


    private void insertsessionform() {
    	Connection conn =getConnection();
		String query ="insert into consecutive (Lecture1,Lecture2,subjectcode,subject,groupID,tag1,tag2)"
				+ "values(?,?,?,?,?,?,?)";
		try {
			
			
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, sgl1.getText());
		preparedStatement.setString(2, sgl2.getText());
		preparedStatement.setString(3, sgsc.getText());
		preparedStatement.setString(4, sgs.getText());
		preparedStatement.setString(5, sggid.getText());
		preparedStatement.setString(6, sgt1.getText());
		preparedStatement.setString(7, sgt2.getText());
		preparedStatement.execute();
		
		JOptionPane.showMessageDialog(null, "Insert Successfully");
		}	
		catch(SQLException ex) {
		JOptionPane.showMessageDialog(null, ex);
	}
	
}
    
    @FXML
    void clear(ActionEvent event) {

    	sgl1.setText(" ");
    	sgl2.setText(" ");
    	sgsc.setText(" ");
    	sgs.setText(" ");
    	sggid.setText(" ");
    	sgt1.setText(" ");
    	sgt2.setText(" ");
    	
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			
			
		
	}


}
