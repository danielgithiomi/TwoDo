import { FormProvider } from "react-hook-form";
import type { FormProps } from "../Form.types";

export function Form<TFormValues extends Record<string, any>>({
  id,
  methods,
  children,
  className,
  onZodSubmit,
}: FormProps<TFormValues>) {
  return (
    <FormProvider {...methods}>
      <form
        id={id}
        className={className}
        onSubmit={methods.handleSubmit(onZodSubmit)}
      >
        {children}
      </form>
    </FormProvider>
  );
}
