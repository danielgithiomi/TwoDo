import { type FC } from "react";
import { Select } from "./Select";
import { useFormContext } from "react-hook-form";
import type { FormSelectProps } from "../Select.types";

export const FormSelect: FC<FormSelectProps> = ({
  id,
  name,
  label,
  options,
  className,
  includeSelectOption = true,
}) => {
  const {
    register,
    formState: { errors },
  } = useFormContext();

  const selectError = errors[name];

  return (
    <Select
      id={id}
      label={label}
      name={name}
      options={options}
      register={register}
      error={selectError}
      className={className}
      includeSelectOption={includeSelectOption}
    />
  );
};
