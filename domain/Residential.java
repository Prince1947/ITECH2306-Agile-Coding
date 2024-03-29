package domain;

/**
 * @author TAKeogh
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0.
 * Concrete class of Property abstract class. Currently, Waste Management, Green Waste Management and
 * a Fire Services Levy are charged together with CIV value multiplied by a CIV rate.
 */

public class Residential extends Property{

	private String propertyType;
	private String architecturalStyle;
	private static final double CIV_RATE = 0.004;
	private static final int WASTE_MANAGEMENT_UNITS = 1;
	private static final double WASTE_MANAGEMENT_FEES = 400.00;
	private static final int GREEN_WASTE_MANAGEMENT_UNITS = 1;
	private static final double GREEN_WASTE_MANAGEMENT_FEES = 90.00;
	private static final double FIRE_SERVICES_BASE = 110;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType wasteManagement;
	private ServiceType greenWasteManagement;
	private ServiceType fireServicesLevy;
	
	
	
	public Residential(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,
			String propertyType,String architecturalStyle) {			
			super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
			setCapitalImprovedRate(CIV_RATE);
			this.propertyType=propertyType;
			this.architecturalStyle=architecturalStyle;		
	}
	
	
	public Residential() {
		super();
		// We are explicit about our defaults for Strings
		this.setPropertyType("House");
		this.setArchitecturalStyle("Modern");
		setCapitalImprovedRate(CIV_RATE);
	}
	
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getArchitecturalStyle() {
		return architecturalStyle;
	}

	public void setArchitecturalStyle(String architecturalStyle) {
		this.architecturalStyle = architecturalStyle;
	}
	
	@Override
	public void setUpExtraServices() {
		// At this stage, this is perhaps more understandable but there may be better alternatives
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
	}
	
	@Override
	public double calculateExtraServices() {		
		return wasteManagement.calculateChargeForServiceType() +
			   greenWasteManagement.calculateChargeForServiceType() +
			   fireServicesLevy.calculateChargeForServiceType();
	}

	@Override
	public String toString() {
		return  super.toString() + "Residential [\n" + 
									 "Property Type=" + propertyType + ", Architectural Style=" +  architecturalStyle+"\n"+
									wasteManagement.toString() + "\n" +
									greenWasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}

}
