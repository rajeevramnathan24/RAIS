package com.apache.RAIS.resources.Tobedeleted;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager_Donotuse {

private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String File) {
        if (extent == null) {
            extent = new ExtentReports(File, true);
            //new ExtentReports(filePath6, true);
            
            }
        
        return extent;
    }
	
}
