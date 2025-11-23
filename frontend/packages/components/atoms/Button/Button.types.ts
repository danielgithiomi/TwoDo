import { ButtonHTMLAttributes } from "react";

export interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    id: string;
    label: string;
    loading?: boolean;
    className?: string;
    disabled?: boolean;
    variant?: "primary" | "secondary";
    size?: "small" | "medium" | "large";

    // Callback function
    onClick?: () => void;
}