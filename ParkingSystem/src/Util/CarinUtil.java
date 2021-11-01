package Util;

public class CarinUtil {
	private String ParkId;
	private String isEmpty;

	public CarinUtil(String ParkId, String isEmpty)
	{
		this.ParkId=ParkId;
		this.isEmpty=isEmpty;
	}

	public String getParkId() {
		return ParkId;
	}

	public void setParkId(String parkId) {
		ParkId = parkId;
	}

	public String getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
	}

	public CarinUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

}
