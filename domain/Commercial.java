package domain;

/**
 * @author  Ramanjot Kaur
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0
 * This class extend the property abstract class, it represents a commercial property 
 */
public class Commercial extends Property {
	
	//name of the business
	private String buisnessName;
	//abn of the busines
	private String ABN;
	//services needed by commercial property
	private ServiceType wasteManagement;
	private ServiceType fireServicesLevy;
	//constants to store the detail of charges and units
	private static final int WASTE_MANAGEMENT_UNITS = 2;
	private static final double WASTE_MANAGEMENT_FEES = 400.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	private static final double CIV_RATE = 0.006;
	
	
	/*
	 * Parameterised constructor to initialise the commercial property object
	 * with all the attributes
	 * */
	
	public Commercial(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,String buisnessName,String ABN) {
		//passing attributes to super class which is property
		super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
		//setting attributes
		this.buisnessName=buisnessName;
		this.ABN=ABN;
		setCapitalImprovedRate(CIV_RATE);
		
	}
	
	
	/**
	 * Default constructor to initialise the commercial property object with default values
	 * */
	public Commercial() {
		this.buisnessName="";
		this.ABN="";
		setCapitalImprovedRate(CIV_RATE);
	}

	
	
	//getter and setter methods
	public String getBuisnessName() {
		return buisnessName;
	}



	public void setBuisnessName(String buisnessName) {
		this.buisnessName = buisnessName;
	}



	public String getABN() {
		return ABN;
	}



	public void setABN(String aBN) {
		ABN = aBN;
	}


	
	//over ridden method to set up extra services needed by the property 
	@Override
	public void setUpExtraServices() {
		//waste management service
		wasteManagement = new UnitAndRateService("Waste Management",
				  WASTE_MANAGEMENT_UNITS,
				  WASTE_MANAGEMENT_FEES);
		//fire levy service
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
	}
	//function to get the extra services charges
	@Override
	public double calculateExtraServices() {		
		return wasteManagement.calculateChargeForServiceType() +
			   fireServicesLevy.calculateChargeForServiceType();
	}
	
	//to show the commercial property details in well formatted way with all necessary details
	@Override
	public String toString() {
		return  super.toString() + "Commercial [\n" + 
				 "Buisness Name=" + buisnessName + ", ABN=" +  ABN+"\n"+
									wasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}
	
}
