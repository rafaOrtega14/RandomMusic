package com.example.mac.musicote;

/**
 * Created by David on 07/12/2016.
 */

public enum Scale {
    StandardMayor(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}),
    PentatonicaMayor(new int[]{0, 2, 4, 7, 9}),
    PentatonicaMenor(new int[]{0, 3, 5, 7, 10});

    private final int[] intervals;/*Son acumulativos*/

    Scale(int[] intervals) {
        this.intervals = intervals;
    }

    public int[] getIntervals() {
        return intervals;
    }
}
