package TaxiBookingSystem;
import java.util.*;

public class TaxiBooking {
	
	static List<taxi> createTaxis(int n){
		List<taxi> taxis = new ArrayList<taxi>();
		for(int i=0; i<n; i++) {
			taxi t = new taxi();
			taxis.add(t);
		}
		return taxis;
	}
	
	static List<taxi> getFreeTaxis(List<taxi> allTaxis, char pickup, int pickupTime){
		List<taxi> freeTaxis = new ArrayList<taxi>();
		for(taxi t : allTaxis) {
			if(pickupTime >= t.freeTime && (Math.abs(t.currentSpot - pickup)) <= (pickupTime - t.freeTime)) {
				freeTaxis.add(t);
			}
		}
		return freeTaxis;
	}
	
	//Book ticket method
	static void bookTaxi(int customerId, char pickupPoint, char dropPoint, int pickupTime, List<taxi> taxiList) {
		taxi bookedTaxi = null;
		int min = Integer.MAX_VALUE;
		char dropSpot = 'Z';
		int dropTime = 0;
		int earnings = 0;
		String tripDetail = "";
		for(taxi t : taxiList) {
			int distanceBwCustAndTaxi = Math.abs(pickupPoint-t.currentSpot)*15;
			if(distanceBwCustAndTaxi<min) {
				bookedTaxi = t;
				
				dropSpot = dropPoint;
				
				int distanceBwPickAndDrop = Math.abs(pickupPoint - dropPoint)*15;
				
				earnings = ((distanceBwPickAndDrop-5)*10)+100;
				
				dropTime = pickupTime + (distanceBwPickAndDrop/15); //Drop time is the next free time
				
				tripDetail = "   "+customerId + "	       " + pickupPoint+"	   "+dropPoint+"	    "+pickupTime+"		   "+dropTime+"		"+earnings;
			}
			min = distanceBwCustAndTaxi;
		}
		bookedTaxi.setDetails(dropTime, dropSpot, bookedTaxi.totalEarning+earnings, tripDetail);
		System.out.println("Taxi "+bookedTaxi.id+" is Booked Succesfully!");
	}

	public static void main(String[] args) {
		List<taxi> taxis = createTaxis(4);
		try (Scanner scanner = new Scanner(System.in)) {
			int id = 1;
			int choice;
			int customerId;
			while(true) {
				System.out.println("Enter 1 for Booking : ");
				System.out.println("Enter 2 for printing the Taxi details");
				choice = scanner.nextInt();
				char pickupPoint;
				char dropPoint;
				int pickupTime;
				scanner.nextLine();
				switch(choice) {
				case 1 : System.out.println("-----choice 1-------Book tickets-------------");
						 customerId = id;
						 System.out.println("Enter Pick up point : ");
						 pickupPoint = scanner.next().charAt(0);
						 System.out.println("Enter drop point : ");
						 dropPoint = scanner.next().charAt(0);
						 System.out.println("Enter Pickup Timing : ");
						 pickupTime = scanner.nextInt();
						 scanner.nextLine();
						 
						 //Invalid locations
						 if(pickupPoint < 'A' || pickupPoint > 'F' || dropPoint < 'A' || dropPoint > 'F') {
							 System.out.println("Give valid Pick up and Drop locations. Valid locations are A,B,C,D,E,F.");
							 return;
						 }
						 
						 //Get free taxis
						 List<taxi> freeTaxis = getFreeTaxis(taxis,pickupPoint,pickupTime);
						 
						 if(freeTaxis.size() == 0) {
							 System.out.println("All Taxis are busy. No taxis can be alloted");
							 continue;
						 }
						 
						 //Sort Free Taxis
						 Collections.sort(freeTaxis,(a,b)->Integer.compare(a.getTotalEarning(),b.getTotalEarning()));
						 
						 //Book ticket
						 bookTaxi(customerId,pickupPoint, dropPoint, pickupTime, freeTaxis);
						 id++;
			
						 break;
						 
				case 2 : System.out.println("----choice 2------View taxi Details-----------");
						 for(taxi ele : taxis) {
							 ele.printTaxidetails();
						 }
						 for(taxi ele : taxis) {
							 ele.printDetails();
						 }
						 break;
				default : continue;
				}
			}
		}
	}
}
