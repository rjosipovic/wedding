package home.playground.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.playground.dto.SummaryDTO;
import home.playground.service.SummaryService;

@RestController
@RequestMapping("/api")	
public class SummaryController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SummaryService summaryService;
	
	@GetMapping("/summary")
	public ResponseEntity<SummaryDTO> getSummary() {
		log.debug("REST reguest to get summary");
		return new ResponseEntity<SummaryDTO>(this.summaryService.getSummary(), HttpStatus.OK); 
	}
}
