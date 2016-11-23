
package com.mycompany.aretheytrue;

/**
 * @author Greg Svoboda
 */
public class areTheyTrue {
    
    
    public static void main(String[] args) {
        
        
        boolean a = true;
        boolean b= false;
        String answer = howTrue((boolean) a, (boolean) b);
        
     }//END OF MAIN   
    
    //METHOD: HOW TRUE?
    
  public static String howTrue(boolean a, boolean b){  
        
    String answer = "";
        
        if (!a && !b) {
            System.out.println("NEITHER, DUDE!");
        }
        else if ((a==true)&&(b==true)) {
            System.out.println("BOTH!");
  
        }
        else {
            System.out.println("ONLY ONE!");  
        }
       
    
    
    return answer;
    
}//END OF howTrue
    

}// end of ARE THEY TRUE?
