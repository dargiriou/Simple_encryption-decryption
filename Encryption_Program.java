package da222kf_assign1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//C:\\Dimitris_Argyriou.txt
//C:\\Users\\dargi\\Desktop\\Dimitrios_Argyriou.txt
//C:\\Users\\dargi\\Desktop\\Dimitrios_Argyriou_Encrypted.txt 
//C:\\Users\\dargi\\Desktop\\Dimitrios_Argyriou_EncryptedTrans.txt
//C:\Users\dargi\Documents\Dimitrios_Argyriou.txt
//C:/Users/dargi/Documents/Dimitrios_Argyriou.txt
public class Encryption_Program 
{
    public static final char Alphabet[]  = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j',
    		'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','w', 'x', 'y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
    		'T','U','V','W','X','Y','Z',',', '.', ')','(','-','_','!','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i','j',
    		'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v','w', 'x', 'y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S',
    		'T','U','V','W','X','Y','Z',',', '.', ')','(','-','_','!'};
    
    
    //strings
	private static String fileUrl;
	
	public static FileWriter fw = null;
                                          
	//ints
	private static int inputOption = 0;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException 
	{
		 while(true)
	     {
			
			// "menu"
			System.out.println("\n\n");
			System.out.println("|-----------------------------------|");
	        System.out.println("|Encryption / Decryption Program    |");
	        System.out.println("|-----------------------------------|");
	        System.out.println("|Choose one option                  |");
	        System.out.println("| 1.) Encryption with Transposition |");
	        System.out.println("| 2.) Encryption with Substitution  |");
	        System.out.println("| 3.) Decryption with Transposition |");
	        System.out.println("| 4.) Decryption with Substitution  |");
	        System.out.println("| 5.) Exit!                         |");
	        System.out.println("-------------------------------------");
	        
	        System.out.print("Enter a valid option: ");
	        inputOption = sc.nextInt();
	        
	        //switch statement to chose an option from the "menu"
	        switch(inputOption)
	        {
		        case 1:
		        {
		        	//call method for option 1
		        	encryptionWithTranspositionOption();
		        	break;
	
		        }
		        case 2:
		        {
		        	//call method for option 2
		        	encryptionWithSubstitutionOption();
		        	break;
	
		        }
		        case 3:
		        {
		        	//call method for option 3
		        	decryptionWithTranspositionOption();
		        	break;
	
		        }
		        case 4:
		        {
		        	//call method for option 4
		        	decryptionWithSubstitutionOption();
		        	break;
		        	
		        }
		        case 5://exit
		        {
		        	System.out.println("Goodbye!");
		        	sc.close();
					System.exit(0);	
		        }
		        default:
		        {
		        	System.err.println("Incorrect entry.");	        	
		        	break;
		        }
	        
	        }

		
		
		}
       
    }
	
	
	private static void decryptionWithSubstitutionOption() throws IOException 
	{
			{
				System.out.print("Please provide a valid url: ");
				String inputUrl = sc.next();
				fileUrl = inputUrl;
				
				System.out.print("Please provide a key: ");
				int theKey = sc.nextInt();
				
				while(theKey < 0 || theKey > 255)
				{
					
					System.err.println("Please provide an integer from 0 - 255");
					theKey = sc.nextInt();
				}
				theKey = theKey%59;
				
			
				List<String> textContent = new ArrayList<>();//declaring a list
				InputStream bytes = new FileInputStream(fileUrl);
			
				//reading from the file line by line
				Reader characters = new InputStreamReader(bytes, StandardCharsets.UTF_8);
				BufferedReader lines = new BufferedReader(characters);
				
				while (true) 
				{
					String line = lines.readLine();
					if (line == null)
						break;
					textContent.add(line);
					decryptWithSub(theKey, line);//decrypting each line
				}
			  
				lines.close();
			
			}
			
		}

	private static void decryptionWithTranspositionOption() throws IOException {
		
		System.out.print("Please provide a valid url: ");
		String inputUrl = sc.next();
		fileUrl = inputUrl;
		
		System.out.print("Please provide a key: ");
		String theKey = sc.next();
		
		List<String> textContent = new ArrayList<>();
		InputStream bytes = new FileInputStream(fileUrl);
		
			
		Reader characters = new InputStreamReader(bytes, StandardCharsets.UTF_8);
		BufferedReader lines = new BufferedReader(characters);
			
		  while (true) 
		  {
		    String line = lines.readLine();
		    if (line == null)
		      break;
		    textContent.add(line);
		    writeDecryptedFile(decryptWithTrans(line, theKey));
		  }
		  
		lines.close();
		
	}

	private static void encryptionWithSubstitutionOption() throws IOException 
	{
		System.out.print("Please provide a valid url: ");
		String inputUrl = sc.next();
		fileUrl = inputUrl;
		
		System.out.print("Please provide a key: ");
		int theKey = sc.nextInt();
		
		while(theKey < 0 || theKey > 255)
		{
			
			System.err.println("Please provide an integer from 0 - 255");
			theKey = sc.nextInt();
		}
		theKey = theKey%59;
		List<String> textContent = new ArrayList<>();
		InputStream bytes = new FileInputStream(fileUrl);
			
		Reader characters = new InputStreamReader(bytes, StandardCharsets.UTF_8);
		BufferedReader lines = new BufferedReader(characters);
			
		while (true) 
		  {
		    String line = lines.readLine();
		    if (line == null)
		      break;
		    textContent.add(line);
		    encryptWithSub(theKey, line);
		  } 
		lines.close();	
		
	}

	private static void encryptionWithTranspositionOption() throws IOException
	{
		System.out.print("Please provide a valid url: ");
		String inputUrl = sc.next();
		fileUrl = inputUrl;
		
		System.out.print("Please provide a key: ");
		String theKey = sc.next();
		
		List<String> textContent = new ArrayList<>();
		InputStream bytes = null;
		
		try 
		{
			bytes = new FileInputStream(fileUrl);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Reader characters = new InputStreamReader(bytes, StandardCharsets.UTF_8);
		BufferedReader lines = new BufferedReader(characters);
			
		while (true) 
		  {
		    String line = lines.readLine();
		    if (line == null)
		      break;
		    textContent.add(line);
		    writeEncryptedFile(encryptWithTrans(line, theKey));
		    
		  } 
		lines.close();
	}
	
	
	/**
	 * Methods for encrypting end decrypting
	 * 
	 */
	
	private static void encryptWithSub(int key, String str)
	{
		
		char encrypted[] = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++)
		{
			
			if(str.charAt(i) == ' ')
			{
				encrypted[i] = ' ';
			}
			else if (str.charAt(i) == '*')
			{
				encrypted[i] = '*';
			}
			else
			{
				int j = 0;
				do
				{
					if(str.charAt(i) == Alphabet[j])
					{
						j++;
						break;
					}
					
					j++;
				}
				while(j != Alphabet.length - 59);
				
				encrypted[i] = Alphabet[j - 1 + key];
			}		 
		}
		System.out.println(encrypted);
		
		/**
		 * MAGIA
		 */
		
			String[] parts = fileUrl.split("[.]");
			String pathAndName = parts[0];
			String fileType = parts[1];
		  File file = new File(pathAndName + "_Encrypted."+fileType);
		  try {
		  FileWriter fw = new FileWriter(file,true);
		  BufferedWriter bw = new BufferedWriter(fw);
		  bw.write(encrypted);
		  bw.newLine();
		  bw.close();
		  } 
		  catch (IOException e) 
		  {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
	}
		
	private static void decryptWithSub(int key, String str)
	{
		char decrypted[] = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++)
		{
			
			if(str.charAt(i) == ' ')
			{
				decrypted[i] = ' ';
			}
			else if (str.charAt(i) == '*')
			{
				decrypted[i] = '*';
			}
			else
			{
				int j = 0;
				do
				{
					if(str.charAt(i) == Alphabet[j])
					{
						j++;
						break;
					}
					
					j++;
				}
				while(j != Alphabet.length - 59);
				
				decrypted[i] = Alphabet[j-1-key + 59];
			}		 
		}
		System.out.println(decrypted);
		
		// Splitting the given url and using the parts accordingly so i can rename it
		String[] parts = fileUrl.split("[.]");
		String pathAndName = parts[0];
		String fileType = parts[1];
		String[] stateParts = pathAndName.split("_");
		String state = stateParts [0];
		
		//writing on a new file
		  File file = new File(state + "_Decrypted."+ fileType);
		  FileWriter fw;
		  try {
			fw = new FileWriter(file,true);
		
		  BufferedWriter bw = new BufferedWriter(fw);
		  bw.write(decrypted);
		  bw.newLine();
		  bw.close();
		  } 
		  catch (IOException e) 
		  {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
	}
	
	
		/*
		 * Create a matrix  which and then fill it with the same
		 * letters from the plainText but in a different order.
		 */
	public static String encryptWithTrans(String plainText, String key)
	{
		int[] sortedArr = sortKey(key);

        int keyLength = sortedArr.length;
        int plainTextLength = plainText.length();

        int row = (int) Math.ceil((double) plainTextLength / keyLength);

        char[][] matrix = new char[row][keyLength];
        int counter = 0;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < keyLength; j++)
            {
                if (plainTextLength == counter)//to fill empty matrix spaces with a random alpha
                {
                	matrix[i][j] = fillWithRandomAlpha();
                    counter--;
                } else {
                	matrix[i][j] = plainText.charAt(counter);
                }

                counter++;
            }
        }
        String encrypted = "";
        
        for (int i = 0; i < keyLength; i++)
        {
            for (int j = 0; j < keyLength; j++)
            {
                if (i == sortedArr[j])
                {
                    for (int k = 0; k < row; k++)
                    {
                        encrypted = encrypted + matrix[k][j];
                    }
                }
            }
        }
        return encrypted;
    }
		public static void writeEncryptedFile(String str)
		{
			String[] parts = fileUrl.split("[.]");
			String pathAndName = parts[0];
			String fileType = parts[1];
			//writing on a new file
		  File file = new File(pathAndName + "_EncryptedTrans."+fileType);
		  FileWriter fw;
		try {
			fw = new FileWriter(file,true);
		
		  BufferedWriter bw = new BufferedWriter(fw);
		  bw.write(str);
		  bw.newLine();
		  bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		public static void writeDecryptedFile(String str)
		{
			String[] parts = fileUrl.split("[.]");
			String pathAndName = parts[0];
			String fileType = parts[1];
			String[] stateParts = pathAndName.split("_");
			String state = stateParts [0];
			
				//writing to a new file 
			  File file = new File(state + "_Decrypted_Trans."+ fileType);
			  FileWriter fw;
			  try {
				fw = new FileWriter(file,true);
			
			  BufferedWriter bw = new BufferedWriter(fw);
			  bw.write(str);
			  bw.newLine();
			  bw.close();
			  } 
			  catch (IOException e) 
			  {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  }
		
		}
			
	
		  
	
	
	private static String decryptWithTrans(String encText, String key)
	{
		 int[] sortedArr = sortKey(key);
	        int keyLength = sortedArr.length;
	        int plainTextLength = encText.length();

	        int row = (int) Math.ceil((double) plainTextLength / keyLength);

	        String symbols = "(?<=\\G.{" + row + "})";
	        String[] tmp = encText.split(symbols);

	        char[][] matrix = new char[row][keyLength];

	        for (int i = 0; i < keyLength; i++)
	        {
	            for (int j = 0; j < keyLength; j++)
	            {
	                if (sortedArr[i] == j) {
	                    for (int k = 0; k < row; k++)
	                    {
	                    	matrix[k][j] = tmp[sortedArr[j]].charAt(k);
	                    }
	                }
	            }
	        }

	        String decrypted = "";
	        
	        for (int i = 0; i < row; i++)
	        {
	            for (int j = 0; j < keyLength; j++)
	            {
	                decrypted = decrypted + matrix[i][j];
	            }
	        }

	        return decrypted;
	}
	
	public static char fillWithRandomAlpha()//create random alpha for the empty space
	{
        Random rd = new Random();
        return (char)(rd.nextInt(26) + 'a');
    }
	
	public static int[] sortKey(String key)
	{
        String[] keys = key.split("");
        Arrays.sort(keys);
        
        int[] num = new int[key.length()];
        for (int i = 0; i < keys.length; i++)
        {
            for (int j = 0; j < key.length(); j++)
            {
                if (keys[i].equals(key.charAt(j) + ""))
                {
                    num[j] = i;
                    break;
                }
            }
        }

        return num;
    }
}
