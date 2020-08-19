package kr.co.jhta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jhta.dao.CategoryDao;
import kr.co.jhta.dao.ProductDao;
import kr.co.jhta.vo.Category;
import kr.co.jhta.vo.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public Product addNewProduct(Product product) {
		productDao.insertProduct(product);
		
		//inesrt 작업 완료후 product의 no에 저장된 상품번호로 저장된 상품 상세 정보 조회
		Product savedProduct = productDao.getProductByNo(product.getNo());
		return savedProduct;
	}
	
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	public Product getProductDetail(long productNo) {
		return productDao.getProductByNo(productNo);
	}
}
