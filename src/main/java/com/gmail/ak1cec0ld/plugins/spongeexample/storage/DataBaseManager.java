package com.gmail.ak1cec0ld.plugins.spongeexample.storage;

import com.gmail.ak1cec0ld.plugins.spongeexample.SpongeExample;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
    private SpongeExample plugin;
    public DataBaseManager(SpongeExample in){
        plugin = in;
        dbInitCheck();
    }

    private static Connection dbConnection = null;
    private static DataSource dbSource = null;
    private static void connectDB() throws SQLException {
        if(dbConnection != null) {
            if (!dbConnection.isClosed()) {
                dbConnection.close();
            }
        }
        if(dbSource == null) {
            dbSource = Sponge.getServiceManager().provide(SqlService.class).get().getDataSource("jdbc:h2:." + File.separator + "exData");
        }
        dbConnection = dbSource.getConnection();
    }
    public void dbInitCheck(){
        try {
            connectDB();
            boolean playerTableExists = dbConnection.getMetaData().getTables(null,null,"Players",null).next();
            if(!playerTableExists){
                dbConnection.prepareStatement("CREATE TABLE Players (ID INTEGER NOT NULL AUTO_INCREMENT, PLAYER TINYTEXT)").executeUpdate();
            }
            dbConnection.close();
        } catch (SQLException throwables) {
            plugin.getLogger().warn("dbInitCheck error");
        }
    }

    public boolean dbContains(String input){
        try {
            connectDB();
            ResultSet queryResult = dbConnection.prepareStatement("SELECT * FROM Players WHERE PLAYER="+input).executeQuery();
            if(queryResult.wasNull()){
                plugin.getLogger().info("Found nothing");
            }
        } catch (SQLException throwables) {
            plugin.getLogger().warn("dbContains error");
        }
        return false;
    }
    public void dbInsert(String input){
        try{
            connectDB();
            dbConnection.prepareStatement("INSERT INTO Players VALUES('"+input+"')").executeUpdate();
        } catch (SQLException e){
            plugin.getLogger().warn("dbInsert error");
        }
    }
}
