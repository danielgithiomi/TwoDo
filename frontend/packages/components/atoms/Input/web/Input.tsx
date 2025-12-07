import { FC } from "react";
import { cn } from "@tdp/libs";
import { InputProps } from "../Input.types";

export const Input: FC<InputProps> = ({
  id,
  name,
  label,
  error,
  register,
  className,
  placeholder,
  ...rest
}) => {
  return (
    <div className="form-field-component-wrapper">
      {label && (
        <label className="text-start" htmlFor={id}>
          {label}
        </label>
      )}
      <input
        id={id}
        type="text"
        {...register(name)}
        placeholder={placeholder}
        className={cn(
          "form-field-component",
          {
            "form-field-error": error,
          },
          className
        )}
        {...rest}
      />
      {error && (
        <p className="form-field-error-message">{error.message?.toString()}</p>
      )}
    </div>
  );
};
