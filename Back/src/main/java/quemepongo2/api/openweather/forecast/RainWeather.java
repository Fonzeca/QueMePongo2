package main.java.quemepongo2.api.openweather.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RainWeather {
	private Double $3h;

	public Double get$3h() {
		return $3h;
	}

	public void set$3h(Double $3h) {
		this.$3h = $3h;
	}

}
