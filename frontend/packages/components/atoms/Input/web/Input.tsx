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
    <div className="flex flex-col">
      {label && (
        <label className="text-start" htmlFor={id}>
          {label}
        </label>
      )}
      <input
        id={id}
        placeholder={placeholder}
        type="text"
        {...register(name)}
        className="p-1 my-2 rounded-md border"
        {...rest}
      />
      {error && (
        <p className="text-red-500 text-sm">{error.message?.toString()}</p>
      )}
    </div>
  );
};
