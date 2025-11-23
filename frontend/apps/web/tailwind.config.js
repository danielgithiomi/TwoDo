import path from "path";

/** @type {import('tailwindcss').Config} */
export default {
  content: [
    path.join(__dirname, "./index.html"),
    path.join(__dirname, "./src/**/*.{js,ts,jsx,tsx}"),

    // Scan shared packages correctly
    path.join(__dirname, "../../packages/libs/**/*.{js,ts,jsx,tsx}"),
    path.join(__dirname, "../../packages/components/**/*.{js,ts,jsx,tsx}"),
  ],
  theme: {
    extend: {},
  },
  plugins: [],
};
