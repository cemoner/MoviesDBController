package com.company;

import java.sql.*;

public class DbHelper {
    String userName = "root";
    String password = "Omer2828#";
    String dbUrl = "jdbc:mysql://localhost:3306/movies";

    public Connection getConnection(String dataBaseName) throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dataBaseName;
        return DriverManager.getConnection(dbUrl, userName, password);

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password);
    }

    public void retrieveMovies() throws SQLException {
        Connection connectToM = getConnection();
        Statement statement = connectToM.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");
        while (resultSet.next()) {
            System.out.print(resultSet.getString("movie_title") + " ");
            System.out.print(resultSet.getString("director") + " ");
            System.out.print(resultSet.getString("movie_year") + " ");
            System.out.println(resultSet.getString("genre_id"));
            System.out.print("\n");
        }
        statement.close();
        connectToM.close();
    }

    public void retrieveMovies(String filter) throws SQLException {
        Connection connection = getConnection("movies");

        String query = "SELECT * FROM movies WHERE " + filter;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.print(resultSet.getString("movie_title") + " ");
            System.out.print(resultSet.getString("director") + " ");
            System.out.print(resultSet.getString("movie_year") + " ");
            System.out.println(resultSet.getString("genre_id"));
            System.out.print("\n");
        }
        statement.close();
        connection.close();

    }

    public void insertMovie(String movie_title, String director,
                            int movie_year, String genre_title) throws SQLException {
        Connection connectToM = getConnection();

        Statement statement2 = connectToM.createStatement();
        ResultSet resultSet = statement2.executeQuery("SELECT genre_id FROM genres" +
                " WHERE genre_title = " + "'" + genre_title + "'");

        resultSet.next();
        int genre_id = Integer.parseInt(resultSet.getString("genre_id"));

        String insertQuery = "INSERT INTO movies (movie_title,director,movie_year,genre_id)" +
                " VALUES(?,?,?,?)";
        PreparedStatement statement = connectToM.prepareStatement(insertQuery);
        statement.setString(1, movie_title);
        statement.setString(2, director);
        statement.setInt(3, movie_year);
        statement.setInt(4, genre_id);

        statement.executeUpdate();
        System.out.println("Movie has been added");
        statement.close();
        connectToM.close();
    }

    public void deleteMovies(String filter) throws SQLException {
        String query = "DELETE FROM movies WHERE " + filter;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        System.out.println("Delete operation is successful");
        connection.close();

    }

    public void updateMovies(String sql) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
        connection.close();

    }
}
