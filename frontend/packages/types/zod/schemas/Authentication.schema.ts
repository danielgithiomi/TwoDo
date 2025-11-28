import { z } from "zod";

export const SignUpFormSchema = z.object({
  first_name: z.string().min(1, "First name is required"),
  last_name: z.string().min(1, "Last name is required"),
  email_address: z.email("Invalid email address"),
  password: z.string().min(8, "Password must be at least 8 characters"),
  confirm_password: z.string().min(8, "Password must be at least 8 characters"),
}).refine((data)=> data.password === data.confirm_password, {
  message: "Passwords do not match",
  path: ["confirm_password"],
});

export type SignUpFormValues = z.infer<typeof SignUpFormSchema>;