package domain;

/**
 * @author Prince  
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0
 * This class extend the property abstract class, it represents a Hospital property 
 */
public class Hospital extends Property{

	//status of the hospital
	private String status;
	//facilities of the hospital
	private String[] facilities;
	//number of floors in the hosptial
	private int numberOfFloors;
	private ServiceType industrialWasteDisposal;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 600.00;
	private ServiceType fireServicesLevy;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	private static final double CIV_RATE = 0.0036;
	/*
	 * Parameterized constructor to initialize the Hospital property object with all attributes
	 * with all the attributes
	 * */
	
	public Hospital(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,
			String status,String[] facilities,int numberOfFloors) {		
			//passing attributes to super class which is property
			super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
			setCapitalImprovedRate(CIV_RATE);
			//setting attributes
			this.status=status;
			this.facilities=facilities;
			this.numberOfFloors=numberOfFloors;	
	}
	
	
	/*
	 * Default constructor
	 * */
	public Hospital() {
		super();
		this.status="Public";
		this.facilities=new String[2];
		this.facilities[0]="OPD";
		this.facilities[1]="ICU";
		this.numberOfFloors=2;
		setCapitalImprovedRate(CIV_RATE);
		
	}
	
	
	//getter and setters
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String[] getFacilities() {
		return facilities;
	}



	public void setFacilities(String[] facilities) {
		this.facilities = facilities;
	}



	public int getNumberOfFloors() {
		return numberOfFloors;
	}



	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}


	//setting up extra services
	@Override
	public void setUpExtraServices() {
		industrialWasteDisposal = new UnitAndRateService("Industrial Waste Disposal",
				INDUSTRIAL_WASTE_DISPOSAL_UNITS,
				INDUSTRIAL_WASTE_DISPOSAL_FEES);
		
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
	}
	
	
	//calculating extra services
	@Override
	public double calculateExtraServices() {
		// TODO Auto-generated method stub
		return industrialWasteDisposal.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		//to get all the facilities in the hospital
		String facilitiestr="";
		for(int i=0;i<facilities.length;i++) {
			facilitiestr+=facilities[i]+"|";
		}
		
		return  super.toString() + "Hospital [\n" + 
				"Status=" + status+ ", Number Of Floors="+numberOfFloors+", Facilities="+facilitiestr+ "\n"+
				industrialWasteDisposal.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}
	
}
