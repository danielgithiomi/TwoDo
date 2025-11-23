import type { RoutePaths } from "./RoutePaths";

export type RouteDestination = "Home" | "Login" | "NotFound";

export type RoutePath = (typeof RoutePaths)[keyof typeof RoutePaths];
