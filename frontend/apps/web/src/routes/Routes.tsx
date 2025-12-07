import { RootLayout } from "@layouts";
import { RoutePaths } from "./RoutePaths";
import type { RouteObject } from "react-router-dom";
import { Home, SignUp, Login, UserProfile } from "@pages";

export const AppRoutes: RouteObject[] = [
  {
    element: <RootLayout />,
    children: [
      {
        index: true,
        element: <Home />,
        path: RoutePaths.Home,
      },
      {
        path: RoutePaths.Login,
        element: <Login />,
      },
      {
        path: RoutePaths.NotFound,
        element: <div>404</div>,
      },
      {
        path: RoutePaths.Profile,
        element: <UserProfile />,
      },
      {
        path: RoutePaths.Register,
        element: <SignUp />,
      },
    ],
  },
];
