package com.clearso.lottery;

import java.util.Arrays;

public class Demo {

	public static void main(String[] args) {
		RandSeed rs = new RandSeed(10, 50, 100, 150);
		rs.generate();
		RandGenerate rg = new RandGenerate();
		rg.reload(rs.getSeed(), rs.getDivision(), 0, rs.getDivision(), System.currentTimeMillis());
		long [] sr;
		sr = new long[(int) rs.getDivision()];
		for(int i=0; i<rs.getDivision(); i++) {
			sr[i] = rg.next();
		}
		for(int i=0; i<rs.getDivision(); i++) {
			System.out.printf(" %d", sr[i]);
		}
		Arrays.sort(sr);
		System.out.println();
		for(int i=0; i<rs.getDivision(); i++) {
			System.out.printf(" %d", sr[i]);
		}
	}
	
}
