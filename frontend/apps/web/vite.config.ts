import path from "path";
import { defineConfig } from "vite";
import tailwindcss from "@tailwindcss/vite";
import { pathAliases } from "../../packages/constants";

function pathResolver(alias: string) {
  return path.resolve(__dirname, alias);
}

const webAliases = pathAliases.web;

// https://vite.dev/config/
export default defineConfig({
  plugins: [tailwindcss()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
      "@tdw/atoms": pathResolver(webAliases.atoms),
      "@tdw/images": pathResolver(webAliases.images),

      // Packages (@tdp)
      "@tdp/api": pathResolver(webAliases.api),
      "@tdp/types": pathResolver(webAliases.types),
      "@tdp/stores": pathResolver(webAliases.stores),
      "@tdp/constants": pathResolver(webAliases.constants),
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
