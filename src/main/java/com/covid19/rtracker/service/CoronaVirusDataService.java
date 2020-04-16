package com.covid19.rtracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.covid19.rtracker.models.Indiadata;
import com.covid19.rtracker.models.LocationStats;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoronaVirusDataService {

	private final RestTemplate restTemplate;

	public CoronaVirusDataService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private static String VIRUS_CONFIRMED_CASE_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String VIRUS_DEATH_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private static String VIRUS_RECOVERED_CASES_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static String INDIA_CORONA_DATA = "https://api.rootnet.in/covid19-in/stats/daily";
	private ArrayList<LocationStats> allStats = new ArrayList<>();

	public ArrayList<LocationStats> getAllStats() {
		return allStats;
	}

	private Indiadata indiadata = new Indiadata();
	private ArrayList<LocationStats> allconfirmedcases = new ArrayList<>();
	private ArrayList<LocationStats> allDeathcases = new ArrayList<>();
	private ArrayList<LocationStats> allrecorvedcases = new ArrayList<>();

	public Indiadata getIndiadata() {
		return indiadata;
	}

	@PostConstruct
	@Scheduled(cron = "* 30 * * * * ")
	public void fetchVirusData() throws IOException, InterruptedException {
		ArrayList<LocationStats> newStats = new ArrayList<>();
		String response = this.restTemplate.getForObject(URI.create(VIRUS_CONFIRMED_CASE_URL), String.class);
		newStats = getRecord(response);
		this.allStats = newStats;
	}

	@PostConstruct
	@Scheduled(cron = "* 39 * * * * ")
	public void fetchVirusConfirmcaseData() throws IOException, InterruptedException {
		ArrayList<LocationStats> newStats = new ArrayList<>();
		String response = this.restTemplate.getForObject(URI.create(VIRUS_CONFIRMED_CASE_URL), String.class);
		newStats = getRecord(response);
		this.allconfirmedcases = newStats;
	}

	public ArrayList<LocationStats> getAllconfirmedcases() {
		return allconfirmedcases;
	}

	@PostConstruct
	@Scheduled(cron = "* 33 * * * * ")
	public void fetchVirusRecoverdcaseData() throws IOException, InterruptedException {
		ArrayList<LocationStats> newStats = new ArrayList<>();
		String response = this.restTemplate.getForObject(URI.create(VIRUS_RECOVERED_CASES_URL), String.class);

		newStats = getRecord(response);
		this.allrecorvedcases = newStats;
	}

	@PostConstruct
	@Scheduled(cron = "* 36 * * * * ")
	public void fetchVirusDeathscaseData() throws IOException, InterruptedException {
		ArrayList<LocationStats> newStats = new ArrayList<>();
		String response = this.restTemplate.getForObject(URI.create(VIRUS_DEATH_CASES_URL), String.class);

		newStats = getRecord(response);
		this.allDeathcases = newStats;
	}

	public ArrayList<LocationStats> getAllDeathcases() {
		return allDeathcases;
	}

	public ArrayList<LocationStats> getAllrecorvedcases() {
		return allrecorvedcases;
	}

	private ArrayList<LocationStats> getRecord(String request) throws IOException {
		ArrayList<LocationStats> newStats = new ArrayList<>();
		StringReader in = new StringReader(request);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			LocationStats locationState = new LocationStats();
			locationState.setState(record.get("Province/State"));
			locationState.setCountry(record.get("Country/Region"));
			String totTodaystr = record.get(record.size() - 1);
			String preDayStr = record.get(record.size() - 2);
			int totalcasetoday = 0;
			int predaycases = 0;
			if ("".equalsIgnoreCase(totTodaystr)) {
				totalcasetoday = 0;
			} else {
				totalcasetoday = Integer.parseInt(totTodaystr);
			}
			if ("".equalsIgnoreCase(preDayStr)) {
				predaycases = 0;
			} else {
				predaycases = Integer.parseInt(preDayStr);
			}

			locationState.setLatestTotalCases(totalcasetoday);
			locationState.setDiffcases(totalcasetoday - predaycases);

			newStats.add(locationState);
		}
		return newStats;
	}

	@PostConstruct
	@Scheduled(cron = "* 40 * * * * ")
	private Indiadata getIndiaDailyData() {
		Indiadata indiaData = new Indiadata();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put("content-type:", "application/json");
			String response = this.restTemplate.getForObject(INDIA_CORONA_DATA, String.class, uriParams);

			indiaData = mapper.readValue(response, Indiadata.class);
			indiadata=indiaData;
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return indiaData;

	}
}
