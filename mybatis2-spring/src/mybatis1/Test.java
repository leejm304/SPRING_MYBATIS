package mybatis1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.vo.Customer;

public class Test {

	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			// inputStream = new FileInputStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			try (SqlSession session = sqlSessionFactory.openSession()) {
				// Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog",
				// 101);
				// session.update("com.my.vo.Customer.updateName", "id1");

				// insert
				Customer c = new Customer();
				c.setId("newc");
				c.setPwd("12345");
				c.setName("jessica");
				c.setAddr("seoul");
				session.insert("com.my.vo.Customer.insert", c);

				// delete
//				session.delete("com.my.vo.Customer.delete", "idtest");
//				session.commit();

				// update
//				HashMap<String, String> map = new HashMap();
//				 map.put("id", "id1");
//				 map.put("name", "바티스2");
//				 session.update("com.my.vo.Customer.updateName", map);
//				 session.commit();

				// select
				 Customer c1 = session.selectOne("com.my.vo.Customer.selectById", "id1");
				 System.out.println(c1.getName()+ " : " + c1.getPwd());
			 
				 int cnt = session.selectOne("com.my.vo.Customer.selectCount");
				 System.out.println("총 고객 수  : " + cnt);

				HashMap map2 = session.selectOne("com.my.vo.Customer.selectGroup");
				System.out.println(map2.get("C1") + ":" + map2.get("C2"));

				List<Customer> list = session.selectList("com.my.vo.Customer.selectAll");
				System.out.println("총 고객 수  : " + list.size());
				for (Customer c2 : list) {
					System.out.println(c.getId() + " : " + c.getName());
				}
				
				Customer c3 = session.selectOne("com.my.vo.Customer.selectZipcodeById", "id9");
				System.out.println(c3.getId() + " : " + c3.getPost().getBuildingno());
				session.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
