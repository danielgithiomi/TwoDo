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
  loadingVariant = "spinner",
  loadingLabel = "Loading...",
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
    const isTextLoader = loadingVariant === "text";
    const isSpinnerLoader = loadingVariant === "spinner";
    const isCompositeLoader = loadingVariant === "composite";

    return (
      <div className="absolute inset-0 flex flex-row gap-2 items-center justify-center">
        {(isCompositeLoader || isSpinnerLoader) && <Spinner />}
        {(isCompositeLoader || isTextLoader) && (<span className="text-sm">{loadingLabel}</span>)}
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
