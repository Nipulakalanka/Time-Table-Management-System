package application.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

import application.Model.Session;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManageSession implements Initializable {

    int index = -1;

    @FXML
    private TableView<Session> managesessionTable;

    @FXML
    private TableColumn<Session, Integer> managedtableIDtable;

    @FXML
    private TableColumn<Session, String> lecturenameTable;

    @FXML
    private TableColumn<Session, String> subjectcodeIDtable;


    @FXML
    private TableColumn<Session, String> subjectnameTable;

    @FXML
    private TableColumn<Session, String> groupidTable;

    @FXML
    private TableColumn<Session, String> tagsTable;

    @FXML
    private TableColumn<Session, String> noOfstudentsTable;

    @FXML
    private TableColumn<Session, String> durationTable;

    @FXML
    private Button addSessionBtn;


    @FXML
    private Button manageSessionDeleteBtn;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField lecturenameTxt;

    @FXML
    private TextField groupidTxt;

    @FXML
    private TextField subjectnameTxt;

    @FXML
    private TextField subjectcodeTxt;

    @FXML
    private TextField durationTxt;

    @FXML
    private TextField numofstudentTxt;

    @FXML
    private TextField tagsTxt;

    @FXML
    void addsessionbtnaction(ActionEvent event) {

    }


    String query =null;
    ResultSet resultsset =null;
    PreparedStatement preparedStatement;



    @FXML
    void tableSelect(MouseEvent event) {
        index = managesessionTable.getSelectionModel().getFocusedIndex();
        if(index <= -1) {
            return;
        }

        idTxt.setText(managedtableIDtable.getCellData(index).toString());
        lecturenameTxt.setText(lecturenameTable.getCellData(index).toString());
        subjectnameTxt.setText(subjectnameTable.getCellData(index).toString());
        subjectcodeTxt.setText(subjectcodeIDtable.getCellData(index).toString());
        groupidTxt.setText(groupidTable.getCellData(index).toString());
        tagsTxt.setText(tagsTable.getCellData(index).toString());
        numofstudentTxt.setText(noOfstudentsTable.getCellData(index).toString());
        durationTxt.setText(durationTable.getCellData(index).toString());

    }


    private void RefreshSessionTable (){

        ObservableList<Session> list = getsessionlist();

        managedtableIDtable.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ID"));
        lecturenameTable.setCellValueFactory(new PropertyValueFactory<Session , String>("Lecturename"));
        subjectnameTable.setCellValueFactory(new PropertyValueFactory<Session , String>("Subjectname"));
        subjectcodeIDtable.setCellValueFactory(new PropertyValueFactory<Session , String>("Subjectcode"));
        groupidTable.setCellValueFactory(new PropertyValueFactory<Session , String>("GroupID"));
        tagsTable.setCellValueFactory(new PropertyValueFactory<Session , String>("Tags"));
        noOfstudentsTable.setCellValueFactory(new PropertyValueFactory<Session , String>("NumofStudents"));
        durationTable.setCellValueFactory(new PropertyValueFactory<Session,String>("Duration"));

        managesessionTable.setItems(list);


    }



    @FXML
    void update(ActionEvent event) {

        try {
            Connection con =getConnection();

            String idTableText = idTxt.getText();
            String lecturenameTableText = lecturenameTxt.getText();
            String subjectnameTableText = subjectnameTxt.getText();
            String subjectcodeTableText = subjectcodeTxt.getText();
            String groupidTableText= groupidTxt.getText();
            String tagsTableText = tagsTxt.getText();
            String numofstudentTableText = numofstudentTxt.getText();
            String durationTableText = durationTxt.getText();


//            System.out.println(idTableText);

            String query ="update managesessionm set Id='"+idTableText+"',lecturename='"+lecturenameTableText+"',subjectname ='"+subjectnameTableText+"',subjectcode ='"+subjectcodeTableText+"',groupid ='"+groupidTableText+"',tags ='"+tagsTableText+"',numofstudents ='"+numofstudentTableText+"',duration ='"+durationTableText+"'where Id='"+idTableText+"'";


            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Update Successfully");
            RefreshSessionTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }


    public ObservableList<Session> getsessionlist() {

        ObservableList<Session> sessionlist = FXCollections.observableArrayList();
        Connection con = getConnection();
        String query = "SELECT * FROM managesessionm";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Session session;
            while (rs.next()) {

                session = new Session(rs.getInt("Id"),rs.getString("lecturename"),rs.getString("subjectname"),rs.getString("subjectcode"),rs.getString("groupid"),rs.getString("tags"),rs.getString("numofstudents"),rs.getString("duration"));
                sessionlist.add(session);


            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return sessionlist;
    }

    public void showSession(){

        ObservableList<Session> list = getsessionlist();

        managedtableIDtable.setCellValueFactory(new PropertyValueFactory<Session, Integer>("ID"));
        lecturenameTable.setCellValueFactory(new PropertyValueFactory<Session , String>("Lecturename"));
        subjectnameTable.setCellValueFactory(new PropertyValueFactory<Session , String>("Subjectname"));
        subjectcodeIDtable.setCellValueFactory(new PropertyValueFactory<Session , String>("Subjectcode"));
        groupidTable.setCellValueFactory(new PropertyValueFactory<Session , String>("GroupID"));
        tagsTable.setCellValueFactory(new PropertyValueFactory<Session , String>("Tags"));
        noOfstudentsTable.setCellValueFactory(new PropertyValueFactory<Session , String>("NumofStudents"));
        durationTable.setCellValueFactory(new PropertyValueFactory<Session,String>("Duration"));

        managesessionTable.setItems(list);
    }



    public void initialize (URL arg0, ResourceBundle arg1){

        showSession();
        RefreshSessionTable();
    }




    public Connection getConnection() {
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
            return con;
        }catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }


    @FXML
    void delete(ActionEvent event) {
        {
            Connection con =getConnection();
            String query ="delete from managesessionm where Id =?";
            try {
                preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, idTxt.getText());
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Deleted Successfully");
                RefreshSessionTable();
            }catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }


        }

    }


}
