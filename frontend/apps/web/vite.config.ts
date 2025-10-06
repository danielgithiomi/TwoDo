import path from "path";
import {defineConfig} from "vite";
import react from "@vitejs/plugin-react";
import pathAliases from "./path_aliases";

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
            "@tdw/api": pathResolver(webAliases.api),
            "@tdw/atoms": pathResolver(webAliases.atoms),
            "@tdw/images": pathResolver(webAliases.images),
            "@tdw/constants": pathResolver(webAliases.constants),
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
