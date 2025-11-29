import { type FC } from "react";
import { RoutePaths } from "@routes";
import { Form } from "@tdw/molecules";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { useAuthentication } from "@tdp/api";
import { FormInput, Button } from "@tdw/atoms";
import { zodResolver } from "@hookform/resolvers/zod";
import { LoginFormSchema, type LoginFormValues } from "@tdp/types";

export const Login: FC = () => {
  const { loginUser } = useAuthentication();
  const {
    data: loginResponse,
    error: loginError,
    mutateAsync,
    isPending,
  } = loginUser;

  const loginMethods = useForm<LoginFormValues>({
    resolver: zodResolver(LoginFormSchema),
    mode: "onBlur",
    defaultValues: {
      usernameOrEmail: "",
      password: "",
    },
  });

  const onSubmit = async (data: LoginFormValues) => {
    try {
      await mutateAsync(data);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <h1 className="text-3xl uppercase underline underline-offset-4 mb-4">
        Login
      </h1>

      <Form id="login-form" methods={loginMethods} onZodSubmit={onSubmit}>
        <FormInput
          label="Username or Email"
          id="username-or-email"
          name="usernameOrEmail"
          placeholder="Username or Email"
        />
        <FormInput
          label="Password"
          id="password"
          name="password"
          placeholder="••••••••"
        />
        <Button
          id="login-form-submit-button"
          key="submit"
          type="submit"
          label="Login"
          loading={isPending}
          disabled={isPending}
        />
      </Form>

      <div>
        Dont have an account?{" "}
        <Link
          className="underline underline-offset-2 hover:text-red-400"
          to={RoutePaths.SignUp}
        >
          Sign Up
        </Link>
      </div>

      {loginError && (
        <p className="text-red-500">
          There was an error logging in:
          <span className="font-bold"> {loginError.message} </span>
        </p>
      )}
      {loginResponse && (
        <p className="text-green-500">
          User logged in successfully: {loginResponse.body.jwtToken}
        </p>
      )}
    </>
  );
};
