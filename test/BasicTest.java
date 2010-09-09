import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }
	
    @Test
    public void createAndRetrieveEvent() {
        // Create a new user and save it
        new Event("Triathlon de Feins", "Sprint et CD", new Date()).save();
        
        // Retrieve the event from the title
        Event feins = Event.find("byTitle", "Triathlon de Feins").first();
        
        // Test 
        assertNotNull(feins);
        assertEquals("Sprint et CD", feins.description);
    }

    
    @Test
    public void useTheCompetitionRelation() {
        // Create a new event
        Event parisEvent = new Event("Triathlon de Paris", "CD et GP", new Date()).save();
     
        // Create a new types
    	Type tri = new Type("Triathlon").save();
    	Type aqu = new Type("Aquathlon").save();
    	Type dua = new Type("Duathlon").save();    	
    	        
        // Create a new formats
    	Format cd = new Format("CD").save();
    	Format sprint = new Format("Sprint").save();
    	Format avenir = new Format("Avenir").save();
    	
        // Add competitions to this event
        parisEvent.addCompetition(tri, cd);
        parisEvent.addCompetition(dua, sprint);
        parisEvent.addCompetition(aqu, avenir);
     
        // Count things
        assertEquals(1, Event.count());
        assertEquals(3, Type.count());
        assertEquals(3, Format.count());
        assertEquals(3, Competition.count());
     
        // Delete the event
        parisEvent.delete();
        
        // Check that all competition have been deleted
        assertEquals(0, Event.count());
        assertEquals(0, Competition.count());
    }

}
