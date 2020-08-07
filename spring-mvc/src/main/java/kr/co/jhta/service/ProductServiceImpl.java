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
	
	public void addNewProduct(Product product) {
		productDao.insertProduct(product);
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
