package home.playground.service;

import java.util.List;

import home.playground.dto.GuestDTO;
import home.playground.dto.SummaryDTO;

public interface GuestService {
	
	public List<GuestDTO> getAllGuests();
	
	public GuestDTO getById(Long id);
	
	public GuestDTO createGuest(GuestDTO guestDTO);
	public GuestDTO updateGuest(GuestDTO guestDTO);
	public void deleteGuest(Long id);
	
	public List<String> getGuestTypes();
	public List<String> getGuestOfList();
	
	public SummaryDTO getSummary();
}
