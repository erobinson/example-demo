import { Agency } from "./agency.model";

export interface Route {
  primaryKey: string;
  agency: Agency;
  routeId: string;
  routeLabel: string;
}
