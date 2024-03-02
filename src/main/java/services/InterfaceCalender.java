package services;



import models.Calendar;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCalender<T> {
    int ajoutercalender (T t) throws SQLException;
    int modifiercalender (T t,int id) throws SQLException ;
    int supprimercalender (int id) throws SQLException ;
    List<T> listAllcalenders () throws SQLException ;
    Calendar getCalendarById(int id) throws SQLException;
}
