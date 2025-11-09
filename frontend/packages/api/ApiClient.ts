import { API_BASE_URL } from "@tdp/constants";

export async function ApiClient<T>(
  endpoint: string,
  retry: boolean = true,
  options: RequestInit = {}
): Promise<T> {
  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    ...options,
    credentials: "include",
    headers: {
      ...options.headers,
      "Content-Type": "application/json",
    },
  });

  if (!response.ok) {
    console.log("Error at the API client");

    if (retry) console.log("Retrying the API call");
  }

  return await response.json();
}
