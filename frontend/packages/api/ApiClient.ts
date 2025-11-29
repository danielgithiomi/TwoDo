import { API_BASE_URL } from "@tdp/constants";
import { RetrieveAuthToken } from "@tdp/libs";

export async function ApiClient<T>(
  endpoint: string,
  retry: boolean = true,
  options: RequestInit = {},
  includeToken: boolean = true
): Promise<T> {
  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    ...options,
    credentials: "include",
    headers: {
      ...options.headers,
      "Content-Type": "application/json",
      ...(includeToken
        ? { Authorization: `Bearer ${RetrieveAuthToken()}` }
        : {}),
    },
  });

  if (!response.ok) {
    if (retry) console.log("Retrying the API call");
    else throw await response.json().catch(() => ({}));
  }

  return await response.json();
}
