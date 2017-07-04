package strilets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ORDERS")
public class Order {

	public Order() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orders_id;

	@NotBlank
	@Size(max = 30)
	@Column(name = "SURNAME")
	private String surname;

	@NotBlank
	@Size(max = 15)
	@Column(name = "CELL_PHONE")
	private String cellPhone;

	@NotBlank
	@Size(max = 30)
	@Column(name = "PLACE")
	private String place;

	@Size(max = 30)
	@Column(name = "DATE")
	private String date;

	private int buy;

	public int getId() {
		return orders_id;
	}

	public void setId(int orders_id) {
		this.orders_id = orders_id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

}
