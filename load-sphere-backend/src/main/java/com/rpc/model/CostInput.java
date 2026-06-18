package com.rpc.model;

public class CostInput {
    private int    costId;
    private int    loadId;
    private double fuelRate;
    private double mileageKmpl;
    private double tollCharges;
    private double driverAllowance;

    public CostInput() {}

    public CostInput(int costId, int loadId, double fuelRate,
                     double mileageKmpl, double tollCharges, double driverAllowance) {
        this.costId          = costId;
        this.loadId          = loadId;
        this.fuelRate        = fuelRate;
        this.mileageKmpl     = mileageKmpl;
        this.tollCharges     = tollCharges;
        this.driverAllowance = driverAllowance;
    }

    public int    getCostId()                       { return costId; }
    public void   setCostId(int id)                 { this.costId = id; }
    public int    getLoadId()                       { return loadId; }
    public void   setLoadId(int id)                 { this.loadId = id; }
    public double getFuelRate()                     { return fuelRate; }
    public void   setFuelRate(double r)             { this.fuelRate = r; }
    public double getMileageKmpl()                  { return mileageKmpl; }
    public void   setMileageKmpl(double m)          { this.mileageKmpl = m; }
    public double getTollCharges()                  { return tollCharges; }
    public void   setTollCharges(double t)          { this.tollCharges = t; }
    public double getDriverAllowance()              { return driverAllowance; }
    public void   setDriverAllowance(double d)      { this.driverAllowance = d; }
}
