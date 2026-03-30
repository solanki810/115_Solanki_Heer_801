
import com.mycompany.q4.CustomerForm;
import com.mycompany.q4.DBConnection;
import java.sql.Connection;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author root
 */
public class Q4 {

   public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new CustomerForm().setVisible(true)
        );
    }
}
