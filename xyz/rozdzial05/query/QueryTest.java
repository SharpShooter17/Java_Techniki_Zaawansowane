package query;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

/**
  * Program wykonujący kilka złożonych operacji na bazie danych.
 * @version 1.30 2012-06-05
 * @author Cay Horstmann
 */
public class QueryTest
{
   private static final String allQuery = "SELECT Books.Price, Books.Title FROM Books";

   private static final String authorPublisherQuery = "SELECT Books.Price, Books.Title"
         + " FROM Books, BooksAuthors, Authors, Publishers"
         + " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN"
         + " AND Books.Publisher_Id = Publishers.Publisher_Id AND Authors.Name = ?"
         + " AND Publishers.Name = ?";

   private static final String authorQuery
      = "SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors"
         + " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN"
         + " AND Authors.Name = ?";

   private static final String publisherQuery
      = "SELECT Books.Price, Books.Title FROM Books, Publishers"
         + " WHERE Books.Publisher_Id = Publishers.Publisher_Id AND Publishers.Name = ?";


   private static final String priceUpdate = "UPDATE Books " + "SET Price = Price + ? "
         + " WHERE Books.Publisher_Id = (SELECT Publisher_Id FROM Publishers WHERE Name = ?)";

   private static Scanner in;
   private static ArrayList<String> authors = new ArrayList<>();
   private static ArrayList<String> publishers = new ArrayList<>();
   
   public static void main(String[] args) throws IOException
   {
      try (Connection conn = getConnection())
      {
         in = new Scanner(System.in);
         authors.add("Any");
         publishers.add("Any");
         try (Statement stat = conn.createStatement())
         {
            // Wypełnia listę autorów
            String query = "SELECT Name FROM Authors";
            try (ResultSet rs = stat.executeQuery(query))
            {
               while (rs.next())
                  authors.add(rs.getString(1));
            }
               
            // Wypełnia listę wydawców
            query = "SELECT Name FROM Publishers";
            try (ResultSet rs = stat.executeQuery(query))
            {
               while (rs.next())
                  publishers.add(rs.getString(1));
            }
         }
         boolean done = false;
         while (!done)
         {
            System.out.print("Q) zapytanie C) aktualizacja E) zakończenie: ");
            String input = in.next().toUpperCase();
            if (input.equals("Q"))
               executeQuery(conn);
            else if (input.equals("C"))
               changePrices(conn);
            else 
               done = true;
         }
      }
      catch (SQLException e)
      {
         for (Throwable t : e)
            System.out.println(t.getMessage());
      }
   }

   /**
    * Wykonuje wybrane zapytanie.
    * @param conn połączenie z bazą danych
    */
   private static void executeQuery(Connection conn) throws SQLException
   {
      String author = select("Tabela Authors:", authors);
      String publisher = select("Tabela Publishers:", publishers);
      PreparedStatement stat;
      if (!author.equals("Any") && !publisher.equals("Any"))
      {
         stat = conn.prepareStatement(authorPublisherQuery);
         stat.setString(1, author);
         stat.setString(2, publisher);
      }
      else if (!author.equals("Any") && publisher.equals("Any"))
      {
          stat = conn.prepareStatement(authorQuery);
          stat.setString(1, author);
      }
      else if (author.equals("Any") && !publisher.equals("Any"))
      {
          stat = conn.prepareStatement(publisherQuery);
          stat.setString(1, publisher);
      }
      else
         stat = conn.prepareStatement(allQuery);
      
      try (ResultSet rs = stat.executeQuery())
      {
         while (rs.next())
            System.out.println(rs.getString(1) + ", " + rs.getString(2));
      }
   }

   /**
    * Wykonuje polecenie aktualizacji cen.
    * @param conn połączenie z bazą danych
    */
   public static void changePrices(Connection conn) throws SQLException
   {
      String publisher = select("Tabela Publishers:", publishers.subList(1, publishers.size()));
      System.out.print("Zmiana cen o: ");
      double priceChange = in.nextDouble();
      PreparedStatement stat = conn.prepareStatement(priceUpdate);
      stat.setDouble(1, priceChange);
      stat.setString(2, publisher);
      int r = stat.executeUpdate();
      System.out.println("Zaktualizowano wierszy: " + r);
   }
   
   /**
    * Prosi użytkownika o wprowadzenie łańcucha
    * @param prompt łańcuch zachęty
    * @param options opcje, z których może wybierać użytkownik
    * @return wybrana opcja
    */
   public static String select(String prompt, List<String> options)
   {
      while (true)
      {
         System.out.println(prompt);     
         for (int i = 0; i < options.size(); i++)
            System.out.printf("%2d) %s%n", i + 1, options.get(i));
         int sel = in.nextInt();
         if (sel > 0 && sel <= options.size())
            return options.get(sel - 1);
      }      
   }

   /**
    * Nawiązuje połączenie z bazą danych, wykorzystując właściwości
    * zapisane w pliku database.properties
    * @return połączenie z bazą danych
    */
   public static Connection getConnection() throws SQLException, IOException
   {
      Properties props = new Properties();
      try (InputStream in = Files.newInputStream(Paths.get("database.properties")))
      {
         props.load(in);
      }

      String drivers = props.getProperty("jdbc.drivers");
      if (drivers != null) System.setProperty("jdbc.drivers", drivers);
      String url = props.getProperty("jdbc.url");
      String username = props.getProperty("jdbc.username");
      String password = props.getProperty("jdbc.password");

      return DriverManager.getConnection(url, username, password);
   }
}
