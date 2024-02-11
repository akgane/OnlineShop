package kz.aitu.onlineshopspring.repositories.interfaces;

import kz.aitu.onlineshopspring.models.products.Accessories;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface AccessoriesRepositoryInterface {
    boolean addAccessory(Accessories accessory);
    boolean updateAccessory(Accessories accessory);
    boolean deleteAccessory(int id);
    Accessories getAccessory(int id);
    List<Accessories> getAccessoriesByGender(int gender);
    List<Accessories> getAccessoriesByPrice(double max);
    List<Accessories> getAccessoriesByPrice(double min, double max);
    List<Accessories> getAllAccessories();
    List<Accessories> parseRS(ResultSet rs);
    String[] arrToStr(Array arr);
    void closeConnection(Connection con);
    boolean executeStatement(PreparedStatement st);
    boolean executeStatement(PreparedStatement st, int rsType);
    ResultSet executeQuery(PreparedStatement st);
    ResultSet executeQuery(PreparedStatement st, int rsType);
}
