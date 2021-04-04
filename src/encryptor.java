import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class encryptor implements interface_encryption {
	
	public static int count=0;
	public static int countlistener=0;
	
	

	
	public static  Scanner scan=new Scanner(System.in);
	
	public String type="",messageShift="",messageBinary="" ,keyString="",nonmessage;
	public String keyByte;
	
	
	
	public static List <Listener> listener_al=new ArrayList<Listener>();
	public static List <encryptor> encryptor_al=new ArrayList<encryptor>();
	
	
	
	
	
	
	/**
	 * @param type
	 * @param messageShift
	 * @param messageBinary
	 * @param keyString
	 * @param keyByte
	 */
	public encryptor(String type, String messageShift, String messageBinary, String keyString, String keyByte,String nonmessage) {
		super();
		this.type = type;
		this.messageShift = messageShift;
		this.messageBinary = messageBinary;
		this.keyString = keyString;
		this.keyByte = keyByte;
		this.nonmessage = nonmessage;
	}

public static void menu() {
		
		
		
		String choose="";
		String s="\n\nWelcome to encyrption program\n\n"
				+"Send message     : 1\n"
				+"New Listener     : 2\n"
				+"quit             : q\n";
		System.out.println(s);
		String line="",keyBinary="";
		int keyShift=1;
		
		choose=scan.nextLine();
		switch (choose) {
		/*case "1":
			count++;
			
			
			System.out.print("Your message :");
			line=scan.nextLine();
			System.out.print("\nYour key for shift:");
			keyShift=scan.nextInt();
			scan.nextLine();
			System.out.print("\nYour key for binary (key length bigger than message length)\n");
			keyBinary=scan.nextLine();
			
			
			menu();
			break;*/
			
		case "1":
			count++;
			System.out.print("Your message :");
			line=scan.nextLine();
			System.out.print("\nYour key for shift:");
			keyShift=scan.nextInt();
			scan.nextLine();
			System.out.print("\nYour key for binary (key length bigger than message length)\n");
			keyBinary=scan.nextLine();
			System.out.print("Listener name:");
			String listenername=scan.nextLine();
			
			register(line,keyShift,keyBinary,listenername);
			
			menu();
			break;
			
		case "2":
			countlistener++;
			System.out.print("New Listener nickname:");
			String name=scan.nextLine();
			System.out.println("New listener type :(general or spy)");
			String typelistener=scan.nextLine();
			listener_al.add(new Listener(name,typelistener));
			menu();
			break;
			
		case "q":
			System.out.println("Program exit...");
			System.exit(0);
			break;
			
		default:
			System.out.println("wrong choice,try again..");
			menu();
			break;
		}
	
	}

	public static  void sendAll() {
		
		System.out.println("\n\nYour message submitting");
		System.out.println("\nYour message submitted\n\n");
		
		int size=listener_al.size();
		int sizeana=encryptor_al.size();
		int i=0;
		for(int j=1;j<sizeana;j=j+2) {
			while(i<size) {
				if((listener_al.get(i).type).equals("spy")) {
					
					listener_al.get(i).update(encryptor_al.get(j+1).messageBinary,encryptor_al.get(j+1).keyString,"","spy",size);
				}
				else if((listener_al.get(i).type).equals("general")) {
					listener_al.get(i).update(encryptor_al.get(j).messageShift,"",encryptor_al.get(j).keyByte,"general",size);
				
				}
				else {
					System.out.println("something is wrong..");
				}
				i++;;
			}
			
			
		}
		
		
	}
	
	public static void register(String line,int keyShift,String keyBinary,String listenername) {
		
		
		
		
		encryptor_al.get(0).shiftEncryption(line,keyShift );
		encryptor_al.get(0).binaryEncryption(line, keyBinary);
		
		
		System.out.println("Your message:");
		for(int i=1;i<((count*2)+1);i++) {
			
			
			if((i%2)==1) {
				System.out.println(i+" ) "+"Type:"+encryptor_al.get(i).type+" Key: "+encryptor_al.get(i).keyByte
						+" Encrypt Message: "+encryptor_al.get(i).messageShift+" Non encrypt message:"+encryptor_al.get(i).nonmessage);
			}
			else if(i%2==0) {
				System.out.println(i+" ) "+"Type:"+encryptor_al.get(i).type+" Key: "+encryptor_al.get(i).keyString
						+" Encrypt Message: "+encryptor_al.get(i).messageBinary+" Non encrypt message:"+encryptor_al.get(i).nonmessage);
			}
			
			}
		System.out.println("\n\nYour Listener");
		for(int j=0;j<countlistener;j++) {
			System.out.println(j+") "+listener_al.get(j).name+ " " + listener_al.get(j).type);
		}
		
		sendAll();
		
		
	}


	@Override
	public  String shiftEncryption(String sentences, int key) {
		String output="";
		
		for(int i=0;i<sentences.length();i++) {
			
				char ch = (char)(((int)sentences.charAt(i) + (int)key)); 
				output=output+String.valueOf(ch);
				
				
			}
		
		
		
		encryptor_al.add(new encryptor("shift",output,"","",String.valueOf(key),sentences));
		
		
		
		return null;
	}



	



	@Override
	public String binaryEncryption(String sentences, String key) {
		
			int length=sentences.length();
		  byte[] bytesmsg = sentences.getBytes();
		  StringBuilder binarymsg = new StringBuilder();
		  for (byte b : bytesmsg)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binarymsg.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binarymsg.append(' ');
		  }
		  
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
		  
		 
		  
		  
			  
		  
		  String sentencesCH="";
		  String keyCH="";
		  String encryptmsg="";
		  
		  int count=0;
		  
		  for(int l=0;l<(sentences.length()*8)+sentences.length();l++) {
			  
			  
			  count++;
			  
			  	
			  sentencesCH=String.valueOf((binarymsg.charAt(l)));
			  keyCH=(String.valueOf(binarykey.charAt(l)));
			  
			  if(sentencesCH.equals(" ")) {
				  
			  }
			  else {
				  if(sentencesCH.equals(keyCH)) {
					  encryptmsg= encryptmsg+"0";
					  
				  }
				  else {
					  encryptmsg=encryptmsg+"1";
				  
			  }
			  }
			  
			  if(count==8) {
				  encryptmsg=encryptmsg+" ";
				  count=-1;
			  }
		  
		  
		  		
		  		
		  }
		  
		  
		  
	        
		  
		  encryptor_al.add(new encryptor("binary","",encryptmsg,key,"",sentences));
		  
		
		
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		/*Scanner inFile;
		String line="";
		try {
			inFile = new Scanner(new File("text.txt"));
			
			while(inFile.hasNextLine()){
				line =line+inFile.nextLine()+" ";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(line);
		
		encryptor_al.add(new encryptor("","","","",""));
		Byte keyShift= 1;
		String keyBinary="qwertyuiopskfneiskfoe";
		encryptor_al.get(0).shiftEncryption(line, keyShift);*/
		encryptor_al.add(new encryptor("","","","","",""));
		menu();
		
		
		
		
	}
	
	
	
}
