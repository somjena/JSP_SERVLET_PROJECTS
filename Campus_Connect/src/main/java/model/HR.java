package model;

public class HR extends User {
    private String companyName;
    private String companyDescription;
    private String companyLogoPath;

    public HR() {}

    public HR(int hrId, String username, String password, String email,
              String fullName, String contactNumber,
              String companyName, String companyDescription, String companyLogoPath) {
        super(hrId, username, password, email, "hr", fullName, contactNumber);
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.companyLogoPath = companyLogoPath;
    }

    // Getters and Setters
    public String getCompanyName() {
        return null;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyLogoPath() {
        return companyLogoPath;
    }

    public void setCompanyLogoPath(String companyLogoPath) {
        this.companyLogoPath = companyLogoPath;
    }

    @Override
    public String toString() {
        return "HR [companyName=" + companyName + ", userId=" + getUserId() + "]";
    }

    public void setHrId(int hrId) {
    }
}