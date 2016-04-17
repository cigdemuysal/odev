package com.codepath.examples.contactloader;

import java.util.ArrayList;

public class Contact {
	public String id;
	public String name;
	public ArrayList<ContactPhone> numbers;
	private String _phoneNo;
	private String _displayName;




	public String getDisplayName(){return _displayName;}
	public void setDisplayName(String displayName){_displayName=displayName;}
	public void set_phoneNo(String _phoneNo){ this._phoneNo=_phoneNo; }
	public String get_phoneNo (){return _phoneNo;}

	public Contact(String id, String name) {
		this.id = id;
		this.name = name;
		this.numbers = new ArrayList<ContactPhone>();
	}

	@Override
	public String toString() {
		String result = name;
		if (numbers.size() > 0) {
			ContactPhone number = numbers.get(0);
			result += " (" + number.number + " - " + number.type + ")";
		}

		return result;
	}
	public void addNumber(String number, String type) {
		numbers.add(new ContactPhone(number,                                                    type));
	}
}
