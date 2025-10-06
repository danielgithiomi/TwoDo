import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
      "@tdw/images": path.resolve(__dirname, "./public/assets/images"),
      "@tdw/atoms": path.resolve(__dirname, "./../../packages/components/atoms"),
    }
  },
  server: {
    fs: {
      allow: [".."]
    }
  },
  publicDir: "public",
  build: {
    assetsDir: "assets",
    copyPublicDir: true
  }
});
