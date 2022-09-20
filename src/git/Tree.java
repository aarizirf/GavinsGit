package git;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore.Entry;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
	
	private ArrayList<String> fullArray;
	
	private String sha1Array = ""; 
	
	String text;
	
	public static void main(String[] args) {
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		arr.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		arr.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		arr.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		arr.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		try {
			Tree t = new Tree(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Tree (ArrayList<String> strArr) throws IOException {
		this.fullArray = strArr; //save array into fullArray
		
		File index = new File("./test/index.txt");
		HashMap<String, String> indexMap = getMapFromFile(index);
		text = "";
		
		for (String s : fullArray) {//create string with all lines in array
			String withFilename = s + " ";
			
			String sha = s.substring(s.length()-40);
			
			System.out.println(sha);
			
			
			for (java.util.Map.Entry<String, String> entry : indexMap.entrySet()) {
				System.out.println(entry.getValue());
//	            if (entry.getValue().equals(sha)) {
//	                withFilename += entry.getKey();
//
//	            }
	            withFilename += "filenameexample";
	        }
			
			System.out.println(withFilename);
			
			
			text+=withFilename + "\n";
			
			System.out.println(sha1Array);
		}
		sha1Array = getSha1(text.trim());//turn list of blobs/trees -> sha1 for name
		
		writeList();
	}
	
	public HashMap<String, String> getMapFromFile(File file) {
		HashMap<String, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals("")) continue;
                String hash = line.substring(line.length()-40);
                String filename = line.substring(0, line.length()-43);
                map.put(filename, hash);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return map;
    }
	
	private void writeList() throws IOException {
		File f = new File("test/objects/" + sha1Array);
		FileWriter writer = new FileWriter (f);
		
		writer.append(text);
//		for (String s : fullArray) {
//			writer.append(s + "\n");
//		}
		
		writer.close();
	}
	
	
	private static String getSha1 (String input) {
		String value = input;
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}

		return sha1;
	}
}
