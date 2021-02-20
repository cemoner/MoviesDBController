package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        DbHelper dbHelper = new DbHelper();
        /* dbHelper.insertMovie("The Hobbit:The Battle of the Five Armies","Peter Jackson",
                2014,"Fantasy"); */

        dbHelper.retrieveMovies();
    }
}
