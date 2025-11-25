import { FC } from "react";
import { InputProps } from "../Input.types";

export const Input: FC<InputProps> = ({ id, name, placeholder, label, ...rest }) => {
  return (
    <div className="flex flex-col">
      {label && (
        <label className="text-start" htmlFor={id}>
          {label}
        </label>
      )}
      <input
        id={id}
        name={name}
        placeholder={placeholder}
        type="text"
        {...rest}
        className="p-1 my-2 rounded-md border"
      />
    </div>
  );
};
