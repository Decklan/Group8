package test;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManagerTest {
    protected Manager ChocAn;

    @Test
    public void isIDTaken() throws Exception {
        ChocAn = new Manager(433671813);
        assertTrue(ChocAn.isIDTaken(987654321));
        assertFalse(ChocAn.isIDTaken(433671813));
    }

    @Test
    public void isValidMember() throws Exception {
        ChocAn = new Manager(433671813);
        assertTrue(ChocAn.isValidMember(638287304)); //Active Member
        assertTrue(ChocAn.isValidMember(200630841)); //Suspended Member
        assertFalse(ChocAn.isValidMember(433671813)); //Manager
        assertFalse(ChocAn.isValidMember(884003881)); //Provider
        //Active == Suspended == True
        Assert.assertEquals(ChocAn.isValidMember(638287304), ChocAn.isValidMember(200630841));
    }

}