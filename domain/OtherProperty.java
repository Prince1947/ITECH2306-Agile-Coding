package domain;

/**
 * @author TAKeogh
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0.
 * Concrete class of Property abstract class.
 * Currently a Fire Services Levy is charged together with CIV value multiplied by a CIV rate.
 *
 */
public class OtherProperty extends Property {

	private String specialDescription;
	private static final double CIV_RATE = 0.0025;
	private static final double FIRE_SERVICES_BASE = 150;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	private ServiceType fireServicesLevy;
	
	
	public OtherProperty(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,
			String specialDescription) {			
			super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
		this.specialDescription=specialDescription;
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	public OtherProperty() {
		super();
		this.setSpecialDescription("None");
		setCapitalImprovedRate(CIV_RATE);
	}
	
	

	public String getSpecialDescription() {
		return specialDescription;
	}

	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}

	@Override
	public void setUpExtraServices() {
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
				FIRE_SERVICES_BASE,
				FIRE_SERVICES_PERCENT,
				getCapitalImprovedValue());
		
	}

	@Override
	public double calculateExtraServices() {
		return fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		return super.toString() + "OtherProperty [\n" +
				 "Special Description=" + specialDescription+"\n"+
								  fireServicesLevy.toString() + "]\n ";	
	}

}
