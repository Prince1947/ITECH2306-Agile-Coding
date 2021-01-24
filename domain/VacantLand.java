package domain;

/**
 * @author Prince  
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0.
 * This class extend the property abstract class, it represents a Hospital property 
 *
 */
public class VacantLand extends Property{
	
	//overlays for the land
	private String[] overlays;
	//extra service
	private ServiceType fireServicesLevy;
	//constants for extra service charges
	private static final double FIRE_SERVICES_BASE = 50;
	private static final double FIRE_SERVICES_PERCENT = 0.00007;
	private static final double CIV_RATE = 0.001;
	
	/*
	 * Parameterized constructor to initialize the VacantLand property object
	 * with all the attributes
	 * */
	public VacantLand(String description,String location,double area,double siteValue,double capitalImprovedValue, 
			double netAnnualValue,String valuationDate,RatePayer owner,String[] overlays) {
		super(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,owner);
		this.overlays=overlays;
		setCapitalImprovedRate(CIV_RATE);
	}
	
	public VacantLand() {
		overlays=new String[1];
		overlays[0]="";
		setCapitalImprovedRate(CIV_RATE);
	}
	
	
	//getter and setters
	public String[] getOverlays() {
		return overlays;
	}
	
	public void setOverlays(String[] overlays) {
		this.overlays = overlays;
	}
	
	
	//over ridden method to set up extra services needed by the property there are 1 extra services
	@Override
	public void setUpExtraServices() {
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
	}

	//calculating extra services
	@Override
	public double calculateExtraServices() {		
		return fireServicesLevy.calculateChargeForServiceType();
	}
	
	
	
	@Override
	public String toString() {
		String overslaystr="";
		for(int i=0;i<overlays.length;i++) {
			overslaystr+=overlays[i]+"|";
		}
		return  super.toString() + "VacantLand [\n Overlay:" + overslaystr+"\n"+
									fireServicesLevy.toString() + " ]\n ";
	}

}
