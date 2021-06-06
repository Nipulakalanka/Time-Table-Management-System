package application.Controller;

public class AddTimeModel {
	private int id;
	private int numOfWorkingDays;
	private String workingDays;
	private String workingTimePerDay;
	
	AddTimeModel (int id,int numOfWorkingDays,String workingDays,String workingTimePerDay){
		this.id = id;
		this.numOfWorkingDays=numOfWorkingDays;
		this.workingDays=workingDays;
		this.workingTimePerDay=workingTimePerDay;
	}
	
	public int getId () {
		return id;
	}
	public int getNumOfWorkingDays() {
		return numOfWorkingDays;
	}
	
	public String getWorkingDays() {
		return workingDays;
	}
	public String getWorkingTimePerDay() {
		return workingTimePerDay;
	}

	
	
	
}
