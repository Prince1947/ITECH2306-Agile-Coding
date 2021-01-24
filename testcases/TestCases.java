package testcases;


/**
 * @author Ramanjot Kaur
 * created 1-9-2021
 * @version 1.0
 * The test cases class test different commercial,industrial,community, and other type of properties to verify that their rates
 * is calculated as per the services and values given in specification
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

import domain.Commercial;
import domain.Community;
import domain.Industrial;
import domain.OtherProperty;
import domain.Residential;

public class TestCases {

	//testing for commercial property without charity 
	@Test
	public void testCommercial() {
		//creating commercial property object
		Commercial com= new Commercial();
		//setting up the CIV
		com.setCapitalImprovedValue(400000);
		//setting up service charges
		com.setUpExtraServices();
		//getting the rate
		double value=com.calculateRates();
		//checking if rate matches with the calculated rate
		assertEquals(value, 3428, 0.001);
		
	}
	
	
	//testing for commercial property with charity 
	@Test
	public void testCommercialCharity() {
		Commercial com= new Commercial();
		com.setCapitalImprovedValue(400000);
		//setting the charity to true to get 20% discount
		com.getOwner().setCharity(true);
		com.setUpExtraServices();
		double value=com.calculateRates();
		//checking with 3428-20% of 3428 as 20$ discount as owner's charity is set to true
		assertEquals(value, 3428-(3428*0.20), 0.001);
		
	}
	
	//testing for industrial property 
	@Test
	public void testIndustrial() {
		//creating industrial object
		Industrial indus= new Industrial();
		//setting up the CIV
		indus.setCapitalImprovedValue(400000);
		//setting up service charges
		indus.setUpExtraServices();
		//getting the rate
		double value=indus.calculateRates();	
		//checking if rate matches with the calculated rate
		assertEquals(value, 5228, 0.001);
		
	}
	
	//testing for industrial property with charity 
	@Test
	public void testIndustrialCharity() {
		Industrial indus= new Industrial();
		indus.setCapitalImprovedValue(400000);
		//setting the owner's charity status to true
		indus.getOwner().setCharity(true);
		indus.setUpExtraServices();
		double value=indus.calculateRates();
		//checking with 3428-20% of 3428 as 20$ discount as owner's charity is set to true
		assertEquals(value, 5228-(5228*0.20), 0.001);
		
	}

	
	//testing for community property 
	@Test
	public void testCommunity() {
		//creating community property object
		Community community= new Community();
		//setting up the CIV
		community.setCapitalImprovedValue(400000);		
		//checking for 5 events
		//setting events
		community.setNumberOfEvents(5);
		//setting up extra services
		community.setUpExtraServices();
		//calculating rates
		double value=community.calculateRates();
		//checking if rate matches with the calculated rate
		assertEquals(value, 2618, 0.001);
		
		//checking with changed number of events
		community.setNumberOfEvents(2);
		community.setUpExtraServices();
		value=community.calculateRates();
		//checking if rate matches with the calculated rate	as number of events is decreased by 3 so rate is also reduced by 3*200=600	
		assertEquals(value, 2018, 0.001);
		
	}
	
	//testing for community property 
	@Test
	public void testCommunityCharity() {
		//creating community property object with charity
		Community community= new Community();
		//setting up the CIV
		community.setCapitalImprovedValue(400000);
		//checking for 5 events
		community.setNumberOfEvents(5);
		community.getOwner().setCharity(true);
		community.setUpExtraServices();
		double value=community.calculateRates();
		assertEquals(value, 2618-(2618*0.20), 0.001);
		//checking with changed number of events (2)
		community.setNumberOfEvents(2);
		community.setUpExtraServices();
		value=community.calculateRates();
		assertEquals(value, 2018-(2018*0.20), 0.001);
		
		
		
	}
	
	//testing for Otherproperty 
	@Test
	public void testOther() {
		OtherProperty other= new OtherProperty();
		//setting up the civ
		other.setCapitalImprovedValue(400000);
		//setting serices charge
		other.setUpExtraServices();
		//getting the rate
		double value=other.calculateRates();	
		//checking if rate matches with the calculated rate		
		assertEquals(value, 1178, 0.001);
		
	}
	
	
	//testing for Otherproperty with charity
	@Test
	public void testOtherCharity() {
		OtherProperty other= new OtherProperty();
		other.setCapitalImprovedValue(400000);
		other.getOwner().setCharity(true);
		other.setUpExtraServices();
		double value=other.calculateRates();
		//checking if rate matches with the calculated rate with 20% discount		
		assertEquals(value, 1178-(1178*0.20), 0.001);
		
	}

}
