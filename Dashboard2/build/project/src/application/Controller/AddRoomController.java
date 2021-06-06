package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Model.Location;
import application.Model.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class AddRoomController implements Initializable{
	
	@FXML
    private TextArea lbl1;
	@FXML
    private TextArea lbl2;
	@FXML
    private TextArea lbl3;
	@FXML
    private TextArea lbl4;
	@FXML
    private TextArea lbl5;
	@FXML
    private TextArea lbl6;
	@FXML
    private TextArea lbl7;
   
	
	String query =null;
	ResultSet resultsset =null;
	PreparedStatement preparedStatement;
	Students student;
	String groups;
	String subgroups;
	int count = 0;
	
   
    
	@FXML
    public ComboBox<String> groupcombo;

    ObservableList <String> List1 = FXCollections.observableArrayList(getGroupid());
	
    
    @FXML
    public ComboBox<String> comboroom4;

    ObservableList <String> List2 = FXCollections.observableArrayList(getroomid());
    
    @FXML
    public ComboBox<String> subjectcombo;

    ObservableList <String> List3 = FXCollections.observableArrayList(getsubject());
    
    @FXML
    public ComboBox<String> comborrom1;

    ObservableList <String> List4 = FXCollections.observableArrayList(getroomid());
    
    
    @FXML
    public ComboBox<String> tagcombo;

    ObservableList <String> List5 = FXCollections.observableArrayList(gettag());
    
    @FXML
    public ComboBox<String> comboroom2;

    ObservableList <String> List6 = FXCollections.observableArrayList(getroomid());
    
    @FXML
    public ComboBox<String> leccombo;

    ObservableList <String> List7 = FXCollections.observableArrayList(getlec());
    
    @FXML
    public ComboBox<String> comboroom3;

    ObservableList <String> List8 = FXCollections.observableArrayList(getroomid());

	/*
	 * @FXML public ComboBox<String> sessioncombo;
	 * 
	 * ObservableList <String> List9 =
	 * FXCollections.observableArrayList(getsession());
	 */
    
    @FXML
    public ComboBox<String> comboroom5;

    ObservableList <String> List10 = FXCollections.observableArrayList(getroomid());
    
    @FXML
    public ComboBox<String> comboroom6;

    ObservableList <String> List12 = FXCollections.observableArrayList(getroomid());
    
	
	 @FXML public ComboBox<String> consessioncombo;
	 
	ObservableList <String> List11 =FXCollections.observableArrayList(getconsession());
	 
    
    
    @FXML
    public ComboBox<String>  tag1combo;

    ObservableList <String> List13 = FXCollections.observableArrayList(gettag());
    
    @FXML
    public ComboBox<String>    subcombo1;

    ObservableList <String> List14 = FXCollections.observableArrayList(getsubject());
  
    @FXML
    public ComboBox<String> comboroom7;

    ObservableList <String> List15 = FXCollections.observableArrayList(getroomid());
    
    
  
	
    public Connection getConnection() {
		Connection con;
		try{
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
			return con;
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
			return null;
			
		}
	}
    
    
	/*
	 * private ArrayList<String> getsession() { ArrayList<String> output= new
	 * ArrayList<String>();
	 * 
	 * Connection con =getConnection(); String query ="SELECT * From lectures";
	 * Statement st; ResultSet rs;
	 * 
	 * try { preparedStatement =con.prepareStatement(query); rs
	 * =preparedStatement.executeQuery(query);
	 * 
	 * while(rs.next()) { String room=rs.getString("Lecturename");
	 * 
	 * 
	 * 
	 * output.add(room);
	 * 
	 * 
	 * System.out.println(output);
	 * 
	 * 
	 * }
	 * 
	 * }catch(Exception e) { e.printStackTrace();
	 * 
	 * } return output;
	 * 
	 * }
	 */


	/*
	 * private Callback getconsession() { // TODO Auto-generated method stub return
	 * null; }
	 */

	private ArrayList<String> getconsession() {
		
		ArrayList<String> output= new ArrayList<String>();
    	
    	Connection con =getConnection();
		String query ="SELECT * From consecutive";
		Statement st;
		ResultSet rs;
		
		try {
			preparedStatement =con.prepareStatement(query);
			rs =preparedStatement.executeQuery(query);
			
			while(rs.next()) {
				String consecutiveid=rs.getString("consecutiveID");
				
				
				
				 output.add(consecutiveid);
				
					
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return output;
	}


	private ArrayList<String> getlec() {
		
		ArrayList<String> output= new ArrayList<String>();
    	
    	Connection con =getConnection();
		String query ="SELECT * From lectures";
		Statement st;
		ResultSet rs;
		
		try {
			preparedStatement =con.prepareStatement(query);
			rs =preparedStatement.executeQuery(query);
			
			while(rs.next()) {
				String lecname=rs.getString("Lecturename");
				
				
				
				 output.add(lecname);
				
				 
				
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return output;
	}

	private ArrayList<String> gettag() {
    		ArrayList<String> output= new ArrayList<String>();
    	
    	Connection con =getConnection();
		String query ="SELECT * From tags";
		Statement st;
		ResultSet rs;
		
		try {
			preparedStatement =con.prepareStatement(query);
			rs =preparedStatement.executeQuery(query);
			
			while(rs.next()) {
				String tag=rs.getString("tagr");
				
				
				
				 output.add(tag);
				
				 
				
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return output;
	}


	private ArrayList<String> getsubject() {
    	ArrayList<String> output= new ArrayList<String>();
    	
    	Connection con =getConnection();
		String query ="SELECT * From student";
		Statement st;
		ResultSet rs;
		
		try {
			preparedStatement =con.prepareStatement(query);
			rs =preparedStatement.executeQuery(query);
			
			while(rs.next()) {
				String subject=rs.getString("Subjectcode");
				
				
				
				 output.add(subject);
				
				 
				 
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return output;
		
	}


	public ArrayList<String> getroomid() {
    	
    	ArrayList<String> output= new ArrayList<String>();
    	
    	Connection con =getConnection();
		String query ="SELECT * From location";
		Statement st;
		ResultSet rs;
		
		try {
			preparedStatement =con.prepareStatement(query);
			rs =preparedStatement.executeQuery(query);
			
			while(rs.next()) {
				String room=rs.getString("rname");
				
				
				
				 output.add(room);
				
				 
				 
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return output;
		
	}


	public ArrayList<String> getGroupid() {
    	
    	ArrayList<String> output= new ArrayList<String>();
    	ArrayList<String> output1= new ArrayList<String>();
    	
    	Connection con =getConnection();
		String query ="SELECT * From students";
		Statement st;
		ResultSet rs;
		
		try {
			preparedStatement =con.prepareStatement(query);
			rs =preparedStatement.executeQuery(query);
			
			while(rs.next()) {
				groups=rs.getString("groupid");
				subgroups=rs.getString("sgroupid");
				
				
				 output.add(groups);
				 output1.add(subgroups);
				 
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return output;
		
		
	
		 
}
	
	
	  @FXML
	    void getlbl4(MouseEvent event) {
		  
		  lbl4.setText("Group ID: " +groupcombo.getValue() +" "+"Room:" +comboroom4.getValue());
		
		
		
	}
	  
	  @FXML
	    void getlbl1(MouseEvent event) {
		  
		  lbl1.setText("Subject ID: " +subjectcombo.getValue() +" "+"Room:" +comborrom1.getValue());
		
		
		}
	  
	  @FXML
	    void getlbl2(MouseEvent event) {
		  
		  lbl2.setText("Tag ID: " +tagcombo.getValue() +" "+"Room:" +comboroom2.getValue());
		
		}
	  
	  @FXML
	    void getlbl3(MouseEvent event) {
		  
		  lbl3.setText("Lec ID: " +leccombo.getValue() +" "+"Room:" +comboroom3.getValue());
		
		}
	
		/*
		 * @FXML void getlbl5(MouseEvent event) {
		 * 
		 * lbl4.setText("Tag ID: " +.getValue() +" "+"Room:" +comboroom3.getValue());
		 * 
		 * }
		 */
	
	  @FXML
	    void getlbl7(MouseEvent event) {
		  
		  lbl7.setText("Tag ID: " +tag1combo.getValue() +" "+ "Subject ID: " +subcombo1.getValue()+" "+"Room:" +comboroom7.getValue());
		
		}
	  
	  @FXML
	    void getlbl5(MouseEvent event) {
		  
	  }
	  
	  @FXML
	    void getlbl6(MouseEvent event) {
		  
	  }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 groupcombo.setItems(List1);
		 comboroom4.setItems(List2);
		 subjectcombo.setItems(List3);
		 comborrom1.setItems(List4);
		 tagcombo.setItems(List5);
		 comboroom2.setItems(List6);
		 leccombo.setItems(List7);
		 comboroom3.setItems(List8);
		 comboroom5.setItems(List10);
		 consessioncombo.setItems(List11);
		 comboroom6.setItems(List12); 
		 tag1combo.setItems(List13);
		 subcombo1.setItems(List14);
		 comboroom7.setItems(List15);
		
		
	}
	
	
	 	@FXML
	    void groupbtn(ActionEvent event) {
	 		
	 		Connection con =getConnection();
			String query ="insert into grouproom (id,groupname,roomname) values(?,?,?)";
			try {
				
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, groupcombo.getValue());
			preparedStatement.setString(3, comboroom4.getValue());
			
			preparedStatement.execute();
			
			JOptionPane.showMessageDialog(null, "Insert Successfully");
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		 
		 }

	    @FXML
	    void lecbtn(ActionEvent event) {
	    	
	    	 
			 Connection con =getConnection();
				String query ="insert into lcroom (id,lecname,roomname) values(?,?,?)";
				try {
					
				preparedStatement = getConnection().prepareStatement(query);
				preparedStatement.setInt(1, 0);
				preparedStatement.setString(2, leccombo.getValue());
				preparedStatement.setString(3, comboroom3.getValue());
				
				preparedStatement.execute();
				
				JOptionPane.showMessageDialog(null, "Insert Successfully");
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}

	    }

	    @FXML
	    void sessionbtn(ActionEvent event) {

	    }

	    @FXML
	    void subtbn(ActionEvent event) {
	    	
	    
	    	
	    	
	    	String str = "STUD";
			 
			 Connection con =getConnection();
				String query ="insert into subgroup (id,subname,subroom) values(?,?,?)";
				try {
					
				preparedStatement = getConnection().prepareStatement(query);
				preparedStatement.setString(1, str+(count++));
				preparedStatement.setString(2, subjectcombo.getValue());
				preparedStatement.setString(3, comborrom1.getValue());
				
				preparedStatement.execute();
				
				JOptionPane.showMessageDialog(null, "Insert Successfully");
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}

	    }
	    
		
	

	    @FXML
	    void tagbtn(ActionEvent event) {
	    	
	    	
			 
			 Connection con =getConnection();
				String query ="insert into tagroom (id,tagname,roomanme) values(?,?,?)";
				try {
					
				preparedStatement = getConnection().prepareStatement(query);
				preparedStatement.setInt(1, 0);
				preparedStatement.setString(2, tagcombo.getValue());
				preparedStatement.setString(3, comboroom2.getValue());
				
				preparedStatement.execute();
				
				JOptionPane.showMessageDialog(null, "Insert Successfully");
			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}

	    }

	    @FXML
	    void tagsubbtn(ActionEvent event) {

	    }
	    
	    @FXML
	    void consessionbtn(ActionEvent event) {

	    }

}
