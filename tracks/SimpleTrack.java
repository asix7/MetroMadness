package com.unimelb.swen30006.metromadness.tracks;

import java.awt.geom.Point2D.Float;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.trains.Train;

public class SimpleTrack extends Track {


	private boolean occupied;
	
	public SimpleTrack(Float start, Float end, Color col) {
		super(start, end, col);
		this.occupied = false;
	}
	
	@Override
	public void render(ShapeRenderer renderer){
		renderer.rectLine(getStartPos().x, getStartPos().y, getEndPos().x, getEndPos().y, getLINEWIDTH());
	}
	
	@Override
	public void enter(Train t){
		this.occupied = true;
	}

	@Override
	public boolean canEnter(boolean forward){
		return !this.occupied;
	}

	@Override
	public void leave(Train t){
		this.occupied = false;
	}
	
	@Override
	public String toString() {
		return "Track [startPos=" + getStartPos() + ", endPos=" + getEndPos() + ", trackColour=" + getTrackColour() + ", occupied="
				+ occupied + "]";
	}

}
