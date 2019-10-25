import java.lang.annotation.Annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.First;

public class Test {

	public static void main(String[] args) {
		String path = "beans2.xml";
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext(path);
//		Context ctx = 
//				new AnnotationConfigApplicationContext(Beans.class);
		First first = ctx.getBean("first", a.First.class);
		System.out.println(first);
	}

}
