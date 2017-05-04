
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//SQL code used to create the table 
/*
 * CREATE TABLE BASKET_BALL_HOOP 
(
BASKET_BALL_HOOP_ID INTEGER,
PRODUCT_NAME VARCHAR(255),
DIMENSIONS VARCHAR(255),
COLOUR VARCHAR(255),
MANUFACTURER VARCHAR(255),
PRICE INTEGER
)
 */

@Entity
@Table(name = "BASKET_BALL_HOOP")

public class BasketBallHoop implements Serializable{

	@Id @Column(name = "BASKET_BALL_HOOP_ID")
	private Long basketBallHoopId;
	
	@Column (name = "PRODUCT_NAME")
	private String productName;
	
	@Column (name="DIMENSIONS")
	private String dimensions;
	
	@Column (name="COLOUR")
	private String colour;
	
	@Column (name="MANUFACTURER")
	private String manufacturer;
	
	@Column (name="PRICE")
	private Long price;

	public Long getBasketBallHoopId() {
		return basketBallHoopId;
	}

	public void setBasketBallHoopId(Long basketBallHoopId) {
		this.basketBallHoopId = basketBallHoopId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacter) {
		this.manufacturer = manufacter;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}

