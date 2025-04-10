package com.Entities;

public class Report {

    private int reportID;
    private String type;
    private String content;
    private int adminID;
    private int userId;

    public Report(int reportId, String reportType, String reportContent,int adminID, int userId) {
        
    }

    // Default constructor
    public Report() {
        this.reportID = 0;
        this.type = "";
        this.content = "";
        this.userId = 0;
    }
    public Report(int reportID, String type, String content, int userId) {
        this .reportID = reportID;
        this.type = type;
        this.content = content;
        this.userId = userId;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String toString() {
        return "Report reportID= " + reportID + ", type= " + type + ", content= " + content + ", adminID= " + adminID ;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
