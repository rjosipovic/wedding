package home.playground.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import home.playground.dto.SummaryDTO;
import home.playground.repository.GuestRepository;

@Service
@Transactional
public class SummaryServiceImpl implements SummaryService {
	
	private GuestRepository guestRepository;
	
	public SummaryServiceImpl(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}
	
	@Override
	public SummaryDTO getSummary() {
		SummaryDTO dto = new SummaryDTO();
		
		dto.setArrivingCount(guestRepository.getArrivalCount());
		dto.setReceptionCount(guestRepository.getPresentAtReceptionCount());
		dto.setWithBusCount(guestRepository.getTravelingWithBusCount());
		dto.setAtWeddingCount(guestRepository.getPresentAtWeddingCount());
		dto.setAtDinnerCount(guestRepository.getPresentAtDinnerCount());
		dto.setSleepingCount(guestRepository.getSleepingCount());
		dto.setSundayLunchCount(guestRepository.getPresentAtSundayLunchCount());
		return dto;
	}
}
