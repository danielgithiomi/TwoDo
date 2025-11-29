import { ReactNode } from "react";
import { InputHTMLAttributes } from "react";
import { SubmitHandler, UseFormReturn } from "react-hook-form";

export interface FormProps<TFormValues extends Record<string, any>>
  extends InputHTMLAttributes<HTMLInputElement> {
  id: string;
  className?: string;
  children: ReactNode;
  methods: UseFormReturn<TFormValues>;
  onZodSubmit: SubmitHandler<TFormValues>;
}
