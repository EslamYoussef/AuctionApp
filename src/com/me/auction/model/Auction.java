package com.me.auction.model;

import java.util.ArrayList;

public class Auction {

	private Long id;
	private String itemTitle;
	private String itemDescription;
	private Long startDate;
	private Long itemOwnerId;

	private String itemOwnerName;
	private Integer durationInHours;
	private Long winnerUserId;
	private Integer isClosed;
	private ArrayList<Bid> bidsList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getItemOwnerId() {
		return itemOwnerId;
	}

	public void setItemOwnerId(Long itemOwnerId) {
		this.itemOwnerId = itemOwnerId;
	}

	public Integer getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(Integer durationInHours) {
		this.durationInHours = durationInHours;
	}

	public ArrayList<Bid> getBidsList() {
		return bidsList;
	}

	public void setBidsList(ArrayList<Bid> bidsList) {
		this.bidsList = bidsList;
	}

	public Long getWinnerUserId() {
		return winnerUserId;
	}

	public void setWinnerUserId(Long winnerUserId) {
		this.winnerUserId = winnerUserId;
	}

	public Integer getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Integer isClosed) {
		this.isClosed = isClosed;
	}

	public String getItemOwnerName() {
		return itemOwnerName;
	}

	public void setItemOwnerName(String itemOwnerName) {
		this.itemOwnerName = itemOwnerName;
	}

}
