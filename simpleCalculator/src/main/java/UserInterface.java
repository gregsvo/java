
public class UserInterface {

    public static void main(String[] args) {

        boolean playAgain = false;
        ConsoleIO startCalc = new ConsoleIO();
        BasicMathOperations basicMath = new BasicMathOperations();
        
        while (!playAgain) {
            
            System.out.println("Welcome to the best calculator EVAR.");

            int choice = startCalc.getAndVerifyInt(" Please select: "
                    + "[1]-Addition "
                    + "[2]-Subtraction "
                    + "[3]-Multiplication "
                    + "[4]-Division "
                    + "[5]-Quit ");
            
            switch (choice) {
                
                case 5:
                    System.out.println("Thanks for calculating!!");
                    
                    return;
                    
                case 1:
                    double x = startCalc.getDouble(" Enter the first number you want to add: ");
                    double y = startCalc.getDouble(" Enter the second number you want to add: ");
                    double solution = basicMath.addValues(x, y);
                    System.out.println(solution);
                    break;
                    
                case 2:
                    x = startCalc.getDouble(" Enter the a number: ");
                    y = startCalc.getDouble(" Enter the second number you want to subtract from " + x + ". ");
                    solution = basicMath.subtractValues(x, y);
                    System.out.println(solution);
                    break;
                    
                case 3:
                    x = startCalc.getDouble(" Enter the first number you want to multiply: ");
                     y = startCalc.getDouble(" Enter the second number you want to multiply: ");
                     solution = basicMath.multiplyValues(x, y);
                    System.out.println(solution);
                    break;
                    
                case 4:
                     x = startCalc.getDouble(" Enter the first number: ");
                     y = startCalc.getDouble(" Enter the second number you want to divide " + x + " by: ");
                     solution = basicMath.divideValues(x, y);
                    System.out.println(solution);

            }
           
        }
    }

}