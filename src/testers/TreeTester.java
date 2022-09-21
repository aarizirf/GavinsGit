package testers;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import git.*;

class TreeTester {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//create test folder, objects folder in test, three txt files
		File foldert = new File("./test");
		foldert.mkdirs();
		File foldero = new File("./test/objects");
		foldero.mkdirs();
		PrintWriter writer = new PrintWriter("./test/txt.txt");
		writer.println("some content");
		writer.close();
		PrintWriter writer1 = new PrintWriter("./test/txt1.txt", "UTF-8");
		writer1.println("some content1");
		writer1.close();
		PrintWriter writer2 = new PrintWriter("./test/txt2.txt", "UTF-8");
		writer2.println("some content2");
		writer2.close();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//deleting all txt files
		File f = new File("./test/txt.txt");
		f.delete();
		File f1 = new File("./test/txt1.txt");
		f1.delete();
		File f2 = new File("./test/txt2.txt");
		f2.delete();
		File index = new File("./test/index.txt");
		index.delete();
		
		//deleting objects folder w/ all contents  inside
		File folder = new File("./test/objects");
		String[]entries = folder.list();
		for(String s: entries){
		    File currentFile = new File(folder.getPath(),s);
		    currentFile.delete();
		}
		folder.delete();
		
		//delete test folder
		File test = new File("./test");
		test.delete();
	}
	
//	TODO: update this test to make sure filenames are correct
	@Test
	void testTree() throws IOException {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		arr.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		arr.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		arr.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		arr.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		Tree tree = new Tree(arr);
		File file = new File("./test/objects/dd4840f48a74c1f97437b515101c66834b59b1be");
		Scanner sc = new Scanner(file);
		
		for(int i = 0; i<arr.size(); i++) {
			assertTrue(arr.get(i).equals(sc.nextLine()));
		}
		Files.deleteIfExists(Paths.get("./test/objects/dd4840f48a74c1f97437b515101c66834b59b1be"));
		assertFalse(file.exists());
	}

}
