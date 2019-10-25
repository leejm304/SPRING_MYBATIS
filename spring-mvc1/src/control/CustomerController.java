package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	static private CustomerController controller  = new CustomerController();
	private CustomerController() {
		service = new CustomerService();
	}
	static public CustomerController getInstance() {
		return controller;
	}
	
	@PostMapping("/login")
	public ModelAndView login(String id,
							  String pwd,
							  HttpSession session) {
							  //HttpServletRequest request) {
		//HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		String str = service.login(id, pwd);
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			if((Long)jsonObj.get("status")==1) {//로그인 성공
				session.setAttribute("loginInfo", id);
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
		//	request.setAttribute("result", str);
		//	String path = "/result.jsp";
		//	return path;
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/result.jsp");
		return mnv;
	}
//	public String login(String id, String pwd, HttpServletRequest request){
//		HttpSession session = request.getSession();
//		session.removeAttribute("loginInfo");
//		
//		String str = service.login(id, pwd);
//		/*--로그인성공시 HttpSession객체의 속성으로 추가--*/
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject)obj;
//			if((Long)jsonObj.get("status")==1) {//로그인 성공
//				session.setAttribute("loginInfo", id);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		request.setAttribute("result", str);
//		String path = "/result.jsp";
//		return path;
//	}
	
	/*
	 * public String join(HttpServletRequest request, HttpServletResponse response)
	 * throws ServletException, IOException { String id =
	 * request.getParameter("id"); String pwd = request.getParameter("pwd"); String
	 * name = request.getParameter("name"); String buildingno =
	 * request.getParameter("buildingno"); String addr2 =
	 * request.getParameter("addr2");
	 * 
	 * String str = service.join(id, pwd, name, buildingno, addr2);
	 * request.setAttribute("result", str); String path = "/result.jsp"; return
	 * path; }
	 */
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", -1); // 의미없는 값
		//request.setAttribute("result", jsonObj.toString());
		//return "result.jsp"; // SpringMVC에서는 뷰가 반드시 필요
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", jsonObj.toString());
		mnv.setViewName("/result.jsp");
		return mnv;
	}
//	public String logout(HttpServletRequest request, HttpSession session) {
//		//HttpSession session = request.getSession();
//        session.invalidate();
//        //return "-1"; 
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("status", -1); // 의미없는 값
//        request.setAttribute("result", jsonObj.toString());
//        return "result.jsp"; // SpringMVC에서는 뷰가 반드시 필요
//	}
	
	@PostMapping("/dupchk")
	public ModelAndView dupchk(String id){
		//String id = request.getParameter("id");
		String str = service.dupchk(id);
		//request.setAttribute("result", str);
		//String path = "/result.jsp";
		//return path;
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/result.jsp");
		return mnv;
	}
}
