/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fss.api.response;

import com.blazebit.persistence.view.CollectionMapping;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;
import fss.model.Customer;
import fss.model.Order;
import fss.model.OrderDetail;
import fss.model.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@EntityView(Customer.class)
public record OrdersByCustomer(
        @IdMapping
        Integer id,
        String name,
        @CollectionMapping
        List<OrderView> orders
        ) {

}

@EntityView(Order.class)
record OrderView(
        @IdMapping
        Integer id,
        LocalDate date,
        short size,
        List<OrderDetailView> orderDetail) {

}

@EntityView(OrderDetail.class)
record OrderDetailView(
        ProductRecord product,
        String unit,
        @Mapping("qty2*unitPrice")
        BigDecimal totalAmount,
        BigDecimal qty,
        BigDecimal qty2) {

    public BigDecimal totalAmount() {
        return this.totalAmount.setScale(2, RoundingMode.HALF_UP);       
    }

}

@EntityView(Product.class)
record ProductRecord(String name) {

}
