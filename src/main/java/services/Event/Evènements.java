package services.Event;

import java.sql.SQLException;
import java.util.List;

public interface Evènements<T>{
    void ajouterevent(T t) throws SQLException;
    void modifier(T t) throws SQLException;
    void supprimer(int id) throws SQLException;
    List<T> recuperer() throws  SQLException;
}
