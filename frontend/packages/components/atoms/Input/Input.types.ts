import { InputHTMLAttributes } from "react";
import {
  Merge,
  FieldError,
  FieldErrorsImpl,
  UseFormRegister,
} from "react-hook-form";

export interface InputProps extends FormInputProps {
  register: UseFormRegister<any>;
  error?: FieldError | Merge<FieldError, FieldErrorsImpl<any>>;
}

export interface FormInputProps extends InputHTMLAttributes<HTMLInputElement> {
  id: string;
  name: string;
  label: string;
  placeholder: string;
}
