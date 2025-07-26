package ch01.ch01_03;

/**
 * 관람객
 */
public class Audience {

	private Bag bag;

	public Audience(Bag bag) {
		this.bag = bag;
	}

	public Bag getBag() {
		return bag;
	}

	public long buy(Ticket ticket) {
		if (this.bag.hasInvitation()) {
			this.bag.setTicket(ticket);
			return 0L;
		} else {
			this.bag.setTicket(ticket);
			this.bag.minusAmount(ticket.getFee());
			return ticket.getFee();
		}
	}

}
