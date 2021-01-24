package domain;

/**
 * @author  Ramanjot Kaur
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0
 * This class extend the property abstract class, it represents a Community property 
 */

public class Community extends Property {
	//category of the community
	private String category;
	//number of events of the community
	private int numberOfEvents;
	//constants
	private static final double CIV_RATE = 0.0025;
	//waste management units and fee
	private static final int WASTE_MANAGEMENT_UNITS = 1;
	private static final double WASTE_MANAGEMENT_FEES = 400.00;
	//green waste management units and fee
	private static final int GREEN_WASTE_MANAGEMENT_UNITS = 1;
	private static final double GREEN_WASTE_MANAGEMENT_FEES = 90.00;
	//fire services base charge and percent to charge of CIV
	private static final double FIRE_SERVICES_BASE = 100;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	//traffic managment levy fees
	private static final double TRAFIC_MANAGEMENT_LEVY_FEES = 200.00;
	
	//extra services
	private ServiceType traficManagementLevy;
	private ServiceType wasteManagement;
	private ServiceType greenWasteManagement;
	private ServiceType fireServicesLevy;
	

	

	/*
	 * Parameterized constructor to initialize the commercial property object
	 * with all the attributes
	 * */
	
	public Community(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,
			String category,int numberOfEvents) {
			//passing attributes to super class which is property
			super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
			//setting categories and number of events
			this.category=category;
			this.numberOfEvents=numberOfEvents;
			setCapitalImprovedRate(CIV_RATE);
	}
	
	/*
	 * Parameterized constructor with default values except for number of events
	 * */
	public Community(int numberOfEvents) {
		//setting number of events
		this.numberOfEvents=numberOfEvents;
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	/*
	 * Default constructor
	 * */
	public Community() {
		super();
		this.category="sporting";
		this.numberOfEvents=5;
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	//getter and setter for class attributes
	public String getCategory() {
		return category;
	}





	public void setCategory(String category) {
		this.category = category;
	}





	public int getNumberOfEvents() {
		return numberOfEvents;
	}





	public void setNumberOfEvents(int numberOfEvents) {
		this.numberOfEvents = numberOfEvents;
	}




	//over ridden method to set up extra services needed by the property there are 4 extra services
	@Override
	public void setUpExtraServices() {
		wasteManagement = new UnitAndRateService("Waste Management",
				  WASTE_MANAGEMENT_UNITS,
				  WASTE_MANAGEMENT_FEES);
		greenWasteManagement = new UnitAndRateService("Green Waste Management",
				  GREEN_WASTE_MANAGEMENT_UNITS,
				  GREEN_WASTE_MANAGEMENT_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
		traficManagementLevy = new UnitAndRateService("Trafic Management Levy",numberOfEvents,TRAFIC_MANAGEMENT_LEVY_FEES);
		
	}
	
	//function to get the extra services charges
	@Override
	public double calculateExtraServices() {
		// TODO Auto-generated method stub
		return wasteManagement.calculateChargeForServiceType() +
				   greenWasteManagement.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType()+
				   traficManagementLevy.calculateChargeForServiceType();
	}
	
	//to show the community property details in well formatted way with all necessary details
	@Override
	public String toString() {
		return  super.toString() + "Community [\n" + 
				 "Category=" + category + ", NumberOfEvents=" +  numberOfEvents+"\n"+
				wasteManagement.toString() + "\n" +
				greenWasteManagement.toString() + " \n " +
				fireServicesLevy.toString() + " \n "+
				traficManagementLevy.toString() + "]\n ";
	}
	
}
