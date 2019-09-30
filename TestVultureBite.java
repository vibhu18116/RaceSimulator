import org.junit.Test;
import static org.junit.Assert.*;

public class TestVultureBite{

	@Test(expected = VultureBiteException.class)
	public void testVultureBite(){
		VultureTile v_tile = new VultureTile();
		v_tile.shake();
	}
}