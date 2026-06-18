package com.rpc.dao;

import com.rpc.model.CostInput;
import com.rpc.util.DBConnection;

import java.sql.*;

public class CostDAO{

    // Save cost input for a load
    public boolean saveCostInput(CostInput cost) {
        String sql = "INSERT INTO cost_inputs (load_id, fuel_rate, mileage_kmpl, toll_charges, driver_allowance) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,    cost.getLoadId());
            ps.setDouble(2, cost.getFuelRate());
            ps.setDouble(3, cost.getMileageKmpl());
            ps.setDouble(4, cost.getTollCharges());
            ps.setDouble(5, cost.getDriverAllowance());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get cost input by load ID (latest entry)
    public CostInput getCostByLoadId(int loadId) {
        String sql = "SELECT * FROM cost_inputs WHERE load_id = ? ORDER BY cost_id DESC LIMIT 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, loadId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CostInput c = new CostInput();
                c.setCostId(rs.getInt("cost_id"));
                c.setLoadId(rs.getInt("load_id"));
                c.setFuelRate(rs.getDouble("fuel_rate"));
                c.setMileageKmpl(rs.getDouble("mileage_kmpl"));
                c.setTollCharges(rs.getDouble("toll_charges"));
                c.setDriverAllowance(rs.getDouble("driver_allowance"));
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
