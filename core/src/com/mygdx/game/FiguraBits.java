package com.mygdx.game;

public enum FiguraBits {
	
	NULL	   		((short) 0x0000),
	BORDE      		((short) 0x0001),
	NAVE       		((short) 0x0002),
	BALA	   		((short) 0x0004),
	ASTEROIDE  		((short) 0x0008),
	BASURA_ESPACIAL ((short) 0x0010),
	CONSUMIBLE 		((short) 0x0020);

    private final short bit;

    FiguraBits(short bit) {
        this.bit = bit;
    }
    
    public short getBit() {
    	return bit;
    }
    
}
