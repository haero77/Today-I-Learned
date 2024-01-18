package ch01;

import java.util.ArrayList;
import java.util.List;

/**
 * 매표소.
 * 관람객이 소극장 입장할 때 매표소에서 초대장을 티켓으로 교환하거나 구매.
 */
public class TicketOffice {

	private Long amount;
	private List<Ticket> tickets = new ArrayList<>();

	public TicketOffice(Long amount, List<Ticket> tickets) {
		this.amount = amount;
		this.tickets = tickets;
	}

	public Ticket getTicket() {
		return tickets.remove(0);
	}

	public void minusAmount(Long amount) {
		this.amount -= amount;
	}

	public void plusAmount(Long amount) {
		this.amount += amount;
	}

}
