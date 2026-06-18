export interface Load {
  loadId:      number;
  origin:      string;
  destination: string;
  cargoType:   string;
  weightTons:  number;
  distanceKm:  number;
  quotedRate:  number;
  createdAt:   string;
}