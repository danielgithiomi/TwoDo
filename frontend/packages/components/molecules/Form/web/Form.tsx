import { FormProvider } from "react-hook-form";
import type { FormProps } from "../Form.types";

export function Form<TFormValues extends Record<string, string>>({
  methods,
  children,
  className,
  onZodSubmit,
}: FormProps<TFormValues>) {
  return (
    <FormProvider {...methods}>
      <form className={className} onSubmit={methods.handleSubmit(onZodSubmit)}>
        {children}
      </form>
    </FormProvider>
  );
}
