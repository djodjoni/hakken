package uk.co.vurt.hakken.domain.job;

import java.io.Serializable;

public class DataItem implements Serializable {

	private final static String TAG = "DataItem";
	
	Long id;
	String name;
	String type;
	String value;
	String pageName;
	
	public DataItem(){}
	
	public DataItem(String pageName, String name, String type, String value) {
		super();
		this.pageName = pageName;
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public DataItem(Long id, String pageName, String name, String type, String value){
		this(pageName, name, type, value);
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DataItem [name=" + name + ", type=" + type + ", value=" + value
				+ ", pageName=" + pageName + "]";
	}
	
	
}
