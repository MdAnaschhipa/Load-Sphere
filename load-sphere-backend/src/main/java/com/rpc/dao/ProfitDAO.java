package com.rpc.dao;

import com.rpc.model.ProfitHistory;
import com.rpc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfitDAO {

    // Save a profit/loss record
    public boolean saveProfit(ProfitHistory history) {
        String sql = "INSERT INTO profit_history (user_id, load_id, total_cost, profit_amt, decision, trip_date) " +
                     "VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,    history.getUserId());
            ps.setInt(2,    history.getLoadId());
            ps.setDouble(3, history.getTotalCost());
            ps.setDouble(4, history.getProfitAmt());
            ps.setString(5, history.getDecision());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all profit history for a user
    public List<ProfitHistory> getHistoryByUser(int userId) {
        List<ProfitHistory> list = new ArrayList<>();
        String sql = "SELECT ph.*, l.origin, l.destination, l.quoted_rate " +
                     "FROM profit_history ph " +
                     "JOIN loads l ON ph.load_id = l.load_id " +
                     "WHERE ph.user_id = ? " +
                     "ORDER BY ph.trip_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get all history (admin view)
    public List<ProfitHistory> getAllHistory() {
        List<ProfitHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM profit_history ORDER BY trip_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private ProfitHistory mapRow(ResultSet rs) throws SQLException {
        ProfitHistory h = new ProfitHistory();
        h.setHistoryId(rs.getInt("history_id"));
        h.setUserId(rs.getInt("user_id"));
        h.setLoadId(rs.getInt("load_id"));
        h.setTotalCost(rs.getDouble("total_cost"));
        h.setProfitAmt(rs.getDouble("profit_amt"));
        h.setDecision(rs.getString("decision"));
        h.setTripDate(rs.getTimestamp("trip_date"));
        return h;
    }
}
