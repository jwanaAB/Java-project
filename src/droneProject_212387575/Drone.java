package droneProject_212387575;

import java.util.ArrayList;

public class Drone implements Comparable<Drone> {
	protected Integer droneCode;
	protected boolean status;
	protected double battery;
	protected ArrayList<Manager> managers;
	private Integer countManager;
	
	//building constructor
	public Drone(int droneCode, boolean status, double battery) {
		super();
		this.droneCode = droneCode;
		this.status = status;
		this.battery = battery;
		this.managers= new ArrayList<Manager>(0);
		countManager = 0;
	}
	
	//building constructor
	public Drone(int droneCode, boolean status, double battery, Manager[] managers, int countManager) {
		super();
		this.droneCode = droneCode;
		this.status = status;
		this.battery = battery;
		this.managers = new ArrayList<Manager>(0);
		this.countManager = managers.length;
	}

	// add Manager function
		public void addManager(Manager newManager)
		{
			if(newManager == null) {
				System.out.println("ERROR : null manager!!!");
				return;
			}
			for(Manager m : managers) {
				if(m.getId() == newManager.getId()){
					System.out.println("The manager is already in the drone class!");
					return;
				}
			}

			managers.add(newManager);
			countManager++;
			System.out.println("manager added successfuly to the drone class ;) ");
		}
		
	//getters setters and toString	
	public Integer getDroneCode() {
		return droneCode;
	}
	public void setDroneCode(int droneCode) {
		this.droneCode = droneCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getBattery() {
		return battery;
	}
	public void setBattery(double battery) {
		this.battery = battery;
	}
	@Override
	public String toString() {
		return "Drone [droneCode=" + droneCode + ", status=" + status + ", battery=" + battery + "]";
	}

	@Override
	public int compareTo(Drone o) {
		return this.getDroneCode().compareTo(o.getDroneCode());
	
	}
	
	

}
