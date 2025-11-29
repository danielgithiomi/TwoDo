import { ApiClient } from "@tdp/api";
import {
  LoginRequest,
  LoginResponse,
  SignUpRequest,
  SignUpResponse,
} from "@tdp/types";

export const AuthenticationService = {
  login: async (body: LoginRequest) => {
    return ApiClient<LoginResponse>("/auth/login", false, {
      method: "POST",
      body: JSON.stringify(body),
    }, false);
  },
  signUp: async (body: SignUpRequest) => {
    return ApiClient<SignUpResponse>("/users", false,{
      method: "POST",
      body: JSON.stringify(body),
    }, false);
  },
};
