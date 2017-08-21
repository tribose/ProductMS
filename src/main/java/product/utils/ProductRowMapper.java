package product.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import product.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	 @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        
        product.setProductId(rs.getInt("productId"));
        product.setProductName(rs.getString("productName"));
        product.setProductType(rs.getString("productType"));
        return product;
    }

}
