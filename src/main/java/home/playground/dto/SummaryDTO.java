package home.playground.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SummaryDTO {
	
	private Integer arrivingCount;
	private Integer receptionCount;
	private Integer withBusCount;
	private Integer atWeddingCount;
	private Integer atDinnerCount;
	private Integer sleepingCount;
	private Integer sundayLunchCount;
}
