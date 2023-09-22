package pl.javastart.restoffers.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoryRestController {

    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/names")
    public List<String> getAllCategoriesNames() {
        return categoryService.getCategoriesNames();
    }

    @GetMapping("")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
