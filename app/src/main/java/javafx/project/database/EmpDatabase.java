package javafx.project.database;

import java.sql.*;

public class EmpDatabase {
    private final Connection connection = Database.getConnection();

    // private static EmpDatabase instance;

    // private AdminDatabase admin = AdminDatabase.getInstance();

    private int adminId;

    // private int id;

    private ResultSet data;

    public EmpDatabase(int adminId) {
        this.adminId = adminId;
    }

    public int count() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COUNT(*) AS emp_count FROM employees where admin_id=" + this.adminId);
            resultSet.next();

            int count = resultSet.getInt("emp_count");
            return count;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public ResultSet getData() {
        try {
            Statement statement = connection.createStatement();
            data = statement.executeQuery("Select * from employees where admin_id=" + this.adminId);
            return data;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int setData(String name, String department, String address, String salary, String gender) {
        try {
            String sql = "INSERT INTO employees (name,department,address,salary,gender,admin_id)" +
                    " VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, department);
            statement.setString(3, address);
            statement.setString(4, salary);
            statement.setString(5, gender);
            statement.setInt(6, this.adminId);
            int i = statement.executeUpdate();
            return i;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

}
