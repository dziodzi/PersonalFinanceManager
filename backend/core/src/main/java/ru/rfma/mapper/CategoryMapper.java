package ru.rfma.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.rfma.dto.CategoryDto;
import ru.rfma.entities.Category;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    List<CategoryDto> toDtos(List<Category> categoryList);

    Category toEntity(CategoryDto categoryDto);
    List<Category> toEntities(List<CategoryDto> categoryDtoList);
}
