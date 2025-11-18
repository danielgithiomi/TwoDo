import path from "path";
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import { pathAliases } from "../../packages/constants";

function pathResolver(alias: string) {
  return path.resolve(__dirname, alias);
}

const webAliases = pathAliases.web;

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
      "@tdw/atoms": pathResolver(webAliases.atoms),
      "@tdw/images": pathResolver(webAliases.images),

      // Packages (@tdp)
      "@tdw/api": pathResolver(webAliases.api),
      "@tdw/types": pathResolver(webAliases.types),
      "@tdw/stores": pathResolver(webAliases.stores),
      "@tdw/constants": pathResolver(webAliases.constants),
    },
  },
  server: {
    fs: {
      allow: [".."],
    },
  },
  publicDir: "public",
  build: {
    assetsDir: "assets",
    copyPublicDir: true,
  },
});
