package pl.javastart.restoffers.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getCategoriesNames() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> category.getName())
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setOffers(category.getOffers().size());

        return dto;
    }

    public Category add(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
