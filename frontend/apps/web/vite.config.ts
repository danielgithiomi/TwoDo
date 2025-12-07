import path from "path";
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import tailwindcss from "@tailwindcss/vite";
import { pathAliases } from "../../packages/constants";

function pathResolver(alias: string) {
  return path.resolve(__dirname, alias);
}

const webAliases = pathAliases.web;

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  resolve: {
    alias: {
      "react": path.resolve(__dirname, "../../../node_modules/react"),
      "react-dom": path.resolve(__dirname, "../../../node_modules/react-dom"),

      "@": path.resolve(__dirname, "./src"),
      "@pages": pathResolver(webAliases.pages),
      "@routes": pathResolver(webAliases.routes),
      "@layouts": pathResolver(webAliases.layouts),
      "@tdw/atoms": pathResolver(webAliases.atoms),
      "@tdw/images": pathResolver(webAliases.images),
      "@tdw/molecules": pathResolver(webAliases.molecules),

      // Packages (@tdp)
      "@tdp/api": pathResolver(webAliases.api),
      "@tdp/libs": pathResolver(webAliases.libs),
      "@tdp/hooks": pathResolver(webAliases.hooks),
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
