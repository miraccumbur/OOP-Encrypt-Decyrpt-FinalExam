
public class Listener {
	public String name, type;
	
	/**
	 * @param name
	 * @param type
	 */
	public Listener(String name,String type) {
		super();
		this.name = name;
		this.type=type;
		
	}

	void update(String sentences,String keyBinary,String keyShift,String type,int size) {
		
			if(type.equals("spy")) {
				Spy spy=new Spy();
				
				String output=spy.binaryDecryption(sentences, keyBinary);
				
				System.out.println(name+"'s message:"+output);
				
				
			}
			else if(type.equals("general")) {
				General general=new General();
				String output=general.shifDecryption(sentences, (Integer.valueOf(keyShift)));
				
				System.out.println(name+"'s message:"+output);
			}
			
		
		
		
		
		
	
		
	}
	
}
