package hello;

import org.springframework.data.annotation.Id;

public class Banner {

	@Id
	private String id;

	private String bannerId;

	private Integer cost;

	public Banner() {
		super();
	}

	public Banner(String bannerId, Integer cost) {
		super();
		this.bannerId = bannerId;
		this.cost = cost;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public void updateCost(Integer cost) {
		this.cost = this.cost + cost;
	}

	@Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", cost=" + cost + "]";
	}

}
