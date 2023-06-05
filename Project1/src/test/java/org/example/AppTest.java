//package org.example;
//
//import junit.framework.Assert;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class AppTest
//        extends TestCase {
//    public void testConnection() {
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5445/postgres?user=postgres&password=123");
//            Assert.assertNotNull(conn);
//        } catch (SQLException e) {
//            Assert.fail("Connecting to database failed: " + e.getMessage());
//        }
//    }
//}
