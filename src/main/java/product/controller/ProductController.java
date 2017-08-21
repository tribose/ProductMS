package product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import product.model.Product;
import product.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Product product;
		
	@RequestMapping("/addProduct/productId/{productId}/productName/{productName}/productType/{productType}")
	public Product addProduct(@PathVariable Integer productId,
							  @PathVariable String productName,
							  @PathVariable String productType){
		
		System.out.println("Inside productController: "+productId+"<>"+productName+"<>"+productType);
		
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductType(productType);
		
		return productService.create(product);
	}
	
	@RequestMapping("/allProducts")
    public List<Product> fetchAllProducts() {
		
		List<Product> products = productService.fetchAllProducts();
        return products;
    }
	
	@RequestMapping("/productType/{productType}")
	public List<Product> findProductByType(@PathVariable("productType") String productType){
		
		return productService.findProductByType(productType);
	}
	
	@RequestMapping("/deleteProduct/{productId}")
	public void deleteProduct(@PathVariable Integer productId){
		
		productService.deleteProduct(productId);
	}
}
