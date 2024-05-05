package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository; // Ajoutez le repository de la catégorie

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return mapToProductDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToProductEntity(productDTO);
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + productDTO.getCategoryId())));
        Product savedProduct = productRepository.save(product);
        return mapToProductDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        updateProductEntity(existingProduct, productDTO);
        existingProduct.setCategory(categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + productDTO.getCategoryId())));
        Product updatedProduct = productRepository.save(existingProduct);
        return mapToProductDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setNom(product.getNom());
        dto.setDescription(product.getDescription());
        dto.setPrix(product.getPrix());
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        return dto;
    }

    private Product mapToProductEntity(ProductDTO dto) {
        Product product = new Product();
        product.setNom(dto.getNom());
        product.setDescription(dto.getDescription());
        product.setPrix(dto.getPrix());
        return product;
    }

    private void updateProductEntity(Product product, ProductDTO dto) {
        product.setNom(dto.getNom());
        product.setDescription(dto.getDescription());
        product.setPrix(dto.getPrix());
    }
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId); // Supposons que votre Product a une relation ManyToOne avec Category
        return products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }
}
