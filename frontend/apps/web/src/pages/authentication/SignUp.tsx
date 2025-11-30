import { useEffect, type FC } from "react";
import { useGender } from "@tdp/api";
import { Form } from "@tdw/molecules";
import { useSignUp } from "@tdp/hooks";
import { useForm } from "react-hook-form";
import { omitFromObject } from "@tdp/libs";
import { Button, FormInput } from "@tdw/atoms";
import { zodResolver } from "@hookform/resolvers/zod";
import { SignUpFormSchema, type SignUpFormValues } from "@tdp/types";
import { Link } from "react-router-dom";
import { RoutePaths } from "@routes";

export const SignUp: FC = () => {
  const { genders } = useGender();
  const { createNewUser } = useSignUp();
  const { data, mutateAsync, isPending, error } = createNewUser;

  const onSubmit = async (data: SignUpFormValues) => {
    const rest = omitFromObject(data, ["confirmPassword"]);

    try {
      await mutateAsync(rest);
      methods.reset();
    } catch (error) {
      console.log(error);
    }
  };

  const methods = useForm<SignUpFormValues>({
    mode: "onBlur",
    resolver: zodResolver(SignUpFormSchema),
    defaultValues: {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      gender: "",
      confirmPassword: "",
    },
  });

  useEffect(() => {
    if (genders.length > 0) {
      const defaultGender = genders.includes("Other") ? "Other" : genders[0];

      methods.reset({
        ...methods.getValues(),
        gender: defaultGender,
      });
    }
  }, [genders, methods]);

  return (
    <div>
      <h1 className="text-3xl uppercase underline underline-offset-4 mb-4">
        Sign Up
      </h1>

      <Form id="sign-up-form" methods={methods} onZodSubmit={onSubmit}>
        <FormInput
          label="First Name"
          id="firstName"
          name="firstName"
          placeholder="First Name"
        />
        <FormInput
          label="Last Name"
          id="lastName"
          name="lastName"
          placeholder="Last Name"
        />
        <FormInput
          label="Email Address"
          id="email"
          name="email"
          placeholder="Email Address"
        />
        <div className="flex flex-col gap-2 mb-2">
          <label className="w-full text-left" htmlFor="gender">
            Gender
          </label>
          <select
            id="gender"
            {...methods.register("gender")}
            className="w-full p-2 border border-gray-300 rounded-md"
          >
            {["Select...", ...genders].map((gender) => (
              <option key={gender} value={gender}>
                {gender}
              </option>
            ))}
          </select>
        </div>
        <FormInput
          label="Password"
          id="password"
          name="password"
          placeholder="••••••••"
        />
        <FormInput
          label="Confirm Password"
          id="confirmPassword"
          name="confirmPassword"
          placeholder="••••••••"
        />
        <Button
          id="signup-form-submit-button"
          key="submit"
          type="submit"
          label="Sign Up"
          loading={isPending}
          disabled={isPending}
        />
      </Form>

      <div>
        Already have an account?{" "}
        <Link
          className="underline underline-offset-2 hover:text-red-400"
          to={RoutePaths.Login}
        >
          Login
        </Link>
      </div>

      {error && (
        <p className="text-red-500">
          There was an error signing up:
          <span className="font-bold"> {error.message} </span>
        </p>
      )}
      {data && (
        <p className="text-green-500">
          User created successfully:{" "}
          <span className="font-bold">{data.body.username} </span>
        </p>
      )}
    </div>
  );
};
