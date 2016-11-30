import org.junit.Test;

import static org.junit.Assert.*;


public class UtilityTest {


    @Test
    public void testTestIntegerInput() throws Exception {
        Utility temp = new Utility();
        assertEquals(true, temp.testIntegerInput("12365"));
        assertEquals(true, temp.testIntegerInput("123652323"));
        assertEquals(true, temp.testIntegerInput("-12365"));
        assertEquals(false, temp.testIntegerInput("asdgfg"));
        assertEquals(false, temp.testIntegerInput("daniel sanders"));
    }

}