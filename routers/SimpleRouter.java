package com.unimelb.swen30006.metromadness.routers;

import java.util.ArrayList;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

public class SimpleRouter implements PassengerRouter {

	@Override
	public boolean shouldLeave(Station current, Passenger p, ArrayList<Line> lines, Line trainLine) {
		Station destination = p.getDestination();
		ArrayList<Line> destinationLines = getLines(lines, destination);
		ArrayList<Line> StationLines = getLines(lines, current);
		
		//passenger will leave if he is in his destination or if he is currently in a station that takes him to his destination
		
		//Check is the passenger is in his destionation
		if(destination.equals(current)){
			return true;
		}
		
		//Check if the passenger is in the same Line that his destination
			//If yes, just wait until he reach the destination
		if(destinationLines.contains(trainLine)){
			return false;
		}
		
		//Check if the current station is in the same Line that the destination
			//If yes, get down there to catch other train
		for(int i=0; i<destinationLines.size(); i++){
			
			//If the current station is in the same line that the destination, get down there
			if(StationLines.contains(destinationLines.get(i))){
				return true;
			}
		}	
		
		
		return false;
		
		//return current.equals(p.getDestination());
		//Takes an station and returns all the lines that passes over it
		
	}
	
	private ArrayList<Line> getLines(ArrayList<Line> lines, Station s){
		
		ArrayList<Line> answer = new ArrayList<Line>();
		Line currentLine;
		ArrayList<Station> stations = new ArrayList<Station>();
		
		//Check each line to see if they have the required station
		for(int i=0; i<lines.size(); i++){
			currentLine = lines.get(i);
			stations = currentLine.getStations();
			for(int j=0; j<stations.size(); j++){
				if(s.equals(stations.get(j))){
					//If the line has not yet been added, append it
					if(!answer.contains(currentLine)){
						answer.add(currentLine);
					}
				}
			}
			
		}
		
		return answer;
	}

}
