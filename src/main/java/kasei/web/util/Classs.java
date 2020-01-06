package kasei.web.util;

import java.util.List;

public class Classs {
	
	private String name;
	private List<Student> studentList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "Classs [name=" + name + ", studentList=" + studentList + "]";
	}
}
