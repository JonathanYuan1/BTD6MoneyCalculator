public class BananaFarm {
    private int topPath;
    private int middlePath;
    private int bottomPath;
    private boolean monkeyKnowledge;
    
    private static boolean bananaCentralExists = false;

    public BananaFarm(int topPath, int middlePath, int bottomPath, boolean monkeyKnowledge){
        if (topPath <0 || topPath >5||
            middlePath <0 || middlePath >5 ||
            bottomPath <0 || bottomPath >5){
                throw new IllegalArgumentException("Failed to Make: Tiers Outside 0 and 5.");
            }
        if (topPath != 0 && middlePath != 0 && bottomPath != 0) {throw new IllegalArgumentException("Failed to Make: Triple Crosspaths.");}
        if (topPath >2 && middlePath >2 || topPath>2 && bottomPath >2 || middlePath>2 || bottomPath >2){throw new IllegalArgumentException("Failed to Make: Multiple Main CrossPaths.");}
        //Test whether the tiers are legal
        
        this.topPath = topPath;
        this.middlePath = middlePath;
        this.bottomPath = bottomPath;
        this.monkeyKnowledge = monkeyKnowledge;
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

    public int numBananasPerRound(){
        if (this.topPath==0){
            return 4;
        } else if (this.topPath == 1){
            return 6;
        } else if (this.topPath == 2){
            return 8;
        } else if (this.topPath == 3){
            return 16;
        } else if (this.topPath >3){
            return 5;
        }else {return 0;}
    }
    public double valueOfBanana(){
        if (topPath >= 3){
            double base = 20.0;
            boolean BRFBuff = false;
            if(this.topPath<4 && this.topPath>=0){
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
            return 0; 
        } else if (bottomPath >= 3) { //BOTTOM PATH
            return 0;
        } else {return 0;}
    }
     public double incomePerRound(){
        if (bottomPath == 5){
            return valueOfBanana()*numBananasPerRound() + 4000; //CHANGE: affected by monkey city
        } else{
         return valueOfBanana()*numBananasPerRound();
        }
     }
    
}
