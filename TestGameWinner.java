import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameWinner{

	@Test(expected = GameWinnerException.class)
	public void testGameWin(){
		
		Player p = new Player("Vibhu", 100);
		p.setCurrent(100);

		p.checkWinner();

	}
}