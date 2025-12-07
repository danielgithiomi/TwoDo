import { AppRoutes } from "./routes";
import { RouterProvider, createBrowserRouter } from "react-router-dom";

const router = createBrowserRouter(AppRoutes);

function App() {
  return (
    <RouterProvider router={router} />
  );
}

export default App;
