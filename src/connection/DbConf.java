package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConf {

    private static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance() {
        if (instance == null) {

            Properties prop = new Properties();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream("resources/dbconf.properties");
                prop.load(fileInputStream);
            } catch (IOException ex) {
                Logger.getLogger(DbConf.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(DbConf.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            prop.getProperty("database");

            String connection = "jdbc:mysql://" + prop.getProperty("host") + "/" + prop.getProperty("databaseName");
            Map<String, String> connectionMap = new HashMap();
            connectionMap.put("javax.persistence.jdbc.url", connection);
            connectionMap.put("javax.persistence.jdbc.password", prop.getProperty("password") == null ? "" : prop.getProperty("password"));
            connectionMap.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            connectionMap.put("javax.persistence.jdbc.user", prop.getProperty("user"));
            instance = Persistence.createEntityManagerFactory("hotelPU", connectionMap);
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
