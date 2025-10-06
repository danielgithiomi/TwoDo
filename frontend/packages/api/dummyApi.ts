import { API_BASE_URL } from "../constants/api.constants";

export async function getHelloWorld() {
    const response = await fetch(`${API_BASE_URL}/test`);
    return await response.json();
}