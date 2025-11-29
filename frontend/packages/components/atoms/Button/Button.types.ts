import { ButtonHTMLAttributes } from "react";

export type ButtonVariant = "primary" | "secondary";
export type ButtonSize = "small" | "medium" | "large";

export interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    id: string;
    label: string;
    loading?: boolean;
    size?: ButtonSize;
    className?: string;
    disabled?: boolean;
    variant?: ButtonVariant;

    // Callback function
    onClick?: () => void;
}

export const BUTTON_BACKGROUND_MAP: Record<ButtonVariant, string> = {
    primary: "bg-[#F13F31]",
    secondary: "bg-gray-600",
}

export const BUTTON_SIZE_MAP: Record<ButtonSize, string> = {
    small: "p-2",
    medium: "p-4",
    large: "p-6",
}
