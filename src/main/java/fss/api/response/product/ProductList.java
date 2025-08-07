package fss.api.response.product;

import java.math.BigDecimal;


public record ProductList(Integer id, String name, String clave, String unitSale, String category, String zone, BigDecimal price, String[] unitSales) {}
