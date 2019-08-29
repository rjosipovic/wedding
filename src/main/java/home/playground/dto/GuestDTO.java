package home.playground.dto;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GuestDTO implements Comparable<GuestDTO>{
	
	private Long id;
	@NotNull
	private String fullName;
	@NotNull
	private String type;
	@NotNull
	private String guestOf;
	private Boolean arrivalConfirmed;
	private Boolean presentAtReception;
	private Boolean travelingWithBus;
	private Boolean presentAtWedding;
	private Boolean presentAtDinner;
	private Boolean sleeping;
	private Boolean presentAtSundayLunch;
	
	@Override
	public int compareTo(GuestDTO o) {
		if(this.id != null && o.id != null) {
			return this.id.compareTo(o.id);
		} else {
			return this.fullName.compareTo(o.fullName);
		}
	}
}
