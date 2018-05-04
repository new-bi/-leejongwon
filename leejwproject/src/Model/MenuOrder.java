package Model;

public class MenuOrder {

	// admin 메뉴추가 및 메뉴목록
	private int no;
	private String name;
	private String price;
	private String list;
	private String count;

	public MenuOrder() {
		super();
	}

	public MenuOrder(String name, String price, String list) {
		super();
		this.name = name;
		this.price = price;
		this.list = list;
	}

	public MenuOrder(String name, String price, String list, String count) {
		super();
		this.name = name;
		this.price = price;
		this.list = list;
		this.count = count;
	}

	public MenuOrder(int no, String name, String price, String list, String count) {
		super();
		this.no = no;
		this.name = name;
		this.price = price;
		this.list = list;
		this.count = count;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
