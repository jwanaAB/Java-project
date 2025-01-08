package droneProject_212387575;

public class ExpressDrone extends Drone{
	private double speed;
	private double extraPrice;
	
	//building constructor getters setters and toString
	public ExpressDrone(int droneCode, boolean status, double battery, double speed, double extraPrice) {
		super(droneCode, status, battery);
		this.speed = speed;
		this.extraPrice = extraPrice;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getExtraPrice() {
		return extraPrice;
	}
	public void setExtraPrice(double extraPrice) {
		this.extraPrice = extraPrice;
	}
	@Override
	public String toString() {
		return "ExpressDrone [speed=" + speed + ", extraPrice=" + extraPrice + ", code=" + getDroneCode()
				+ ", status=" + isStatus() + ", Battery=" + getBattery() + "]";
	}
	
	

}

