package pojo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PendingExpensesPojo {
	
	private int pendingId;
	private int pendingRequest;
	private double pendingAmount;
	private String pendingReason;
	private String pendingCreated;
	private String pendingResolved;
	private int pendingResponse;
	private String pendingStatus;
	
	
	public PendingExpensesPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PendingExpensesPojo(int pendingId, int pendingRequest, double pendingAmount, String pendingReason,
			String pendingCreated, String pendingResolved, int pendingResponse, String pendingStatus) {
		super();
		this.pendingId = pendingId;
		this.pendingRequest = pendingRequest;
		this.pendingAmount = pendingAmount;
		this.pendingReason = pendingReason;
		this.pendingCreated = pendingCreated;
		this.pendingResolved = pendingResolved;
		this.pendingResponse = pendingResponse;
		this.pendingStatus = pendingStatus;
	}

	public int getPendingId() {
		return pendingId;
	}

	public void setPendingId(int pendingId) {
		this.pendingId = pendingId;
	}

	public int getPendingRequest() {
		return pendingRequest;
	}

	public void setPendingRequest(int pendingRequest) {
		this.pendingRequest = pendingRequest;
	}

	public double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getPendingReason() {
		return pendingReason;
	}

	public void setPendingReason(String pendingReason) {
		this.pendingReason = pendingReason;
	}

	public String getPendingCreated() {
		return pendingCreated;
	}

	public void setPendingCreated(String pendingCreated) {
		this.pendingCreated = pendingCreated;
	}

	public String getPendingResolved() {
		return pendingResolved;
	}

	public void setPendingResolved(String pendingResolved) {
		this.pendingResolved = pendingResolved;
	}

	public int getPendingResponse() {
		return pendingResponse;
	}

	public void setPendingResponse(int pendingResponse) {
		this.pendingResponse = pendingResponse;
	}

	public String getPendingStatus() {
		return pendingStatus;
	}

	public void setPendingStatus(String pendingStatus) {
		this.pendingStatus = pendingStatus;
	}

	@Override
	public String toString() {
		return "PendingExpensesPojo [pendingId=" + pendingId + ", pendingRequest=" + pendingRequest + ", pendingAmount="
				+ pendingAmount + ", pendingReason=" + pendingReason + ", pendingCreated=" + pendingCreated
				+ ", pendingResolved=" + pendingResolved + ", pendingResponse=" + pendingResponse + ", pendingStatus="
				+ pendingStatus + "]";
	}
	
}
	
	