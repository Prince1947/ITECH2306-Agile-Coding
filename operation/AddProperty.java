package operation;

import java.text.NumberFormat;
import java.util.Scanner;

import domain.Commercial;
import domain.Community;
import domain.Hospital;
import domain.Industrial;
import domain.OtherProperty;
import domain.Property;
import domain.RatePayer;
import domain.Residential;
import domain.School;
import domain.VacantLand;

/**
 * @author Prince and Ramanjot Kaur
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0
 * Concrete class of screen that extends FunctionalDialog and allows for the addition of a Property for a RatePayer.
 */

public class AddProperty extends FunctionalDialog {

	
	private static final int RESIDENTIAL = 1,
			 COMMERCIAL = 2,
			 VACANT_LAND = 3,
			 HOSPITAL = 4,
			 INDUSTRIAL =5,
			 SCHOOL = 6,
			 COMMUNITY = 7,
			 OTHER = 8,
			 END = 0;
	private static final int MIN_PROPERTY_TYPES = 0;
	private static final int MAX_PROPERTY_TYPES = 8;
	private static final String PROPERTY_TYPE_PROMPT = "What type of property are we dealing with? \n" +
											RESIDENTIAL + ". Residential \n" +
											COMMERCIAL + ". Commercial \n" +
											VACANT_LAND + ". Vacant Land \n" +
											HOSPITAL + ". Hospital \n" +
											INDUSTRIAL + ". Industrial \n" +
											SCHOOL + ". School \n" +
											COMMUNITY + ". Community \n" +
											OTHER + ". Other \n" +
											END + ". To exit";
	
	private int propertyType;
	private static final String CIV_PROMPT = "What is the value of the property? ";
	private static final double MIN_CIV = 100.00;
	private static final double MAX_CIV = 50000000.00;
	private static final NumberFormat MYFORMAT = NumberFormat.getNumberInstance();
	private double capitalImprovedValue;
	private static final int SMALL = 1, 
				 MEDIUM = 2,
				 LARGE = 3;
	private static final int MIN_SCHOOL_CATEGORIES = 1;
	private static final int MAX_SCHOOL_CATEGORIES = 3;
	private static final String SCHOOL_CATEGORY_PROMPT = "What type of category is the school or community building? \n" +
												SMALL + ". Small (1-20 members) \n" +
												MEDIUM +". Medium (21-100) \n" +
												LARGE + ". Large (101 upwards \n";
	private int schoolCategory;
	private static final int MIN_COMMUNITY_EVENTS = 1;
	private static final int MAX_COMMUNITY_EVENTS = 10;
	private static final String COMMUNITY_NUMBER_EVENTS_PROMPT = "How many community events will you hold? \n" +
													MAX_COMMUNITY_EVENTS + " is the maximum number you are permitted: \n";
	private int numberCommunityEvents;
	private static final String SELECT_RATE_PAYER_PROMPT = "Select the rate payer for whom property is to be added:";
	private boolean charityStatus;
	private static final int MAX_NO_USER_INPUTS = 4;
	
	
	//general property attributes
	private String description;
	private String location;
	private double area;
	private double siteValue;
	private double capitalImprovedRate;
	private double netAnnualValue;
	private String valuationDate;
	private RatePayer ratePayer;
	
	//residential property attributes
	private String resPropertyType;
	private String architecturalStyle;
	
	//commercial property attributes
	private String businessname,abn;
	
	//vacant lands 
	private String[] overlays;
	
	//hospital
	private String hospitalStatus;
	private String[] facilities;
	private int numberOfFloors;
	
	//industrial
	private String hazardStatus;
	private String heavyVehicleStatus;
	
	//school
	private String classification;
	//community
	private String category;
	
	//other
	private String specialDescription;
	
	public AddProperty(Scanner console) {
		super(MAX_NO_USER_INPUTS,console);
	}
	
	@Override
	public void obtainInput (int i) {
		switch (i)
		{
		case 0:
			propertyType = obtainIntInput(MIN_PROPERTY_TYPES, 
										  MAX_PROPERTY_TYPES, 
										  PROPERTY_TYPE_PROMPT);
			if (propertyType == END)
				setStillRunning(false);
			break;
		case 1:
			capitalImprovedValue = obtainDoubleInput(MIN_CIV, 
													 MAX_CIV, 
													 CIV_PROMPT);
			
			System.out.println("Please enter property description((lot number and portfolio):");
			description=getScanner().nextLine();
			System.out.println("Please enter location:");
			location=getScanner().nextLine();
			area=obtainDoubleInput(1,500000,"Please enter area:");
			capitalImprovedValue=obtainDoubleInput(MIN_CIV, MAX_CIV, CIV_PROMPT);
			siteValue=obtainDoubleInput(MIN_CIV, MAX_CIV, "Please enter site value:");
			netAnnualValue=obtainDoubleInput(MIN_CIV,MAX_CIV,"Please enter net annual value:");
			System.out.println("Please enter valuation date(dd MMM yyyy):");
			valuationDate=getScanner().nextLine();
			break;
		case 2:
			if (propertyType == SCHOOL) {
					schoolCategory = obtainIntInput(MIN_SCHOOL_CATEGORIES, 
												MAX_SCHOOL_CATEGORIES, 
												SCHOOL_CATEGORY_PROMPT);
					System.out.println("Please enter classification – public, private or independent: ");
					classification=getScanner().nextLine();
				}
			else if (propertyType == COMMUNITY) {
					numberCommunityEvents = obtainIntInput(MIN_COMMUNITY_EVENTS, 
														   MAX_COMMUNITY_EVENTS, 
														   COMMUNITY_NUMBER_EVENTS_PROMPT );
					System.out.println("Please enter category (eg cultural, sporting, health):");
					classification=getScanner().nextLine();
					
				}
			else if (propertyType==RESIDENTIAL) {
						System.out.println("Please enter type (eg townhouse, house):");
						resPropertyType=getScanner().nextLine();
						System.out.println("Please enter Architectural style (eg Victorian, Federation):");
						architecturalStyle=getScanner().nextLine();
				}
			else if (propertyType==COMMERCIAL) {
				System.out.println("Please enter business name:");
				businessname=getScanner().nextLine();
				System.out.println("Please enter ABN");
				abn=getScanner().nextLine();
			}
			else if (propertyType==VACANT_LAND) {
		
				int n = obtainIntInput(1,20,"Please enter Number of Overlay's to store: ");
				String temp[] = new String[n];
				for(int i1 = 0 ; i1<n ; i1++) {
					System.out.println("Please Enter Overlay : ");	
					temp[i1]=getScanner().nextLine();
				}
				overlays=temp;
			}
			else if (propertyType==HOSPITAL) {
				int n = obtainIntInput(1,20,"Please enter number of facilities: ");
				String temp[] = new String[n];
				for(int i1 = 0 ; i1<n ; i1++) {
					System.out.println("Please enter facility : ");	
					temp[i1]=getScanner().nextLine();
					
				}
				facilities=temp;
				System.out.println("Please enter Status of Hospital (Public or Private):");
				hospitalStatus=getScanner().nextLine();
				numberOfFloors=obtainIntInput(1,100,"Please enter number of facilities: ");
			}
			else if(propertyType==INDUSTRIAL) {				
				System.out.println("Please enter Hazard status of Industry : ");
				hazardStatus=getScanner().nextLine();
	            System.out.println("Please enter Heavy Vehicle status: ");
	            heavyVehicleStatus=getScanner().nextLine();
			}
			else if(propertyType==OTHER) {
				System.out.println("Please enter special description : ");
				specialDescription=getScanner().nextLine();
			}
			
			
			break;
		case 3:
			ratePayer = selectRatePayer(SELECT_RATE_PAYER_PROMPT);
			if (ratePayer == null)
				setStillRunning(false);
			break;
		}
	}
	
	// These next input and validation methods could perhaps be tidied up/refactored
	private int obtainIntInput(int min, int max, String prompt) {
		System.out.println(prompt);
		return validateInt(min, max);
	}

	private double obtainDoubleInput(double min, double max, String prompt) {
		System.out.println(prompt);
		return validateDouble(min, max);
	}

	private RatePayer selectRatePayer(String prompt) {
		System.out.println(prompt);
		if(MainMenu.ratePayerCount==0) {
			System.out.println("No rate payer records found");
			return null;
		}
		while(true) {			
			int i=0;
			for(i=0;i<MainMenu.ratePayerCount;i++){
				System.out.println((i+1)+")"+MainMenu.ratepayers[i]);
			}
			int id=getScanner().nextInt();
			if(id-1>MainMenu.ratePayerCount || id-1<0) {
				System.out.println("Invalid ID");	
				return null;
			}else {				
				return MainMenu.ratepayers[id-1];			
			}
		}
	}
	
	private int validateInt(int min, int max) {
		int userInput;
		do {
			System.out.print("Enter a selection ("+min + "-" + max +"):");
			if (!getScanner().hasNextInt())
				userInput = max+1;
			else
				userInput = getScanner().nextInt();	// obtain the input
			getScanner().nextLine();					// gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println();		// put a space before the next output	
		return userInput; 

	}
	
	private double validateDouble(double min, double max){
		double userInput;
		do {
			MYFORMAT.setMinimumFractionDigits(2);
			MYFORMAT.setMaximumFractionDigits(2);
			System.out.print("Enter a selection ("+ MYFORMAT.format(min) + "-" + MYFORMAT.format(max) +"):");

			if (!getScanner().hasNextDouble())
				userInput = max+0.01;
			else
				userInput = getScanner().nextDouble();	// obtain the input
			getScanner().nextLine();					// gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println();		// put a space before the next output	
		return userInput; 

	}
	
	private boolean validateBoolean(){
		boolean userInput;
		System.out.print("Enter a selection -(true or false)");

		if (!getScanner().hasNextBoolean()) {
			userInput = false;
			System.out.println("Invalid choice. Assuming false.");
		}	
		else
			userInput = getScanner().nextBoolean();	// obtain the input
		getScanner().nextLine();					// gets rid of the newline after the input we just read
		System.out.println();		                // put a space before the next output	
		return userInput; 

	}

	@Override
	public void respondToInput() {
		Property property = null;
	
		
		switch (propertyType) 
		{
			case(RESIDENTIAL):
				property = new Residential(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,resPropertyType,architecturalStyle);
				break;
			case(COMMERCIAL):
				property = new Commercial(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,businessname,abn);
				break;
			case(VACANT_LAND):
				property = new VacantLand(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,overlays);
				break;
			case(HOSPITAL):
				property = new Hospital(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,hospitalStatus,facilities,numberOfFloors);
				break;
			case(INDUSTRIAL):
				property = new Industrial(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,hazardStatus,heavyVehicleStatus);
				break;
			case(SCHOOL):
				String scategory="";
				if(schoolCategory==1) {
					scategory = "small";
				}else if (schoolCategory==2) {
					scategory="medium";
				}else if (schoolCategory==3) {
					scategory = "large";
				}
				property = new School(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,classification,scategory);
				
				break;
			case(COMMUNITY):
				property = new Community(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,classification,numberCommunityEvents);
				break;
			case(OTHER):
				property = new OtherProperty(description,location,area,siteValue,capitalImprovedValue,netAnnualValue,valuationDate,ratePayer,specialDescription);
				break;
			case(END):
				break;				
		}
		
		if (property != null) {
			property.setCapitalImprovedValue(capitalImprovedValue);
			property.setUpExtraServices();
			property.getOwner().setCharity(charityStatus);
			System.out.println("Details Selected: " + property +  "\n" +
							   "Owner Charity Status: " + property.getOwner().isCharity() + "\n\n" +	
							   "Total Rate Costs: " +property.calculateRates() + "\n");
			if(MainMenu.propertyCount<MainMenu.properties.length) {
					
				MainMenu.properties[MainMenu.propertyCount]=property;
				MainMenu.propertyCount++;
			}
			
		}

		
		
	}



}
