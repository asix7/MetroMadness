package com.unimelb.swen30006.metromadness.passengers;

import java.util.ArrayList;

import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

public class PassengerGenerator {
	
	// The station that passengers are getting on
	//public Station current;
	// All the stations
	public ArrayList<Station> stations;
	
	// The max volume
	public float maxVolume;
	
	public PassengerGenerator(ArrayList<Station> stations){
		//this.current = current;
		this.stations = stations;
	}
	
	public Passenger[] generatePassengers(Station current){
		//If the station is inactive, maxPax = 0 and no passengers is created
		int count = (int) (Math.random()*current.maxPax);
		Passenger[] passengers = new Passenger[count];
		for(int i=0; i<count; i++){
			passengers[i] = generatePassenger(current);
		}
		return passengers;
	}
	
	public Passenger generatePassenger(Station current){
		// Pick a random station from the line
		
		Station destination = stations.get((int)(Math.random() * stations.size()));
		
		//Checks that the destination is not the current station and
			//that the destination is active (maxpax!=0)
		while(destination.maxPax==0 || current.equals(destination)){
			destination = stations.get((int)(Math.random() * stations.size()));
		}
		Passenger passenger = new Passenger(current, destination);
		return passenger;
	}
	
}
