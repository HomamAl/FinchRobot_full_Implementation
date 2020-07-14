package dance;


public class NumberSystem{

	public int getDecimal(String hex){	
		
		String digits="0123456789ABCDEF";
		int val=0;
		hex=hex.toUpperCase();
		for(int i=0;i<hex.length();i++){
		char c= hex.charAt(i);
		int digit=digits.indexOf(c);
		val=16*val+digit;
		
		}
		return val;
	}
	
	public String getOctal(int x){
		String oct="";
		int rem=0;
		while(x>0){
			rem=x%8;
			oct=rem+oct;
			x=x/8;
		}
		return oct;
	}
	
	public String getBinary(int x){
		String bin="";
		int rem=0;
		while(x!=0){
			rem=x%2;
			bin=rem+bin;
			x=x/2;
		}
		return bin; 
	}

}

