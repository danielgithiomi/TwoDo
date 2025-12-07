import classNames from "classnames";
import { twMerge } from "tailwind-merge";
import {
  AUTH_TOKEN_STORAGE_KEY as token_key,
  AUTH_LOGGED_IN_USER_STORAGE_KEY as user_key,
} from "@tdp/constants";
import { UserResponseDTO as User } from "types/api";

/* -------------------------------------------------------------------------------------------------
 * Merge class names
 * -----------------------------------------------------------------------------------------------*/
/**
 * function to merge class names to override default styles with custom styles
 * @param inputs - The class names to merge
 * @returns The merged class names
 */
export function cn(...inputs: Parameters<typeof classNames>) {
  return twMerge(classNames(...inputs));
}

/* -------------------------------------------------------------------------------------------------
 * Omit
 * -----------------------------------------------------------------------------------------------*/
/**
 * function to omit keys from an object
 * @param obj - The object to omit keys from
 * @param keys - The keys to omit
 * @returns The object with the specified keys omitted
 */
export function omitFromObject<
  T extends Record<string, any>,
  K extends keyof T
>(obj: T, keys: K[]): Omit<T, K> {
  const clone = { ...obj };
  for (const key of keys) {
    delete clone[key];
  }
  return clone;
}

/* -------------------------------------------------------------------------------------------------
 * Authentication Local Storage
 * -----------------------------------------------------------------------------------------------*/
export function StoreJwtToken(token: string) {
  localStorage.setItem(token_key, token);
}

export function StoreLoggedInUser(user: User) {
  localStorage.setItem(user_key, JSON.stringify(user));
}

export function RetrieveAuthenticationToken(): string {
  const token = localStorage.getItem(token_key);
  if (!token) throw new Error("No auth token found");
  return token;
}

export function RetrieveLoggedInUser() {
  const user = localStorage.getItem(user_key);
  if (!user) {
    localStorage.removeItem(token_key);
    console.warn("No logged in user found");
    throw new Error("No logged in user found");
  }
  return user;
}

export function ClearStoredAuthentication() {
  localStorage.removeItem(user_key);
  localStorage.removeItem(token_key);
}

export function isAuthenticated(): boolean {
  const token = localStorage.getItem(token_key);
  return !!token;
}
