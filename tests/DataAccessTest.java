import org.junit.Test;

import static org.junit.Assert.*;


public class DataAccessTest {

    protected DataAccess dataAccess = new DataAccess();

    @Test
    public void serviceVerification() throws Exception {

        assertEquals(true, dataAccess.serviceVerification(453965)); //valid service ID
        assertEquals(false, dataAccess.serviceVerification(655965));//invalid service ID
    }

    @Test
    public void testUserVerification() throws Exception {

        assertEquals("manager", DataAccess.userVerification(1));
        assertEquals("member", DataAccess.userVerification(587857871));
        assertEquals("provider", DataAccess.userVerification(860261318));
        assertEquals("invalid", DataAccess.userVerification(860212345));
    }

    /*
     suspendMember() returns true with invalid IDs because the DataAccess class does not check
     for valid IDs(Manager class does).
      */
    @Test
    public void suspendMember() throws Exception {

        assertEquals(true, dataAccess.suspendMember(473956649));    //valid member ID
        assertEquals(true, dataAccess.suspendMember(98781));        //invalid member ID
    }

    /*
    unsuspendMember() returns true with invalid IDs because the DataAccess class does not check
    for valid IDs(Manager class does).
     */
    @Test
    public void unsuspendMember() throws Exception {

        assertEquals(true, dataAccess.unsuspendMember(200630841));  //valid member ID
        assertEquals(true, dataAccess.unsuspendMember(78781));      //invalid member ID
    }

    /*
    addOrganization, updateOrganization, and removeOrganization use the same three
    IDs for the sake of testing.
     */
    @Test
    public void addOrganization() throws Exception {

        assertEquals(true, dataAccess.addOrganization(998877665,
                "Donald Trump", "Trump Tower", "New York", "NY", 10022, "member"));

        assertEquals(true, dataAccess.addOrganization(887766554,
                "Hillary Clinton", "Wall Street", "New York", "NY", 10022, "provider"));

        assertEquals(true, dataAccess.addOrganization(776655443,
                "Bernie Sanders", "Vermont St", "Montpelier", "VT", 55601, "manager"));
    }

    @Test
    public void updateOrganization() throws Exception {

        assertEquals(true, dataAccess.updateOrganization(998877665,
                "Sir Donald Trump", "White House", "Washington", "DC", 20500, "member"));

        assertEquals(true, dataAccess.updateOrganization(887766554,
                "Miss Hillary Clinton", "Not The Whit House", "Washington", "DC", 20500, "provider"));

        assertEquals(true, dataAccess.updateOrganization(776655443,
                "Feel The Bern", "Not Wall Street", "Washington", "DC", 20500, "manager"));
    }

    @Test
    public void removeOrganization() throws Exception {

        assertEquals(true, dataAccess.removeOrganization(998877665));
        assertEquals(true, dataAccess.removeOrganization(887766554));
        assertEquals(true, dataAccess.removeOrganization(776655443));
    }

}
