import { Header } from "@layouts";
import { Outlet } from "react-router-dom";

export const RootLayout = () => {
  return (
    <main className="flex flex-col min-h-screen">
      <Header />
      <section className="flex-1 grid">
        <Outlet />
      </section>
    </main>
  );
};
