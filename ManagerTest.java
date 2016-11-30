import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Van on 11/29/16.
 */
public class ManagerTest {
    protected Manager ChocAn;

    @Test
    public void randomDigitsID() throws Exception {

    }

    @Test
    public void isIDTaken() throws Exception {
        ChocAn = new Manager(433671813);
        assertTrue(ChocAn.isIDTaken(987654321));
        assertFalse(ChocAn.isIDTaken(433671813));
    }

    @Test
    public void isValidMember() throws Exception {
        ChocAn = new Manager(433671813);

    }

    @Test
    public void addOrganization() throws Exception {

    }

    @Test
    public void removeOrganization() throws Exception {

    }

    @Test
    public void updateOrganization() throws Exception {

    }

    @Test
    public void changeMemberStanding() throws Exception {

    }

    @Test
    public void menuPrompt() throws Exception {

    }

    @Test
    public void editSubmenu() throws Exception {

    }

    @Test
    public void submenuRun() throws Exception {

    }

    @Test
    public void run() throws Exception {

    }

}