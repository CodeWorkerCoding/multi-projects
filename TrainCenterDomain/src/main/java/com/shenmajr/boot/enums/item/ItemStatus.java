package com.shenmajr.boot.enums.item;

public enum ItemStatus {
	DEL(0,"已删除"),
	FORBID(1,"被禁用"),
	HIDDEN(2,"不显示"),
	NORMAL(3,"正常显示"),
	INVALID(4,"无效的菜单");
	
	private Integer value;
	
	private String desc;
	
	private ItemStatus(Integer value, String desc){
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
