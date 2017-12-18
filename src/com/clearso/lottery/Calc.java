package com.clearso.lottery;

//if n < 1,373,653, it is enough to test a = 2 and 3.
//if n < 9,080,191, it is enough to test a = 31 and 73.
//if n < 4,759,123,141, it is enough to test a = 2, 7, and 61. * we choose this
//if n < 2,152,302,898,747, it is enough to test a = 2, 3, 5, 7, and 11.

public class Calc {

	public static final long prime_max = 4759123141L;
	
	public static boolean isPrime(long target) {
		if(target==2 || target==3) {
			return true;
		}else if( (target&1) == 0 || (target%3 == 0) ) {
			return false;
		}else if( (target>2) && millerRabin(2,target) && 
		    (target<=7 || millerRabin(7,target)) &&
		    (target<=61|| millerRabin(61,target))) {
			return true;
		}else {
			return false;
		}
	}
		
	private static boolean millerRabin(long base, long target) {
		long d;
		for(d=target-1; 0 == (d&1); d>>=1);
		long test = powerMod(base, d, target);
		if( test == 1 || test == target-1) {
			return true;
		}else {
			long t;
			for(t = (target-1)/2; d != t; ) {
				d<<=1;
				if(powerMod(base, d, target) == target-1) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static long powerMod(long base, long power, long divisor) {
		if (power == 0) {
			return 1;
		}else if(power == 1) {
			return base % divisor;
		}else {
			long mod;
			mod = base%divisor;
			if(0 == (power & 1)) {
				return powerMod(mod*mod, power >> 1, divisor);
			}else {
				return (mod * powerMod(mod*mod, power/2, divisor))%divisor;
			}
		}
	}
}
