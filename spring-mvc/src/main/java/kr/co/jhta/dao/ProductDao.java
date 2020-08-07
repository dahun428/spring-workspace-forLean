package kr.co.jhta.dao;

import java.util.List;

import kr.co.jhta.vo.Product;

public interface ProductDao {

	void insertProduct(Product product);
	void updateProduct(Product product);
	Product getProductByNo(long productNo);
	List<Product> getAllProducts();
	List<Product> getProductsByCategoryId(String categoryId);
}
