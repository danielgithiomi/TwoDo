// export const API_BASE_URL: string = import.meta.env.VITE_DEV_API_BASE_URL || "http://localhost:8080/api/v1";
// export const API_USERNAME: string = import.meta.env.VITE_DEV_API_USERNAME;
// export const API_PASSWORD: string = import.meta.env.VITE_DEV_API_PASSWORD;

export const API_BASE_URL: string = "http://localhost:8080/api/v1";
export const API_USERNAME: string = "admin";
export const API_PASSWORD: string = "admin123";

if (!API_BASE_URL || !API_USERNAME || !API_PASSWORD) {
  throw new Error(
    "API_BASE_URL, API_USERNAME and API_PASSWORD must be defined in the environment variables"
  );
}

export const AUTH_TOKEN_STORAGE_KEY: string = "auth_token";
export const AUTH_LOGGED_IN_USER_STORAGE_KEY: string = "logged_in_user";
