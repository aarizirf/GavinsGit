package testers;
import git.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BlobTester {
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
	
	@Test
	void isBlobFileCreated() {
		Blob b = new Blob("txt.txt");
		File f = new File("./test/objects/94e66df8cd09d410c62d9e0dc59d3a884e458e05");
		assertTrue(f.exists());//checks if file named the sha1 of some content exists in objects
	}
	
	

}
