import { type FC } from "react";
import { Input } from "@tdw/atoms";

export const SignUp: FC = () => {
  return (
    <div>
      <h1 className="text-3xl uppercase underline underline-offset-4 mb-4">
        Sign Up
      </h1>

      <form action="">
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
      </form>
    </div>
  );
};
