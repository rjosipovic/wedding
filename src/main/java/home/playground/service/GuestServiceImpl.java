package home.playground.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import home.playground.domain.Guest;
import home.playground.domain.Guest.GuestOf;
import home.playground.domain.Guest.GuestType;
import home.playground.dto.GuestDTO;
import home.playground.dto.SummaryDTO;
import home.playground.exception.GuestDoesNotExistException;
import home.playground.exception.InvalidRequestException;
import home.playground.repository.GuestRepository;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {

	private GuestRepository guestRepository;

	public GuestServiceImpl(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	@Override
	public SummaryDTO getSummary() {
		return null;
	}

	@Override
	public GuestDTO createGuest(GuestDTO guestDTO) {
		return toDto(this.guestRepository.save(fromDto(guestDTO)));
	}

	@Override
	public void deleteGuest(Long id) {

		Optional<Guest> optionalGuest = this.guestRepository.findById(id);
		if (optionalGuest.isPresent()) {
			guestRepository.deleteById(id);
		} else {
			throw new GuestDoesNotExistException(String.format("Guest with id [%s] does not exist", id));
		}
	}

	@Override
	public GuestDTO getById(Long id) {
		Optional<Guest> optionalGuest = this.guestRepository.findById(id);
		if (optionalGuest.isPresent()) {
			return toDto(optionalGuest.get());
		} else {
			throw new GuestDoesNotExistException(String.format("Guest with id [%s] does not exist", id));
		}
	}

	@Override
	public GuestDTO updateGuest(GuestDTO guestDTO) {

		if (guestDTO.getId() == null) {
			throw new InvalidRequestException("Guest id is missing");
		}

		Optional<Guest> optionalGuest = guestRepository.findById(guestDTO.getId());
		if (optionalGuest.isPresent()) {
			executeUpdate(guestDTO, optionalGuest.get());
			Optional<Guest> optionalUpdated = guestRepository.findById(optionalGuest.get().getId());
			if (optionalUpdated.isPresent()) {
				return toDto(optionalUpdated.get());
			} else {
				throw new RuntimeException(String.format("Unable to fetch updated Guest [%s]", guestDTO.getId()));
			}
		} else {
			throw new GuestDoesNotExistException(
					String.format("Unable to update, guest [%s] does not exist", guestDTO.getId()));
		}
	}
	
	private void executeUpdate(GuestDTO dto, Guest guest) {
		if (!dto.getFullName().equals(guest.getFullName())) {
			guest.setFullName(dto.getFullName());
		}
		if (!dto.getType().equals(guest.getType().name())) {
			guest.setType(GuestType.fromName(dto.getType()));
		}
		if (!dto.getGuestOf().equals(guest.getGuestOf().name())) {
			guest.setGuestOf(GuestOf.fromName(dto.getGuestOf()));
		}
		if (dto.getArrivalConfirmed() != guest.getArrivalConfirmed()) {
			guest.setArrivalConfirmed(dto.getArrivalConfirmed());
		}
		if (dto.getPresentAtReception() != guest.getPresentAtReception()) {
			guest.setPresentAtReception(dto.getPresentAtReception());
		}
		if (dto.getTravelingWithBus() != guest.getTravelingWithBus()) {
			guest.setTravelingWithBus(dto.getTravelingWithBus());
		}
		if (dto.getPresentAtWedding() != guest.getPresentAtWedding()) {
			guest.setPresentAtWedding(dto.getPresentAtWedding());
		}
		if (dto.getPresentAtDinner() != guest.getPresentAtDinner()) {
			guest.setPresentAtDinner(dto.getPresentAtDinner());
		}
		if (dto.getSleeping() != guest.getSleeping()) {
			guest.setSleeping(dto.getSleeping());
		}
		if (dto.getPresentAtSundayLunch() != guest.getPresentAtSundayLunch()) {
			guest.setPresentAtSundayLunch(dto.getPresentAtSundayLunch());
		}
		this.guestRepository.save(guest);
	}

	@Override
	public List<GuestDTO> getAllGuests() {
		List<GuestDTO> guestList = new ArrayList<>();
		Iterable<Guest> guests = this.guestRepository.findAll();
		for (Guest guest : guests) {
			guestList.add(toDto(guest));
		}
		Collections.sort(guestList);
		return guestList;
	}

	private Guest fromDto(GuestDTO dto) {
		Guest guest = new Guest();

		if (dto.getId() != null) {
			guest.setId(dto.getId());
		}
		guest.setFullName(dto.getFullName());
		guest.setType(GuestType.fromName(dto.getType()));
		guest.setGuestOf(GuestOf.fromName(dto.getGuestOf()));

		if (dto.getArrivalConfirmed() != null) {
			guest.setArrivalConfirmed(dto.getArrivalConfirmed());
		}
		if (dto.getPresentAtReception() != null) {
			guest.setPresentAtReception(dto.getPresentAtReception());
		}
		if (dto.getTravelingWithBus() != null) {
			guest.setTravelingWithBus(dto.getTravelingWithBus());
		}
		if (dto.getPresentAtWedding() != null) {
			guest.setPresentAtWedding(dto.getPresentAtWedding());
		}
		if (dto.getPresentAtDinner() != null) {
			guest.setPresentAtDinner(dto.getPresentAtDinner());
		}
		if (dto.getSleeping() != null) {
			guest.setSleeping(dto.getSleeping());
		}
		if (dto.getPresentAtSundayLunch() != null) {
			guest.setPresentAtSundayLunch(dto.getPresentAtSundayLunch());
		}
		return guest;
	}

	private GuestDTO toDto(Guest guest) {
		GuestDTO dto = new GuestDTO();

		dto.setId(guest.getId());
		dto.setFullName(guest.getFullName());
		dto.setType(guest.getType().name());
		dto.setGuestOf(guest.getGuestOf().name());

		dto.setArrivalConfirmed(guest.getArrivalConfirmed());
		dto.setPresentAtReception(guest.getPresentAtReception());
		dto.setTravelingWithBus(guest.getTravelingWithBus());
		dto.setPresentAtWedding(guest.getPresentAtWedding());
		dto.setPresentAtDinner(guest.getPresentAtDinner());
		dto.setSleeping(guest.getSleeping());
		dto.setPresentAtSundayLunch(guest.getPresentAtSundayLunch());

		return dto;
	}

	@Override
	public List<String> getGuestOfList() {
		List<String> guestOfList = new ArrayList<String>();
		for(GuestOf guestOf : GuestOf.values()) {
			guestOfList.add(guestOf.name());
		}
		return guestOfList;
	}
	
	@Override
	public List<String> getGuestTypes() {
		List<String> guestTypeList = new ArrayList<String>();
		for (GuestType type : GuestType.values()) {
			guestTypeList.add(type.name());
		}
		return guestTypeList;
	};
}
