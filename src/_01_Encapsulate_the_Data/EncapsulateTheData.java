package _01_Encapsulate_the_Data;

public class EncapsulateTheData {

    /*
     * itemsReceived cannot negative. All negative parameters should set
     * itemsReceived to 0.
     */

    private int itemsReceived;
    public int getItemsReceived() {
    	return itemsReceived;
    }
    public void setItemsReceived(int i) {
    	if (i < 0) {
    		itemsReceived = 0;	
    	} else {
    		itemsReceived = i;
    	}
    }
    /*
     * degreesTurned must be locked between 0.0 and 360.0 inclusive. All
     * parameters outside this range should set degreesTurned to the nearest
     * bound.
     */
    
    private double degreesTurned;
    public double getDegreesTurned() {
    	return degreesTurned;
    }
    public void setDegreesTurned(double d) {
    	if (d < 0) {
    		degreesTurned = 0;
    	} else if (d > 360) {
    		degreesTurned = 360;
    	} else {
    		degreesTurned = d;
    	}
    }
    /*
     * nomenclature must not contain an empty String. An empty String parameter
     * should set nomenclature to a String with a single space.
     */

    private String nomenclature;
    public String getNomenclature() {
    	return nomenclature;
    }
    public void setNomenclature(String s) {
    	if (s == "") {
    		nomenclature = " "; 
    	} else {
    		nomenclature = s;
    	}
    }
    /*
     * memberObj must not be a String. A String parameter should set memberObj
     * to a new Object(); Hint: Use the instanceof operator.
     */

    private Object memberObj;
    public Object getMemberObj() {
    	return memberObj;
    }
    public void setMemberObj(Object i) {
    	if (i instanceof String) {
    		memberObj = new Object();
    	} else {
    		memberObj = i;
    	}
    }

}
