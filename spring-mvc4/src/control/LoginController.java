package control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping("/a") // (Get/Post)요청이들어오면 a()와 매핑하겠다.
	// @GetMapping("/a")//(Get)요청이들어오면 a()와 매핑하겠다.
	// @PostMapping("/a")//(Post)요청이들어오면 a()와 매핑하겠다.

//	public void a() {
//		System.out.println("LoginController의 a()호출됨");
//	}

	// 주소 리턴하기
//	public String a() {
//		System.out.println("LoginController의 a()호출됨");
//		return "/result.jsp";//view 이름
//	}

	// return 없이 viewer호출하기 (mvc:view-resolvers)
//	public void a() { 
//		System.out.println("LoginController의 a()호출됨");
//		//return "/WEB-INF/a.jsp"; //default	
//		/*
//		 *
//		 *<mvc:jsp prefix="/" suffix=".jsp" />인 경우
//		 * return "result.jsp";
//		 */
//	}	

	// parameter 함께 전달할때
	//http://localhost:8080/spring-mvc1/a?id=aaa&pwd=bbb 요청
	public String a(String id, @RequestParam("pwd") String p) {
		System.out.println("LoginController의 a()호출됨");
		System.out.println("id=" + id + " p=" + p);
		return "/result.jsp";
	}
}
