import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {
    protected Provider providence;

    @Test
    public void testMemberVerification() throws Exception {
        providence = new Provider(884003881);
        assertEquals(true, providence.memberVerification(644147161)); //test using valid ID
        assertEquals(false, providence.memberVerification(-4562135)); //test using invalid ID
        assertEquals(false, providence.memberVerification(1));        //test using manager's ID
    }

    @Test
    public void testProviderDirectoryDisplay() throws Exception {

    }

    @Test
    public void testCreateBill() throws Exception {
        providence = new Provider(884003881);
        assertEquals(true,providence.data.serviceVerification(175812));
        assertEquals(false,providence.data.serviceVerification(111111));
    }

    @Test
    public void testMenuDisplay() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

    }
}
