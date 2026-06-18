package com.rpc.dao;

import com.rpc.model.Load;
import com.rpc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoadDAO {

    // Get all available loads
    public List<Load> getAllLoads() {
        List<Load> loads = new ArrayList<>();
        String sql = "SELECT * FROM loads ORDER BY load_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                loads.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loads;
    }

    // Get a single load by ID
    public Load getLoadById(int loadId) {
        String sql = "SELECT * FROM loads WHERE load_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, loadId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Admin: Add a new load
    public boolean addLoad(Load load) {
        String sql = "INSERT INTO loads (origin, destination, cargo_type, weight_tons, distance_km, quoted_rate) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, load.getOrigin());
            ps.setString(2, load.getDestination());
            ps.setString(3, load.getCargoType());
            ps.setDouble(4, load.getWeightTons());
            ps.setDouble(5, load.getDistanceKm());
            ps.setDouble(6, load.getQuotedRate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Admin: Delete a load
    public boolean deleteLoad(int loadId) {
        String sql = "DELETE FROM loads WHERE load_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, loadId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Load mapRow(ResultSet rs) throws SQLException {
        Load l = new Load();
        l.setLoadId(rs.getInt("load_id"));
        l.setOrigin(rs.getString("origin"));
        l.setDestination(rs.getString("destination"));
        l.setCargoType(rs.getString("cargo_type"));
        l.setWeightTons(rs.getDouble("weight_tons"));
        l.setDistanceKm(rs.getDouble("distance_km"));
        l.setQuotedRate(rs.getDouble("quoted_rate"));
        return l;
    }
}
