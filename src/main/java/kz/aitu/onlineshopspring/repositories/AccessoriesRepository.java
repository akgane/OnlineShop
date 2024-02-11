package kz.aitu.onlineshopspring.repositories;

import kz.aitu.onlineshopspring.data.interfaces.DBInterface;
import kz.aitu.onlineshopspring.models.products.Accessories;
import kz.aitu.onlineshopspring.repositories.interfaces.AccessoriesRepositoryInterface;
import org.hibernate.annotations.processing.SQL;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.jdbc.support.JdbcUtils.closeResultSet;
import static org.springframework.jdbc.support.JdbcUtils.commonDatabaseName;

@Repository
public class AccessoriesRepository implements AccessoriesRepositoryInterface {

    private final DBInterface db;
    public AccessoriesRepository(DBInterface db) { this.db = db; }

    @Override
    public boolean addAccessory(Accessories accessory) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "insert into accessories (id, gender, price, title, description," +
                    "category_id, color, size, has_size, images) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, accessory.getId());
            st.setInt(2, accessory.getGender());
            st.setDouble(3, accessory.getPrice());
            st.setString(4, accessory.getTitle());
            st.setString(5, accessory.getDescription());
            st.setInt(6, accessory.getCategoryId());
            st.setString(7, accessory.getColor());
            st.setString(8, accessory.getSize());
            st.setBoolean(9, accessory.isHasSize());
            Array images = con.createArrayOf("VARCHAR", accessory.getImages());
            st.setArray(10, images);

            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("SQL exception (addAccessory()): " + e.getMessage());
        }
        finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean updateAccessory(Accessories accessory) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "update accessories set gender = ?, price = ?, title = ?," +
                    "description = ?, category_id = ?, color = ?, size = ?, " +
                    "has_size = ?, images = ? where id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, accessory.getGender());
            st.setDouble(2, accessory.getPrice());
            st.setString(3, accessory.getTitle());
            st.setString(4, accessory.getDescription());
            st.setInt(5, accessory.getCategoryId());
            st.setString(6, accessory.getColor());
            st.setString(7, accessory.getSize());
            st.setBoolean(8, accessory.isHasSize());
            Array images = con.createArrayOf("VARCHAR", accessory.getImages());
            st.setArray(9, images);
            st.setInt(10, accessory.getId());

            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("SQL exception (): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteAccessory(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "delete from accessories where id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            return executeStatement(st);
        }catch (SQLException e){
            System.out.println("SQL exception (deleteAccessories()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public Accessories getAccessory(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from accessories where id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = executeQuery(st);
            List<Accessories> res = parseRS(rs);
            if (res != null){
                return res.get(0);
            }
        }catch (SQLException e){
            System.out.println("SQL exception (getAccessory()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Accessories> getAccessoriesByGender(int gender) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from accessories where gender = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, gender);

            return parseRS(executeQuery(st));
        }catch (SQLException e){
            System.out.println("SQL exception (getAccessoriesByGender()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Accessories> getAccessoriesByPrice(double max) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from accessories where price <= ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setDouble(1, max);

            return parseRS(executeQuery(st));
        }catch (SQLException e){
            System.out.println("SQL exception (getAccessoriesByPrice(max)): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Accessories> getAccessoriesByPrice(double min, double max) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from accessories where price between ? and ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setDouble(1, min);
            st.setDouble(2, max);

            return parseRS(executeQuery(st));
        }catch (SQLException e){
            System.out.println("SQL exception (getAccessoriesByPrice(min, max)): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Accessories> getAllAccessories() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from accessories";

            PreparedStatement st = con.prepareStatement(sql);
            return parseRS(executeQuery(st));
        }catch (SQLException e){
            System.out.println("SQL exception (getAllAccessories()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Accessories> parseRS(ResultSet rs) {
        List<Accessories> accessories = new LinkedList<>();

        try {
            while (rs.next()){
                int id = rs.getInt("id");
                int gender = rs.getInt("gender");
                double price = rs.getDouble("price");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                String color = rs.getString("color");
                String size = rs.getString("size");
                boolean hasSize = rs.getBoolean("has_size");
                String[] images = arrToStr(rs.getArray("images"));
                Accessories accessory = Accessories.builder().id(id).gender(gender)
                        .price(price).title(title).description(description)
                        .categoryId(categoryId).color(color).size(size)
                        .hasSize(hasSize).images(images).build();
                accessories.add(accessory);
            }
            return accessories;
        }catch (SQLException e){
            System.out.println("SQL exception (A_parseRS()): " + e.getMessage());
        }
        return null;
    }

    @Override
    public String[] arrToStr(Array arr) {
        try {
            String[] result = null;
            if(arr != null){
                result = (String[]) arr.getArray();
            }
            return result;
        }catch (SQLException e){
            System.out.println("SQL exception (arrToStr()): " + e.getMessage());
        }
        return null;
    }

    @Override
    public void closeConnection(Connection con){
        try {
            if(con != null){
                con.close();
            }
        }catch (SQLException e){
            System.out.println("SQL exception when closing connection (accessoriesRepository): " + e.getMessage());
        }
    }

    @Override
    public boolean executeStatement(PreparedStatement st) {
        return executeStatement(st, ResultSet.TYPE_FORWARD_ONLY);
    }

    @Override
    public boolean executeStatement(PreparedStatement st, int rsType) {
        Connection con = null;
        try {
            con = db.getConnection();
            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("SQL exception when executing statement (accessoriesRepository): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public ResultSet executeQuery(PreparedStatement st) {
        return executeQuery(st, ResultSet.TYPE_FORWARD_ONLY);
    }

    @Override
    public ResultSet executeQuery(PreparedStatement st, int rsType) {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            rs = st.executeQuery();
        }catch (SQLException e){
            System.out.println("SQL exception when executing query (accessoriesRepository): " + e.getMessage());
            closeResultSet(rs);
        }finally {
            closeConnection(con);
        }
        return rs;
    }
}
