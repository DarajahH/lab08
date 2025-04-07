package academy.javapro.lab08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class InsuranceRatingEngine {
    // Knowledge base (facts about insurance rates)
	Map<String, Object> knowledgeBase = new HashMap<String, Object>();
    // Rules list
	List<Rule> rules = new ArrayList<>();
    // Constructor initializes the knowledge base and rules
	
	public InsuranceRatingEngine() {
		
	initializeKnowledgeBase();
	initializeRules();
		
	}
	

    private void initializeKnowledgeBase() {
        // Base rates by vehicle category
        // TODO: Add the following key baseRate.sedan with value 1000.0 to the knowledgeBase map
        // TODO: Add the following key baseRate.suv with value 1200.0 to the knowledgeBase map
        // TODO: Add the following key baseRate.luxury with value 1500.0 to the knowledgeBase map
        // TODO: Add the following key baseRate.sports with value 1800.0 to the knowledgeBase map
    	
    	knowledgeBase = new HashMap<>();
    	
    			knowledgeBase.put("baseRate.sedan", 1000.0);
    			knowledgeBase.put("baseRate.suv", 1200.0);
    	        knowledgeBase.put("baseRate.luxury", 1500.0);
    	        knowledgeBase.put("baseRate.sports", 1800.0);

        // Age risk factors
    	        
    	        knowledgeBase.put("ageFactor.16-19", 2.0);
    	        knowledgeBase.put("ageFactor.20-24", 1.5);
    	        knowledgeBase.put("ageFactor.25-65", 1.0);
    	        knowledgeBase.put("ageFactor.66+", 1.3);
    	        
        // TODO: Add the following key ageFactor.16-19 with value 2.0 to the knowledgeBase map
        // TODO: Add the following key ageFactor.20-24 with value 1.5 to the knowledgeBase map
        // TODO: Add the following key ageFactor.25-65 with value 1.0 to the knowledgeBase map
        // TODO: Add the following key ageFactor.66+ with value 1.3 to the knowledgeBase map

        // Accident surcharges
    	        
    	knowledgeBase.put("accidentSurcharge.0", 0.0);
        knowledgeBase.put("accidentSurcharge.1", 300.0);
        knowledgeBase.put("accidentSurcharge.2+", 600.0);
        
        // TODO: Add the following key accidentSurcharge.0 with value 0.0 to the knowledgeBase map
        // TODO: Add the following key accidentSurcharge.1 with value 300.0 to the knowledgeBase map
        // TODO: Add the following key accidentSurcharge.2+ with value 600.0 to the knowledgeBase map
    }

    private void initializeRules() {
        // Base rate rule - determines the starting premium based on vehicle type
    	
    	rules = new ArrayList();
    	
    	Rule baseRateRule = new Rule(
    	        "base rate",  // Rule name
    	        profile -> true, // Predicate: applies to all profiles
    	        (profile, premium) -> { // BiConsumer: action to take
    	            String vehicleCategory = determineVehicleCategory(profile); // Get vehicle category
    	            Double baseRate = knowledgeBase.get("baseRate." + vehicleCategory); // Get base rate from knowledgeBase
    	            if (baseRate != null) {
    	                premium.setBaseRate(baseRate); // Set base rate on premium object
    	            } else {
    	                // Handle the case where the vehicle category is not found
    	                System.err.println("Error: base rate not found for vehicle: " + vehicleCategory);
    	            }
    	        }
    	    );
    	    rules.add(baseRateRule);
    	    
    	    
        // TODO: Call the rules.add method with a new Rule object where the first argument is "base rate"
        // TODO: Create a new Predicate object profile that always returns true
        // TODO: Create a new BiConsumer object with profile and premium as arguments
        // TODO: Create a new String variable vehicleCategory and assign the result of the determineVehicleCategory method with profile as argument
        // TODO: Create a new double variable baseRate and assign the value of the knowledgeBase map with the key "baseRate." + vehicleCategory
        // TODO: Call the setBaseRate method on the premium object with baseRate as argument

        // Age factor rule - adjusts premium based on driver's age
        // TODO: Call the rules.add method with a new Rule object where the first argument is "age factor"
        // TODO: Create a profile predicate that always returns true
        // TODO: Create a new BiConsumer object with profile and premium as arguments
        // TODO: Create a new int variable age and assign the result of the getAge method on the profile object
        // TODO: Create a new double variable factor
        // TODO: Create a new String variable explanation

        // TODO: If age is less than 20 then assign the value of the knowledgeBase map with the key "ageFactor.16-19" to the factor variable and assign the value "Drivers under 20 have higher statistical risk" to the explanation variable

        // TODO: If age is less than 25 then assign the value of the knowledgeBase map with the key "ageFactor.20-24" to the factor variable and assign the value "Drivers 20-24 have moderately higher risk" to the explanation variable
        // TODO: If age is less than 66 then assign the value of the knowledgeBase map with the key "ageFactor.25-65" to the factor variable and assign the value "Standard rate for drivers 25-65" to the explanation variable

        // TODO: Otherwise, assign the value of the knowledgeBase map with the key "ageFactor.66+" to the factor variable and assign the value "Slight increase for senior drivers" to the explanation variable

        // TODO: Create a new double variable adjustment and assign the result of the getBaseRate method on the premium object multiplied by (factor - 1.0)
        // TODO: Call the addAdjustment method on the premium object with "Age factor", adjustment, and explanation as arguments


        // Accident history rule - adds surcharges for recent accidents
        // TODO: Call the rules.add method with a new Rule object where the first argument is "accident history"
        // TODO: Create a new Predicate object profile that checks if the number of accidents in the last five years is greater than 0
        // TODO: Create a new BiConsumer object with profile and premium as arguments
        // TODO: Create a new int variable accidents and assign the result of the getAccidentsInLastFiveYears method on the profile object
        // TODO: Create a new variable surcharge of type double
        // TODO: Create a new variable explanation of type String
        // TODO: Create a if statement that checks if accidents is equal to 1
        // TODO: If the condition is true, assign the value of the knowledgeBase map with the key "accidentSurcharge.1" to the surcharge variable
        // TODO: Assign the value "Surcharge for 1 accident in past 5 years" to the explanation variable
        // TODO: Otherwise, assign the value of the knowledgeBase map with the key "accidentSurcharge.2+" to the surcharge variable
        // TODO: Assign the value "Major surcharge for 2+ accidents in past 5 years" to the explanation variable

        // TODO: Call the addAdjustment method on the premium object with "Accident history", surcharge, and explanation as arguments
    }

    // Helper method to determine vehicle category
    private String determineVehicleCategory(DriverProfile profile) {
        // TODO: Create a new String variable make and assign the result of the getVehicleMake method on the profile object
        // TODO: Create a new String variable model and assign the result of the getVehicleModel method on the profile object

        // Simple classification logic
        // TODO: If make is equal to "bmw", "mercedes", "lexus", or "audi", return "luxury"
        // TODO: If make is equal to "ferrari", "porsche", "mustang", or "corvette", return "sports"
        // TODO: If model is equal to "suv", "explorer", "tahoe", or "highlander", return "suv"
        // TODO: Otherwise, return "sedan"
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Calculate premium by applying all applicable rules
    public Premium calculatePremium(DriverProfile profile) {
        // TODO: Create a new Premium object named premium

        // Apply all rules that match the profile
        // TODO: For each rule, if the rule matches the profile, apply the rule to the profile and premium

        // TODO: Return the premium object
        throw new UnsupportedOperationException();
    }

    // Rule class
    static class Rule {
        // TODO: Create a private String variable named name
    	// TODO: Create a private Predicate<DriverProfile> variable named condition
        // TODO: Create a private BiConsumer<DriverProfile, Premium> variable named action
    	
    	String name;
    	
    	private Predicate<DriverProfile> condition;
    	
    	private BiConsumer<DriverProfile, Premium> action;
        

		public Rule(String name, Object condition, Object action) {
		
			this.name = name;
			
		}
		
		public Object matches(Object condition) {
			
			
			return condition;
		}
    		
    	
        // TODO: Create a public constructor for Rule with name, condition, and action as arguments and assign them to the corresponding instance variables

        // TODO: Create a public method named matches that takes a DriverProfile object as an argument and returns the result of the test method on the condition object with the profile as an argument

        // TODO: Create a public method named apply that takes a DriverProfile and Premium object as arguments and calls the accept method on the action object with the profile and premium as arguments

        // TODO: Create a public method named getName that returns the name instance variable
    }
}
