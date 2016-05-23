package com.mygdx.pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import sun.misc.Queue;


public class OpenSet extends PriorityQueue<Field>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(Field f){
		if(f.isMoveable()){
			return super.add(f);
	}
		return false;
	}
}
