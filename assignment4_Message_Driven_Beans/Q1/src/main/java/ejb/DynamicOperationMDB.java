/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 *
 * @author riya vesuwala
 */
@MessageDriven(
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myQueue")
    }
)

public class DynamicOperationMDB implements MessageListener{

    @PersistenceContext(unitName = "myPU")
    EntityManager em;
    
    @Override
    public void onMessage(Message message) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            try {
                TextMessage txt = (TextMessage) message;
                String msg = txt.getText();

                System.out.println("Received Message: " + msg);

                // Split message
                String[] parts = msg.split(":");

                String operation = parts[0];       // I / U / D
                String table = parts[1];           // table name
                String columns = parts[2];         // column names
                String values = parts[3];          // values
                String where = (parts.length > 4) ? parts[4] : "";

                switch (operation) {

                    case "I":
                        insertData(table, columns, values);
                        break;

                    case "U":
                        updateData(table, columns, values, where);
                        break;

                    case "D":
                        deleteData(table, where);
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void insertData(String table, String columns, String values) {

            String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")";
            System.out.println(sql);

            Query q = em.createNativeQuery(sql);
            q.executeUpdate();
        }

        private void updateData(String table, String columns, String values, String where) {

            String[] colArr = columns.split(",");
            String[] valArr = values.split(",");

            StringBuilder setClause = new StringBuilder();

            for (int i = 0; i < colArr.length; i++) {
                setClause.append(colArr[i]).append("=").append(valArr[i]);
                if (i < colArr.length - 1) {
                    setClause.append(",");
                }
            }

            String sql = "UPDATE " + table + " SET " + setClause + " " + where;
            System.out.println(sql);

            Query q = em.createNativeQuery(sql);
            q.executeUpdate();
        }

        private void deleteData(String table, String where) {

            String sql = "DELETE FROM " + table + " " + where;
            System.out.println(sql);

            Query q = em.createNativeQuery(sql);
            q.executeUpdate();
        }

    
}
