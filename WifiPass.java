import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.SecurityException;
import java.util.Arrays;

public class WifiPass {

	private File name;
	private int count = 1;
	private int j;

	public void readFile(){

		

			name = new File("/etc/NetworkManager/system-connections");

		 	
	}
	public void processFile(){
		
		String listDir[] = name.list();

			
			for(j = 0; j < listDir.length; j++){

				System.out.printf("%d. %s\n", count++, listDir[j]);

			}
		
			System.out.println("Select Wifi to Reveal Password");
			
			Scanner input = new Scanner(System.in);
			
			String line;
 
			try {

					BufferedReader open = new BufferedReader(new FileReader("/etc/NetworkManager/system-connections/" + listDir[input.nextInt()-1]));
					
					
					while((line = open.readLine()) != null){

						if(line.startsWith("psk")){
						
							String[] parts = line.split("=");
							System.out.println("The Password is: " + parts[1]);

						}
					
				}
			}catch (FileNotFoundException fe){

				System.err.println("File not found" + fe);
			}catch(SecurityException se){

				System.err.println("Error No Permissions!");
				
			}catch(IOException ie){
		
				System.out.println(ie);

			}
			
	}
	
}
