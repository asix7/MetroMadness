/**
 * 
 */
package com.unimelb.swen30006.metromadness.routers;

import java.util.ArrayList;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

public interface PassengerRouter {

	public boolean shouldLeave(Station current, Passenger p, ArrayList<Line> lines, Line trainLine);
	
}
