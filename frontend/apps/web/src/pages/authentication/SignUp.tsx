import { Form } from "@tdw/molecules";
import { useForm } from "react-hook-form";
import { Button, Input } from "@tdw/atoms";
import { zodResolver } from "@hookform/resolvers/zod";
import { SignUpFormSchema, type SignUpFormValues } from "@tdp/types";

export const SignUp: React.FC = () => {
  const onSubmit = (data: SignUpFormValues) => {
    console.log(data);
  };

  const methods = useForm<SignUpFormValues>({
    resolver: zodResolver(SignUpFormSchema),
    defaultValues: {
      first_name: "",
      last_name: "",
      email_address: "",
      password: "",
      confirm_password: "",
    },
  });

  return (
    <div>
      <h1 className="text-3xl uppercase underline underline-offset-4 mb-4">
        Sign Up
      </h1>

      <Form methods={methods} onZodSubmit={onSubmit}>
        <Input
          label="First Name"
          id="first_name"
          name="first_name"
          placeholder="First Name"
        />
        <Input
          label="Last Name"
          id="last_name"
          name="last_name"
          placeholder="Last Name"
        />
        <Input
          label="Email Address"
          id="email_address"
          name="email_address"
          placeholder="Email Address"
        />
        <Input
          label="Password"
          id="password"
          name="password"
          placeholder="Password"
        />
        <Input
          label="Confirm Password"
          id="confirm_password"
          name="confirm_password"
          placeholder="Confirm Password"
        />
        <Button key="submit" type="submit" label="Sign Up" />
      </Form>
    </div>
  );
};
