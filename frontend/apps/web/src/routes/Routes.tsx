import { RoutePaths } from "./RoutePaths";
import { Home, SignUp, Login } from "@pages";
import type { RouteObject } from "react-router-dom";

export const AppRoutes: RouteObject[] = [
    {
        index: true,
        element: <Home />,
        path: RoutePaths.Home
    },
    {
        path: RoutePaths.Login,
        element: <Login />
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