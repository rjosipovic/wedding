package home.playground.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import home.playground.dto.GuestDTO;
import home.playground.exception.GuestDoesNotExistException;
import home.playground.exception.InvalidRequestException;
import home.playground.repository.GuestRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class GuestServiceTest {
	
	private static GuestDTO dto = new GuestDTO();
	private Set<GuestDTO> guestCreated = new HashSet<>();
	
	@Autowired
	private GuestService guestService;
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Before
	public void setup() throws Exception {
		dto.setFullName("Antony Stark");
		dto.setType("REGULAR");
		dto.setGuestOf("GROOM");
	}
	
	@After
	public void cleanUp() throws Exception {
		
		for(GuestDTO dto : guestCreated) {
			this.guestRepository.deleteById(dto.getId());						
		}
	}
	
	@Test
	public void addGuestSuccess() throws Exception {
		GuestDTO created = guestService.createGuest(dto);
		assertThat(created.getId()).isNotNull();
		guestCreated.add(created);
	}
	
	@Test(expected = GuestDoesNotExistException.class)
	public void guestDoesNotExist() throws Exception {
		guestService.getById(1342343323L);
	}
	
	@Test(expected = GuestDoesNotExistException.class)
	public void deleteNonExistingGuest() throws Exception {
		guestService.deleteGuest(1342342342343L);
	}
	
	@Test(expected = GuestDoesNotExistException.class)
	public void deleteGuestSuccess() throws Exception {

		GuestDTO created = guestService.createGuest(dto);
		assertThat(created.getId()).isNotNull();
		
		guestService.deleteGuest(created.getId());
		guestService.getById(created.getId());	
	}
	
	@Test(expected = GuestDoesNotExistException.class)
	public void updateNonExistingGuest() throws Exception {
		dto.setId(1234L);
		guestService.updateGuest(dto);
	}
	
	@Test(expected = InvalidRequestException.class)
	public void updateMissingIdFail() throws Exception {
		guestService.updateGuest(dto);
	}

	@Test
	public void updateGuestSuccess() throws Exception {
		GuestDTO created = guestService.createGuest(dto);
		created.setArrivalConfirmed(Boolean.TRUE);
		GuestDTO updated = guestService.updateGuest(created);
		assertThat(updated.getArrivalConfirmed()).isTrue();
		guestCreated.add(updated);
	}
}
