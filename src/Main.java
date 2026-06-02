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
        System.out.println("Start Round: ");
        int start = scan.nextInt();
        System.out.println("End Round: ");
        int end = scan.nextInt();

        BananaFarm bananaFarm = new BananaFarm(top, mid, bottom, true); //initialize
        System.out.println("Cash Per Banana: " + bananaFarm.valueOfBanana());
        System.out.println("Number of Bananas per Round: " + bananaFarm.numBananasPerRound());
        System.out.println("Income Per Round: " + bananaFarm.incomePerRound());


    }
    
}
