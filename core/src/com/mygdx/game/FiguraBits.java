package com.mygdx.game;

public enum FiguraBits {
	
	NULL	   ((short) 0x0000),
	BORDE      ((short) 0x0001),
	NAVE       ((short) 0x0002),
	BALA	   ((short) 0x0004),
	ENEMIGO    ((short) 0x0008),
	CONSUMIBLE ((short) 0x0016);

    public final short bit;

    FiguraBits(short bit) {
        this.bit = bit;
    }
    
}
