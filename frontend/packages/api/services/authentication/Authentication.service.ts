import { ApiClient } from "@tdp/api";
import { SignUpRequest, GlobalApiResponse, SignUpResponse } from "@tdp/types";

export const AuthenticationService = {
  signUp: async (body: SignUpRequest) => {
    return ApiClient<GlobalApiResponse<SignUpResponse>>("/auth/sign-up", false, {
      method: "POST",
      body: JSON.stringify(body),
    });
  },
};
