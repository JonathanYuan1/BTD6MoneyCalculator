public class BananaFarm {
    private int topPath;
    private int middlePath;
    private int bottomPath;
    private boolean monkeyKnowledge;
    private double start;
    private boolean sold = false;
    
    private static boolean bananaCentralExists = false;

    public BananaFarm(int topPath, int middlePath, int bottomPath, boolean monkeyKnowledge, double startingCash){
        if (topPath <0 || topPath >5||
            middlePath <0 || middlePath >5 ||
            bottomPath <0 || bottomPath >5){
                throw new IllegalArgumentException("Failed to Make: Tiers Outside 0 and 5.");
            }
        if (topPath != 0 && middlePath != 0 && bottomPath != 0) {throw new IllegalArgumentException("Failed to Make: Triple Crosspaths.");}
        boolean topmid = topPath > 2 && middlePath>2;
        boolean topbot = topPath >2 && bottomPath>2;
        boolean midbot = middlePath >2 && bottomPath>2;
        if (topmid || topbot || midbot){throw new IllegalArgumentException("Failed to Make: Multiple Main CrossPaths.");}
        //Test whether the inputs are legal
        
        this.topPath = topPath;
        this.middlePath = middlePath;
        this.bottomPath = bottomPath;
        this.monkeyKnowledge = monkeyKnowledge;
        this.start = startingCash;
        if (topPath == 5){
            bananaCentralExists = true;
        }
    }
    public int getTopPath(){
        return this.topPath;
    }
    public int getMiddlePath(){
        return this.middlePath;
    }
    public int getBottomPath(){
        return this.bottomPath;
    }
    public boolean isSold(){
        return sold;
    }
    public void sell(){
        sold = true;
    }
    public void collect(){
        start = 0;
    }
    public int getMaxStore(){
        if (middlePath>=3){
            if (monkeyKnowledge){
                if (middlePath>=4){
                    return 12500;
                } else{
                    return 9500;
                }
            } else {
                if (middlePath>=4){
                    return 10000;
                } else{
                    return 7000;
                }
            }
        } else {return 0;}
    }
    public boolean shouldCollectNow() {
        double potentialAmountInBank = (start+ this.valueOfBanana())*1.15;
        return potentialAmountInBank > this.getMaxStore();
    }
    public double getDepositAmount(){
        return start;
        /*double potentialAmountInBank = (start + this.valueOfBanana())*1.15;
            if (monkeyKnowledge){
                if (middlePath>=4){
                    if (potentialAmountInBank > 12500){//imf full
                        return 12500;
                    } else { //imf not full
                        return potentialAmountInBank;
                    }
                } else {
                    if (potentialAmountInBank > 9500){ //bank full
                        return 9500;
                    } else { //bank not full
                        return potentialAmountInBank;
                    }
                }
            } else { // no Monkey Knowledge
                if (middlePath>=4){
                    if (potentialAmountInBank > 10000){//imf full
                        return 10000;
                    } else { //imf not full
                        return potentialAmountInBank;
                    }
                } else {
                    if (potentialAmountInBank > 7000){ //bank full
                        return 7500;
                    } else { //bank not full
                        return potentialAmountInBank;
                    }
                }
            }*/
    }

    public int numBananasPerRound(){
        int base = 4;
        if (topPath <= 2){ //for top path tier 2 or below, gain 2 extra bananas for each tier of top path
            base += topPath *2;
        }
        if (topPath == 3){
            base=16;}
        if (topPath >=4){
            base=5;
        }
        if (middlePath>=3){
            base=1; //because banks are weird, seperate system
        }
        if (bottomPath>= 3){
            base+=12;
        }
        return base;
    }
    public double valueOfBanana(){
        double base = 20.0;
        if (topPath >= 3){ //TOP PATH
            boolean BRFBuff = false;
            if(this.topPath == 3){
                base = 20.0;
            } else if (this.topPath == 4){
                base = 300.0;
                if (bananaCentralExists) {BRFBuff = true;}
            } else if (this.topPath == 5){
                base = 1200.0;
            } else {base = 0.0;}

            if (middlePath == 2){
                if (monkeyKnowledge){
                    if (BRFBuff){base = 488;}
                    else{base *= 1.3;}
                }
                else {
                    if(BRFBuff){base = 469;}
                    else{base *= 1.25;}
                }
            } else {
                if (BRFBuff) {base = 375;}
            }
            return base;

        } else if (middlePath >=3 ) { //MIDDLE PATH
            base = 180;
            base += topPath*40;
            if (monkeyKnowledge){ //for valueable bananas
                base*=1.3;
            } else{
                base*=1.25;
            }
        } else if (bottomPath >= 3) { //BOTTOM PATH
            if (bottomPath >= 4){ //Central Market
                base = 70.0;
            }
            if (middlePath ==2){ //Valuable Bananas
                if (monkeyKnowledge){return base*1.3;}
                else{return base*1.25;}
            } else{return base;}      
        } //END CHECK
        if (middlePath ==2){ //less than three tiers for each path (no main path)
                if (monkeyKnowledge){return base*1.3;}
                else{return base*1.25;}
        } else{return base;}  

    }
     public double incomePerRound(){
        if (sold) { //sold should give no income
            return 0;
        }
        if (middlePath >=3){
            double potentialAmountInBank = (start + this.valueOfBanana())*1.15;
            int max = this.getMaxStore();
            double amountMade = 0;
            if (potentialAmountInBank > max){
                amountMade = max - start;
                start = max;
                return amountMade;
            } else{
                amountMade = potentialAmountInBank - start;
                start = potentialAmountInBank;
                return amountMade;
            }

            /*if (monkeyKnowledge){
                if (middlePath>=4){
                    if (potentialAmountInBank > 12500){//imf full
                        double amountMade = 12500- start;
                        start =12500;
                        return amountMade;
                    } else { //imf not full
                        double amountMade = potentialAmountInBank - start;
                        start = potentialAmountInBank;
                        return amountMade;
                    }
                } else {
                    if (potentialAmountInBank > 9500){ //bank full
                        double amountMade = 9500-start;
                        start = 9500;
                        return amountMade;
                    } else { //bank not full
                        double amountMade = potentialAmountInBank - start;
                        start = potentialAmountInBank;
                        return amountMade;
                    }
                }
            } else { // no Monkey Knowledge
                if (middlePath>=4){
                    if (potentialAmountInBank > 10000){//imf full
                        double amountMade = 10000- start;
                        start =10000;
                        return amountMade;
                    } else { //imf not full
                        double amountMade = potentialAmountInBank - start;
                        start = potentialAmountInBank;
                        return amountMade;
                    }
                } else {
                    if (potentialAmountInBank > 7000){ //bank full
                        double amountMade = 7000-start;
                        start = 7000;
                        return amountMade;
                    } else { //bank not full
                        double amountMade = potentialAmountInBank - start;
                        start = potentialAmountInBank;
                        return amountMade;
                    }
                }
            }*/
        }


        if (bottomPath == 5){
            return valueOfBanana()*numBananasPerRound() + 4000; //CHANGE: affected by monkey city
        } else{
         return valueOfBanana()*numBananasPerRound();
        }
     }
    
}
