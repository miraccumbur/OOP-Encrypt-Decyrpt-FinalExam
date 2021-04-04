
public class General implements interface_decryption{

	@Override
	public String shifDecryption(String sentences,int key) {
		
		String output="";
		
		for(int i=0;i<sentences.length();i++) {
			
				char ch = (char)(((int)sentences.charAt(i) - key)); 
				output=output+String.valueOf(ch);
				
				
			}
		
		
		
		
		return output;
	}

	@Override
	public String binaryDecryption(String sentences,String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
