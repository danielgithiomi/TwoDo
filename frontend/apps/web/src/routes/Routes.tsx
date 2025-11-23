import { Home } from "../pages/Home";
import type { ReactNode } from "react";
import { RoutePaths } from "./RoutePaths";
import type { RoutePath } from "./Routes.types";
import type { RouteObject } from "react-router-dom";

export interface AppRoute {
    path: RoutePath;
    element: ReactNode;
}

export const AppRoutes: RouteObject[] = [
    {
        path: RoutePaths.Home,
        element: <Home />
    },
    {
        path: RoutePaths.Login,
        element: <div>Login</div>
    },
    {
        path: RoutePaths.NotFound,
        element: <div>404</div>
    },
    {
        path: RoutePaths.SignUp,
        element: <div>Sign Up</div>
    }
]