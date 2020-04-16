package com.covid19.rtracker.controller;

import java.util.Comparator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covid19.rtracker.models.Datum;
import com.covid19.rtracker.models.Indiadata;
import com.covid19.rtracker.models.LocationStats;
import com.covid19.rtracker.service.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;

	@GetMapping("/india")
	public String home(Model model) {
		Indiadata indDate = coronaVirusDataService.getIndiadata();
		model.addAttribute("IndiaCases", indDate);		
		List<Datum> datums=indDate.getData();
		model.addAttribute("Datum", datums.get(datums.size()-1));
		return "home";
	}

	
	@GetMapping("/daily")
	public String dailySeries(Model model) {
		Indiadata indDate = coronaVirusDataService.getIndiadata();
		model.addAttribute("IndiaCases", indDate);		
		List<Datum> datums=indDate.getData();
		model.addAttribute("Datum", datums.get(datums.size()-1));
		model.addAttribute("allcases", indDate.getData());
		return "indiadaily";
	}
	
	@GetMapping("/confirmedcases")
	public String confirmedcases(Model model) {
		List<LocationStats> allstats = coronaVirusDataService.getAllconfirmedcases();
		int totalcases = allstats.stream().mapToInt(stats -> stats.getLatestTotalCases()).sum();
		int toalnewcases = allstats.stream().mapToInt(stats -> stats.getDiffcases()).sum();
		allstats=allstats.stream().sorted(Comparator.comparingInt(LocationStats::getLatestTotalCases).reversed()).collect(Collectors.toList());
		model.addAttribute("LocationStats", allstats);
		model.addAttribute("totalReportedCases", totalcases);
		model.addAttribute("newCases", toalnewcases);
		return "confirmedcase";
	}

	@GetMapping("/deathcases")
	public String deathcases(Model model) {
		List<LocationStats> allstats = coronaVirusDataService.getAllDeathcases();
		int totalcases = allstats.stream().mapToInt(stats -> stats.getLatestTotalCases()).sum();
		int toalnewcases = allstats.stream().mapToInt(stats -> stats.getDiffcases()).sum();
		allstats=allstats.stream().sorted(Comparator.comparingInt(LocationStats::getLatestTotalCases).reversed()).collect(Collectors.toList());
		model.addAttribute("LocationStats", allstats);
		model.addAttribute("totalReportedCases", totalcases);
		model.addAttribute("newCases", toalnewcases);
		return "deathcases";
	}

	@GetMapping("/recoveredcases")
	public String recoveredcases(Model model) {
		List<LocationStats> allstats = coronaVirusDataService.getAllrecorvedcases();
		int totalcases = allstats.stream().mapToInt(stats -> stats.getLatestTotalCases()).sum();
		int toalnewcases = allstats.stream().mapToInt(stats -> stats.getDiffcases()).sum();
		allstats=allstats.stream().sorted(Comparator.comparingInt(LocationStats::getLatestTotalCases).reversed()).collect(Collectors.toList());
		model.addAttribute("LocationStats", allstats);
		model.addAttribute("totalReportedCases", totalcases);
		model.addAttribute("newCases", toalnewcases);
		return "recoveredcases";
	}

	@GetMapping("/")
	public String menu() {
		return "menu";
	}
}
