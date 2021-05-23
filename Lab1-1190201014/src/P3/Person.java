package P3;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
	private List <Person> edge;
	public Person(String name)
	{
		this.name=name;
		edge=new ArrayList<>();
	}
	public String getname()
	{
		return this.name;
	}
	public void addfri(Person b)
	{
		this.edge.add(b);
	}
	public List <Person> getedge()
	{
		return this.edge;
	}
}
