/**
 * 
 */
package com.projectmyorg.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmyorg.commons.StringUtils;
import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.Country;
import com.projectmyorg.domain.masters.State;
import com.projectmyorg.dto.request.CityStateCountryDto;
import com.projectmyorg.dto.response.BaseResponse;
import com.projectmyorg.dto.response.CityStateCountryResponse;
import com.projectmyorg.exceptions.BadRequestException;
import com.projectmyorg.exceptions.InternalServerErrorException;
import com.projectmyorg.exceptions.RecordNotFoundException;
import com.projectmyorg.repo.CityRepository;
import com.projectmyorg.repo.CountryRepository;
import com.projectmyorg.repo.StateRepository;

/**
 * @author Shankar D
 *
 */
@Service
public class CityStateCountryServiceImpl implements CityStateCountryService {

	private static final String HAS_BEEN_CREATED = " has been created.";

	private static final String IS_ALREADY_EXISTS = " is already exists!";

	private static final Logger LOGGER = LoggerFactory.getLogger(CityStateCountryServiceImpl.class);

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Override
	public BaseResponse saveCountry(CityStateCountryDto request) {
		BaseResponse response = new BaseResponse();
		if (StringUtils.isEmpty(request.getCountry())) {
			throw new BadRequestException("Country should not be null or empty!");
		}
		try {
			Optional<Country> countryOpt = countryRepo.findByCountryName(request.getCountry());
			if (countryOpt.isPresent()) {
				response.setStatus(false);
				response.setMessage("Country with name" + request.getCountry() + IS_ALREADY_EXISTS);
				LOGGER.warn("Country " + request.getCountry() + IS_ALREADY_EXISTS);
			} else {
				Country country = new Country();
				country.setCountryName(request.getCountry());
				countryRepo.save(country);
				LOGGER.info("Country " + request.getCountry() + HAS_BEEN_CREATED);
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating country {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return response;
	}

	@Override
	public CityStateCountryResponse findAllCountries() {
		try {
			LOGGER.info("Fetching all the countries");
			List<Country> countries = countryRepo.findAll();
			return new CityStateCountryResponse(countries);
		} catch (Exception e) {
			LOGGER.error("An error occured while fetching countries {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public Country findCountryByName(String countryName) {
		return countryRepo.findByCountryName(countryName).orElseThrow(
				() -> new RecordNotFoundException("Not able to find the country with name " + countryName));
	}

	@Override
	public BaseResponse saveState(CityStateCountryDto request) {
		BaseResponse response = new BaseResponse();
		if (StringUtils.isEmpty(request.getState()) && StringUtils.isEmpty(request.getCountry())) {
			throw new BadRequestException("Country & state should not be null or empty!");
		}
		Country country = findCountryByName(request.getCountry());
		try {
			Optional<State> stateOpt = stateRepo.findByStateNameAndCountry(request.getState(), country);
			if (stateOpt.isPresent()) {
				response.setStatus(false);
				response.setMessage("State with name" + request.getState() + IS_ALREADY_EXISTS);
				LOGGER.warn("State " + request.getState() + IS_ALREADY_EXISTS);
			} else {
				State state = new State();
				state.setStateName(request.getState());
				state.setCountry(country);
				stateRepo.save(state);
				LOGGER.info("State " + request.getState() + HAS_BEEN_CREATED);
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating state {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return response;
	}

	@Override
	public CityStateCountryResponse findAllStatesByCountry(String countryName) {
		Country country = findCountryByName(countryName);
		try {
			LOGGER.info("Fetching all the states");
			List<State> states = stateRepo.findByCountry(country);

			CityStateCountryResponse response = new CityStateCountryResponse();
			response.setStates(states);
			return response;

		} catch (Exception e) {
			LOGGER.error("An error occured while fetching states {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public State findStateByNameAndCountry(String stateName, Country country) {
		return stateRepo.findByStateNameAndCountry(stateName, country)
				.orElseThrow(() -> new RecordNotFoundException("Not able to find the state with name " + stateName));
	}

	@Override
	public BaseResponse saveCity(CityStateCountryDto request) {
		BaseResponse response = new BaseResponse();

		if (StringUtils.isEmpty(request.getCity()) && StringUtils.isEmpty(request.getState())
				&& StringUtils.isEmpty(request.getCountry())) {
			throw new BadRequestException("City name, state & country should not be null or empty!");
		}
		Country country = findCountryByName(request.getCountry());
		State state = findStateByNameAndCountry(request.getState(), country);
		try {
			Optional<City> cityOpt = cityRepo.findByCityNameAndState(request.getCity(), state);
			if (cityOpt.isPresent()) {
				response.setStatus(false);
				response.setMessage("City with name" + request.getCity() + IS_ALREADY_EXISTS);
				LOGGER.warn("City " + request.getState() + IS_ALREADY_EXISTS);
			} else {
				City city = new City();
				city.setCityName(request.getCity());
				city.setState(state);
				cityRepo.save(city);
				LOGGER.info("City " + request.getCity() + HAS_BEEN_CREATED);
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while creating city {}", e.getMessage());
			throw new InternalServerErrorException();
		}
		return response;
	}

	@Override
	public CityStateCountryResponse findAllCitiesByStateAndCountry(String stateName, String countryName) {
		Country country = findCountryByName(countryName);
		State state = findStateByNameAndCountry(stateName, country);
		try {
			LOGGER.info("Fetching all the cities");
			List<City> cities = cityRepo.findByState(state);

			CityStateCountryResponse response = new CityStateCountryResponse();
			response.setCities(cities);
			return response;

		} catch (Exception e) {
			LOGGER.error("An error occured while fetching cities {}", e.getMessage());
			throw new InternalServerErrorException();
		}
	}

	@Override
	public City findCityByNameAndState(String cityName, State state) {
		return cityRepo.findByCityNameAndState(cityName, state)
				.orElseThrow(() -> new RecordNotFoundException("Not able to find the city with name " + cityName));
	}

}
