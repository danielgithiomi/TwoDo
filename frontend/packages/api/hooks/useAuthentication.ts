import { useMutation } from "@tanstack/react-query";
import { AuthenticationService as service } from "@tdp/api";
import { SignUpRequest, SignUpResponse, UseSignUpReturn } from "@tdp/types";

export const useAuthentication = (): UseSignUpReturn => {
  const createNewUser = useMutation<SignUpResponse, Error, SignUpRequest>({
    mutationKey: ["sign-up"],
    mutationFn: service.signUp,
  });

  return {
    createNewUser,
  };
};
