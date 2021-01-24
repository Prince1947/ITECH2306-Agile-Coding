package operation;

import java.util.Scanner;

import domain.RatePayer;

/**
 * @author Prince and Ramanjot Kaur
 * created 29-Nov-2020 8:30:00am.
 * @version 1.0
 * Concrete class of screen that extends FunctionalDialog and allows for the addition of a RatePayer.
 */

public class AddRatePayer extends FunctionalDialog {

	private RatePayer ratepayer;
	public AddRatePayer(Scanner console) {
		super(1, console);
	}

	@Override
	public void obtainInput(int i) {
		try {
			String name="",address="",postcode="",telephone="",company="";
			boolean charitablestatus;
			
			System.out.print("Please enter the name : ");
			name=getScanner().nextLine();
			System.out.print("Please enter the Postal Address : ");
			address=getScanner().nextLine();
			System.out.print("Please enter the Postal Code : ");
			postcode=getScanner().nextLine();
			System.out.print("Do you want to add Telephone Number (y/n) : ");
			String confirmationForTelephoneNumber = getScanner().nextLine();
			
			while(!(confirmationForTelephoneNumber.equals("y") || confirmationForTelephoneNumber.equals("n")) ) {
				System.out.println("WRONG INPUT !! Kindly Enter (y/n) : ");
				confirmationForTelephoneNumber = getScanner().nextLine();				
			}			
			if(confirmationForTelephoneNumber.equals("y")) {
				System.out.println("Enter Telephone Number : ");
				telephone=getScanner().nextLine();
			}			
			System.out.println("Enter the type of Company(eg individual or public)");
			company=getScanner().nextLine();
			System.out.println("Enter the charitable status of rate payer");
			charitablestatus=validateBoolean();	
			if(MainMenu.ratePayerCount<MainMenu.ratepayers.length) {
				this.ratepayer=new RatePayer(name,address,postcode,telephone,company,charitablestatus);			
				MainMenu.ratepayers[MainMenu.ratePayerCount]=ratepayer;
				MainMenu.ratePayerCount++;
			}
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
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
		// TODO Auto-generated method stub
		System.out.println("Rate Payer added");
		if(ratepayer!=null) {
			System.out.println("Rate Payer:"+this.ratepayer);
		}else {
			System.out.println("Error while adding the rate payer!");
		}
		setStillRunning(false);
	}

}
