
package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView addOrder(String[] prod_noArray, HttpSession session){
		
		ModelAndView mnv = new ModelAndView(); 
		JSONObject jsonObj = new JSONObject();
		//로그인이 된 사용자만 주문할 수 있도록 
		if(session.getAttribute("loginInfo") == null){
			//로그인이 안 된경우 0 
			jsonObj.put("status", 0);
			mnv.addObject("result", jsonObj.toString());
		}else {
			Map<Product, Integer> cart = (Map)session.getAttribute("cart");
			OrderInfo info = new OrderInfo();
			String id = (String)session.getAttribute("loginInfo");
			Customer c = new Customer();
			c.setId(id);
			info.setCustomer(c);
			List<OrderLine> lines = new ArrayList<>();
			info.setOrderLines(lines);
			if(cart!=null) {
				for(Product p: cart.keySet()) {
					for(String prod_no: prod_noArray) {
						if(p.getProd_no().equals(prod_no)) {
							OrderLine line = new OrderLine();
							line.setProduct(p);
							line.setOrder_quantity(cart.get(p));
							lines.add(line);
							cart.remove(p);
							break;
						}
					}
				}
			}
			String result = service.add(info);
			//주문 정상처리되면 cart 비우기 
			//session.removeAttribute("cart");
			//session.setAttribute("cart", cart);
			jsonObj.put("status", 1);
			mnv.addObject("result", jsonObj.toString());
		}//end else
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	@PostMapping("/orderlist") 
	public ModelAndView orderList(String id){

		try {
			List<OrderInfo> list = service.findById(id);
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mnv = new ModelAndView();
		
		return mnv;
	}

}
