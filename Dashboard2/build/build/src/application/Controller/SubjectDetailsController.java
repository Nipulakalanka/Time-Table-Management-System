package application.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

import application.Model.Student;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//    @FXML
//    private Spinner MangeSpinnerTutorial;
//
//    @FXML
//    private Spinner MangeSpinnerLec;
//
//    @FXML
//    private Spinner MangeSpinnerLab;
//
//    @FXML
//    private Spinner MangeSpinnerEvaluation;
//
//
//    @FXML
//    private ChoiceBox<String> MangeOfferdYearChoice;
//    private String[] MangeOfferdYear = {"1", "2", "3", "4"};
//
//
//
//    public void initialize(URL arg0, ResourceBundle arg1) {


public class SubjectDetailsController implements Initializable {

    int index = -1;


    @FXML
    private TableView<Student> mangesubjectTable;

    @FXML
    private TableColumn<Student, Integer> idTable;

    @FXML
    private TableColumn<Student, String> offerdyearTable;

    @FXML
    private TableColumn<Student, String> offerdsemesterTable;

    @FXML
    private TableColumn<Student, String> subjectnameTable;

    @FXML
    private TableColumn<Student, String> subjectcodeTable;

    @FXML
    private TableColumn<Student, String> TutehrsTable;

    @FXML
    private TableColumn<Student, String> LecturshrsTable;

    @FXML
    private TableColumn<Student, String> labhrsTable;

    @FXML
    private TableColumn<Student, String> evaluationhrsTable;

    @FXML
    private TextField subjectnameChoice;

    @FXML
    private TextField subjectcodeChoice;

    @FXML
    private Button updatebtn;

    @FXML
    private Button deletebtn;

    @FXML
    private TextField offerdyearChoice;

    @FXML
    private TextField offerdsemesterChoice;

    @FXML
    private TextField numoflecChoice;

    @FXML
    private TextField numoflabChoice;

    @FXML
    private TextField numofevaluationChoice;

    @FXML
    private TextField numoftutehrsChoice;


    @FXML
    private TextField idChoice;


    @FXML
    void update(ActionEvent event) {

        try {
            Connection con =getConnection();

            String idTableText = idChoice.getText();
            String offerdyearTableText = offerdyearChoice.getText();
            String offerdsemesterTableText = offerdsemesterChoice.getText();
            String subjectnameTableText = subjectnameChoice.getText();
            String subjectcodeTableText=subjectcodeChoice.getText();
            String tutehrsTableText = numoftutehrsChoice.getText();
            String lecturshrsTableText = numoflecChoice.getText();
            String labhrsTableText = numoflabChoice.getText();
            String evaluationhrsTableText = numofevaluationChoice.getText();


            System.out.println(idTableText);

            String query ="update student set Id ='"+idTableText+"',Offerdyear ='"+offerdyearTableText+"',Offerdsemster ='"+offerdsemesterTableText+"',Subjectname ='"+subjectnameTableText+"',Subjectcode ='"+subjectcodeTableText+"',Numtutehrs ='"+tutehrsTableText+"',Numlechrs ='"+lecturshrsTableText+"',Numlabhrs ='"+labhrsTableText+"',Numevalhrs ='"+evaluationhrsTableText+"'where Id='"+idTableText+"'";


            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Update Successfully");
            RefreshTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }



    }



    String query =null;
    ResultSet resultsset =null;
    PreparedStatement preparedStatement;




    @FXML
    void getSelected (MouseEvent event) {
        index = mangesubjectTable.getSelectionModel().getFocusedIndex();
        if(index <= -1) {
            return;
        }

        idChoice.setText(idTable.getCellData(index).toString());
        offerdyearChoice.setText(offerdyearTable.getCellData(index).toString());
        offerdsemesterChoice.setText(offerdsemesterTable.getCellData(index).toString());
        subjectnameChoice.setText(subjectnameTable.getCellData(index).toString());
        subjectcodeChoice.setText(subjectcodeTable.getCellData(index).toString());
        numoftutehrsChoice.setText(TutehrsTable.getCellData(index).toString());
        numoflecChoice.setText(LecturshrsTable.getCellData(index).toString());
        numoflabChoice.setText(labhrsTable.getCellData(index).toString());
        numofevaluationChoice.setText(evaluationhrsTable.getCellData(index).toString());


    }


    private void RefreshTable() {


        ObservableList<Student> list = getstudentslist();


        idTable.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        offerdyearTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Offerdyear"));
        offerdsemesterTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Offerdsemester"));
        subjectnameTable.setCellValueFactory(new PropertyValueFactory<Student , String>("SubjectName"));
        subjectcodeTable.setCellValueFactory(new PropertyValueFactory<Student , String>("SubjectCode"));
        TutehrsTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Numtutehrs"));
        LecturshrsTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Numlechrs"));
        labhrsTable.setCellValueFactory(new PropertyValueFactory<Student,String>("Numlabhrs"));
        evaluationhrsTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Numevalhrs"));

        mangesubjectTable.setItems(list);



    }



    public void initialize(URL arg0, ResourceBundle arg1) {
    showLectures();
    RefreshTable();

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

    public ObservableList<Student> getstudentslist() {

        ObservableList<Student> studentsList = FXCollections.observableArrayList();
        Connection con = getConnection();
        String query = "SELECT * FROM student";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Student student;
            while (rs.next()) {

                student = new Student(rs.getInt("Id"),rs.getString("Offerdyear"),rs.getString("Offerdsemster"),rs.getString("Subjectname"),rs.getString("Subjectcode"),rs.getString("Numtutehrs"),rs.getString("Numlechrs"),rs.getString("Numlabhrs"),rs.getString("Numevalhrs"));
                studentsList.add(student);


            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return studentsList;
    }


    public void showLectures() {

        ObservableList<Student> list = getstudentslist();


        idTable.setCellValueFactory(new PropertyValueFactory<Student, Integer>("ID"));
        offerdyearTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Offerdyear"));
        offerdsemesterTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Offerdsemester"));
        subjectnameTable.setCellValueFactory(new PropertyValueFactory<Student , String>("SubjectName"));
        subjectcodeTable.setCellValueFactory(new PropertyValueFactory<Student , String>("SubjectCode"));
        TutehrsTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Numtutehrs"));
        LecturshrsTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Numlechrs"));
        labhrsTable.setCellValueFactory(new PropertyValueFactory<Student,String>("Numlabhrs"));
        evaluationhrsTable.setCellValueFactory(new PropertyValueFactory<Student , String>("Numevalhrs"));

        mangesubjectTable.setItems(list);


    }

    @FXML
    void delete(ActionEvent event) {
        Connection con =getConnection();
        String query ="delete from student where Id =?";
        try {
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, idChoice.getText());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
            RefreshTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }


}








