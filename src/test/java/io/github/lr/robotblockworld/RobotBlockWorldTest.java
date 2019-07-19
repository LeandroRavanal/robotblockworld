package io.github.lr.robotblockworld;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class RobotBlockWorldTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().muteForSuccessfulTests().enableLog();
	
	@Test
	public void onlyQuit() {
        String[] expectedResults = { 
        		"1\n2\n3\n4\n5\n" 
        };
        runTest(expectedResults, "quit");
	}
	
	@Test
	public void move1to2_ok() {
        String[] expectedResults = {
        		"\n2 1\n3\n4\n5\n"
        };
        runTest(expectedResults, "move 1 to 2", "quit");
	}
	
	@Test
	public void move1to2move3to1_ok() {
        String[] expectedResults = {
        		"\n2 1 3\n\n4\n5\n"
        };
        runTest(expectedResults, "move 1 to 2", "move 3 to 1", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3_ok() {
		String[] expectedResults = {
				"\n2 1 3 4\n\n\n5\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3move5to4_ok() {
		String[] expectedResults = {
				"\n2 1 3 4 5\n\n\n\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "move 5 to 4", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3move5to4move5to5_invalidLastMove() {
		//Equals move
		String[] expectedResults = {
				"\n2 1 3 4 5\n\n\n\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "move 5 to 4", "move 5 to 5", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to1_invalidLastMove() {
        //To is not leaf 
		String[] expectedResults = {
        		"\n2 1 3\n\n4\n5\n"
        };
        runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 1", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to2_invalidLastMove() {
        //To is root (not leaf)
		String[] expectedResults = {
        		"\n2 1 3\n\n4\n5\n"
        };
        runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 2", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to5_ok() {
        String[] expectedResults = {
        		"\n2 1 3\n\n\n5 4\n"
        };
        runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 5", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to5move5to3_ok() {
        //From root with son
		String[] expectedResults = {
        		"\n2 1 3 5\n\n4\n\n"
        };
        runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 5", "move 5 to 3", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to5move3to4_ok() {
		//From leaf with father
		String[] expectedResults = {
				"\n2 1\n\n\n5 4 3\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 5", "move 3 to 4", "quit");
	}

	@Test
	public void move1to2move3to1move4to5move1to4_ok() {
        //From with father and son
		String[] expectedResults = {
        		"\n2\n3\n\n5 4 1\n"
        };
        runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 5", "move 1 to 4", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3move5to4move1to4_invalidLastMove() {
		//Same row, both middle
		String[] expectedResults = {
				"\n2 1 3 4 5\n\n\n\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "move 5 to 4", "move 5 to 4", "move 1 to 4", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3move5to4move2to4_invalidLastMove() {
		//Same row, from root
		String[] expectedResults = {
				"\n2 1 3 4 5\n\n\n\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "move 5 to 4", "move 5 to 4", "move 2 to 4", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3move5to4move1to5_invalidLastMove() {
		//Same row, to leaf
		String[] expectedResults = {
				"\n2 1 3 4 5\n\n\n\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "move 5 to 4", "move 5 to 4", "move 1 to 5", "quit");
	}
	
	@Test
	public void move1to2move3to1move4to3move5to4move2to5_invalidLastMove() {
		//Same row, from root to leaf
		String[] expectedResults = {
				"\n2 1 3 4 5\n\n\n\n"
		};
		runTest(expectedResults, "move 1 to 2", "move 3 to 1", "move 4 to 3", "move 5 to 4", "move 5 to 4", "move 2 to 5", "quit");
	}
	
	@Test
	public void skipWrongCommand() {
		String[] expectedResults = { 
				"1\n2\n3\n4\n5\n" 
		};
		runTest(expectedResults, "moev 1 to 2", "quit");
	}
	
	@Test
	public void skipWrongArgument() {
		String[] expectedResults = { 
				"1\n2\n3\n4\n5\n" 
		};
		runTest(expectedResults, "move 0 to 2", "quit");
	}
	
	@Test
	public void skipWrongMove() {
        String[] expectedResults = { 
        		"1\n2\n3\n4\n5\n" 
        };
        runTest(expectedResults, "move 1 to 6", "quit");
	}
	
	@Test
	public void skipEqualsMove() {
        String[] expectedResults = { 
        		"1\n2\n3\n4\n5\n" 
        };
        runTest(expectedResults, "move 1 to 1", "quit");
	}
	
    private void runTest(String[] expectedOutput, String... input) {
        systemInMock.provideLines(input);
        
        RobotBlockWorld.main(new String[] { "5" });
        
        Assert.assertEquals(String.join("", expectedOutput), systemOutRule.getLogWithNormalizedLineSeparator());
    }
    
}
