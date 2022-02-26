/**
 * 
 */
package com.projectmyorg.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.Country;
import com.projectmyorg.domain.masters.State;

/**
 * @author Shankar D
 *
 */
public class CityStateCountryResponse extends BaseResponse {

	private List<Country> countries = new ArrayList<>();
	private List<State> states = new ArrayList<>();
	private List<City> cities = new ArrayList<>();

	/**
	 * 
	 */
	public CityStateCountryResponse() {
		super();
	}

	/**
	 * @param data
	 */
	public CityStateCountryResponse(List<Country> countries) {
		super();
		this.countries = countries;
	}
	
	/**
	 * @return the countries
	 */
	public List<Country> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	/**
	 * @return the states
	 */
	public List<State> getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(List<State> states) {
		this.states = states;
	}

	/**
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * @param cities the cities to set
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	

}
