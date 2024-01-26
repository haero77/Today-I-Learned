package ch01.ch01_03;

/**
 * 관람객이 소지품을 보관할 용도로 들고오는 가방
 */
public class Bag {

	private Long amount;
	private Invitation invitation;
	private Ticket ticket;

	// 이벤트 당첨되지 않은 관람객은 초대장이 없다.
	public Bag(long amount) { // Long이 아닌 long 을 사용함으로써 인스턴스 생성 시점에 amount가 null인 것을 방지
		this(null, amount);
	}

	// 이벤트 당첨된 관람객
	public Bag(Invitation invitation, long amount) {
		this.amount = amount;
		this.invitation = invitation;
	}

	public boolean hasInvitation() {
		return invitation != null;
	}

	public boolean hasTicket() {
		return ticket != null;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void minusAmount(Long amount) {
		this.amount -= amount;
	}

	public void plusAmount(Long amount) {
		this.amount += amount;
	}

}
