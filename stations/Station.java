package com.unimelb.swen30006.metromadness.stations;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.passengers.PassengerGenerator;
import com.unimelb.swen30006.metromadness.routers.PassengerRouter;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;

public class Station {
	
	private static final int PLATFORMS=2;
	private Point2D.Float position;
	private static final float RADIUS=6;
	private static final int NUM_CIRCLE_STATMENTS=100;
	private static final int MAX_LINES=3;
	private String name;
	private ArrayList<Line> lines;
	private ArrayList<Train> trains;
	private static final float DEPARTURE_TIME = 2;
	private PassengerRouter router;
	private int maxPax;
	private ArrayList<Passenger> waiting;
	
	private boolean passangerEnter;
	private boolean passangerLeave;
	
	

	public Station(float x, float y, PassengerRouter router, String name, int maxPax, boolean canEnter, boolean canLeave){
		this.name = name;
		this.router = router;
		this.position = new Point2D.Float(x,y);
		this.lines = new ArrayList<Line>();
		this.trains = new ArrayList<Train>();
		this.passangerEnter = canEnter;
		this.passangerLeave = canLeave;
		
		this.maxPax = maxPax;
		this.waiting = new ArrayList<Passenger>();
	}
	
	public ArrayList<Train> getTrains(){
		return this.trains;
	}
	
	public ArrayList<Line> getLines(){
		return this.lines;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPLATFORMS(){
		return this.PLATFORMS;
	}
	
	public Point2D.Float getPosition(){
		return this.position;
	}
	
	public ArrayList<Passenger> getWaitingList(){
		return this.waiting;
	}
	
	public void addWaiting(Passenger p){
		this.waiting.add(p);
	}
	
	public int getMaxPax(){
		return this.maxPax;
	}
	public boolean isActive(){
		return (passangerEnter || passangerLeave);
	}
	
	public boolean passangerCanEnter(){
		return passangerEnter; 
	}
	
	public boolean passangerCanLeave(){
		return passangerLeave; 
	}
	
	public void registerLine(Line l){
		this.lines.add(l);
	}
	
	public void render(ShapeRenderer renderer){
		float radius = RADIUS;
		for(int i=0; (i<this.lines.size() && i<MAX_LINES); i++){
			Line l = this.lines.get(i);
			renderer.setColor(l.getLineColour());
			renderer.circle(this.position.x, this.position.y, radius, NUM_CIRCLE_STATMENTS);
			radius = radius - 1;
		}
		
		// Calculate the percentage
		float t = this.trains.size()/(float)PLATFORMS;
		Color c = Color.WHITE.cpy().lerp(Color.DARK_GRAY, t);
		renderer.setColor(c);
		renderer.circle(this.position.x, this.position.y, radius, NUM_CIRCLE_STATMENTS);		
	}
	
	public void enter(Train t) throws Exception {
		if(getTrains().size() >= getPLATFORMS()){
			throw new Exception();
		} else {
			// Add the train
			this.getTrains().add(t);
			
			// Add the waiting passengers
			if(maxPax!=0){
				Iterator<Passenger> pIter = this.waiting.iterator();
				while(pIter.hasNext()){
					Passenger p = pIter.next();
					try {
						t.embark(p);
						pIter.remove();
					} catch (Exception e){
						// Do nothing, already waiting
						break;
					}
				}
				
				// Add the new passenger
				
			}
		}
	}
	
	
	public void depart(Train t) throws Exception {
		if(this.trains.contains(t)){
			this.trains.remove(t);
		} else {
			throw new Exception();
		}
	}
	
	public boolean canEnter(Line l) throws Exception {
		return trains.size() < PLATFORMS;
	}

	// Returns departure time in seconds
	public float getDepartureTime() {
		return DEPARTURE_TIME;
	}

	public boolean shouldLeave(Passenger p, Line trainLine) {
		return this.router.shouldLeave(this, p, this.lines, trainLine);
	}

	@Override
	public String toString() {
		return "Station [position=" + position + ", name=" + name + ", trains=" + trains.size()
				+ ", router=" + router + "]";
	}

	public Passenger generatePassenger(Station s) {
		return new Passenger(this, s);
	}
	
	
}
