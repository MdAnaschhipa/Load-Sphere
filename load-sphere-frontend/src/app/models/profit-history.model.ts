export interface ProfitHistory {
  historyId:   number;
  userId:      number;
  loadId:      number;
  totalCost:   number;
  profitAmt:   number;
  decision:    string;
  tripDate:    string;
  origin:      string;
  destination: string;
  quotedRate:  number;
}