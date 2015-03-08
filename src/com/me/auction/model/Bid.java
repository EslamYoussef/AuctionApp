package com.me.auction.model;

public class Bid {

	private Long id;
	private Integer bidValue;
	private Long userId;
	private String userName;
	private Long bidDate;
	private Long auctionId;
	private Integer isWon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBidValue() {
		return bidValue;
	}

	public void setBidValue(Integer bidValue) {
		this.bidValue = bidValue;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBidDate() {
		return bidDate;
	}

	public void setBidDate(Long bidDate) {
		this.bidDate = bidDate;
	}

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public Integer getIsWon() {
		return isWon;
	}

	public void setIsWon(Integer isWon) {
		this.isWon = isWon;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
