//AUTHOR: GREG SVOBODA
import java.util.Scanner;

 

public class WindowMaster {
    
    static Scanner sc = new Scanner(System.in);
    
public static void main(String[] args) {
        
    //Define variables
        String userInputHeight = "";
        String userInputWidth = "";
        String userInputglassCost = "";
        String userInputtrimCost = "";
        final float MAX_HEIGHT = 25.5f;
        final float MAX_WIDTH = 18.75f;
        final float MIN_HEIGHT = 1.0f;
        final float MIN_WIDTH = 1.0f;
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;
        
    //call methods to receive and parse user input        
        float height = getUserHeight(MAX_HEIGHT,MIN_HEIGHT);
        float width = getUserWidth(MAX_WIDTH,MIN_WIDTH);
        float trimCost = getUserTrimCost();
        float glassCost = getUserGlassCost();

    //calculate area of window        
        areaOfWindow = height * width;
        
    //calculate perimeter of window 
        perimeterOfWindow = 2 * (height + width);
        
    //calculate total cost
        cost = ((glassCost * areaOfWindow) + (trimCost * perimeterOfWindow));
        
    //print out cost
        System.out.println("Window height: "+ height);
        System.out.println("Window width: "+ width);
        System.out.println("Window area: "+ areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Price of trim = $" + trimCost);
        System.out.println("Price of glass = $" + glassCost);
        System.out.println("Total cost: $" + cost);
    //END OF OUTPUT
    }

    //BEGINNING OF METHODS

    //METHOD: GET USER HEIGHT
    public static float getUserHeight(float max, float min){
        
        boolean isValid = false;
        float heightOutput = 0;
        String userInputHeight;
        
        while (!isValid) {
            try {
                System.out.println("Please enter the window height desired:");
                userInputHeight = sc.nextLine();
                heightOutput = Float.parseFloat(userInputHeight);
                
                if (heightOutput < max && heightOutput > min)
                    isValid = true;
                else
                    System.out.println("Height needs to be between 1.0 and 25.5.");
            }
            catch (NumberFormatException nfe) {
                System.out.println("You have not entered a number.");
            }
        }
        return heightOutput;
    }   
 
    //METHOD: GET USER WIDTH  
    
        public static float getUserWidth(float max, float min){
      
        boolean isValid = false;
        float widthOutput = 0;
        String userInputWidth;
        
        while (!isValid) {
            try {
                System.out.println("Please enter the window width desired:");
                userInputWidth = sc.nextLine();
                widthOutput = Float.parseFloat(userInputWidth);
                
                if (widthOutput < max && widthOutput > min)
                    isValid = true;
                else
                    System.out.println("Width needs to be between 1.0 and 18.75.");
            }
            catch (NumberFormatException nfe) {
                System.out.println("You have not entered a number.");
            }
        }
                return widthOutput;
    }

    //METHOD: GET USER TRIM COST
        
         public static float getUserTrimCost(){       
            boolean isValid = false;
            float trimCostOutput = 0;
            String userInputTrimCost;
            float trimCost = 0;
             
            while (!isValid) {
            try {
                System.out.println("Please enter cost of trim:");
                userInputTrimCost = sc.nextLine();
                trimCost = Float.parseFloat(userInputTrimCost);
                isValid = true;
            }
            catch (NumberFormatException nfe) {
                System.out.println("You have not entered a number.");
            }
        }
             return trimCost;
    }
   
    //METHOD: GET USER GLASS COST         
         
            public static float getUserGlassCost(){       
            boolean isValid = false;
            float glassCost = 0;
            String userInputGlassCost;
           
             
            while (!isValid) {
            try {
                System.out.println("Please enter cost of glass:");
                userInputGlassCost = sc.nextLine();
                glassCost = Float.parseFloat(userInputGlassCost);
                isValid = true;
            }
            catch (NumberFormatException nfe) {
                System.out.println("You have not entered a number.");
            }
            }
             return glassCost;
            } 
    }
    //END
