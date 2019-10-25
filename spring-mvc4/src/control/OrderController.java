package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.NotFoundException;
import com.my.service.OrderService;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping("/addorder")
	public ModelAndView addOrder(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		
		//로그인 안된 사용자는 로그인 작업하기
		if(session.getAttribute("loginInfo")==null) {
			JSONObject obj = new JSONObject();
			obj.put("status", 0); //로그인 안된경우
			mnv.addObject("result", obj.toString());
		}
		else {
			Map<Product, Integer> cart = (Map)session.getAttribute("cart");

			OrderInfo orderInfo =new OrderInfo(); //cart내용으로 채우기
			
			Customer customer = new Customer();
			customer.setId(session.getAttribute("loginInfo").toString());
			orderInfo.setCustomer(customer);
			
			List<OrderLine> orderLines = new ArrayList<>();
			for(Product p : cart.keySet()) {
				OrderLine orderLine = new OrderLine();
				orderLine.setProduct(p);
				orderLine.setOrder_quantity(cart.get(p));
				orderLines.add(orderLine);
			}
			orderInfo.setOrderLines(orderLines);
			String result = service.add(orderInfo);
			//cart비우기
			session.removeAttribute("cart");
			
			mnv.addObject("result", result);
			
		}
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	@RequestMapping("/orderlist")
	public ModelAndView getOrderList(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		String id = session.getAttribute("loginInfo").toString();
		try {
			List<OrderInfo> orderInfos = service.findById(id);
			mnv.addObject("list",orderInfos);
			mnv.addObject("result",1);
		} catch (NotFoundException e) {
			e.printStackTrace();
			mnv.addObject("result",-1);
		}
		mnv.setViewName("/orderlist.jsp");
		return mnv;		
	}
}
