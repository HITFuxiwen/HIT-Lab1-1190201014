package P3;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class FriendshipGraphTest {

	@Test
	public void testaddVertex()
	{
		FriendshipGraph graph= new FriendshipGraph();
		Person a= new Person("stu");
		Person b= new Person("stu2");
		graph.addVertex(a);
		assertEquals(a,graph.per.get(0));
		graph.addVertex(b);
		assertEquals(b,graph.per.get(1));
	}
	@Test
	public void testaddEdge()
	{
		FriendshipGraph graph= new FriendshipGraph();
		Person a= new Person("stu");
		Person b= new Person("stu2");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addEdge(a, b);
		graph.addEdge(b, a);
		assertEquals(b,a.getedge().get(0));
		assertEquals(a,b.getedge().get(0));
	}
	@Test
	public void testgetDistance()
	{
		FriendshipGraph graph= new FriendshipGraph();
		Person a= new Person("a");
		Person b= new Person("b");
		Person c= new Person("c");
		Person d= new Person("d");
		Person e= new Person("e");
		Person f= new Person("f");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		graph.addEdge(a, b);
		graph.addEdge(b, a);
		graph.addEdge(a, c);
		graph.addEdge(c, d);
		graph.addEdge(e, f);
		graph.addEdge(f, e);
		assertEquals(1,graph.getDistance(a, b));
		assertEquals(1,graph.getDistance(b, a));
		assertEquals(1,graph.getDistance(a, c));
		assertEquals(-1,graph.getDistance(c, a));
		assertEquals(2,graph.getDistance(a, d));
		assertEquals(3,graph.getDistance(b, d));
		assertEquals(1,graph.getDistance(e, f));
		assertEquals(-1,graph.getDistance(a, e));
	}
	final void test() {
		fail("Not yet implemented"); // TODO
	}

}
