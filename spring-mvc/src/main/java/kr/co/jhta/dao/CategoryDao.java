package kr.co.jhta.dao;

import java.util.List;

import kr.co.jhta.vo.Category;

public interface CategoryDao {

	/*
	 * create table spring_categories(
    category_id varchar2(100) primary key,
    category_name varchar2(200) not null

);
	 */
	Category getCategoryById(String categoryId);
	List<Category> getAllCategories();
	
}
