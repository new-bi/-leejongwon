package Model;

public class Order {
	private int no;
	
	private String orderSit;
	private String orderName;
	private String orderPrice;
	private String orderList;
	private String orderCount;
	
	public Order() {
		super();
	}

	public Order(int no, String orderSit, String orderName, String orderPrice, String orderList, String orderCount) {
		super();
		this.no = no;
		this.orderSit = orderSit;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.orderList = orderList;
		this.orderCount = orderCount;
	}

	public Order(String orderSit, String orderName, String orderPrice, String orderList, String orderCount) {
		super();
		this.orderSit = orderSit;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.orderList = orderList;
		this.orderCount = orderCount;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrderSit() {
		return orderSit;
	}

	public void setOrderSit(String orderSit) {
		this.orderSit = orderSit;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderList() {
		return orderList;
	}

	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}

	public String getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "Order [orderSit=" + orderSit + ", orderName=" + orderName + ", orderPrice=" + orderPrice
				+ ", orderList=" + orderList + ", orderCount=" + orderCount + "]";
	}
	

	
	
}
