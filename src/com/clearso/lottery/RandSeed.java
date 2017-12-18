package com.clearso.lottery;

public class RandSeed {
	
	private final static long go_seed = 65537;
	private long run_seed;
	private long division;
	
	private long[] vmin = new long[2];
	private long[] vmax = new long[2];
	
	public RandSeed(long u1, long s1, long u2, long s2) {
		vmin[0] = u1;
		vmax[0] = s1;
		vmin[1] = u2;
		vmax[1] = s2;
	}
	
	private long choosePrime(long min, long max) {
		long seed = min + (long)(Math.random()*(max-min)*0.8);
		return nextPrime(seed);
	}
	
	private long nextPrime(long from) {
		for(from |= 1L; !Calc.isPrime(from); from += 2);
		return from;
	}
	
	public void generate() {
		long p = choosePrime(vmin[0], vmax[0]);
		long q = choosePrime(vmin[1], vmax[1]);
		division = p*q;
		long oula = (p-1)*(q-1);
		for(run_seed = go_seed; gcd(run_seed, oula) != 1; run_seed = nextPrime(run_seed + 2));
	}

	public long getSeed() {
		return run_seed;
	}
	
	public long getDivision() {
		return division;
	}
	
	public long gcd(long src1, long src2) {
		while(src2 != 0) {
			long r = src2;
			src2 = src1 % src2;
			src1 = r;
		}
		return src1;
	}
	
}
