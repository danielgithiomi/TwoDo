import { useGender } from "@tdp/api";
import { Form } from "@tdw/molecules";
import { useSignUp } from "@tdp/hooks";
import { useForm } from "react-hook-form";
import { omitFromObject } from "@tdp/libs";
import { Button, FormInput } from "@tdw/atoms";
import { zodResolver } from "@hookform/resolvers/zod";
import { SignUpFormSchema, type SignUpFormValues } from "@tdp/types";

export const SignUp: React.FC = () => {
  const { genders } = useGender();
  const { createNewUser } = useSignUp();
  const { data, mutateAsync, isPending, error } = createNewUser;

  const onSubmit = async (data: SignUpFormValues) => {
    const rest = omitFromObject(data, ["confirmPassword"]);

    try {
      await mutateAsync(rest);
    } catch (error) {
      console.log(error);
    }
  };

  const methods = useForm<SignUpFormValues>({
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
            defaultValue={genders?.[genders.indexOf("Other") + 1]}
            className="w-full p-2 border border-gray-300 rounded-md"
          >
            {["Select...", ...genders]?.map((gender) => (
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
          key="submit"
          type="submit"
          label="Sign Up"
          loading={isPending}
          disabled={isPending}
        />
      </Form>

      {error && (
        <p className="text-red-500">
          There was an error signing up: {error.message}
        </p>
      )}
      {data && (
        <p className="text-green-500">
          User created successfully: {data.body.username}
        </p>
      )}
    </div>
  );
};
