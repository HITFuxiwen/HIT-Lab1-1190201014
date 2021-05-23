package P3;

import java.util.*;

public class FriendshipGraph {
	public Set <String> name = new HashSet<String>();
	public List <Person> per = new ArrayList<Person>();
	public void addVertex(Person s)
	{
		if(name.contains(s.getname()))
		{
			System.out.println("error");
			System.exit(0);
		}
		else
		{
			name.add(s.getname());
			per.add(s);
		}
	}
	public void addEdge(Person a, Person b)
	{
		if(a==b)
		{
			System.out.println("error");
			System.exit(0);
		}
		List <Person>p=a.getedge();
		if(!p.contains(b))
			a.addfri(b);
		else
		{
			System.out.println("error");
			System.exit(0);
		}
	}
	public int getDistance(Person a,Person b)
	{
		if(a==b) return 0;
		Map<Person,Integer> dis= new HashMap<>();
		Person[] q=new Person[10000];
		int front=0,tail=1; q[tail]=a;
		dis.put(a, 0);
		while(front<tail)
		{
			front++; Person p=q[front];
			List<Person> edge=p.getedge();
			for(Person e : edge)
			{
				if(!dis.containsKey(e))
				{
					tail++; q[tail]=e;
					int dist=dis.get(p);
					dis.put(e, dist+1);
					if(e==b) return dist+1;
				}
			}
		}
		return -1;
		
	}
	public static void main(String[] args)
	{
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer= new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		//should print 1
		System.out.println(graph.getDistance(rachel, ben)); 
		//should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		//should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		
	}
}
