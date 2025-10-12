import { API_BASE_URL, API_TOKEN } from "@tdp/constants";

const headers: RequestInit["headers"] = {
  "Content-Type": "application/json",
  Authorization: `Basic ${API_TOKEN}`,
};

export async function getActiveUsers() {
  const endpoint = `${API_BASE_URL}/users`;

  const response = await fetch(endpoint, { headers });
  return response.json();
}
