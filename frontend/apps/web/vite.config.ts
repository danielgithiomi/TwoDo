import path from 'path';
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
      "@tdw/images": path.resolve(__dirname, './public/assets/images'),
    },
  },
  server: {
    fs: {
      allow: ['..'],
    },
  },
  publicDir: 'public',
  build: {
    assetsDir: 'assets',
    copyPublicDir: true,
  },
});
