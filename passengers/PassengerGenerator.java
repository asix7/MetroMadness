package com.unimelb.swen30006.metromadness.passengers;

import java.util.ArrayList;

import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;

public class PassengerGenerator {
	
	// The station that passengers are getting on
	//public Station current;
	// All the stations
	public ArrayList<Station> stations;
	public ArrayList<Train> trains;
	
	// The max volume
	public float maxVolume;
	
	public PassengerGenerator(ArrayList<Station> stations, ArrayList<Train> trains){
		this.trains = trains;
		this.stations = stations;
	}
	
	public Passenger[] generatePassengers(Station current, ArrayList<Train> trains){
		//If the station is inactive, maxPax = 0 and no passengers is created
		int count = (int) (Math.random()*current.maxPax);
		Passenger[] passengers = new Passenger[count];
		for(int i=0; i<count; i++){
			passengers[i] = generatePassenger(current, trains);
		}
		return passengers;
	}
	
	public Passenger generatePassenger(Station current, ArrayList<Train> trains){
		// Pick a random station from the line
		
		Station destination = stations.get((int)(Math.random() * stations.size()));
		ArrayList<Line> workingLines = new ArrayList<Line>();
		ArrayList<Station> workingStations = new ArrayList<Station>();
		
		//Checks all the lines and stations that have trains running
		for(int n=0; n<trains.size(); n++){
			Line line = (trains.get(n)).getTrainLine();
			workingLines.add(line);
			workingStations.addAll(line.getStations());
		}
		
		
		
		//Checks that the destination is not the current station and
			//that the destination is active (maxpax!=0)
				//and that there is trains running to that stations
		while(destination.maxPax==0 || current.equals(destination) || (!workingStations.contains(destination))){
			destination = stations.get((int)(Math.random() * stations.size()));
		}
		Passenger passenger = new Passenger(current, destination);
		return passenger;
	}
	
}
