import org.junit.Test;
import static org.junit.Assert.*;

public class TestTrampolineTile{

	@Test(expected = TrampolineException.class)
	public void testTrampolineTile(){
		TrampolineTile t_tile = new TrampolineTile();
		t_tile.shake();
	}
}