package com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq;

public class PreferenciaRq {

	private boolean bufanda,  lentes,  paraguas, protectorSolar, gorra;

	public PreferenciaRq(boolean bufanda, boolean lentes, boolean paraguas, boolean protectorSolar, boolean gorra) {
		this.bufanda = bufanda;
		this.lentes = lentes;
		this.paraguas = paraguas;
		this.protectorSolar = protectorSolar;
		this.gorra = gorra;
	}

	public PreferenciaRq() {
	}

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

	public boolean isGorra() {
		return gorra;
	}

	public void setGorra(boolean gorra) {
		this.gorra = gorra;
	}
}
