import { FC } from "react";
import { cn } from "@tdp/libs";
import { Spinner } from "@tdp/components/atoms";
import {
  ButtonProps,
  BUTTON_SIZE_MAP,
  BUTTON_BACKGROUND_MAP,
} from "../Button.types";

export const Button: FC<ButtonProps> = ({
  id,
  label,
  onClick,
  className,
  loading = false,
  size = "medium",
  disabled = false,
  variant = "primary",
  ...rest
}) => {
  const buttonSizeClasses = BUTTON_SIZE_MAP[size];
  const buttonBackgroundClasses = BUTTON_BACKGROUND_MAP[variant];

  const buttonClasses = cn(
    "base-button-component",
    buttonSizeClasses,
    buttonBackgroundClasses,
    {
      "opacity-75 cursor-not-allowed hover:opacity-75": disabled || loading,
    },
    className
  );

  function LoadingLayout() {
    return (
      <div className="absolute z-1 inset-0 bg-transparent flex items-center justify-center">
        <Spinner />
      </div>
    );
  }

  function ButtonContent() {
    return (
      <div
        className={cn("w-full h-full flex items-center justify-center", {
          invisible: loading,
        })}
      >
        <span>{label}</span>
      </div>
    );
  }

  return (
    <button
      id={id}
      onClick={onClick}
      disabled={disabled}
      className={buttonClasses}
      {...rest}
    >
      <ButtonContent />
      {loading && <LoadingLayout />}
    </button>
  );
};
