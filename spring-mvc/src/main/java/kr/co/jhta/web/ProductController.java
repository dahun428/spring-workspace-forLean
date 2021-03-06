package kr.co.jhta.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.jhta.form.ProductForm;
import kr.co.jhta.service.ProductService;
import kr.co.jhta.vo.Category;
import kr.co.jhta.vo.Product;

/*
 * @ModelAttribute
 * 	- Model 객체에 지정된 이름으로 정보를 저장
 * 	@ModelAttribute("이름")
 * pulic 객체 메소드(){	//해당 컨트롤러에 정의된 요청이 올 때마다. 메소드는 실행된다.
 * 					//메소드가 반환하는 값은 Model객체에 저장된다.
 * 	return 객체		// 메소드가 반환하는 값
 * }
 * - Model 객체에서 지정된 이름의 정보를 가져온다.
 * @RequestMapping("/경로")
 * public String 메소드(@Modelattriubte("이름") 객체 변수명, ...){
 * 
 * }
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@ModelAttribute("categories")
	public List<Category> categories(){
		System.out.println("전체 카테고리 정보 조회 -> Model 객체 저장");
		List<Category> categories = productService.getAllCategories();
		return categories;
	}
	
	
	@RequestMapping("/list.do")
	public String products(Model model) {
		System.out.println("/list.do 요청 처리 시작 : " + model);
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		
		return "product/list";
	}
	@GetMapping("/add.do")
	public String form(Model model) {
		System.out.println("/add.do 요청 처리 시작 : " + model );
		return "product/form";
	}
	@PostMapping("/add.do")
	public String addProduct(ProductForm productForm) {
		
		Product product = new Product();
		BeanUtils.copyProperties(productForm, product);
		productService.addNewProduct(product);
		
		return "redirect:list.do";
	}
	
}
