package application.Controller;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ManageController implements Initializable{
	   @FXML
	    private TableView<AddTimeModel> tdmanage;

	    @FXML
	    private TableColumn<AddTimeModel, Integer> trid;

	    @FXML
	    private TableColumn<AddTimeModel,Integer > trnowd;

	    @FXML
	    private TableColumn<AddTimeModel, String> trwd;

	    @FXML
	    private TableColumn<AddTimeModel, String> trtpd;

	    @FXML
	    private Button bupdate;

	    @FXML
	    private Button bdelete;

	    @FXML
	    private TextField tblid;


	    @FXML
	    private TextField trnowd1;

	    @FXML
	    private TextField trtpd1;


	    @FXML
	    void deleteON(MouseEvent event)  {

	    	Connection con =getConnection();
	    	String query = "delete from tvmanage where NumWorkingDays= ?";
	    	try {
	    	preparedStatement = con.prepareStatement(query);
	    	preparedStatement.setString(1, trnowd1.getText());
	    	preparedStatement.execute();
	    	JOptionPane.showMessageDialog(null, "Delete");
	    	updateTime();

	    	} catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, e);
	    	}
	    	
	    }
	    

	  

	    @FXML
	    void UpdateTime(MouseEvent event) {


	    	try {
	    		Connection conn =getConnection();
	    		String id = tblid.getText();
	    		String ayrsem = trnowd1.getText();
	    		String prog = trtpd1.getText();
	    	


	    		String query = "update tvmanage set NumWorkingDays = '"+ayrsem+"',TimePerDay = '"+prog+" ' where ID='"+id+"' ";
	    		preparedStatement= conn.prepareStatement(query);
	    		preparedStatement.execute();
	    		JOptionPane.showMessageDialog(null, "Update");
	    		updateTime();

	    		} catch (Exception e) {
	    		JOptionPane.showMessageDialog(null, e);
	    		}
	    }
	    
	    
	    int index = -1;
	    ResultSet resultsset =null;
	    PreparedStatement preparedStatement;
	    @FXML
	    void getselected(MouseEvent event) {
	    index = tdmanage.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	    return;
	    }
	    tblid.setText(trid.getCellData(index).toString());
	    trnowd1.setText(trnowd.getCellData(index).toString());
	    trtpd1.setText(trtpd.getCellData(index).toString());
	 
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
	    
	    
	    public ObservableList<AddTimeModel> getTimeList(){
	    	ObservableList<AddTimeModel> timeList = FXCollections.observableArrayList();
	    	Connection con = getConnection();
	    	String query = "SELECT * FROM tvmanage";
	    	Statement st;
	    	ResultSet rs;
	    	try {
	    	st = ((java.sql.Connection) con).createStatement();
	    	rs= st.executeQuery(query);
	    	AddTimeModel addtimemodel;
	    	while(rs.next()) {
	    		addtimemodel = new AddTimeModel(rs.getInt("ID"),rs.getInt("NumWorkingDays"),rs.getString("WorkingDays"),rs.getString("TimePerDay"));
	    	timeList.add(addtimemodel);
	    	
	    	
	    	}
	    	}
	    	catch(Exception ex)
	    	{
	    	ex.printStackTrace();
	    	}
	    	return timeList ;
	    	}

	    	public void showStudents() {
	    	ObservableList<AddTimeModel> list= getTimeList();
	    	trid.setCellValueFactory(new PropertyValueFactory<AddTimeModel , Integer>("id"));
	    	trnowd.setCellValueFactory(new PropertyValueFactory<AddTimeModel , Integer>("numOfWorkingDays"));
	    	trwd.setCellValueFactory(new PropertyValueFactory<AddTimeModel, String>("workingDays"));
	    	trtpd.setCellValueFactory(new PropertyValueFactory<AddTimeModel, String>("workingTimePerDay"));
	    	
	    	tdmanage.setItems(list);

	    	 }
	    	
	    	
	    	public void updateTime() {
		    	ObservableList<AddTimeModel> list= getTimeList();
		    	trid.setCellValueFactory(new PropertyValueFactory<AddTimeModel , Integer>("id"));
		    	trnowd.setCellValueFactory(new PropertyValueFactory<AddTimeModel , Integer>("numOfWorkingDays"));
		    	trwd.setCellValueFactory(new PropertyValueFactory<AddTimeModel, String>("workingDays"));
		    	trtpd.setCellValueFactory(new PropertyValueFactory<AddTimeModel, String>("workingTimePerDay"));
		    	
		    	tdmanage.setItems(list);

		    	 }

			@Override
			public void initialize(URL arg0, ResourceBundle arg1) {
				updateTime();
				showStudents();
			}
			
			
}
