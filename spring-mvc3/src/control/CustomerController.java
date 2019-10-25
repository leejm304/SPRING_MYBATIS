package control;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;



// @Controller
//@Controller + @ResponseBody => @RestController
@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@PostMapping("/login")
	@ResponseBody
	/* POJO타입으로 반환 */
	/* 1. 반환값을 Jackson라이브러리 활용하여 JSON문자열로 변환시킨다. */
	/* 2. 응답형식을 application/json타입으로 설정한다 */
	public Map<String, Object> login(String id,  String pwd, HttpSession session) {
							  
		session.removeAttribute("loginInfo");
		Map<String, Object> map = service.login(id, pwd);
		if((Integer)map.get("status") == 1) {
			session.setAttribute("loginInfo", id);
		}
		return map;
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject)obj;
//			if((Long)jsonObj.get("status")==1) {//로그인 성공
//				session.setAttribute("loginInfo", id);
//			}
//		}catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return str;
	}
	
//	@PostMapping("/login")
//	public ModelAndView login(String id,
//							  String pwd,
//							  HttpSession session) {
//							  //HttpServletRequest request) {
//		//HttpSession session = request.getSession();
//		session.removeAttribute("loginInfo");
//		
//		String str = service.login(id, pwd);
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject)obj;
//			if((Long)jsonObj.get("status")==1) {//로그인 성공
//				session.setAttribute("loginInfo", id);
//			}
//		}catch (ParseException e) {
//			e.printStackTrace();
//		}
//		//	request.setAttribute("result", str);
//		//	String path = "/result.jsp";
//		//	return path;
//		ModelAndView mnv = new ModelAndView();
//		mnv.addObject("result", str);
//		mnv.setViewName("/result.jsp");
//		return mnv;
//	}
	
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
	
	   @PostMapping("/join")
	   @ResponseBody
	   public Map<String, Integer> join(@ModelAttribute("c") Customer c
	            , String buildingno
	            , @RequestParam(required = false, defaultValue = "0") int age // (required = false) : 선택전달(필수X)
	            // 모든 요청전달 데이터는 string 타입으로 받아오려함 : string으로 전달된것이 없음 
	            // -> null : defaultValue = "0" 빈 문자열로 설정해야함
	            , @RequestParam("addr2") String addr){
	     Post post = new Post();
	     post.setBuildingno(buildingno);
	     c.setPost(post);
	     c.setAddr(addr);
	     return service.join(c);
	   }
	
//	   @PostMapping("/join")
//	   public ModelAndView join(@ModelAttribute("c") Customer c
//			   				, String buildingno
//			   				, @RequestParam(required = false, defaultValue = "0") int age // (required = false) : 선택전달(필수X)
//			   				// 모든 요청전달 데이터는 string 타입으로 받아오려함 : string으로 전달된것이 없음 
//			   				// -> null : defaultValue = "0" 빈 문자열로 설정해야함
//			   				, @RequestParam("addr2") String addr) {
////	   System.out.println(c.getId() + ":" + c.getPwd() + ":" + c.getName());
//		   System.out.println("age=" + age);
//		   Post post = new Post();
//		   post.setBuildingno(buildingno);
//		   c.setPost(post);
//		   c.setAddr(addr);
//		   String str = service.join(c);
//		   String path = "/result.jsp";
//		   ModelAndView mnv = new ModelAndView();
//		   mnv.addObject("result", str);
//		   mnv.setViewName(path);
//		   return mnv;
//	   }
	   
	//   public String join(String id
//			   , String pwd
//			   , String name
//			   , String buildingno
//			   , String addr2) {
//		   Customer c = new Customer();
//		   c.setId(id);
//		   c.setPwd(pwd);
//		   c.setName(name);
//		   Post post = new Post();
//		   post.setBuildingno(buildingno);
//		   c.setPost(post);
//		   c.setAddr(addr2);
////	     String str = service.join(c);
////	     request.setAttribute("result", str);
//	       String path = "/result.jsp";
//	       return path;
	//   }
	   
	   
	//   public String join(HttpServletRequest request, HttpServletResponse response) {
//	      String id = request.getParameter("id");
//	      String pwd = request.getParameter("pwd");
//	      String name = request.getParameter("name");
//	      String buildingno = request.getParameter("buildingno");
//	      String addr = request.getParameter("addr2");
//	      
//	      Customer c = new Customer();
//	      c.setId(id);
//	      c.setPwd(pwd);
//	      c.setName(name);
//	      c.setAddr(addr);
//	      Post post = new Post();
//	      c.setPost(post);
//	      post.setBuildingno(buildingno);
//	      
//	      String str = service.join(c);
//	      request.setAttribute("result", str);
//	      
//	      String path = "/result.jsp";
//	      return path;
	//   }
	   
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
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();
	}	   
	   
//	@RequestMapping("/logout")
//	public ModelAndView logout(HttpSession session) {
//		session.invalidate();
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("status", -1); // 의미없는 값
//		//request.setAttribute("result", jsonObj.toString());
//		//return "result.jsp"; // SpringMVC에서는 뷰가 반드시 필요
//		ModelAndView mnv = new ModelAndView();
//		mnv.addObject("result", jsonObj.toString());
//		mnv.setViewName("/result.jsp");
//		return mnv;
//	}
	
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
	@ResponseBody
	public Map<String, Integer> dupchk(String id){
		return service.dupchk(id);
	}
	
//	@PostMapping("/dupchk")
//	public ModelAndView dupchk(String id){
//		//String id = request.getParameter("id");
//		String str = service.dupchk(id);
//		//request.setAttribute("result", str);
//		//String path = "/result.jsp";
//		//return path;
//		ModelAndView mnv = new ModelAndView();
//		mnv.addObject("result", str);
//		mnv.setViewName("/result.jsp");
//		return mnv;
//	}
}
