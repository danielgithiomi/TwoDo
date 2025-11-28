import { ReactNode } from "react";
import { InputHTMLAttributes } from "react";
import { SignUpFormValues } from "@tdp/types";
import { SubmitHandler, UseFormReturn } from "react-hook-form";

export interface FormProps<TFormValues extends Record<string, string>>
  extends InputHTMLAttributes<HTMLInputElement> {
  className?: string;
  children: ReactNode;
  methods: UseFormReturn<TFormValues>;
  onZodSubmit: SubmitHandler<TFormValues>;
}
