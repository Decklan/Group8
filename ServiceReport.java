import java.util.Date;

// Holds report information for a member
public class ServiceReport {
    // What we need for the service report is just the date it was provided, the
    // the provider name, and the service name. No need for anything else. Can
    // modify data access to account for this.
    public Date ServiceDate;
    public int SerivceID;
    public int MemberId;
    public int ProviderID;
    public String ServiceName;
    public String PatientName;
    public String Comment;
    public float Fee;
}
