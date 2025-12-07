import {FC} from "react";
import {cn} from "@tdp/libs";
import {BUTTON_BACKGROUND_MAP, BUTTON_SIZE_MAP, ButtonProps,} from "../Button.types";

export const Button: FC<ButtonProps> = ({
    id,
    label,
    onClick,
    loading = false,
    size = "medium",
    disabled = false,
    variant = "primary",
    className,
    ...rest
}) => {
    const buttonClasses = cn(
        "cursor-pointer hover:opacity-90 transition-opacity rounded-sm",
        BUTTON_SIZE_MAP[size],
        BUTTON_BACKGROUND_MAP[variant],
        {
            "opacity-50 cursor-not-allowed": disabled || loading,
        },
        className
    );

    return (
        <button
            id={id}
            onClick={onClick}
            disabled={disabled}
            className={buttonClasses}
            {...rest}
        >
            {loading ? "Loading..." : label}
        </button>
    );
};
