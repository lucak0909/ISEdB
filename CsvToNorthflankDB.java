import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class CsvToNorthflankDB {

    private static final String DB_URL = "jdbc:mysql://primary.movies--lxdqmkzlvhqw.addon.code.run:3306/0c185bbbeccf?useSSL=true";
    private static final String DB_USER = "19e9017cf7f39d6f";
    private static final String DB_PASSWORD = "551ce41389e4022e4343f6c9e0e4b7";
    private static final String CSV_FILE = "movies.csv";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
        ) {
            String line;
            boolean isFirstLine = true;

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO movies (title, release_date) VALUES (?, ?)"
            );

            while ((line = br.readLine()) != null) {
                if (isFirstLine) { isFirstLine = false; continue; } // skip header

                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                stmt.setString(1, parts[0].trim());
                stmt.setDate(2, Date.valueOf(parts[1].trim()));
                stmt.executeUpdate();
            }

            System.out.println("Data successfully inserted into Northflank MySQL DB.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
