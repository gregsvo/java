
import com.mycompany.vendingmachine.controllers.VendingController;
import com.mycompany.vendingmachine.dto.Change;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class App {
    
    public static void main(String[] args) {
        
        VendingController vc = new VendingController();
        vc.run();
        
        
    }
    
}
