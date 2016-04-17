package com.unimelb.swen30006.metromadness.tracks;

import java.awt.geom.Point2D.Float;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.trains.Train;

public class DualTrack extends Track {

	private boolean forwardOccupied;
	private boolean backwardOccupied;
	
	public DualTrack(Float start, Float end, Color col) {
		super(start, end, col);
		this.forwardOccupied = false;
		this.backwardOccupied = false;
	}
	
	
	@Override
	public void enter(Train t){
		if(t.getForward()){
			this.forwardOccupied = true;
		} else {
			this.backwardOccupied = true;
		}
	}

	@Override
	public boolean canEnter(boolean forward) {
		if(forward){
			return !this.forwardOccupied;
		} else {
			return !this.backwardOccupied;
		}
	}

	@Override
	public void leave(Train t) {
		if(t.getForward()){
			this.forwardOccupied = false;
		} else {
			this.backwardOccupied = false;
		}
	}
	
	@Override
	public void render(ShapeRenderer renderer){
		renderer.rectLine(getStartPos().x, getStartPos().y, getEndPos().x, getEndPos().y, getLINEWIDTH());
		renderer.setColor(new Color(245f/255f,245f/255f,245f/255f,0.5f).lerp(getTrackColour(), 0.5f));
		renderer.rectLine(getStartPos().x, getStartPos().y, getEndPos().x, getEndPos().y, getLINEWIDTH()/3);
		renderer.setColor(getTrackColour());
	}
	
	@Override
	public String toString() {
		return "Track [startPos=" + getStartPos() + ", endPos=" + getEndPos() + ", trackColour=" + getTrackColour() + ", forwardOccupied="
				+ forwardOccupied + ", backwardOccupied=" + backwardOccupied + "]";
	}
}
