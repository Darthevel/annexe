package com.ehret.gridlayoutexample;

/**
 * Created by EHRET_G on 26/05/13.
 */
public class EventPlanning {
    private int start;
    private int end;
    private String label;
    private int drawable;

    public EventPlanning(int start, int end, String label, int drawable) {
        this.start = start;
        this.end = end;
        this.label = label;
        this.drawable = drawable;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getLabel() {
        return label;
    }

    public int getDrawable() {
        return drawable;
    }
}
