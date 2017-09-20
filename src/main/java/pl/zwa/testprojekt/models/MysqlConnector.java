package pl.zwa.testprojekt.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {
    //private static final - stałe, nie będą się zmieniać
    private static final String SQL_LINK = "jdbc:mysql://5.135.218.27:3306/xxx";
    private static final String SQL_USER = "xxx";
    private static final String SQL_PASS = "xxx";
    private static final String SQL_CLASS = "com.mysql.jdbc.Driver";

    //singleton
    //konstruktor jest możliwy wew. tej samej klasy mimo iż jest private, takim zapisem możemy wywołać connector z innej klasy
    private static MysqlConnector connector = new MysqlConnector();
    //tym zapisem możemy dostać się z innej klasy
    public static MysqlConnector getInstance(){
        return connector;
    }

    //będziemy się odwoływać do connection z innych klas przez gettera
    private Connection connection;

    //prywatny konstruktor - blokujemy możliwość utworzenia instancji takiej klasy z zewnątrz
    private MysqlConnector(){
        connect();
    }

    private void connect() {
        try {
            Class.forName(SQL_CLASS);
            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);
            System.out.println("Połączono!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //getter - ponieważ jest private
    public Connection getConnection() {
        return connection;
    }
}
