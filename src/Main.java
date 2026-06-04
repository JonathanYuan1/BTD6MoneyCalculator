import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Top Path: ");
        Scanner scan = new Scanner(System.in);
        int top = scan.nextInt();
        System.out.println("Middle Path: ");
        int mid = scan.nextInt();
        System.out.println("Bottom Path");
        int bottom = scan.nextInt();
        System.out.println("Monkey Knowledge Active? ");
        boolean mk = scan.nextBoolean();
        int startingCash = 0;
        if (mid >= 3){
            System.out.println("Starting Deposit: ");
            startingCash = scan.nextInt();
        }
        BananaFarm bananaFarm = new BananaFarm(top, mid, bottom, mk, startingCash); //initialize

        System.out.println("Start Round (e.g. starting round 20): ");
        int start = scan.nextInt();
        System.out.println("End Round (e.g. before round 40): ");
        int end = scan.nextInt();  
        
        if (mid >=3){
            double totalIncome = 0;
            for (int i = start; i<end; i++){
                double incomeMade = bananaFarm.incomePerRound();
                if (bananaFarm.shouldCollectNow() || i==end-1){  
                    totalIncome += bananaFarm.getDepositAmount();
                    System.out.println("ROUND " + i+ "   Cash Made This Round: " + (int)incomeMade + ", Bank Stores: " + (int)bananaFarm.getDepositAmount() +" COLLECT NOW");
                    bananaFarm.collect();
                } else {
                    System.out.println("ROUND " + i+ "   Cash Made This Round: " + (int)incomeMade + ", Bank Stores: " + (int)bananaFarm.getDepositAmount());
                }
            }
            System.out.println("Total Income: " + (int)totalIncome);
        } else{
        System.out.println("Total Income: " + bananaFarm.incomePerRound() * (end-start));
        System.out.println("Cash Per Banana: " + bananaFarm.valueOfBanana());
        System.out.println("Number of Bananas per Round: " + bananaFarm.numBananasPerRound());
        System.out.println("Income Per Round: " + bananaFarm.incomePerRound());
        }
        
        
    }
    
}
