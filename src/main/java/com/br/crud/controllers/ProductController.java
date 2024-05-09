package com.br.crud.controllers;

import com.br.crud.dtos.ProdutosRecordDto;
import com.br.crud.models.ProductModels;
import com.br.crud.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;
        @PostMapping("/products")
    public ResponseEntity<ProductModels> saveProduct(@RequestBody @Valid ProdutosRecordDto produtosRecordDto){
        var productModel = new ProductModels();
        System.out.println(produtosRecordDto);
        BeanUtils.copyProperties(produtosRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModels>> getAllProducts(){
            return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public  ResponseEntity<Object> GetOneProducts(@PathVariable(value = "id") UUID id){
        Optional<ProductModels> product0 = productRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }
    @PutMapping("/products/{id}")
    public  ResponseEntity<Object> PutProducts(@PathVariable(value = "id") UUID id,@RequestBody @Valid ProdutosRecordDto produtosRecordDto){
        Optional<ProductModels> product0 = productRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não existe");
        }
        var productModels = product0.get();
        BeanUtils.copyProperties(produtosRecordDto, productModels);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModels));
    }


    @DeleteMapping("/products/Delete/{id}")
    public ResponseEntity<Object> DeleteProduct(@PathVariable(value = "id")UUID id){
            Optional<ProductModels> product0 = productRepository.findById(id);
            if(product0.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto para Deletar");
            }
            productRepository.delete(product0.get());
            var productF = product0.get();
            
            return ResponseEntity.status(HttpStatus.OK).body(productF);
    }
}
