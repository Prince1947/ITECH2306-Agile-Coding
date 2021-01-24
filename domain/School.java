package domain;

/**
 * @author Prince  
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0.
 * This class extend the property abstract class, it represents a Hospital property 
 */
public class School extends Property {
	//classification
	private String classification;
	//category of the school small,medium and large
	private String category;
	
	//services
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	private ServiceType traficManagementLevy;
	
	//constants for extra service rate and unit charges
	private static final double CIV_RATE = 0.003;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	private static final int INDUSTRIAL_WASTE_DISPOSAL_UNITS = 2;
	private static final double INDUSTRIAL_WASTE_DISPOSAL_FEES = 600.00;
	
	//assuming single unit
	private static final int TRAFIC_MANAGEMENT_LEVY_UNITS = 1;
	private static final double TRAFIC_MANAGEMENT_LEVY_FEES = 200.00;
	
	
	/*
	 * Parameterized constructor to initialize the industrial property object
	 * with all the attributes
	 * */
	public School(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,
			String classification,String category) {
			super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
		this.classification=classification;
		this.category=category;
		setCapitalImprovedRate(CIV_RATE);
	}

	/**
	 * Default constructor
	 * */
	public School() {
		super();
		this.classification="public";
		this.category="medium";
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	/*
	 * Parameterized constructor to initialize the school with just category attribute
	 * */
	public School(String category) {
		super();
		this.classification="public";
		this.category=category;
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	
	
	//getter and setter methods
	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	
	//over ridden method to set up extra services needed by the property there are 3 extra services
	@Override
	public void setUpExtraServices() {
		// TODO Auto-generated method stub
		industrialWasteDisposal = new UnitAndRateService("Waste Management",
				INDUSTRIAL_WASTE_DISPOSAL_UNITS,
				INDUSTRIAL_WASTE_DISPOSAL_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
		
		//to get the extra charge based on the category
		double extra=0;
		if(this.category.equals("small")) {
			extra=60;
		}else if (this.category.equals("medium")) {
			extra=80;
		}else if (this.category.equals("large")) {
			extra=100;
		}
		traficManagementLevy = new BaseAndExtraService("Trafic Management Levy",TRAFIC_MANAGEMENT_LEVY_UNITS,TRAFIC_MANAGEMENT_LEVY_FEES+extra);
	}
	
	
	
	
	//calculating extra services
	@Override
	public double calculateExtraServices() {
		// TODO Auto-generated method stub
		return industrialWasteDisposal.calculateChargeForServiceType() +
				traficManagementLevy.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}
	
	
	@Override
	public String toString() {
		return  super.toString() + "School [\n" + "classification=" + classification + ", category=" +  category+"\n"+
				industrialWasteDisposal.toString() + "\n" +
				traficManagementLevy.toString() + "\n" +
				fireServicesLevy.toString() + " ]\n ";
	}
	
}
