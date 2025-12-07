import { FC } from "react";
import { cn } from "@tdp/libs";
import { type SelectProps } from "./../Select.types";

export const Select: FC<SelectProps> = ({
  id,
  name,
  label,
  error,
  options,
  register,
  className,
  includeSelectOption,
  ...rest
}) => {
  return (
    <div className={cn("form-field-component-wrapper", className)}>
      {label && (
        <label className="text-start" htmlFor={name}>
          {label}
        </label>
      )}

      <select
        id={id}
        {...register(name)}
        
        className={cn("form-field-component", {
          "form-field-error": error,
        })}
        {...rest}
      >
        {options.map((option, index) => (
          <option key={index} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>

      {error && (
        <p className="form-field-error-message">{error.message?.toString()}</p>
      )}
    </div>
  );
};
