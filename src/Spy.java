
public class Spy implements interface_decryption {

	@Override
	public String shifDecryption(String sentences,int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String binaryDecryption(String sentences ,String key) {
		
		byte[] byteskey = key.getBytes();
		  StringBuilder binarykey = new StringBuilder();
		  for (byte b : byteskey)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binarykey.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binarykey.append(' ');
		  }
		  
		String sifrelenmis="";
		  String keyCH1="";
		  String sum="";
		  String encryptmsg=sentences;
		  
		  int count2=0;
		  for(int l=0;l<(encryptmsg.length());l++) {
			  
			  	count2++;
			  sifrelenmis=String.valueOf((encryptmsg.charAt(l)));
			  keyCH1=(String.valueOf(binarykey.charAt(l)));
			  
			  
			  
			  if(sifrelenmis.equals(" ")) {
				  
			  }
			  else {
				  if(sifrelenmis.equals(keyCH1)) {
					  sum= sum+"0";
					  
				  }
				  else {
					  sum=sum+"1";
				  
			  }
				 
			  }
			  if (count2==8) {
				  sum=sum+" ";
				  count2=-1;
				  
			  }
			  
			  
		  
		  
			  
		  		
		  }
		  
		  
		  String output="";
		  int index = 0;
			while(index < sum.length()) {
				String temp = sum.substring(index, index+8);
				Integer num = Integer.parseInt(temp,2);
				char letter =(char) (int)num;
				
				output=output+letter;
				index +=9;
			}
		
		return output;
	}

}
