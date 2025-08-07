package fss.repository;

import fss.model.Customer;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Param;
import jakarta.data.repository.Query;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;
import jakarta.data.repository.Update;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author ehernandez
 */
@Repository
public interface CustomerRepository {

    @Find
    Optional<Customer> byId(Integer id);
    
    @Find
    Optional<Customer> byName(String name);
        
    @Find
    @OrderBy("id")
    List<Customer> all();   
    
    @Query("where route in :rutas")
    List<Customer> allByRoute(Set<String> rutas);   

    @Insert
    Customer insert(Customer c);
    
    @Update
    Customer update(Customer c);
    
    @Save
    Customer upsert(Customer c);        
    
    @Delete
    void delete(Customer c);       
        

}
