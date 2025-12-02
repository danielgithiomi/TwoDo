import type { RouteDestination } from "./Routes.types";

export const RoutePaths: Record<RouteDestination, string> = {
  Home: "/",
  Login: "/login",
  Register: "/register",
  NotFound: "*",
} as const;
