import { InputHTMLAttributes } from "react";
import {
  Merge,
  FieldError,
  FieldErrorsImpl,
  UseFormRegister,
} from "react-hook-form";

export interface SelectProps extends FormSelectProps {
  register: UseFormRegister<any>;
  error?: FieldError | Merge<FieldError, FieldErrorsImpl<any>>;
}

export interface FormSelectProps extends InputHTMLAttributes<HTMLSelectElement> {
  id: string;
  name: string;
  label: string;
  className?: string;
  options: Array<SelectOption>;
  includeSelectOption?: boolean;
}

export interface SelectOption {
    value: string | number;
    label: string;
}
