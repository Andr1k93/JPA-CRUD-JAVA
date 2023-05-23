package com.dao;

import java.util.List;

import com.entity.Category;

public interface CategoryDAO {

	public void insertCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Integer categoryId);

	public List<Category> readCategory();
}
