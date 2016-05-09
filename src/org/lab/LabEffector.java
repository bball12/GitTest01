package org.lab;

public abstract class LabEffector {

	public abstract void doRun();
		
	public void run() {
		doRun();
	}
}
