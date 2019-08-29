package home.playground.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import home.playground.domain.Guest;
import home.playground.domain.Guest.GuestOf;
import home.playground.domain.Guest.GuestType;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GuestRepositoryTest {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Test
	public void testAddDeleteGuestSuccess() throws Exception {

		Guest g = new Guest();
		g.setFullName("Antony Stark");
		g.setType(GuestType.REGULAR);
		g.setGuestOf(GuestOf.GROOM);
		
		guestRepository.save(g);
		Long id = g.getId();
		assertThat(id).isNotNull();		
		
		guestRepository.deleteById(id);
		assertThat(guestRepository.findById(id).isPresent()).isFalse();
	}
	
	@Test
	public void testArrivalCount() {
		int count = guestRepository.getArrivalCount();
		System.out.println("COUNT: " + count);
	}
}
