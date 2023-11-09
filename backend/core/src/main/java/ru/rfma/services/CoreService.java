package ru.rfma.services;

import org.springframework.stereotype.Service;
import ru.rfma.dto.CategoryDto;
import ru.rfma.dto.OperationDto;
import ru.rfma.entities.Category;
import ru.rfma.entities.Operation;
import ru.rfma.mapper.CategoryMapper;
import ru.rfma.mapper.OperationMapper;
import ru.rfma.repo.CategoryRepository;
import ru.rfma.repo.OperationRepository;

import java.util.List;

@Service
public class CoreService {
    private final CategoryRepository categoryRepository;

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final CategoryMapper categoryMapper;

    public CoreService(final CategoryRepository categoryRepository, final OperationRepository operationRepository,
                       OperationMapper operationMapper,
                       CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDto createCategory(final String name, final Float limit) {
        Category category = new Category(name, limit);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    public CategoryDto getCategoryById(final Integer id) {
        return categoryMapper.toDto(categoryRepository.getById(id));
    }

    public CategoryDto getCategoryByName(final String name) {
        return categoryMapper.toDto(categoryRepository.getCategoryByName(name));
    }

    public CategoryDto updateCategoryLimit(final Integer id, final Float limit) {
         final Category category = categoryRepository.getById(id);
         category.setLimit(limit);
         categoryRepository.save(category);
         return categoryMapper.toDto(category);
    }

    public void deleteCategory(final Integer id) {
         categoryRepository.deleteById(id);
    }

    public OperationDto createOperation(final OperationDto operationDto) {
        Operation operation = operationMapper.toEntity(operationDto);
        operationRepository.save(operation);
        return operationMapper.toDto(operation);
    }

    public OperationDto getOperationById(final Integer id) {
        Operation operation = operationRepository.getById(id);
        return operationMapper.toDto(operation);
    }

    public List<OperationDto> getAllOperations() {
        List<Operation> operationList = operationRepository.findAll();
        return operationMapper.toDtos(operationList);
    }

    public void deleteOperationById(final Integer id) {
        operationRepository.deleteById(id);
    }
}
