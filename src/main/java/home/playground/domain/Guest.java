package home.playground.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "guest")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Guest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_guest_gen")
	@SequenceGenerator(name = "seq_guest_gen", sequenceName = "guest_id_seq")
	private Long id;
	
	@Column(name = "full_name", nullable = false, unique = true, length = 128)
	private String fullName;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, length = 32)
	private GuestType type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "guest_of", nullable = false, length = 5)
	private GuestOf guestOf;
	
	@Column(name = "arriving", nullable = true)
	private Boolean arrivalConfirmed;

	@Column(name = "at_reception", nullable = true)
	private Boolean presentAtReception;
	
	@Column(name = "in_bus", nullable = true)
	private Boolean travelingWithBus;
	
	@Column(name = "at_wedding", nullable = true)
	private Boolean presentAtWedding;
	
	@Column(name = "at_dinner", nullable = true)
	private Boolean presentAtDinner;
	
	@Column(name = "sleeping", nullable = true)
	private Boolean sleeping;
	
	@Column(name = "at_lunch", nullable = true)
	private Boolean presentAtSundayLunch;
	
	public static enum GuestOf {
		BRIDE,
		GROOM;
		
		public static GuestOf fromName(String name) {
			switch (name) {
			case "GROOM":
				return GROOM;
			case "BRIDE":
				return BRIDE;
			default:
				throw new IllegalArgumentException(String.format("Unable to transform string [%s] to a known GuestOf", name));
			}
		}
	}
	
	public static enum GuestType {
		BRIDE,
		GROOM,
		BESTMAN,
		MADE_OF_HONOR,
		PARENT,
		MUSICIAN,
		PHOTOGRAPHER,
		REGULAR,
		CHILD;
		
		public static GuestType fromName(String name) {
			switch (name) {
			case "BRIDE":
				return BRIDE;
			case "GROOM":
				return GROOM;
			case "BESTMAN":
				return BESTMAN;
			case "MADE_OF_HONOR":
				return MADE_OF_HONOR;
			case "PARENT":
				return PARENT;
			case "MUSICIAN":
				return MUSICIAN;
			case "PHOTOGRAPHER":
				return PHOTOGRAPHER;
			case "REGULAR":
				return REGULAR;
			case "CHILD":
				return CHILD;
			default:
				throw new IllegalArgumentException(String.format("Unable to transform string [%s] to a known GuestType", name));
			}
		}
	}
}
