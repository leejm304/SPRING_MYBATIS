package mybatis1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.dao.OrderDAO;
import com.my.dao.OrderDAOOracle;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.service.OrderService;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderTest {

	//단위테스트 하기위해 J Unit 이용해보세요!
	public static void main(String[] args) {
		//OrderDAOOracle dao = new OrderDAOOracle();
		String path = "beans.xml";
		ApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext(path);
//		OrderDAO dao = ctx.getBean("orderDAO", com.my.dao.OrderDAO.class);
		OrderService service = ctx.getBean("orderSerivce", com.my.service.OrderService.class);
		String id = "id1";
		try {
			List<OrderInfo> list = service.findById(id);
			for(OrderInfo info: list) {
				int order_no = info.getOrder_no();
				Timestamp order_time = info.getOrder_time();
				List<OrderLine> lines = info.getOrderLines();
				System.out.println("주문 기본정보 : "
						+ order_no + ", "
						+ order_time
						);
				System.out.println("----------------------");
				for(OrderLine line: lines) {
					int p_order_no = line.getOrder_no();
					Product p = line.getProduct();
					String p_no = p.getProd_no();
					String p_name = p.getProd_name();
					int p_price = p.getProd_price();
					int quantity = line.getOrder_quantity();
					System.out.println("주문 상세정보 : "
							+ p_order_no + ", "
							+ p_no + ", "
							+ p_name + ", "
							+ p_price + ", "
							+ quantity
							);
					System.out.println("----------------------");
				}
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
		
//		OrderInfo info = new OrderInfo();
//		Customer c = new Customer();
//		c.setId(id);
//		info.setCustomer(c); // 주문자
//		
//		List<OrderLine> orderLines = new ArrayList<>();
//		
//		OrderLine line1 = new OrderLine();
//		Product p1 = new Product();
//		p1.setProd_no("10001");
//		line1.setProduct(p1); // 주문 상품 번호
//		line1.setOrder_quantity(2); // 주문수량
//		orderLines.add(line1);
//		
//		OrderLine line2 = new OrderLine();
//		Product p2 = new Product();
//		p2.setProd_no("10003");
//		line2.setProduct(p2);
//		line2.setOrder_quantity(3);
//		orderLines.add(line2);
//		
//		info.setOrderLines(orderLines);
//		
//		info.setOrderLines(orderLines);
//		try {
//			dao.insert(info);
//			System.out.println("주문 추가 성공!");
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
		
	}
}
