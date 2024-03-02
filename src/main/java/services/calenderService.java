package services;


import models.Calendar;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class  calenderService implements InterfaceCalender<Calendar> {
    private Connection connection;
    public calenderService (){
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public int ajoutercalender(Calendar calendar) throws SQLException {
        String req = "INSERT INTO calendar (name, startdate, enddate) " +
                "VALUES ('" + calendar.getName() + "', " +
                "'" + calendar.getStartdate() + "', " +
                "'" + calendar.getEnddate() + "')";
        Statement statement = connection.createStatement();
        int rows= statement.executeUpdate(req);
        if (rows > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public int modifiercalender(Calendar calendar,int id) throws SQLException {
        String req = "UPDATE calendar SET " +
                "name = '" + calendar.getName() + "', " +
                "startdate = '" + calendar.getStartdate() + "', " +
                "enddate = '" + calendar.getEnddate() + "' " +
                "WHERE id = " + id;
        Statement statement = connection.createStatement();
        int rowsUpdated = statement.executeUpdate(req);
        if (rowsUpdated > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int supprimercalender(int id) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String reqSelectEvents = "SELECT id FROM evenement WHERE calender = ?";
        try (PreparedStatement statement1 = connection.prepareStatement(reqSelectEvents)) {
            statement1.setInt(1, id);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        EventService eventService = new EventService();
        for (int eventId : ids) {
            if (eventService.supprimerEvent(eventId) == 0) {
                return 0;
            }
        }
        String reqDeleteCalendar = "DELETE FROM calendar WHERE id = ?";
        int rowsDeleted = 0;
        try (PreparedStatement statement2 = connection.prepareStatement(reqDeleteCalendar)) {
            statement2.setInt(1, id);
            rowsDeleted = statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return rowsDeleted > 0 ? 1 : 0;
    }



    @Override
    public List<Calendar> listAllcalenders() throws SQLException {
        String sql = "select * from calendar " ;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql) ;
        List<Calendar> list = new ArrayList<>();
        while (rs.next()) {
            Calendar calendar = new Calendar();
            calendar.setId(rs.getInt("id"));
            calendar.setName(rs.getString("name"));
            calendar.setStartdate(rs.getDate("startdate"));
            calendar.setEnddate(rs.getDate("enddate"));
            list.add(calendar) ;
        }
        return list;
    }

    @Override
    public Calendar getCalendarById(int id) throws SQLException {
        String sql = "SELECT * FROM calendar WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            Calendar calendar = new Calendar();
            calendar.setId(rs.getInt("id"));
            calendar.setName(rs.getString("name"));
            calendar.setStartdate(rs.getDate("startdate"));
            calendar.setEnddate(rs.getDate("enddate"));
            return calendar;
        } else {
            return null;
        }
    }
}
