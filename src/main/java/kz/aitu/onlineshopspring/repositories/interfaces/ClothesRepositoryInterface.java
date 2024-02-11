package kz.aitu.onlineshopspring.repositories.interfaces;

import kz.aitu.onlineshopspring.models.products.Clothes;

import javax.naming.ldap.PagedResultsControl;
import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface ClothesRepositoryInterface {
    boolean addClothing(Clothes clothing);
    boolean updateClothing(Clothes clothing);
    boolean deleteClothing(int id);
    Clothes getClothing(int id);
    List<Clothes> getClothesByGender(int gender);
    List<Clothes> getClothesByPrice(double max);
    List<Clothes> getClothesByPrice(double min, double max);
    List<Clothes> getAllClothes();
    List<Clothes> parseRS(ResultSet rs);
    String[] arrToStr(Array arr);
    void closeConnection(Connection con);
    boolean executeStatement(PreparedStatement st);
    boolean executeStatement(PreparedStatement st, int rsType);
    ResultSet executeQuery(PreparedStatement st);
    ResultSet executeQuery(PreparedStatement st, int rsType);
}
