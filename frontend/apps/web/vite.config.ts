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
      "react": path.resolve(__dirname, "../../../node_modules/react"),
      "react-dom": path.resolve(__dirname, "../../../node_modules/react-dom"),

      "@": path.resolve(__dirname, "./src"),
      "@pages": pathResolver(webAliases.pages),
      "@tdw/atoms": pathResolver(webAliases.atoms),
      "@tdw/images": pathResolver(webAliases.images),
      "@tdw/molecules": pathResolver(webAliases.molecules),

      // Packages (@tdp)
      "@tdp/api": pathResolver(webAliases.api),
      "@tdp/libs": pathResolver(webAliases.libs),
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
