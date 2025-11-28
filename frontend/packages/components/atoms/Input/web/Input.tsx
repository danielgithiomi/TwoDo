import { FC } from "react";
import { InputProps } from "../Input.types";
import { useFormContext } from "react-hook-form";

export const Input: FC<InputProps> = ({
  id,
  name,
  label,
  placeholder,
  ...rest
}) => {
  const {
    register,
    formState: { errors },
  } = useFormContext();

  const error = errors[name];

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
