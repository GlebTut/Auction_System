package com.Services;

import com.DAO.reportDAO;
import com.Entities.Report;
import java.sql.SQLException;

public class ReportService {

    public static void createReport(Report report) throws SQLException {
        // Ensure that admin privileges is not null or empty 
        if (report.getType() == null || report.getType().isEmpty()) {
            throw new IllegalArgumentException("Report type can't be empty.");
        }
        reportDAO.createReport(report);
    }
}
