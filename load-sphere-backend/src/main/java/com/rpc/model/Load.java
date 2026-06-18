package com.rpc.model;

public class Load {
    private int    loadId;
    private String origin;
    private String destination;
    private String cargoType;
    private double weightTons;
    private double distanceKm;
    private double quotedRate;

    public Load() {}

    public Load(int loadId, String origin, String destination,
                String cargoType, double weightTons, double distanceKm, double quotedRate) {
        this.loadId      = loadId;
        this.origin      = origin;
        this.destination = destination;
        this.cargoType   = cargoType;
        this.weightTons  = weightTons;
        this.distanceKm  = distanceKm;
        this.quotedRate  = quotedRate;
    }

    public int    getLoadId()                  { return loadId; }
    public void   setLoadId(int id)            { this.loadId = id; }
    public String getOrigin()                  { return origin; }
    public void   setOrigin(String o)          { this.origin = o; }
    public String getDestination()             { return destination; }
    public void   setDestination(String d)     { this.destination = d; }
    public String getCargoType()               { return cargoType; }
    public void   setCargoType(String c)       { this.cargoType = c; }
    public double getWeightTons()              { return weightTons; }
    public void   setWeightTons(double w)      { this.weightTons = w; }
    public double getDistanceKm()              { return distanceKm; }
    public void   setDistanceKm(double d)      { this.distanceKm = d; }
    public double getQuotedRate()              { return quotedRate; }
    public void   setQuotedRate(double r)      { this.quotedRate = r; }
}
