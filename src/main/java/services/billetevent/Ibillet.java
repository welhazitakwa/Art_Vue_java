package services.billetevent;

import java.sql.SQLException;
import java.util.List;

public interface Ibillet <T>{
    void ajouterbilletevent(T t) throws SQLException;
    void modifier(T t) throws SQLException;
    void supprimer(int id_event) throws SQLException;
    List<T> recuperer() throws SQLException;
}
