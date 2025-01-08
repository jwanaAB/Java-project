package droneProject_212387575;

import java.util.ArrayList;

public class system {
	
	private ArrayList<Subscription> allSubscriptions = new ArrayList<Subscription>();
	private ArrayList<Manager> allManagers = new ArrayList<Manager>();
	private ArrayList<Drone> allDrones = new ArrayList<Drone>();
	private int subscriptionNum;
	private int managersNum;
	private int dronesNum;
	
	public system(ArrayList<Subscription> allSubscriptions, ArrayList<Manager> allManagers, ArrayList<Drone> allDrones) {
		super();
		//here we ask first if allSubscriptions is null(empty or we didn't received it)so we build new array in length 0
		if(allSubscriptions==null) {
			this.allSubscriptions = new ArrayList<Subscription>(0);
			subscriptionNum = 0 ;
		}
		// in allSubscriptions there are subscriptions
		else {
			this.allSubscriptions = allSubscriptions;
			subscriptionNum = allSubscriptions.size();
		}
		
		//here we ask first if allManagers is null(empty or we didnt received it)so we build new array in length 0
		if(allManagers == null) {
			this.allManagers = new ArrayList<Manager>(0);
			managersNum = 0 ;
		}
		// in allManagers there are managers
		else {
			this.allManagers = allManagers;
			managersNum = allManagers.size();
		}
		
		//here we ask first if allDrones is null(empty or we didnt received it)so we build new array in length 0
		if(allDrones == null) {
			this.allDrones = new ArrayList<Drone>(0);
			dronesNum = 0 ;
		}
		// in allDrones there are drones
		else {
			this.allDrones = allDrones;
			dronesNum = allDrones.size();
		}
	}
	public ArrayList<Subscription> getAllSubscriptions() {
		return allSubscriptions;
	}
	
	public ArrayList<Manager> getAllManagers() {
		return allManagers;
	}
	public void setAllManagers(ArrayList<Manager> allManagers) {
		this.allManagers = allManagers;
	}
	public ArrayList<Drone> getAllDrones() {
		return allDrones;
	}
	public void setAllDrones(ArrayList<Drone> allDrones) {
		this.allDrones = allDrones;
	}
	public void setAllSubscriptions(ArrayList<Subscription> allSubscriptions) {
		this.allSubscriptions = allSubscriptions;
	}
	public int getSubscriptionNum() {
		return subscriptionNum;
	}
	
	public int getManagersNum() {
		return managersNum;
	}
	
	public int getDronesNum() {
		return dronesNum;
	}
	
	// add subscription function
	public void addSubscription(Subscription newSubscription)
	{
		if(newSubscription == null) {
			System.out.println("ERROR : null subscription!!!");
			return;
		}
		for(Subscription s : allSubscriptions) {
			if(s.getSubCode() == newSubscription.getSubCode()){
				System.out.println("The subscription is already here!");
				return;
			}
		}
		
		allSubscriptions.add(newSubscription);
		subscriptionNum++;
		System.out.println("subscription added successfuly ;) ");
	}	
	
	// add Manager function
	public void addManager(Manager newManager)
	{
		if(newManager == null) {
			System.out.println("ERROR : null manager!!!");
			return;
		}
		for(Manager m : allManagers) {
			if(m.getId() == newManager.getId()){
				System.out.println("The manager is already here!");
				return;
			}
		}
		
		allManagers.add(newManager);
		managersNum++;
		System.out.println("manager added successfuly ;) ");
	}	
	
	// add drone function
	public void addDrone(Drone newDrone)
	{
		if(newDrone == null) {
			System.out.println("ERROR : null drone!!!");
			return;
		}
		for(Drone d : allDrones) {
			if(d.getDroneCode() == newDrone.getDroneCode()){
				System.out.println("The drone is already here!");
				return;
			}
		}

		allDrones.add(newDrone);
		dronesNum++;
		System.out.println("Drone added successfuly ;) ");
	}
	
	
	

}

