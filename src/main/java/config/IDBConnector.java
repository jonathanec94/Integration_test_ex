/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;

/**
 *
 * @author nikolai
 */
public interface IDBConnector {
    public IDBConnector  getInstance();
    public Connection getConnection();
    public void closeConnection(); 
}
