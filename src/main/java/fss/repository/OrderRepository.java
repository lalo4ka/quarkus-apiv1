package fss.repository;

import fss.model.Order;
import jakarta.data.Sort;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;
import jakarta.data.repository.Update;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ehernandez
 */
@Repository
public interface OrderRepository {

    @Find
    Optional<Order> byId(Integer id);
    
    @Find
    List<Order> allCustomers(Sort<Order> orderSort);   

    @Insert
    Order insert(Order c);
    
    @Update
    Order update(Order c);
    
    @Save
    Order upsert(Order c);        
    
    @Delete
    Order delete(Order o);

}
