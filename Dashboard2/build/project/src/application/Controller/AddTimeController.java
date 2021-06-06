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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTimeController implements Initializable {
	

    @FXML
    private TextField tfnowd;

    @FXML
    private TextField tfwtpd;

    @FXML
    private Button bsave;

    @FXML
    private Button bclear;
    
    @FXML
    private CheckBox cb1;

    @FXML
    private CheckBox cb2;

    @FXML
    private CheckBox cb3;

    @FXML
    private CheckBox cb4;

    @FXML
    private CheckBox cb5;

    @FXML
    private CheckBox cb6;

    @FXML
    private CheckBox cb7;
    

    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//showLectures();
    	//RefreshTable();
    	
     }
    
    
    public Connection getConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    
    

    
    @FXML
    void saveon(ActionEvent event) {
    	
     
            InsertAddTime(); //call data insert method
            
            try {
                Parent root =FXMLLoader.load(getClass().getResource("/application/View/Manage.fxml")); /*after the save button clicked it
                                                                                                   will be redirect ManageLocation.fxml */
                Scene scene = new Scene(root);
                Stage stage =new Stage();
                stage.setScene(scene);
                stage.show();
               
               
               
            } catch(Exception e) {
                e.printStackTrace();
            }
    }
    
    private void InsertAddTime() {

        Connection con =getConnection();
        String query ="insert into  tvmanage  (NumWorkingDays, 	WorkingDays ,TimePerDay ) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, tfnowd.getText());
            preparedStatement.setString(2, getCheckBox());
            preparedStatement.setString(3, tfwtpd.getText());
      
            preparedStatement.execute();
            
            System.out.println(tfnowd);
            JOptionPane.showMessageDialog(null, "Insert Successfully");
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    
    
    
    }
    
    @FXML
    void CheckEvent(ActionEvent event) {
    	
    	getCheckBox();
    	

    }


	public String getCheckBox() {
		String message1 ="";
        if(cb1.isSelected()) {
            message1 += cb1.getText() +"\n";
        }
        if(cb2.isSelected()) {
            message1 += cb2.getText() +"\n";
        }
        if(cb3.isSelected()) {
            message1 += cb3.getText() +"\n";
        }
        if(cb4.isSelected()) {
            message1 += cb4.getText() +"\n";
        }
        if(cb5.isSelected()) {
            message1 += cb5.getText() +"\n";
        }
        if(cb6.isSelected()) {
            message1 += cb6.getText() +"\n";
        }
        if(cb7.isSelected()) {
            message1 += cb7.getText() +"\n";
        }
       
       
        System.out.println(message1);
        return message1;
		
		
	}
  }
    
    
    
    

