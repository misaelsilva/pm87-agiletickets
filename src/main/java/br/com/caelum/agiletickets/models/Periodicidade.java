package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	
	DIARIA(1), SEMANAL(7);
    private int value;

    public int getValue() {
		return value;
	}

	private Periodicidade(int value) {
            this.value = value;
    }	

}
