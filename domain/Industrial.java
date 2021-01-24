package domain;

/**
 * @author  Ramanjot Kaur
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0.
 * This class extend the property abstract class, it represents a industrial property
 */
public class Industrial extends Property {

	//the hazard status of the industry
	private String hazardStatus;
	//the heavy vehicle status
	private String heavyVehicleStatus;
	
	//extra services
	private ServiceType industrialWasteDisposal;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 600.00;
	private ServiceType fireServicesLevy;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	private static final double CIV_RATE = 0.0065;
	

	/*
	 * Parameterized constructor to initialize the industrial property object
	 * with all the attributes
	 * */
	public Industrial(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,
			String hazardStatus,String heavyVehicleStatus) {			
			//passing attributes to super class which is property

			super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
			//setting class attributes
			this.hazardStatus=hazardStatus;
			this.heavyVehicleStatus=heavyVehicleStatus;
			setCapitalImprovedRate(CIV_RATE);
	}

	/*
	 * Default constructor
	 * */
	public Industrial() {
		super();
		this.hazardStatus="";
		this.heavyVehicleStatus="";
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	//getter and setters

	public String getHazardStatus() {
		return hazardStatus;
	}


	public void setHazardStatus(String hazardStatus) {
		this.hazardStatus = hazardStatus;
	}


	public String getHeavyVehicleStatus() {
		return heavyVehicleStatus;
	}


	public void setHeavyVehicleStatus(String heavyVehicleStatus) {
		this.heavyVehicleStatus = heavyVehicleStatus;
	}

	
	//over ridden method to set up extra services needed by the property there are 2 extra services
	@Override
	public double calculateExtraServices() {
		// TODO Auto-generated method stub
		return industrialWasteDisposal.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}

	//function to get the extra services charges
	@Override
	public void setUpExtraServices() {
		// TODO Auto-generated method stub
		industrialWasteDisposal = new UnitAndRateService("Industrial Waste Disposal",
				INDUSTRIAL_WASTE_DISPOSAL_UNITS,
				INDUSTRIAL_WASTE_DISPOSAL_FEES);
		
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
	}

	
	@Override
	public String toString() {
		return  super.toString() + "Industrial [\n" + 
				"Hazard Status=" + hazardStatus+ ", Heavy Vehicle Status="+heavyVehicleStatus+ "\n"+
				
				industrialWasteDisposal.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}
	
}
