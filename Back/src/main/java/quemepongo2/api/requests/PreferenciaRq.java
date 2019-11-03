package main.java.quemepongo2.api.requests;

public class PreferenciaRq {

	private boolean bufanda,  lentes,  paraguas, protectorSolar;

	public boolean isBufanda() {
		return bufanda;
	}

	public void setBufanda(boolean bufanda) {
		this.bufanda = bufanda;
	}


	public boolean isLentes() {
		return lentes;
	}

	public void setLentes(boolean lentes) {
		this.lentes = lentes;
	}


	public boolean isParaguas() {
		return paraguas;
	}

	public void setParaguas(boolean paraguas) {
		this.paraguas = paraguas;
	}

	public boolean isProtectorSolar() {
		return protectorSolar;
	}

	public void setProtectorSolar(boolean protectorSolar) {
		this.protectorSolar = protectorSolar;
	}
}
