import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestSnakeBite.class,
	TestCricketBite.class,
	TestVultureBite.class,
	TestTrampolineTile.class,
	TestGameWinner.class,
	TestSerialisation.class
})
public class TestSuite { 

}