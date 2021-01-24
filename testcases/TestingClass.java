package testcases;



/**
 * @author Prince  
 * created 1-9-2021
 * @version 1.0
 * The test cases to test different property classes for rate calculation based on the serivces they need as per the specification
 * the testing class tragets residential,school,vacant land, and hospital
 * 
 */



import static org.junit.Assert.*;

import org.junit.Test;

import domain.Hospital;
import domain.Residential;
import domain.School;
import domain.VacantLand;

public class TestingClass {

	
	//test to check residential property rate without charity discount
	@Test
	public void testResidentialWithoutCharity() {
		//creating object using default constructor
		Residential res= new Residential();
		//setting civ 
		res.setCapitalImprovedValue(400000);
		//setting up extra services
		res.setUpExtraServices();
		//getting the rate calculated
		double value=res.calculateRates();
		//validating that rate calculated is correct
		assertEquals(value, 2228.0, 0.00000001);

	}
	
	
	//test to check residential property rate with charity discount
	@Test
	public void testResidential() {
		
		Residential res= new Residential();
		res.setCapitalImprovedValue(400000);
		//setting the charity to true
		res.getOwner().setCharity(true);
		res.setUpExtraServices();
		double value=res.calculateRates();
		//rate must be 20% less that is 2228-445.6=1782.4
		assertEquals(value, 1782.4, 0.00000001);

	}
	
	//test to check school property rate without charity discount
	@Test
	public void testSchool() {
		//testing for school under small category that is 1-50 member
		School res= new School("small");
		res.setCapitalImprovedValue(400000);
		res.getOwner().setCharity(false);
		
		//setting up extra services
		res.setUpExtraServices();
		//getting the calculated rate for the property
		double value=res.calculateRates();
		//validating that rate calculated is correct
		assertEquals(value, 2889.0, 0.00000001);
		
		//changing the category to medium as traffic management charge will change and increase by 20$ as small had 200+ 60
		res.setCategory("medium");
		//setting up extra services
		res.setUpExtraServices();
		//getting the calculated rate for the property
		value=res.calculateRates();
		//validating that rate calculated is correct
		assertEquals(value, 2909.0, 0.00000001);
		
		//changing the category to medium as traffic management charge will change and increase by 20$ as medium had 200+80 and large will have 200+100
		res.setCategory("large");
		//setting up extra services
		res.setUpExtraServices();
		//getting the calculated rate for the property
		value=res.calculateRates();
		//validating that rate calculated is correct
		assertEquals(value, 2929.0, 0.00000001);
	}
	
	//test to check school property rate with charity discount
	@Test
	public void testSchoolWithCharity() {
		School res= new School("small");
		res.setCapitalImprovedValue(400000);
		res.getOwner().setCharity(true);
		res.setUpExtraServices();
		double value=res.calculateRates();		
		//checking that 20% discount is applied
		assertEquals(value, 2311.2, 0.000001);		

		res.setCategory("medium");
		res.setUpExtraServices();
		value=res.calculateRates();
		//checking that 20% discount is applied
		assertEquals(value, 2909.0-(2909*0.2), 0.0001);
		
		res.setCategory("large");
		res.setUpExtraServices();
		value=res.calculateRates();
		//checking that 20% discount is applied
		assertEquals(value, 2929.0-(2929*0.2), 0.0001);

	}
	
	//test to check vacant land property rate without charity discount
	@Test
	public void testVacantLand() {
		//creating object using default constructor
		VacantLand res= new VacantLand();
		//setting the civ
		res.setCapitalImprovedValue(400000);
		res.getOwner().setCharity(false);
		//setting up extra services
		res.setUpExtraServices();
		//calculating the rate
		double value=res.calculateRates();
		//validating that rate calculated is correct
		assertEquals(value, 478.0, 0.0001);
	}
	
	//test to check vacant land property rate with charity discount
	@Test
	public void testVacantLandWithCharity() {
		//creating object using default constructor
		VacantLand res= new VacantLand();
		res.setCapitalImprovedValue(400000);
		//setting the charity of owner to true
		res.getOwner().setCharity(true);
		res.setUpExtraServices();
		double value=res.calculateRates();
		//validating that rate calculated is correct with 20% discount
		assertEquals(value, 478.0-478.0*0.20, 0.0001);
	}
	
	
	//test to check hospital property rate without charity discount
	@Test
	public void testHospitalCharity() {
		//creating object using default constructor
		Hospital res= new Hospital();
		res.setCapitalImprovedValue(400000);
		res.getOwner().setCharity(false);

		//calculating extra service charge
		res.setUpExtraServices();
		double value=res.calculateRates();
		assertEquals(value, 4068, 0.0001);
	}
	//test to check hospital property rate with charity discount
	@Test
	public void testHospitalWithCharity() {
		//creating object using default constructor
		Hospital res= new Hospital();
		res.setCapitalImprovedValue(400000);
		//setting the charity of owner to true
		res.getOwner().setCharity(true);
		//calculating extra service charge
		res.setUpExtraServices();		
		double value=res.calculateRates();
		//validating that rate calculated is correct with 20% discount
		assertEquals(value, 3254.4, 0.0001);
	}
	

}
