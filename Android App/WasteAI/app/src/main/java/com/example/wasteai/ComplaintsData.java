package com.example.wasteai;

public class ComplaintsData {

private String queryID;
private String phNo;
private String complaintRegarding;
private String complaintDescription;

    public ComplaintsData() {
    }

    public String getQueryID() {
        return queryID;
    }

    public void setQueryID(String queryID) {
        this.queryID = queryID;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getComplaintRegarding() {
        return complaintRegarding;
    }

    public void setComplaintRegarding(String complaintRegarding) {
        this.complaintRegarding = complaintRegarding;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
