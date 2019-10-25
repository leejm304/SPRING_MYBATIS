package control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/a")
//	@GetMapping("/a")
//	@PostMapping("/a")
//	public void a() {
//	System.out.println("LoginController의 a()호출");
	
//	public String a() { 
//	System.out.println("LoginController의 a()호출"); //
//	return "/result.jsp";//view이름 
//  }
	
//	public void a() { 
//		System.out.println("LoginController의 a()호출");
		//return "WEB-INF/a.jsp";와 같은 효과를 갖는 mvc:view-resolvers
		/*
		 * <mvc:jsp prefix="/" suffix=".jsp" />인 경우
		 *  return "/a.jsp";와 같은 효과 (/: 웹 컨텍스트안의)
		 */
//	}
	
	//http://localhost:8080/spring-mvc1/a?id=aaa&pwd=bbb 요청시 
	//
	public String a(String id, @RequestParam("pwd") String p) {
		System.out.println("LoginController의 a()호출");
		System.out.println("id="+ id + " & p=" + p);
		return "/result.jsp";
	}
	
}
