package com.mh.crj.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.Product;
import com.mh.crj.model.ProductDto;
import com.mh.crj.model.ProductRequestDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.service.ProductService;
import com.mh.crj.utility.Constants;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody ProductRequestDto productRequestDto) {
    	Product product = productService.createProduct(productRequestDto);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Product created successfully",product));
	}
    
    @GetMapping("/getById/{id}")
	public ResponseEntity<ResponseMessage>  getProduct(@PathVariable Integer id) {
    	Product product = productService.getProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"Product get successfully",product));
	}
    
    @GetMapping("/getProduct/{id}")
	public ProductDto  getProductForfeignClient(@PathVariable Integer id) {
    	Product product = productService.getProduct(id);
    	ProductDto dto = new ProductDto();
    	dto.setId(product.getId());
    	dto.setName(product.getName());
    	dto.setPrice(product.getPrice());
    	dto.setStock(product.getStock());
    	
    	return dto;
    }
	
	
    @GetMapping("/get-all")
	public ResponseEntity<ResponseMessage> getAllProducts() {
		List<Product> allProduct= productService.getAllProduct();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"All Product get successfully",allProduct));
	}
    
    
    @PutMapping("/updateStock/{id}")
    public ResponseEntity<ResponseMessage> updateStock(@PathVariable Integer id,@RequestParam Integer stock) {
    	Product updatedStockProduct = productService.updateStock(id,stock);
    	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"All Product get successfully",updatedStockProduct));
    }
    
    @PutMapping("/updatePrice/{id}")
    public ResponseEntity<ResponseMessage> updatePrice(@PathVariable Integer id,@RequestParam Double price) {
    	Product updatedPriceProduct = productService.updatePrice(id,price);
    	return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"All Product get successfully",updatedPriceProduct));
    }
    
}
