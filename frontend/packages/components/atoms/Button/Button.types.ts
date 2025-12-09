import { ButtonHTMLAttributes } from "react";

export type ButtonVariant = "primary" | "secondary";
export type ButtonSize = "small" | "medium" | "large";
export type ButtonLoadingVariant = "spinner" | "text" | "composite";

export interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  id: string;
  label: string;
  loading?: boolean;
  size?: ButtonSize;
  className?: string;
  disabled?: boolean;
  loadingLabel?: string;
  variant?: ButtonVariant;
  loadingVariant?: ButtonLoadingVariant;

  // Callback function
  onClick?: () => void;
}

export const BUTTON_BACKGROUND_MAP: Record<ButtonVariant, string> = {
  primary: "bg-[var(--color-primary)] text-white",
  secondary: "bg-[var(--color-secondary)] text-white",
};

export const BUTTON_SIZE_MAP: Record<ButtonSize, string> = {
  small: "py-1 px-2",
  medium: "py-2 px-4",
  large: "py-3 px-6",
};
