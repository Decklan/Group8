import org.junit.Test;

import static org.junit.Assert.*;


public class UtilityTest {
    Utility temp = new Utility();

    @Test
    public void testReadString() throws Exception {

    }

    @Test
    public void testReadInt() throws Exception {

    }

    @Test
    public void testCheckAnswer() throws Exception {

    }

    @Test
    public void testTestIntegerInput() throws Exception {
        assertEquals(true, temp.testIntegerInput("12365"));
        assertEquals(true, temp.testIntegerInput("123652323"));
        assertEquals(true, temp.testIntegerInput("-12365"));
        assertEquals(false, temp.testIntegerInput("asdgfg"));
        assertEquals(false, temp.testIntegerInput("daniel sanders"));
    }

    @Test
    public void testClearScreen() throws Exception {

    }

    @Test
    public void testWaitForEnter() throws Exception {

    }

    @Test
    public void testErrorMessage() throws Exception {

    }

    @Test
    public void testWarningMessage() throws Exception {

    }

    @Test
    public void testSuccessMessage() throws Exception {

    }
}