package application.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class PieChartController implements Initializable{
	
	@FXML
    private PieChart piechart;




	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(
				                                    new PieChart.Data("Lectures",13),
				                                    new PieChart.Data("Student",25),
				                                    new PieChart.Data("Subject", 14),
				                                    new PieChart.Data("Rooms", 22));
		
		piechart.setData(pieChartData);
		
		
		
		
	}
	
	
	
	

}
