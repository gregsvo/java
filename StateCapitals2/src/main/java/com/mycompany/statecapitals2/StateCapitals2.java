package com.mycompany.statecapitals2;

import java.util.HashMap;
import java.util.Set;

public class StateCapitals2 {

    public static void main(String[] args) {
    
        boolean playAgain = true;
        Double popMinLimit;
        while (playAgain) {
        
            
            
            
        HashMap<String, CapitalInfo> capMap = new HashMap<>();
        SCconsoleIO console = new SCconsoleIO();
     
    //User defines minimum population limit    
        
        console.getStringAndPrintIt("");
        console.getStringAndPrintIt(" Would you like to limit the minimum size of the city? ");    
        popMinLimit = console.getDoubleMinMax(25000d, 7836453d, "Please enter minimum population:");
        
        
    //Hashmap of cities built (FAKE VALUES) 
    
        capMap.put("Alabama", new CapitalInfo("Birmingham", 546754d, 128d));
        capMap.put("Alaska", new CapitalInfo("juneau", 468246d, 128d));
        capMap.put("Arizona", new CapitalInfo("phoenix", 231679d, 128d));
        capMap.put("Arkansas", new CapitalInfo("littleRock", 7836453d, 128d));
        capMap.put("California", new CapitalInfo("sacramento", 325582d, 128d));
        capMap.put("Colorado", new CapitalInfo("denver", 234521d, 128d));
        capMap.put("Connecticut", new CapitalInfo("hartford", .5645458d, 128d));
        capMap.put("Delaware", new CapitalInfo("dover", 3873543d, 128d));
        capMap.put("Florida", new CapitalInfo("tallahassee", 3453453d, 128d));
        capMap.put("Georgia", new CapitalInfo("atlanta", 794683d, 128d));
        capMap.put("Hawaii", new CapitalInfo("honolulu", 231648d, 128d));
        capMap.put("Idaho", new CapitalInfo("boise", 135648d, 128d));
        capMap.put("Illinois", new CapitalInfo("springfield", 834648d, 128d));
        capMap.put("Indiana", new CapitalInfo("indianapolis", 981462d, 128d));
        capMap.put("Iowa", new CapitalInfo("desMoines", 48545d, 128d));
        capMap.put("Kansas", new CapitalInfo("topeka", 875000d, 128d));
        capMap.put("Kentucky", new CapitalInfo("frankfort", 678757d, 128d));
        capMap.put("Louisiana", new CapitalInfo("batonRouge", 687565d, 128d));
        capMap.put("Maine", new CapitalInfo("augusta", 6756345d, 128d));
        capMap.put("Maryland", new CapitalInfo("annapolis", 875000d, 128d));
        capMap.put("Massachusetts", new CapitalInfo("boston", 6875640d, 128d));
        capMap.put("Michigan", new CapitalInfo("lansing", 875000d, 128d));
        capMap.put("Minnesota", new CapitalInfo("saintPaul", 6876545d, 128d));
        capMap.put("Mississippi", new CapitalInfo("jackson", 875000d, 128d));
        capMap.put("Missouri", new CapitalInfo("jeffersonCity", 854322d, 128d));
        capMap.put("Montana", new CapitalInfo("helena", 524846d, 128d));
        capMap.put("Nebraska", new CapitalInfo("lincoln", 689487d, 128d));
        capMap.put("Nevada", new CapitalInfo("carsonCity", 56482d, 128d));
        capMap.put("New Hampshire", new CapitalInfo("concord", 5649581d, 128d));
        capMap.put("New Jersey", new CapitalInfo("trenton", 541814d, 128d));
        capMap.put("New Mexico", new CapitalInfo("santaFe", 875000d, 128d));
        capMap.put("New York", new CapitalInfo("albany", 654696d, 128d));
        capMap.put("North Carolina", new CapitalInfo("raleigh", 987654d, 128d));
        capMap.put("North Dakota", new CapitalInfo("bismarck", 875000d, 128d));
        capMap.put("Ohio", new CapitalInfo("columbus", 123456d, 128d));
        capMap.put("Oklahoma", new CapitalInfo("oklahomaCity", 875000d, 128d));
        capMap.put("Oregon", new CapitalInfo("salem", +654321d, 128d));
        capMap.put("Pennsylvania", new CapitalInfo("harrisburg", 875000d, 128d));
        capMap.put("Rhode Island", new CapitalInfo("providence", 789123d, 128d));
        capMap.put("South Carolina", new CapitalInfo("columbia", 875000d, 128d));
        capMap.put("South Dakota", new CapitalInfo("pierre", 987321d, 128d));
        capMap.put("Tennessee", new CapitalInfo("nashville", 875000d, 128d));
        capMap.put("Texas", new CapitalInfo("austin", 147258d, 128d));
        capMap.put("Utah", new CapitalInfo("saltLakeCity", 875000d, 128d));
        capMap.put("Vermont", new CapitalInfo("montpelier", 369258d, 128d));
        capMap.put("Virginia", new CapitalInfo("richmond", 875000d, 128d));
        capMap.put("Washington", new CapitalInfo("olympia", 159488d, 128d));
        capMap.put("West Virginia", new CapitalInfo("charleston", 875000d, 128d));
        capMap.put("Wisconsin", new CapitalInfo("madison", 261548d, 128d));
        capMap.put("Wyoming", new CapitalInfo("cheyenne", 25000d, 128d));

        
//Iterate through the hashmap to print
        
            Set<String> keys = capMap.keySet();

            for (String states : keys) {
                
 //Limit print via user input
              
                String cap = capMap.get(states).getName();
                String pop = ("" + capMap.get(states).getPopulation());
                String sqmi = ("" + capMap.get(states).getSquareMilage());

            if (capMap.get(states).getPopulation() >= popMinLimit){
                console.getStringAndPrintIt("***  STATE:  ***");
                console.getStringAndPrintIt(states);
                console.getStringAndPrintIt("***  CAPITAL:  ***");
                console.getStringAndPrintIt(cap);
                console.getStringAndPrintIt("***  POPULATION:  ***");
                console.getStringAndPrintIt(pop);
                console.getStringAndPrintIt("***  SQUARE MILE SIZE:  ***");
                console.getStringAndPrintIt(sqmi);
                console.getStringAndPrintIt("");
                console.getStringAndPrintIt("");
            }   
            }    
                
                
                  
                
        playAgain = console.playAgain("Would you like to try another? [1] YES [2] NO : ");
        console.getStringAndPrintIt("");    
        }
    }
}
