package addressbook;

import java.io.Serializable;

public class Person implements Serializable{
	public String name;
	public int age;
	public String address;
	public String contact;
	
	public Person() {}
	
	public Person(String name, int age, String address, String contact) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.contact = contact;
	}
	
	public String getInfo() {
		return String.format("이름: %s, 나이: %s, 주소: %s, 연락처: %s", name, age, address, contact);
	}
	
	public void printInfo() {
		System.out.println(getInfo());
	}
	public String getName() {
		return name;
		
	}
	public int getAge() {
		return age;
		
	}
	public String getAddress() {
		return address;
		
	}
	public String getContact() {
		return contact;
		
	}
}/////////Person
