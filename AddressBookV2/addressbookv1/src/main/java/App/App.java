package App;

import Controller.Controller;

public class App {

    public static void main(String[] args) {

        Controller addressController = new Controller();

        addressController.run();
        
//        AddressDAOLambdaImpl addressDao = new AddressDAOLambdaImpl();
//        List<Address> newList = addressDao.searchByState("q453");
//        for (Address address : newList) {
//            System.out.println("Address: " +address.getId());
 //       }
        

    }

}
