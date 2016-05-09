package org.lab;

public abstract class LabEffector {

	public abstract void doTest();
		
	public void run() {
		doTest();
	}
}
