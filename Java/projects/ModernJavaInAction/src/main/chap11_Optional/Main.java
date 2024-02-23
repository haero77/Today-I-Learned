package main.chap11_Optional;

public class Main {

	public static void main(String[] args) {

	}

	public String getCarInsuranceName(Person person) {
		if (person == null) {
			return "Unknown";
		}
		Car car = person.getCar();
		if (car == null) {
			return "Unknown";
		}
		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return "Unknown";
		}
		return insurance.getName();
	}

}
