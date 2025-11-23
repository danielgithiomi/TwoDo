import { FC } from "react";
import { ButtonProps } from "../Button.types";

export const Button: FC<ButtonProps> = ({
  id,
  label,
  onClick,
  className,
  ...rest
}) => {
  return (
    <button className={className} id={id} onClick={onClick} {...rest}>
      {label}
    </button>
  );
};
