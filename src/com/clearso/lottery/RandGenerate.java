package com.clearso.lottery;

import java.util.concurrent.atomic.AtomicLong;

public class RandGenerate{
	
	private long[] exp = new long[2];
	private long[] divisor = new long[2];
	private long[] rfrom = new long[2];
	private long[] rto = new long[2];
	private long trigTime[] = new long[2];
	
	private final int reloadDelay = 50000;
	
	AtomicLong walker;
	
	public RandGenerate() {
		walker = new AtomicLong(0);
		trigTime[0] = 0L;
		trigTime[1] = 0L;
	}

	public void reload(long exp, long divisor, long from, long to) {
		reload(exp, divisor, from, to, System.currentTimeMillis()+reloadDelay);
	}
	
	public void reload(long exp, long divisor, long from, long to, long timestamp) {
		int now;
		if(trigTime[1] <= trigTime[0]) {
			now = 1;
		}else {
			now = 0;
		}
		this.exp[now] = exp;
		this.divisor[now] = divisor;
		rfrom[now] = from;
		rto[now] = to;
		trigTime[now] = timestamp;
	}
	
	public long next() {
		int now;
		long ntime = System.currentTimeMillis();
		if(trigTime[1] <= trigTime[0]) {
			now = 0;
		}else {
			now = 1;
		}
		if(ntime < trigTime[now]) {
			now = 1-now;
		}
		long nw = walker.addAndGet(1);
		long cmill = rfrom[now]+(nw % (rfrom[now] - rto[now]));
		return Calc.powerMod(cmill, exp[now], divisor[now]);
	}
	
}
