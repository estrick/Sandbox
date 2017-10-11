import GuessingGame.GuessingGame;
import GuessingGame.Status;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jc234295 on 11/10/17.
 */
public class TestGuessingGame {

    @Test
    public void testCreate() {
        GuessingGame guessingGame = new GuessingGame();
        Assert.assertEquals(1, guessingGame.min);
        Assert.assertEquals(10, guessingGame.max);
    }

    // Check Tests
    @Test
    public void testTrySmaller() {
        GuessingGame guessingGame = new GuessingGame();
        Status status = guessingGame.checkGuess(100);
        Assert.assertEquals(Status.TRY_SMALLER, status);
    }
    @Test
    public void testTryLarger() {
        GuessingGame guessingGame = new GuessingGame(10, 20);
        Status status = guessingGame.checkGuess(1);
        Assert.assertEquals(Status.TRY_LARGER, status);
    }
    @Test
    public void testTryFound() {
        mainLoop: for (int tries = 0; tries < 1000; ++tries) {
            GuessingGame guessingGame = new GuessingGame(10, 1000);
            int num;
            for (num = 10; num < 1001; ++num) {
                Status status = guessingGame.checkGuess(num);
                if (status == Status.FOUND) {
                    continue mainLoop;
                }
            }
            Assert.fail(String.format("Didn't find secret between %d-%d", guessingGame.min, guessingGame.max));
        }
    }

}
