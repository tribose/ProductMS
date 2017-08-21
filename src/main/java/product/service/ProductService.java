package product.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import product.model.Product;
import product.utils.ProductRowMapper;

@Service
public class ProductService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
 
    @Transactional(readOnly=true)
    public List<Product> fetchAllProducts() {
    	
        return jdbcTemplate.query("select * from products", 
                					new ProductRowMapper()
        						);
    }
 
    @Transactional(readOnly=true)
    public List<Product> findProductByType(String productType) {
    	
    	return (List<Product>) jdbcTemplate.query(
	    			"select * from products where productType=?",
	                new String[]{productType}, 
	                new ProductRowMapper()
                );
    }
 
    public Product create(final Product product) 
    {
        final String sql = "insert into products(productId, productName,productType) values(?,?,?)";
 
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, product.getProductId());
                ps.setString(2, product.getProductName());
                ps.setString(3, product.getProductType());
                return ps;
            }
        }, holder);
 
        return product;
    }
    
    public void deleteProduct( Integer productId){
    	
    	final String sql = "delete from products where productId = ?";
    	jdbcTemplate.update(sql, new Object[]{productId});
    	
    }
}
