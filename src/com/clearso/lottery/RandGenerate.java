package com.clearso.lottery;

import java.util.concurrent.atomic.AtomicInteger;

public class RandGenerate{
	
	private long[] exp = new long[2];
	private long[] base = new long[2];
	private long[] rfrom = new long[2];
	private long[] rto = new long[2];
	private long nextTimestamp;
	private int now;
	
	AtomicInteger walker;
	
	public RandGenerate() {
		walker = new AtomicInteger(0);
		nextTimestamp = 0L;
		now = 0;
	}

	public void SetCalc(long exp, long base, long from, long to) {
		SetCalc(exp, base, from, to, 0L);
	}
	
	public void SetCalc(long exp, long base, long from, long to, long timestamp) {
		this.exp[1-now] = exp;
		this.base[1-now] = base;
		this.rfrom[1-now] = from;
		this.rto[1-now] = to;
		this.nextTimestamp = timestamp;
		if(timestamp == 0L) {
			now = 1-now;
		}
	}
	
}
