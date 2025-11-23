import classNames from "classnames";
import { twMerge } from "tailwind-merge";

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
