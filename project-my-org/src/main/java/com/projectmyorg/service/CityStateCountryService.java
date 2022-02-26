/**
 * 
 */
package com.projectmyorg.service;

import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.Country;
import com.projectmyorg.domain.masters.State;
import com.projectmyorg.dto.request.CityStateCountryDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.CityStateCountryResponse;

/**
 * @author Shankar D
 *
 */
public interface CityStateCountryService {

	BaseResponse saveCountry(CityStateCountryDto request);

	CityStateCountryResponse findAllCountries();

	Country findCountryByName(String countryName);

	BaseResponse saveState(CityStateCountryDto request);

	CityStateCountryResponse findAllStatesByCountry(String countryName);

	State findStateByNameAndCountry(String stateName, Country country);

	BaseResponse saveCity(CityStateCountryDto request);

	CityStateCountryResponse findAllCitiesByStateAndCountry(String stateName, String countryName);

	City findCityByNameAndState(String cityName, State state);

}
