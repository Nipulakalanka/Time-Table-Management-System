package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;


import application.Model.Session;
import application.Model.sessionform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sessiontabController implements Initializable {

	  @FXML
	    private TextField filtertext;
	
	 @FXML
	    private Button ccsf;

	    @FXML
	    private Button cdelete;

	    @FXML
	    private TextField tcid;

	    @FXML
	    private TextField tl2;

	    @FXML
	    private TextField tt2;

	    @FXML
	    private TextField tt1;

	    @FXML
	    private TextField tgid;

	    @FXML
	    private TextField tsc;

	    @FXML
	    private TextField tl1;

	    @FXML
	    private TextField ts;

	    @FXML
	    private TableView<sessionform> consec;

	    @FXML
	    private TableColumn<sessionform, Integer> cid;


	    @FXML
	    private TableColumn<sessionform, String> clec1;

	    @FXML
	    private TableColumn<sessionform, String> clec2;

	    @FXML
	    private TableColumn<sessionform, String> csc;

	    @FXML
	    private TableColumn<sessionform, String> cs;

	    @FXML
	    private TableColumn<sessionform, String> cgid;

	    @FXML
	    private TableColumn<sessionform, String> ctn;
	    
	    @FXML
	    private TableColumn<sessionform, String> ct2;

	   
	    @FXML
	    private TableView<Session> paid;

	    @FXML
	    private TableColumn<Session, Integer> ppaid;

	    @FXML
	    private TableColumn<Session, String> pl;

	    @FXML
	    private TableColumn<Session, String> pasc;

	    @FXML
	    private TableColumn<Session, String> pasn;

	    @FXML
	    private TableColumn<Session, String> pagid;

	    @FXML
	    private TableColumn<Session, String> pat;

	    @FXML
	    private TableColumn<Session, String> panof;

	    @FXML
	    private TableColumn<Session, String> padu;
	    
	  
	    @FXML
	    private TableView<Session> noid;

	    @FXML
	    private TableColumn<Session, Integer> naid;

	    @FXML
	    private TableColumn<Session, String> nal;

	    @FXML
	    private TableColumn<Session, String> nasc;

	    @FXML
	    private TableColumn<Session, String> nasn;

	    @FXML
	    private TableColumn<Session, String> nagi;

	    @FXML
	    private TableColumn<Session, String> nat;

	    @FXML
	    private TableColumn<Session, String> nanss;

	    @FXML
	    private TableColumn<Session, String> nad;
	    
	    @FXML
	    private Button cupdate;
	    
	    @FXML
	    private Button paddb;
	    
	    @FXML
	    private Button addnos;
	    
	    @FXML
	    private TextField ptid;

	    @FXML
	    private TextField ptd;

	    @FXML
	    private TextField ptns;

	    @FXML
	    private TextField ptt;

	    @FXML
	    private TextField ptgid;

	    @FXML
	    private TextField ptl;

	    @FXML
	    private TextField pts;

	    @FXML
	    private TextField ptsc;

	    @FXML
	    private Button pbdele;
	    

	    @FXML
	    private Button ndelete;
	    
	    @FXML
	    private TextField ntid;

	    @FXML
	    private TextField ntd;

	    @FXML
	    private TextField ntns;

	    @FXML
	    private TextField ntt;

	    @FXML
	    private TextField ntgid;

	    @FXML
	    private TextField ntsc;

	    @FXML
	    private TextField nts;

	    @FXML
	    private TextField ntl;

	  

	    @FXML
	    void addnonoverlapses(ActionEvent event) throws IOException {

	    	Parent root =FXMLLoader.load(getClass().getResource("/View/manageSession.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show(); 

	    }

	    @FXML
	    void addparallelsession(ActionEvent event)throws IOException {

	    	Parent root =FXMLLoader.load(getClass().getResource("/View/manageSession.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show(); 

	    }

	    int index = -1;
	    
	   	ResultSet resultsset =null;
	   	PreparedStatement preparedStatement;
	   	
	   	
		
	
	   	@FXML
	    void gofrom(ActionEvent event)  throws IOException{
	    	Parent root =FXMLLoader.load(getClass().getResource("/View/sessiongroupform.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
	    }
	   	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showsessionform();
		UpdateConsecutive();
		showpsessionform();
		shownsessionform();
		UpdateParallel();
		UpdateOverlap();
		
		 FilteredList<sessionform> filteredData = new FilteredList<>(getConsecetive(),b-> true);
		 filtertext.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(sessionform -> {
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }
	                String lowerCaseFilter = newValue.toLowerCase();
	                if (sessionform.getSgl1().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	                    return true;
	                } else if (sessionform.getSgl2().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	                    return true; // Filter matches last name.
	                } else if (sessionform.getSgsc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	                    return true;
	                } else
	                    return false;
	            });
	        });
	        SortedList<sessionform> sortedData = new SortedList<>(filteredData);
	        sortedData.comparatorProperty().bind(consec.comparatorProperty());
	        consec.setItems(sortedData);
		


	}
    
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
	
    public ObservableList<sessionform> getConsecetive(){
    	
    	ObservableList<sessionform> consecetiveList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String query = "SELECT * FROM consecutive";
    	
    	Statement st;
    	ResultSet rs;
    	
    	try {
    		
    		st = ((java.sql.Connection) conn).createStatement();
    		rs= st.executeQuery(query);
    		sessionform sform;
    		while(rs.next()) {
    			
    			sform = new sessionform(rs.getInt("consecutiveID"),rs.getString("Lecture1"),rs.getString("Lecture2"),rs.getString("subjectcode"),rs.getString("subject"),rs.getString("groupID"),rs.getString("tag1"), rs.getString("tag2"));
    			consecetiveList.add(sform);
    		
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		
    	}
    	return consecetiveList;
    }
   
	
    @FXML
    void getselect(MouseEvent event) {
    	index = consec.getSelectionModel().getSelectedIndex();
    	  if (index <= -1){
    	         
              return;
          }
          
    	  tcid.setText(cid.getCellData(index).toString());
    	  tl1.setText(clec1.getCellData(index).toString());
    	  tl2.setText(clec2.getCellData(index).toString());
    	  tsc.setText(csc.getCellData(index).toString());
    	  ts.setText(cs.getCellData(index).toString());
    	  tgid.setText(cgid.getCellData(index).toString());
    	  tt1.setText(ctn.getCellData(index).toString());
    	  tt2.setText(ct2.getCellData(index).toString());
    }

    
	 public void showsessionform() {
	    	
	    	ObservableList<sessionform> list= getConsecetive();
	    	cid.setCellValueFactory(new PropertyValueFactory<sessionform, Integer>("sfid"));
	    	clec1.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgl1"));
	    	clec2.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgl2"));
	    	csc.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgsc"));
	    	cs.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgs"));
	    	cgid.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sggid"));
	    	ctn.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgt1"));
	    	ct2.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgt1"));
	    	
	    	consec.setItems(list);

	    }
	 
	    @FXML
	    void conupdate(MouseEvent event) {

	    	 try {
	 	    	Connection conn =getConnection();
	 	    	String id = tcid.getText();
	 	        String l1 = tl1.getText();
	 	        String l2 = tl2.getText();
	 	        String sc = tsc.getText();
	 	        String s = ts.getText();
		        String sgid = tgid.getText();
		        String st1 = tt1.getText();
		        String st2 = tt2.getText();
	 	        
	 	        
	 	        String query = "update consecutive set Lecture1= '"+l1+"',Lecture2= '"+l2+"',subjectcode= '"+sc+"',subject= '"+s+"',groupID ='"+sgid+"',tag1 ='"+st1+"',tag2 ='"+st2+"' where consecutiveID='"+id+"' ";
	 	        preparedStatement= conn.prepareStatement(query);
	 	        preparedStatement.execute();
	 	        JOptionPane.showMessageDialog(null, "Update");
	 	        UpdateConsecutive();
	 	       
	 	    } catch (Exception e) {
	 	        JOptionPane.showMessageDialog(null, e);
	 	    }
	 	
	    	
	    }
	 
	   @FXML
	    void condelete(MouseEvent event) {
		   
		   	Connection conn =getConnection();
		    String query = "delete from consecutive where consecutiveID= ?";
		        try {
		        	preparedStatement = conn.prepareStatement(query);
		        	preparedStatement.setString(1, tcid.getText());
		        	preparedStatement.execute();
		            JOptionPane.showMessageDialog(null, "Delete");
		            UpdateConsecutive();
		           
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, e);
		        }

	    }
	   
	    private void UpdateConsecutive() {
	    	
	    	ObservableList<sessionform> list= getConsecetive();
	    	cid.setCellValueFactory(new PropertyValueFactory<sessionform , Integer>("sfid"));
	    	clec1.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgl1"));
	    	clec2.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgl2"));
	    	csc.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgsc"));
	    	cs.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgs"));
	    	cgid.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sggid"));
	    	ctn.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgt1"));
	    	ct2.setCellValueFactory(new PropertyValueFactory<sessionform, String>("sgt1"));
	    	
	    	
	    	consec.setItems(list);
	    	
	    }
	    

	    
	    
	    public ObservableList<Session> getpsessionlist() {

	        ObservableList<Session> psessionlist = FXCollections.observableArrayList();
	        Connection con = getConnection();
	        String query = "SELECT * FROM parallel";

	        Statement st;
	        ResultSet rs;

	        try {

	            st = con.createStatement();
	            rs = st.executeQuery(query);
	            Session session;
	            while (rs.next()) {

	                session = new Session(rs.getInt("pID"),rs.getString("plecture"),rs.getString("pscode"),rs.getString("psubject"),rs.getString("pgroupid"),rs.getString("ptag"),rs.getString("pns"),rs.getString("pduration"));
	                psessionlist.add(session);


	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();

	        }

	        return psessionlist;
	    }
	    
	    @FXML
	    void getselectpa(MouseEvent event)  {
			 	index = paid.getSelectionModel().getSelectedIndex();
		    	  if (index <= -1){
		    	         
		              return;
		          }
		          
		    	  ptid.setText(ppaid.getCellData(index).toString());
		    	  ptl.setText(pl.getCellData(index).toString());
		    	  ptsc.setText(pasc.getCellData(index).toString());
		    	  pts.setText(pasn.getCellData(index).toString());
		    	  ptgid.setText(pagid.getCellData(index).toString());
		    	  ptt.setText(pat.getCellData(index).toString());
		    	  ptns.setText(panof.getCellData(index).toString());
		    	  ptd.setText(padu.getCellData(index).toString());
		    	  
		    	  
		    }
		 
		    @FXML
		    void padelete(ActionEvent event) {
			   	Connection conn =getConnection();
			    String query = "delete from parallel where pID= ?";
			        try {
			        	preparedStatement = conn.prepareStatement(query);
			        	preparedStatement.setString(1, ptid.getText());
			        	preparedStatement.execute();
			            JOptionPane.showMessageDialog(null, "Delete");
			            UpdateParallel();
			           
			        } catch (Exception e) {
			            JOptionPane.showMessageDialog(null, e);
			        }
		    }
		    
		    
			   
			    private void UpdateParallel() {
			    	
			    	ObservableList<Session> list= getpsessionlist();
			    	ppaid.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ID"));
			    	pl.setCellValueFactory(new PropertyValueFactory<Session, String>("Lecturename"));
			    	pasc.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectcode"));
			    	pasn.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectname"));
			    	pagid.setCellValueFactory(new PropertyValueFactory<Session, String>("GroupID"));
			    	pat.setCellValueFactory(new PropertyValueFactory<Session, String>("Tags"));
			    	panof.setCellValueFactory(new PropertyValueFactory<Session, String>("NumofStudents"));
			    	padu.setCellValueFactory(new PropertyValueFactory<Session, String>("Duration"));			    	
			    	
			    	paid.setItems(list);
			    	
			    }
	    
		 public void showpsessionform() {
		    	
		    	ObservableList<Session> list= getpsessionlist();
		    	ppaid.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ID"));
		    	pl.setCellValueFactory(new PropertyValueFactory<Session, String>("Lecturename"));
		    	pasc.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectcode"));
		    	pasn.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectname"));
		    	pagid.setCellValueFactory(new PropertyValueFactory<Session, String>("GroupID"));
		    	pat.setCellValueFactory(new PropertyValueFactory<Session, String>("Tags"));
		    	panof.setCellValueFactory(new PropertyValueFactory<Session, String>("NumofStudents"));
		    	padu.setCellValueFactory(new PropertyValueFactory<Session, String>("Duration"));
		    	
		    	paid.setItems(list);

		    }
		 
		 
		    public ObservableList<Session> getnsessionlist() {

		        ObservableList<Session> nsessionlist = FXCollections.observableArrayList();
		        Connection con = getConnection();
		        String query = "SELECT * FROM nonoverlap";

		        Statement st;
		        ResultSet rs;

		        try {

		            st = con.createStatement();
		            rs = st.executeQuery(query);
		            Session session;
		            while (rs.next()) {

		                session = new Session(rs.getInt("nID"),rs.getString("nlecture"),rs.getString("nscode"),rs.getString("nsubject"),rs.getString("ngroupid"),rs.getString("ntag"),rs.getString("nns"),rs.getString("nduration"));
		                nsessionlist.add(session);


		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();

		        }

		        return nsessionlist;
		    }
		   
		    @FXML
		    void getselectnon(MouseEvent event) {
		    	index = noid.getSelectionModel().getSelectedIndex();
		    	  if (index <= -1){
		    	         
		              return;
		          }
		          
		    	  ntid.setText(naid.getCellData(index).toString());
		    	  ntl.setText(nal.getCellData(index).toString());
		    	  ntsc.setText(nasc.getCellData(index).toString());
		    	  nts.setText(nasn.getCellData(index).toString());
		    	  ntgid.setText(nagi.getCellData(index).toString());
		    	  ntt.setText(nat.getCellData(index).toString());
		    	  ntns.setText(nanss.getCellData(index).toString());
		    	  ntd.setText(nad.getCellData(index).toString());
		    	  
		    	
		    }
		    
		    @FXML
		    void nondelete(ActionEvent event) {

		       	Connection conn =getConnection();
			    String query = "delete from nonoverlap where nID=?";
			        try {
			        	preparedStatement = conn.prepareStatement(query);
			        	preparedStatement.setString(1, ntid.getText());
			        	preparedStatement.execute();
			            JOptionPane.showMessageDialog(null, "Delete");
			            UpdateOverlap();
			           
			        } catch (Exception e) {
			            JOptionPane.showMessageDialog(null, e);
			        }
		    	
		    }
	
		    private void UpdateOverlap() {
		    	
		    	ObservableList<Session> list= getnsessionlist();
		    	naid.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ID"));
		    	nal.setCellValueFactory(new PropertyValueFactory<Session, String>("Lecturename"));
		    	nasc.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectcode"));
		    	nasn.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectname"));
		    	nagi.setCellValueFactory(new PropertyValueFactory<Session, String>("GroupID"));
		    	nat.setCellValueFactory(new PropertyValueFactory<Session, String>("Tags"));
		    	nanss.setCellValueFactory(new PropertyValueFactory<Session, String>("NumofStudents"));
		    	nad.setCellValueFactory(new PropertyValueFactory<Session, String>("Duration"));
		    	
		    	noid.setItems(list);
		    	
		    }
		    
			 public void shownsessionform() {
			    	
			    	ObservableList<Session> list= getnsessionlist();
			    	naid.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ID"));
			    	nal.setCellValueFactory(new PropertyValueFactory<Session, String>("Lecturename"));
			    	nasc.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectcode"));
			    	nasn.setCellValueFactory(new PropertyValueFactory<Session, String>("Subjectname"));
			    	nagi.setCellValueFactory(new PropertyValueFactory<Session, String>("GroupID"));
			    	nat.setCellValueFactory(new PropertyValueFactory<Session, String>("Tags"));
			    	nanss.setCellValueFactory(new PropertyValueFactory<Session, String>("NumofStudents"));
			    	nad.setCellValueFactory(new PropertyValueFactory<Session, String>("Duration"));
			    	
			    	noid.setItems(list);

			    }
			 
			    

}
