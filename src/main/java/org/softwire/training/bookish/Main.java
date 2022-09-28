package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "bookish";
        String user = "root";
        String password = System.getenv("pass");
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        jdbcMethod(connectionString);
        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
        Connection connection = DriverManager.getConnection(connectionString);

        String query = "SELECT name FROM Books";
        try (Statement stm = connection.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                String name = rs.getString("name");
                System.out.printf("Book Name: %s%n", name);
            }
        }

    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);
        String query = "SELECT name FROM Books";
//        List<String> bookList = jdbi.withHandle(handle -> handle.createQuery(query)
//               .mapTo(String.class)
//               .list());
//        bookList.forEach(System.out::println);

        List<Book> bookList = jdbi.withHandle(handle -> {
            return handle.createQuery(query)
                    .mapToBean(Book.class)
                    .list();
        });

        bookList.stream().map(Book::getName).forEach(System.out::println);





    }
}
