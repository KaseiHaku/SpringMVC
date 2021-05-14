package kasei.springmvc.pojo;

import java.util.List;

public class School {

	private String name;
	private List<Classs> classList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Classs> getClassList() {
		return classList;
	}

	public void setClassList(List<Classs> classList) {
		this.classList = classList;
	}

	@Override
	public String toString() {
		return "School [name=" + name + ", classList=" + classList + "]";
	}
}
