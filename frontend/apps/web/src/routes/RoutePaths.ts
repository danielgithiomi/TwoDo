import type { RouteDestination } from "./Routes.types";

export const RoutePaths: Record<RouteDestination, string> = {
  Home: "/",
  Login: "/login",
  SignUp: "/sign-up",
  NotFound: "*",
} as const;
