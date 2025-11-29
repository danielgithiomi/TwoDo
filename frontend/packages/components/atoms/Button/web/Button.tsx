import { FC } from "react";
import { cn } from "@tdp/libs";
import {
  ButtonProps,
  BUTTON_SIZE_MAP,
  BUTTON_BACKGROUND_MAP,
} from "../Button.types";

export const Button: FC<ButtonProps> = ({
  id,
  label,
  onClick,
  size = "medium",
  variant = "primary",
  className,
  ...rest
}) => {
  const buttonClasses = cn(
    "cursor-pointer hover:opacity-90 transition-opacity rounded-sm",
    BUTTON_SIZE_MAP[size],
    BUTTON_BACKGROUND_MAP[variant],
    className
  );

  return (
    <button className={buttonClasses} id={id} onClick={onClick} {...rest}>
      {label}
    </button>
  );
};
