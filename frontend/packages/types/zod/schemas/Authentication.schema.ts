import { z } from "zod";

/* -------------------------------------------------------------------------------------------------
 * Sign Up Schema
 * -----------------------------------------------------------------------------------------------*/
export const SignUpFormSchema = z.object({
  firstName: z.string().min(1, "First name is required"),
  lastName: z.string().min(1, "Last name is required"),
  email: z.email("Invalid email address"),
  gender: z.string().min(1, "Gender is required"),
  password: z.string().min(8, "Password must be at least 8 characters"),
  confirmPassword: z.string().min(8, "Password must be at least 8 characters"),
}).refine((data)=> data.password === data.confirmPassword, {
  message: "Passwords do not match",
  path: ["confirmPassword"],
});

export type SignUpFormValues = z.infer<typeof SignUpFormSchema>;


/* -------------------------------------------------------------------------------------------------
 * Login Schema
 * -----------------------------------------------------------------------------------------------*/
export const LoginFormSchema = z.object({
  usernameOrEmail: z.string().min(6, "Username must be at least 6 characters"),
  password: z.string().min(8, "Password must be at least 8 characters"),
});

export type LoginFormValues = z.infer<typeof LoginFormSchema>;