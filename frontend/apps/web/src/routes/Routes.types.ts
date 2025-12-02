import type { RoutePaths } from "./RoutePaths";

export type RouteDestination = "Home" | "Login" | "Register" | "NotFound";

export type RoutePath = (typeof RoutePaths)[keyof typeof RoutePaths];
