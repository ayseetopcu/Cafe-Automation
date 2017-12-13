package database;

import java.util.ArrayList;

/**
 *
 * @author Tugay Demirel
 */
public interface CrudProcesses {
    
    boolean update(Object o);
    
    ArrayList<? extends Object> read(String condition);
    
    boolean create(Object o);
    
    boolean delete(String codition);
    
}