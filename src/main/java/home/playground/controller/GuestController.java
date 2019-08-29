package home.playground.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import home.playground.dto.GuestDTO;
import home.playground.service.GuestService;


@RestController
@RequestMapping("/api")	
public class GuestController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GuestService guestService;

	@GetMapping("/guest")
	public ResponseEntity<List<GuestDTO>> getAllGuests() {
		log.debug("REST reguest to get all geusts");
		return new ResponseEntity<List<GuestDTO>>(this.guestService.getAllGuests(), HttpStatus.OK); 
	}
	
	@GetMapping("/guest/{id}")
	public ResponseEntity<GuestDTO> getGuest(@PathVariable("id") Long id) {
		log.debug("REST reguest to get geust[{}]", id);
		return new ResponseEntity<GuestDTO>(this.guestService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping("/guest")
	public ResponseEntity<GuestDTO> createGuest(@Valid @RequestBody GuestDTO dto) {
		log.debug("REST reguest to create new geust: {}", dto);
		return new ResponseEntity<GuestDTO>(this.guestService.createGuest(dto), HttpStatus.OK); 		
	}
	
	@PutMapping("/guest")
	public ResponseEntity<GuestDTO> updateGuest(@Valid @RequestBody GuestDTO dto) {
		log.debug("REST reguest to update geust: {}", dto);
		return new ResponseEntity<GuestDTO>(this.guestService.updateGuest(dto), HttpStatus.OK);
	}

	@DeleteMapping("/guest")
	public ResponseEntity<String> deleteGuest(@Valid @RequestBody GuestDTO dto) {
		log.debug("REST reguest to delete existing geusts: {}", dto);
		this.guestService.deleteGuest(dto.getId());
		return new ResponseEntity<String>("DELETED", HttpStatus.OK);
	}
	
	@GetMapping("/guest-of")
	public ResponseEntity<List<String>> getGuestsOfList() {
		log.debug("REST request to get guest-of list");
		return new ResponseEntity<List<String>>(this.guestService.getGuestOfList(), HttpStatus.OK);
	}

	@GetMapping("/guest-type")
	public ResponseEntity<List<String>> getGuestTypes() {
		log.debug("REST request to get guest types");
		return new ResponseEntity<List<String>>(this.guestService.getGuestTypes(), HttpStatus.OK);
	}
}
