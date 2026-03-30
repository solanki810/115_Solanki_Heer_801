/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatefulEjbClass.java to edit this template
 */
package ejb;

import jakarta.ejb.Stateful;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author riya vesuwala
 */
@Stateful
public class VisitBean implements VisitBeanLocal {
    private int visitCount=0;
    Connection con;
    
    public VisitBean()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment3?useSSL=false","root","root");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public int recordVisit(String ip) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        visitCount++;
        
        try{
            PreparedStatement ps=con.prepareStatement("select visit_count from PageVisit where ip_address=?");
            ps.setString(1, ip);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next())
            {
                int count =rs.getInt(1)+1; 
                PreparedStatement update=con.prepareStatement("Update PageVisit set visit_count=? where ip_address=?");
                update.setInt(1, count);
                update.setString(2, ip);
                
                update.executeUpdate();      
                
            }
            else
            {
                PreparedStatement insert=con.prepareStatement("insert into PageVisit values(?,?)");
                
                insert.setString(1, ip);
                insert.setInt(2, 1);
                insert.executeUpdate();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return visitCount;
    }

}
