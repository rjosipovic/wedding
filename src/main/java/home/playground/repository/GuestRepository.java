package home.playground.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import home.playground.domain.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long> {
	
	@Query(value = "SELECT COUNT(*) from Guest g where g.arrivalConfirmed='true'")
	public Integer getArrivalCount();
	
	@Query(value = "SELECT COUNT(*) from Guest g where g.presentAtReception='true'")
	public Integer getPresentAtReceptionCount();
	
	@Query(value = "SELECT COUNT(*) from Guest g where g.travelingWithBus='true'")
	public Integer getTravelingWithBusCount();

	@Query(value = "SELECT COUNT(*) from Guest g where g.presentAtWedding='true'")
	public Integer getPresentAtWeddingCount();

	@Query(value = "SELECT COUNT(*) from Guest g where g.presentAtDinner='true'")
	public Integer getPresentAtDinnerCount();

	@Query(value = "SELECT COUNT(*) from Guest g where g.sleeping='true'")
	public Integer getSleepingCount();

	@Query(value = "SELECT COUNT(*) from Guest g where g.presentAtSundayLunch='true'")
	public Integer getPresentAtSundayLunchCount();
}
