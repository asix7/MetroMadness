package com.unimelb.swen30006.metromadness.tracks;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.trains.Train;

public abstract class Track {
	
	private static final float DRAW_RADIUS=10f;
	private static final int LINE_WIDTH=6;
	private Point2D.Float startPos;
	private Point2D.Float endPos;
	private Color trackColour;
	
	
	public Track(Point2D.Float start, Point2D.Float end, Color trackCol){
		this.startPos = start;
		this.endPos = end;
		this.trackColour = trackCol;
	}
	
	/* Getters - Setters */
	public Color getTrackColour(){
		return this.trackColour;
	}
	
	public Point2D.Float getEndPos(){
		return this.endPos;
	}
	
	public Point2D.Float getStartPos(){
		return this.startPos;
	}
	
	public float getDRAWRADIUS(){
		return this.DRAW_RADIUS;
	}
	
	public float getLINEWIDTH(){
		return this.LINE_WIDTH;
	}
	
	/* Abstract methods */
	public abstract boolean canEnter(boolean forward);
	public abstract void enter(Train t);
	public abstract void leave(Train t);
	public abstract void render(ShapeRenderer renderer);
	public abstract String toString();
}
