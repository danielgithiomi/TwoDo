import { ApiClient } from "@tdp/api";
import { SignUpRequest, SignUpResponse } from "@tdp/types";

export const AuthenticationService = {
  signUp: async (body: SignUpRequest) => {
    return ApiClient<SignUpResponse>("/users", false, {
      method: "POST",
      body: JSON.stringify(body),
    });
  },
};
