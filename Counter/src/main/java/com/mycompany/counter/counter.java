
package com.mycompany.counter;

/**
 * @author Greg Svoboda
 */
public class counter {
    
        public static void main(String[] args) {
    
            int n = 3;
            to10();
            toN((int)n);
    
    
    }// end of main
    
        public static void to10() {

            for (int x=0; x<11; x++){
               System.out.println(x);
        }
    }//END OF toN       
       
        public static void toN(int n) {

            for (int x=0; x<n+1; x++){
               System.out.println(x);
        }
           
    }//END OF toN    
    
}//END
