import { FC } from "react";
import { InputProps } from "../Input.types";

export const Input: FC<InputProps> = ({
  id,
  name,
  label,
  error,
  register,
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
        className="form-field-component"
        {...rest}
      />
      {error && (
        <p className="form-field-error-message">{error.message?.toString()}</p>
      )}
    </div>
  );
};
