package com.data;

import java.util.Vector;

public class LeaveInfo {
	private String name;
    private String startDate;
    private String endDate;
    private String supervisor;
    
    public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
    
    public LeaveInfo() {
    }
    
    // getters and setters
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
 
    public String toString() {
//    	Vector<Vector> vecLeave = new Vector<Vector>();
    	Vector<String> vectLeave = new Vector<String>();
    	vectLeave.add(name);
    	vectLeave.add(startDate);
    	vectLeave.add(endDate);
    	vectLeave.add(supervisor);
    	return String.valueOf(vectLeave);
//        return String.format("%s - %s - %s - %s", name, startDate, endDate,supervisor);
    }
}
