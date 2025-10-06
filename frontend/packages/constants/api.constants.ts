export const API_BASE_URL: string = import.meta.env.VITE_DEV_API_BASE_URL;
export const API_USERNAME: string = import.meta.env.VITE_DEV_API_USERNAME;
export const API_PASSWORD: string = import.meta.env.VITE_DEV_API_PASSWORD;
export const API_TOKEN: string = btoa(`${API_USERNAME}:${API_PASSWORD}`);

if (!API_BASE_URL || !API_USERNAME || !API_PASSWORD || !API_TOKEN) {
  throw new Error(
    "API_BASE_URL, API_USERNAME, API_PASSWORD, and API_TOKEN must be defined in the environment variables"
  );
} else {
  console.log("API_BASE_URL: ", API_BASE_URL);
  console.log("API_USERNAME: ", API_USERNAME);
  console.log("API_PASSWORD: ", API_PASSWORD);
  console.log("API_TOKEN: ", API_TOKEN);
}
