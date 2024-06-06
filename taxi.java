package TaxiBookingSystem;
import java.util.*;

public class taxi {
	static int taxiCount = 0;
	int id;
	int totalEarning;
	char currentSpot;
	int freeTime;
	List<String> trips;
	
	taxi(){
		taxiCount++;
		id = taxiCount;
		freeTime = 6;
		currentSpot = 'A';
		totalEarning = 0;
		trips = new ArrayList<String>();
	}
	
	void setDetails(int freeTime, char currSpot, int earning, String tripDetail){
		this.freeTime = freeTime;
		this.currentSpot = currSpot;
		this.totalEarning = earning;
		trips.add(tripDetail);
	}
	void printTaxidetails() {
		System.out.println("Taxi id - "+id+" Current Spot - "+currentSpot+" totalEarning - "+totalEarning+" Free Time - "+freeTime);
		System.out.println("-----------------------------------------------------------------------");
	}
	
	void printDetails() {
		System.out.println("Taxi  - "+id+" Total Earnings - "+totalEarning);
		System.out.println("Taxi ID		Customer ID	From	To	PickupTime	DropTime	Amount");
		for(String trps : trips) {
			System.out.println("   "+id+"    	"+trps);
		}
		System.out.println("======================================================================");
	}
	
	int getTotalEarning() {
		return totalEarning;
	}
}
