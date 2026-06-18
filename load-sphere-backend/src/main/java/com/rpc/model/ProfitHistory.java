package com.rpc.model;

import java.sql.Timestamp;

public class ProfitHistory {
    private int       historyId;
    private int       userId;
    private int       loadId;
    private double    totalCost;
    private double    profitAmt;
    private String    decision;   // "Accept" or "Reject"
    private Timestamp tripDate;

    public ProfitHistory() {}

    public ProfitHistory(int historyId, int userId, int loadId,
                         double totalCost, double profitAmt, String decision, Timestamp tripDate) {
        this.historyId = historyId;
        this.userId    = userId;
        this.loadId    = loadId;
        this.totalCost = totalCost;
        this.profitAmt = profitAmt;
        this.decision  = decision;
        this.tripDate  = tripDate;
    }

    public int       getHistoryId()              { return historyId; }
    public void      setHistoryId(int id)        { this.historyId = id; }
    public int       getUserId()                 { return userId; }
    public void      setUserId(int id)           { this.userId = id; }
    public int       getLoadId()                 { return loadId; }
    public void      setLoadId(int id)           { this.loadId = id; }
    public double    getTotalCost()              { return totalCost; }
    public void      setTotalCost(double c)      { this.totalCost = c; }
    public double    getProfitAmt()              { return profitAmt; }
    public void      setProfitAmt(double p)      { this.profitAmt = p; }
    public String    getDecision()               { return decision; }
    public void      setDecision(String d)       { this.decision = d; }
    public Timestamp getTripDate()               { return tripDate; }
    public void      setTripDate(Timestamp t)    { this.tripDate = t; }
}
