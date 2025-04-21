package model;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class Job {
    private int jobId;
    private int hrId;
    private HR hr; // HR object reference
    private String title;
    private String description;
    private String requirements;
    private String location;
    private String salary;
    private Date postedDate;
    private Date deadline;
    private boolean isVerified;
    private int verifiedBy;
    private Date verifiedDate;
    private String eligibleBranches; // Comma-separated string
    private double minCgpa;

    // Constructors
    public Job() {}

    public Job(int jobId, int hrId, String title, String description, String requirements,
               String location, String salary, Date postedDate, Date deadline,
               boolean isVerified, int verifiedBy, Date verifiedDate,
               String eligibleBranches, double minCgpa) {
        this.jobId = jobId;
        this.hrId = hrId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salary = salary;
        this.postedDate = postedDate;
        this.deadline = deadline;
        this.isVerified = isVerified;
        this.verifiedBy = verifiedBy;
        this.verifiedDate = verifiedDate;
        this.eligibleBranches = eligibleBranches;
        this.minCgpa = minCgpa;
    }

    // Getters and Setters
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public HR getHr() {
        return hr;
    }

    public void setHr(HR hr) {
        this.hr = hr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public int getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(int verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getEligibleBranches() {
        return eligibleBranches;
    }

    public void setEligibleBranches(String eligibleBranches) {
        this.eligibleBranches = eligibleBranches;
    }

    public double getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(double minCgpa) {
        this.minCgpa = minCgpa;
    }

    // Utility methods
    public List<String> getEligibleBranchesList() {
        return Arrays.asList(eligibleBranches.split(","));
    }

    public boolean isEligibleForBranch(String branch) {
        return getEligibleBranchesList().contains(branch);
    }

    @Override
    public String toString() {
        return "Job [jobId=" + jobId + ", title=" + title + ", company=" +
                (hr != null ? hr.getCompanyName() : "N/A") + ", location=" + location +
                ", deadline=" + deadline + "]";
    }
}