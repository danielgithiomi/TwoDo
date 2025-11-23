import { FC } from "react";
import { cn } from "@tdp/libs";
import { ButtonProps } from "../Button.types";

export const Button: FC<ButtonProps> = ({
  id,
  label,
  onClick,
  className,
  ...rest
}) => {
  return (
    <button className={cn("bg-red-600", className)} id={id} onClick={onClick} {...rest}>
      {label}
    </button>
  );
};
