import java.sql.*;
import java.util.Scanner;

public class DB_Operation {
    static Scanner s = new Scanner(System.in);
    static Statement st = null;
    static Connection c = null;
    public void getStatement() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kit", "root", "");
            st = c.createStatement();
    }
    public void insertRecords(B6C[] records) {
        try {
            getStatement();
            for(B6C record: records){
                st.execute("insert into b6c values('"+record.getId()+"','"+record.getSname()+"')");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public B6C readRecord(String searchingId){
        B6C data = new B6C();
        try {
            getStatement();
            ResultSet record = st.executeQuery("select * from b6c where id = '"+searchingId+"'");
            while(record.next()){
                data.setId(record.getString("id"));
                data.setSname(record.getString("sname"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

}
