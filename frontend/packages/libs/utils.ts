import classNames from "classnames";
import { twMerge } from "tailwind-merge";
import { AUTH_TOKEN_STORAGE_KEY as token_key } from "@tdp/constants";

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
 * Auth Tokens
 * -----------------------------------------------------------------------------------------------*/
export function SaveAuthToken(token: string) {
  localStorage.setItem(token_key, token);
}

export function RetrieveAuthToken(): string {
  const token = localStorage.getItem(token_key);
  if (!token) {
    console.warn("No auth token found");
    throw new Error("No auth token found");
  }
  return token;
}
