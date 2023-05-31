package ch.ubs.m295.m295andreas.dao.subDAOs;

import ch.ubs.m295.m295andreas.dto.ProductTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Optional;

public class ProductDAO {

      @Autowired
      private NamedParameterJdbcTemplate jdbcTemplate;

      public void addProduct(ProductTable product) {
            String sql = "INSERT INTO product (productname, seller, price) VALUES (:productname, :seller, :price)";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("productname", product.getProductname())
                  .addValue("seller", product.getSeller())
                  .addValue("price", product.getPrice());
            jdbcTemplate.update(sql, namedParameters);
      }

      public void updateProduct(ProductTable product) {
            String sql = "UPDATE product SET productname = :productname,  seller = :seller WHERE id = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("id", product.getId())
                  .addValue("productname", product.getProductname())
                  .addValue("seller", product.getSeller())
                  .addValue("price", product.getPrice());
            jdbcTemplate.update(sql, namedParameters);
      }

      public void deleteProduct(int id) {
            String sql =
                   "DELETE p, m FROM Products p " +
                  "LEFT JOIN PurchaseToProductMapping m ON p.id = m.productId " +
                  "WHERE p.id = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("id", id);
            jdbcTemplate.update(sql, namedParameters);
      }

      public Optional<ProductTable> getProduct(int id) {
            String sql = "SELECT * FROM product WHERE id = :id";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                  .addValue("id", id);
            return jdbcTemplate.query(sql, namedParameters);
      }
}