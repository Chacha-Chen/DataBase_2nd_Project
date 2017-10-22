/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
  
class Thread1 extends Thread{
    String name;
    public Thread1(String name){
        this.name=name;
    }
    public void run(){
        String sql="select AVG(runtime) from movies where year=1963;";
        try(Connection conn = Helloworld.connect();
            Statement statement  = conn.createStatement();
            ResultSet rs1 = statement.executeQuery(sql)){
            
            // loop through the result set
            while (rs1.next()) {
                System.out.println(rs1.getDouble("AVG(runtime)"));
            }
        }
    
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    }


class Thread2 extends Thread{
    String name;
    public Thread2(String name){
        this.name=name;
    }
    public void run(){
        try{
            for(int i=0;i<5;i++)
                System.out.println("B: "+i);
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
    }
}

   
  
public class Helloworld {
     /**
     * Connect to a sample database
     * @return 
     */
     public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/Users/chenchacha/db/prob1.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            Thread1 mTh1= new Thread1("A");
            Thread2 mTh2= new Thread2("B");
            mTh1.start();
            mTh2.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}