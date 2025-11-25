import { Home, SignUp } from "@pages";
import { RoutePaths } from "./RoutePaths";
import type { RouteObject } from "react-router-dom";

export const AppRoutes: RouteObject[] = [
    {
        index: true,
        element: <Home />,
        path: RoutePaths.Home
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
        element: <SignUp />
    }
]