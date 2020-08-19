package kr.co.jhta.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.jhta.form.ProductForm;
import kr.co.jhta.service.ProductService;
import kr.co.jhta.vo.Category;
import kr.co.jhta.vo.Product;

/*
 *	@RestController
 *		- REST 요청과 응답을 처리하는 컨트롤러 클래스를 나타낸다.
 *		- @ResponseBody 어노테이션을 생략해도 요청 핸들러 메소드가 반환하는 객체가
 *			Json/xml 응답으로 제공된다.
 *		- 클라이언트와 서버가 JSON 형식의 데이터를 주고 받기 위해서는 jack-databind.jar
 *			라이브러리가 필요하다. 
 *	@Rest 형식의 URL
 *		- /자원이름/파라미터값
 *			EX) /rest/products/
 *				@GetMapping("/rest/products/")
 *				public List<Product> getAllProducts(){ ... }
 *				/rest/products/10
 *				@GetMapping("/rest/products/{no}")
 *				public Product getPrududctDetail(@PathVariable("no") int productNo){ ... }
 *				/rest/products/3/10/가구
 *				@GetMapping("/rest/products/{pageNo}/{rows}/{category}")
 *				public List<Product> getProducts(@PathVariable("pageNo") int pageNo,
 *								@PathVariable("rows") int rows,
 *								@PathVariable("category") String category) { ... } 
 *
 *	@PathVariable
 *			-REST url 의 경로에서 지정된 변수에 해당하는
 *	@RequestBody와 @ResponseBody
 *		- @RequestBody는 요청메시지의 body부의 데이터를 자바객체에 담는다.
 *			public Product addProdct(@RequestBody ProductForm productForm){...}
 *			* 클라이언트에서 서버로 요청메시지를 보낼 때 반드시 요청메시지의 body부에 포함되는 데이터의 컨텐츠 타입을 지정해야한다.
 *			* 프로젝트에 지정된 콘텐츠 타입의 값을 자바객체로 변환하는 라이브러리가 포함되어 있어야한다.
 *		- @ResponseBody는 요청 핸들러 메소드가 반환하는 객체를 응답메세지의 body부에 담는다.
 *			public @ResponseBody List<Product> getAllProduct(){...}
 *			* 클라이언트는 서버로부터 받은 응답데이터의 body부에 포함되는 데이터 컨텐츠
 *				타입을 지정해야한다.
 *			* 프로젝트에 자바객체를 지정된 컨텐츠 타입의 값으로 변환하는 라이브러리가 포함되어 있어야한다.
 *
 *
 */


@RestController
@RequestMapping("/rest")
public class RestProductController {

		@Autowired
		ProductService productService;
		
		
		//모든 상품 정보를 조회
		//	요청 방식 : GET
		//	요청 URL : /rest/products/
		//	요청 data : 없음
		//	응답 data : [상품,상품,상품]
		@GetMapping("/products")
		public List<Product> products(){
			List<Product> products = productService.getAllProducts();
			return products;
		}
		
		// 지정된 번호의 상품 정보 조회
		// 	요청방식: GET
		//	요청 URL : /rest/product/상품번호
		//	요청 데이터 : 없음
		//	응답 데이터 : 상품정보
		
		
		@GetMapping("/products/{no}")
		public Product product(@PathVariable("no") int productNo){
			Product product = productService.getProductDetail(productNo);
			return product;
		}
		
		// 새로운 정보 상품 추가
		// 요청방식 : POST
		//	요청 URL : /rest/products/
		//	요청데이터 : 상품정보(추가할)
		//	응답데이터 : 상품정보(추가된)
		@PostMapping("/products/")
		public Product postProduct(@RequestBody ProductForm productform) {
			
			Product product = new Product();
			product.setCategory(new Category(productform.getCatId()));
			product.setName(productform.getName());
			product.setPrice(productform.getPrice());
			product.setDiscountPrice(productform.getDiscountPrice());
			
			// 새상품 정보 (카테고리, 상품명, 가격, 할인가격) 저장후
			//상품번호, 기본재고, 등록일도 포함된 상품 정보(번호, 카테고리, 상품명, 가격, 할인가격, 기본재고, 등록일)가 획득된다.
			productService.addNewProduct(product);
			return null;
		}
		
		
		//지정된 번호의 상품 정보 삭제
		//요청 방식 : DELETE
		//요청 URL : /rest/products/상품번호
		//요청 데이터 : 없음
		//응답데이터: 없음 혹은 상품정보(삭제처리된)
		@DeleteMapping("/product/{no}")
		public void deleteProduct(@PathVariable("no") int productNo) {
			
			
		}
		//지정된 상품의 번호의 상품정보 변공
		// 요청방식 : PUT
		// 요청URL : /rest/products/상품번호
		// 요청데이터 : 상품정보(변경할)
		// 응답데이터 : 상품정보(변경된)
		
}

