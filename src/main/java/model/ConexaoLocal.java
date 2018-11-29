/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.postgresql.jdbc2.optional.PoolingDataSource;

/**
 *
 * @author caiot
 */
public class ConexaoLocal {
    
    private static Connection connection = null;
    private static final PoolingDataSource source = new PoolingDataSource();
    
    static{
        try {
            source.setServerName("localhost");
            source.setDatabaseName("Chat");
            source.setUser("postgres");
            source.setPassword("1602");
            source.setMaxConnections(5);
            
            new InitialContext().rebind("DataSource", source);
            
            
            
        } catch (NamingException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the connection
     */
    public static Connection getConnection() {
        
        try {
            DataSource dsource = (DataSource)new InitialContext().lookup("DataSource");
            connection = dsource.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }

    /**
     * @param aConnection the connection to set
     */
    public static void setConnection(Connection aConnection) {
        connection = aConnection;
    }

    
}
