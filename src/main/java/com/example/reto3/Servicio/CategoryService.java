package com.example.reto3.Servicio;

import com.example.reto3.Modelo.Category;
import com.example.reto3.Repositorio.CategoryRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService {
    @Autowired

    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category){
        if (category.getId() == null){
            return  categoryRepository.save(category);
        } else{
            Optional<Category> category1 = categoryRepository.getCategory(category.getId());
            if (category1.isEmpty()){
                return categoryRepository.save(category);
            }else {
                return category;
            }
        }
    }

    public Category update(Category category){
        if (category.getId() != null){
            Optional<Category> e = categoryRepository.getCategory(category.getId());
            if (!e.isEmpty()) {
                if (category.getName() != null) {
                    e.get().setName(category.getName());
                }
                if (category.getDescription() != null) {
                    e.get().setDescription(category.getDescription());
                }
                return categoryRepository.save(e.get());
            }
        }
        return category;
    }

    public boolean delete(int id){
        Boolean i = getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);

        return i;
    }
}
