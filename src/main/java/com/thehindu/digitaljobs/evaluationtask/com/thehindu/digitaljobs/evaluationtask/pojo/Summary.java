
package com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.evaluationtask.pojo;

import java.util.List;

public class Summary{
   	private Number area;
   	private String bathrooms;
   	private String bedroom;
   	private String carParking;
   	private List floorPlans;
   	private String landArea;
   	private String noOfUnits;
   	private Number price;
   	private String propertyType;

 	public Number getArea(){
		return this.area;
	}
	public void setArea(Number area){
		this.area = area;
	}
 	public String getBathrooms(){
		return this.bathrooms;
	}
	public void setBathrooms(String bathrooms){
		this.bathrooms = bathrooms;
	}
 	public String getBedroom(){
		return this.bedroom;
	}
	public void setBedroom(String bedroom){
		this.bedroom = bedroom;
	}
 	public String getCarParking(){
		return this.carParking;
	}
	public void setCarParking(String carParking){
		this.carParking = carParking;
	}
 	public List getFloorPlans(){
		return this.floorPlans;
	}
	public void setFloorPlans(List floorPlans){
		this.floorPlans = floorPlans;
	}
 	public String getLandArea(){
		return this.landArea;
	}
	public void setLandArea(String landArea){
		this.landArea = landArea;
	}
 	public String getNoOfUnits(){
		return this.noOfUnits;
	}
	public void setNoOfUnits(String noOfUnits){
		this.noOfUnits = noOfUnits;
	}
 	public Number getPrice(){
		return this.price;
	}
	public void setPrice(Number price){
		this.price = price;
	}
 	public String getPropertyType(){
		return this.propertyType;
	}
	public void setPropertyType(String propertyType){
		this.propertyType = propertyType;
	}
}
