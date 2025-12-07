import { useGender } from "@tdp/api";
import { RoutePaths } from "@routes";
import { Form } from "@tdw/molecules";
import { useSignUp } from "@tdp/hooks";
import { useForm } from "react-hook-form";
import { type FC, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { zodResolver } from "@hookform/resolvers/zod";
import { isAuthenticated, omitFromObject } from "@tdp/libs";
import { SignUpFormSchema, type SignUpFormValues } from "@tdp/types";
import { Button, FormInput, FormSelect, type SelectOption } from "@tdw/atoms";

export const SignUp: FC = () => {
  const navigate = useNavigate();

  // Navigate away if logged in
  useEffect(() => {
    if (isAuthenticated()) {
      navigate(RoutePaths.Profile);
    }
  }, [isAuthenticated]);

  const { genders } = useGender();
  const { createNewUser } = useSignUp();
  const { data, mutateAsync, isPending, error } = createNewUser;

  let genderOptions: SelectOption[] = [];
  if (genders) {
    genderOptions = genders.map((gender) => ({
      value: gender,
      label: gender,
    }));
  } else {
    genderOptions = [];
  }

  const onSubmit = async (data: SignUpFormValues) => {
    const rest = omitFromObject(data, ["confirmPassword"]);

    try {
      await mutateAsync(rest);
      methods.reset();
      navigate(RoutePaths.Profile);
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

  return (
    <section className="flex min-h-[95vh] place-items-center">
      <div className="w-1/4 mx-auto flex flex-col">
        <h1 className="text-3xl uppercase underline underline-offset-4 mb-4 text-center">
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
          <FormSelect
            id="gender"
            name="gender"
            label="Gender"
            options={genderOptions}
          />
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
            className="mx-auto grid place-items-center my-4"
          />
        </Form>

        <div className="text-center">
          Already have an account?{" "}
          <Link className="underline underline-offset-2" to={RoutePaths.Login}>
            Login
          </Link>
        </div>

        {error && (
          <p className="text-red-500 text-center italic my-4">
            There was an error signing up:
            <span className="font-bold"> {error.message} </span>
          </p>
        )}
        {data && (
          <p className="text-green-500 text-center italic my-4">
            User created successfully:{" "}
            <span className="font-bold">{data.body.username} </span>
          </p>
        )}
      </div>
    </section>
  );
};
