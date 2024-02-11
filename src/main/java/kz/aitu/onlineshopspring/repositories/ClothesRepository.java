package kz.aitu.onlineshopspring.repositories;

import kz.aitu.onlineshopspring.models.products.Clothes;
import kz.aitu.onlineshopspring.repositories.interfaces.ClothesRepositoryInterface;
import kz.aitu.onlineshopspring.data.interfaces.DBInterface;
import org.springframework.stereotype.Repository;

import javax.management.timer.TimerMBean;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.jdbc.support.JdbcUtils.closeResultSet;

@Repository
public class ClothesRepository implements ClothesRepositoryInterface {
    private final DBInterface db;
    public ClothesRepository(DBInterface db){
        this.db = db;
    }
    @Override
    public boolean addClothing(Clothes clothing) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "insert into clothes(id, gender, price, title, description, " +
                    "fabric_type, size_and_fit, images) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, clothing.getId());
            st.setInt(2, clothing.getGender());
            st.setDouble(3, clothing.getPrice());
            st.setString(4, clothing.getTitle());
            st.setString(5, clothing.getDescription());
            st.setString(6, clothing.getFabricType());
            Array sizeAndFit = con.createArrayOf("VARCHAR", clothing.getSizeAndFit());
            st.setArray(7, sizeAndFit);
            Array images = con.createArrayOf("VARCHAR", clothing.getImages());
            st.setArray(8, images);

            return executeStatement(st);
        } catch (SQLException e) {
            System.out.println("SQL exception (addClothing()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean updateClothing(Clothes clothing) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "update clothes set gender = ?, price = ?, title = ?, " +
                    "description = ?, fabric_type = ?, size_and_fit = ?, images = ? " +
                    "where id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, clothing.getGender());
            st.setDouble(2, clothing.getPrice());
            st.setString(3, clothing.getTitle());
            st.setString(4, clothing.getDescription());
            st.setString(5, clothing.getFabricType());
            Array sizeAndFit = con.createArrayOf("VARCHAR", clothing.getSizeAndFit());
            st.setArray(6, sizeAndFit);
            Array images = con.createArrayOf("VARCHAR", clothing.getImages());
            st.setArray(7, images);
            st.setInt(8, clothing.getId());

            return executeStatement(st);
        } catch (SQLException e) {
            System.out.println("SQL exception (updateClothes()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteClothing(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "delete from clothes where id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            return executeStatement(st);
        } catch (SQLException e) {
            System.out.println("SQL exception (deleteClothes()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return false;
    }

    @Override
    public Clothes getClothing(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from clothes where id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = executeQuery(st);
            List<Clothes> res = parseRS(rs);
            if(res != null){
                return res.get(0);
            }
        }catch (SQLException e){
            System.out.println("SQL exception (getClothes()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Clothes> getClothesByGender(int gender) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from clothes where gender = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, gender);

            ResultSet rs = executeQuery(st);

            return parseRS(rs);
        }catch (SQLException e){
            System.out.println("SQL exception (getClothesByGender()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Clothes> getClothesByPrice(double max) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from clothes where price <= ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setDouble(1, max);

            ResultSet rs = executeQuery(st);

            return parseRS(rs);
        }catch (SQLException e){
            System.out.println("SQL exception (getClothesByPrice(max)): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Clothes> getClothesByPrice(double min, double max) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from clothes where price between ? and ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setDouble(1, min);
            st.setDouble(2, max);

            ResultSet rs = executeQuery(st);
            return parseRS(rs);
        }catch (SQLException e){
            System.out.println("SQL exception (getClothesByPrice(min, max)): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Clothes> getAllClothes() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "select * from clothes;";

            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = executeQuery(st);
            return parseRS(rs);
        }catch (SQLException e){
            System.out.println("SQL exception (getClothesByPrice()): " + e.getMessage());
        }finally {
            closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Clothes> parseRS(ResultSet rs){
        List<Clothes> clothes = new LinkedList<>();
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                int gender = rs.getInt("gender");
                double price = rs.getDouble("price");
                String title = rs.getString("title");
                String description = rs.getString("description");

                String[] images = arrToStr(rs.getArray("images"));

                String fabricType = rs.getString("fabric_type");

                String[] sizeAndFit = arrToStr(rs.getArray("size_and_fit"));

                Clothes clothing = Clothes.builder()
                                .id(id)
                                .gender(gender)
                                .price(price)
                                .title(title)
                                .description(description)
                                .images(images)
                                .fabricType(fabricType)
                                .sizeAndFit(sizeAndFit)
                                .build();
                clothes.add(clothing);
            }
            return clothes;
        }catch (SQLException e){
            System.out.println("SQL exception (C_parseRS()): " + e.getMessage());
        }
        return null;
    }

    @Override
    public String[] arrToStr(Array arr){
        try{
            String[] result = null;
            if(arr != null){
                result = (String[]) arr.getArray();
            }
            return result;
        }catch (SQLException e){
            System.out.println("SQL exception (C_arrToStr()): " + e.getMessage());
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
            System.out.println("SQL exception when closing connection (clothesRepository): " + e.getMessage());
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
            System.out.println("SQL exception when executing statement (clothesRepository): " + e.getMessage());
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
            System.out.println("SQL exception when executing query (clothesRepository): " + e.getMessage());
            closeResultSet(rs);
        }finally {
            closeConnection(con);
        }
        return rs;
    }
}
