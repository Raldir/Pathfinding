package com.mygdx.pathfinding;

import java.util.concurrent.CopyOnWriteArrayList;

public class OpenSet extends CopyOnWriteArrayList<Field>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(Field f){
		if(f.isMoveable()){
			return super.add(f);
		}else{
			return false;
		}

	}
}
