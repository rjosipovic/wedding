package home.playground.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import home.playground.dto.GuestDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GuestControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testGetAllGuestsSuccess() throws Exception {
		String url = String.format("http://localhost:%d/api/guest", port);
		ResponseEntity<List<GuestDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<GuestDTO>>() {});
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testGuest() throws Exception {
		String url = String.format("http://localhost:%d/api/guest", port);
		
		GuestDTO guest = new GuestDTO();
		guest.setFullName("Steven Rogers");
		guest.setType("REGULAR");
		guest.setGuestOf("GROOM");
		guest.setArrivalConfirmed(Boolean.TRUE);
		
		HttpEntity<GuestDTO> requestForCreate = new HttpEntity<GuestDTO>(guest);
		ResponseEntity<GuestDTO> responseCreate = restTemplate.exchange(url, HttpMethod.POST, requestForCreate, new ParameterizedTypeReference<GuestDTO>() {});
		GuestDTO created = responseCreate.getBody();
		assertThat(created.getId()).isNotNull();
		
		String getURL = String.format("%s/%d", url, created.getId());
		ResponseEntity<GuestDTO> responseGet = restTemplate.exchange(getURL, HttpMethod.GET, null, new ParameterizedTypeReference<GuestDTO>() {});
		GuestDTO get = responseGet.getBody();
		assertThat(created).isEqualTo(get);
		
		get.setPresentAtWedding(Boolean.TRUE);
		HttpEntity<GuestDTO> requestForUpdate = new HttpEntity<GuestDTO>(get);
		ResponseEntity<GuestDTO> responseUpdate = restTemplate.exchange(url, HttpMethod.PUT, requestForUpdate, new ParameterizedTypeReference<GuestDTO>() {});
		GuestDTO updated = responseUpdate.getBody();
		assertThat(updated).isEqualTo(get);
		
		HttpEntity<GuestDTO> requestForDeletion = new HttpEntity<GuestDTO>(created);
		ResponseEntity<String> deletedReponse = restTemplate.exchange(url, HttpMethod.DELETE, requestForDeletion, new ParameterizedTypeReference<String>() {});
		String info = deletedReponse.getBody();
		assertThat(info).isEqualTo("DELETED");
	}
	
	@Test
	public void testAddInvalidGuestFail() throws Exception {
		String url = String.format("http://localhost:%d/api/guest", port);

		GuestDTO guest = new GuestDTO();
		guest.setType("REGULAR");
		guest.setGuestOf("GROOM");
		HttpEntity<GuestDTO> requestForCreate = new HttpEntity<GuestDTO>(guest);
		ResponseEntity<GuestDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestForCreate, new ParameterizedTypeReference<GuestDTO>() {});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void testGuestDoesNotExistGet() throws Exception {
		String url = String.format("http://localhost:%d/api/guest", port);
		String getURL = String.format("%s/%d", url, 122412523556L);
		ResponseEntity<GuestDTO> responseGet = restTemplate.exchange(getURL, HttpMethod.GET, null, new ParameterizedTypeReference<GuestDTO>() {});
		assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testGuestDoesNotExistUpdate() throws Exception {
		String url = String.format("http://localhost:%d/api/guest", port);
		GuestDTO guest = new GuestDTO();
		guest.setFullName("Steven Rogers");
		guest.setType("REGULAR");
		guest.setGuestOf("GROOM");
		guest.setId(122412523556L);
		HttpEntity<GuestDTO> requestForUpdate = new HttpEntity<GuestDTO>(guest);
		ResponseEntity<GuestDTO> responseGet = restTemplate.exchange(url, HttpMethod.PUT, requestForUpdate, new ParameterizedTypeReference<GuestDTO>() {});
		assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void testInvalidUpdateRequest() throws Exception {
		String url = String.format("http://localhost:%d/api/guest", port);

		GuestDTO guest = new GuestDTO();

		HttpEntity<GuestDTO> requestForUpdate = new HttpEntity<GuestDTO>(guest);
		ResponseEntity<GuestDTO> responseUpdate = restTemplate.exchange(url, HttpMethod.PUT, requestForUpdate, new ParameterizedTypeReference<GuestDTO>() {});
		assertThat(responseUpdate.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
}
