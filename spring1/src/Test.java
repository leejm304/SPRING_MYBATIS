import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.First;

public class Test {

	public static void main(String[] args) {
		String path = "beans.xml";
		ApplicationContext ctx; //ctx: 스프링 컨테이너(스프링에서 쓰일 객체관리자)
		ctx = new ClassPathXmlApplicationContext(path);
		First first = ctx.getBean("first-set", a.First.class); //a.First.class로 다운캐스팅 가능하는지 물어보고
		System.out.println(first);
		System.out.println(first.getNum());
		System.out.println(first.getSecond().info());
		First first1 = ctx.getBean("first-set", a.First.class); //a.First.class로 다운캐스팅 가능하는지 물어보고
		System.out.println(first1);
		
		First first2 = ctx.getBean("first-con", a.First.class);
		System.out.println(first2.getNum());
		System.out.println(first2.getMsg());
		
		System.out.println(first1==first2); //false
		System.out.println(first1.getSecond()==first2.getSecond()); //true
		
		First first3 = ctx.getBean("first-list", a.First.class);
		System.out.println(first3.getList());
		
		First first4 = ctx.getBean("first-map", a.First.class);
		System.out.println(first4.getMap());
	}


}
