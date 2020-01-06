package kasei.web.util;

import java.util.List;
import java.util.Map;

public class ClientData {
	private String msg;
	private Person person;
	private List<Person> list;
	private Map<String, Person> map;
	
	
	public Map<String, Person> getMap() {
		return map;
	}

	public void setMap(Map<String, Person> map) {
		this.map = map;
	}

	public List<Person> getList() {
		return list;
	}

	public void setList(List<Person> list) {
		this.list = list;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
