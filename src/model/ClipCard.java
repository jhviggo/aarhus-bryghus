package model;

import java.time.LocalDateTime;

public class ClipCard {

	/**
	 * Attributes
	 */
	private int amountOfClips;
	private LocalDateTime boughtOnDate;

	/**
	 * Contstructor
	 * @param amountOfClips
	 * @param boughtOnDate
	 */
	public ClipCard(int amountOfClips, LocalDateTime boughtOnDate) {
		this.amountOfClips = amountOfClips;
		this.boughtOnDate = boughtOnDate;
	}
	
	/**
	 * Method to get amount of clips left on card.
	 * @return amountOfClips
	 */
	public int getAmountOfClipsLeft() {
		return this.amountOfClips;
	}
	
	/**
	 * Method to get the purchase date
	 * @return boughtOnDate
	 */
	public LocalDateTime getDateofPurchase() {
		return this.boughtOnDate;
	}
	
	
}
