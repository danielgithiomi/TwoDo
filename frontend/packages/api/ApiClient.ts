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
    if (retry) console.log("Retrying the API call");
    else throw await response.json().catch(() => ({}));
  }

  return await response.json();
}
