package dance;

public class GameSetUp extends GameDriver{
	
	public void setColour(int x){
		int red=0,green=0,blue=0;
		red=x;
		green=(x%80)+60;
		blue=(red+green)/2;
		
		writer.println("Red value of the beak: "+red);
		writer.println("Green value of the beak: "+green);
		writer.println("Blue value of the beak: "+blue);
		fin.setLED(red, green, blue);
	}
	
	public int setSpeed(String str){
		int octal=Integer.parseInt(str);
		int speed=0;
		if(octal<60){
			speed=octal+30;
		}
		else if(octal>255){
			speed=255;
		}
		else{
			speed=octal;
		}
		return speed;
		
	}
	
	public void Dance(String str){
		NumberSystem num = new NumberSystem();
		
		int binary=Integer.parseInt(str);
		int moveBack=-1;
		while(binary!=0){
			int leftWheel = setSpeed(num.getOctal(num.getDecimal(getHex())));
			int rightWheel=leftWheel;
			int digit=binary%10;
			if(digit==1){
				fin.setWheelVelocities(leftWheel,rightWheel, 1000);
			}
			else{
				fin.setWheelVelocities(moveBack*(leftWheel),moveBack*(rightWheel), 1000);
			}
			binary=binary/10;
		}
		
	}

}

