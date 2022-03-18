package pojo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FinalExpensesPojo {
	
	private int finalId;
	private int finalRequest;
	private double finalAmount;
	private String finalReason;
	private String finalCreated;
	private Timestamp finalResolved;
	private int finalResponse;
	private String finalStatus;
	
	public FinalExpensesPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinalExpensesPojo(int finalId, int finalRequest, double finalAmount, String finalReason, String finalCreated,
			Timestamp finalResolved, int finalResponse, String finalStatus) {
		super();
		this.finalId = finalId;
		this.finalRequest = finalRequest;
		this.finalAmount = finalAmount;
		this.finalReason = finalReason;
		this.finalCreated = finalCreated;
		this.finalResolved = finalResolved;
		this.finalResponse = finalResponse;
		this.finalStatus = finalStatus;
	}

	public int getFinalId() {
		return finalId;
	}

	public void setFinalId(int finalId) {
		this.finalId = finalId;
	}

	public int getFinalRequest() {
		return finalRequest;
	}

	public void setFinalRequest(int finalRequest) {
		this.finalRequest = finalRequest;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getFinalReason() {
		return finalReason;
	}

	public void setFinalReason(String finalReason) {
		this.finalReason = finalReason;
	}

	public String getFinalCreated() {
		return finalCreated;
	}

	public void setFinalCreated(String finalCreated) {
		this.finalCreated = finalCreated;
	}

	public Timestamp getFinalResolved() {
		return finalResolved;
	}

	public void setFinalResolved(Timestamp finalResolved) {
		this.finalResolved = finalResolved;
	}

	public int getFinalResponse() {
		return finalResponse;
	}

	public void setFinalResponse(int finalResponse) {
		this.finalResponse = finalResponse;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	@Override
	public String toString() {
		return "FinalExpensesPojo [finalId=" + finalId + ", finalRequest=" + finalRequest + ", finalAmount="
				+ finalAmount + ", finalReason=" + finalReason + ", finalCreated=" + finalCreated + ", finalResolved="
				+ finalResolved + ", finalResponse=" + finalResponse + ", finalStatus=" + finalStatus + "]";
	}
	
	
	
	
	
	
	
}