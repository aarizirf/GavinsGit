package testers;
import git.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class CommitTester {

	@Test
	void testCommit() {
		try {
			Commit c = new Commit("objects/hash", "summary", "author", null);
			
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
