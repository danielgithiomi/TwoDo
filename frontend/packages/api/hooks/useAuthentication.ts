import { SaveAuthToken } from "@tdp/libs";
import { useMutation } from "@tanstack/react-query";
import { AuthenticationService as service } from "@tdp/api";
import {
  SignUpRequest,
  SignUpResponse,
  SignUpErrorResponse,
  LoginRequest,
  LoginResponse,
  LoginErrorResponse,
  useAuthenticationReturn,
} from "@tdp/types";

export const useAuthentication = (): useAuthenticationReturn => {
  const loginUser = useMutation<
    LoginResponse,
    LoginErrorResponse,
    LoginRequest
  >({
    mutationKey: ["login"],
    mutationFn: service.login,
    onSuccess: (data: LoginResponse) => {
      const { jwtToken } = data.body;
      SaveAuthToken(jwtToken);
    }
  });

  const createNewUser = useMutation<
    SignUpResponse,
    SignUpErrorResponse,
    SignUpRequest
  >({
    mutationKey: ["sign-up"],
    mutationFn: service.signUp,
  });

  return {
    loginUser,
    createNewUser,
  };
};
