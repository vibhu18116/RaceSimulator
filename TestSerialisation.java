import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class TestSerialisation{
	
	@Test
	public void testSerialize() throws IOException, ClassNotFoundException{

		Race r = new Race("Vibhu", 100);
		r.setCurrentTile(25);
		r.serialize();

		Race returned = App.loadSavedGame("Vibhu");

		assertEquals(r,returned);
	}

	@Test
	public void falseSerialisation() throws IOException, ClassNotFoundException{

		Race r = new Race("VA", 200);
		r.setCurrentTile(25);
		r.serialize();

		r.setCurrentTile(30);

		Race returned = App.loadSavedGame("VA");
		assertNotEquals(r,returned);
	}
}