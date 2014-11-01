/*
Spell Checker
M.Hilton-mph2137
CS3134-Fall2014
*/
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class SpellChecker {
	
	public static QuadraticProbingHashTable hash;
	
	public static int ln=0;
	public static List<String> list; 
	public static ArrayList<String> alternates;
	
	public static ArrayList alternate(String s){
		alternates = new ArrayList<String>();
		//alternates=null;
		//insert letter, change letter, and delete letter
	    for(int i=0; i <= s.length()-1 ; i++) {
	    	//delete letter
            String word3 = s.substring(0, i)+s.substring(i+1);
            if(hash.contains(word3))	{ alternates.add(word3); }
	    	for(char l = 'a'; l <= 'z' ; l++) {
	        	//insert letter
	            String word = (s.substring(0,i) + l + s.substring(i));
	            //change letter
	            String word2 = s.substring(0,i)+l+s.substring(i+1);
	            
	            //check for matches
	            if(hash.contains(word))		{ alternates.add(word); }
	            if(hash.contains(word2))	{ alternates.add(word2); }
	            
	        }
	    }
		
	    return alternates;
	}
	
	public static void process(String s, int j){
		
		s+=" ";
		s=s.replace(". ", "");
		s=s.replace(", ", "");
		s=s.replace("? ", "");
		s=s.replace("! ", "");
		s=s.replace("- ", "");
		s=s.replace("; ", "");
		s=s.replace("' ", "");
		s=s.replace(": ", "");
		s=s.replace(" ","");
		
		if(!hash.contains(s)){
			alternate(s);
			String alt = alternates.toString();
			if(alt.matches(".*[a-zA-Z]+.*")){
				
				System.out.println(s+" is mispelled at line "+ln+". Replacements are "+alt);
			}
			else
				System.out.println(s+" is mispelled at line "+ln+". No replacements found.");
		}
		else
			;//word is properly spelled, do nothing
		
	}

	public static void main(String[] args) {
		BufferedReader reader1 = null, reader2 = null, reader3=null;
		hash = new QuadraticProbingHashTable();
		
		try {
		    File file1 = new File(args[0]);
		    File file2 = new File(args[1]);
		    File file3 = new File(args[2]);
		    reader1 = new BufferedReader(new FileReader(file1));
		    reader2 = new BufferedReader(new FileReader(file2));
		    reader3 = new BufferedReader(new FileReader(file3));
		    
		    String line1, line2, line;
		    
		    while ((line1 = reader1.readLine()) != null) {
		    	hash.insert(line1.toLowerCase());
		    }
		    
		    while((line2 = reader2.readLine()) != null){ 	
		    	hash.insert(line2.toLowerCase());
		    }
	
		    while ((line = reader3.readLine()) != null) {
		    	ln++;
		    	String split[]= line.split("\\s");
		    	for (String token: split)
		    	{
		    		process(token.toLowerCase(), ln);
		    	}
		    }
		   
		   
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader1.close();
		        reader2.close();
		        reader3.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}

	}

}

