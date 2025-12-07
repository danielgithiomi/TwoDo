import { AuthenticationService as service } from "@tdp/api";
import { StoreJwtToken, StoreLoggedInUser } from "@tdp/libs";
import { useMutation, useQuery } from "@tanstack/react-query";
import {
  LoginRequest,
  SignUpRequest,
  LoginResponse,
  SignUpResponse,
  LoginErrorResponse,
  SignUpErrorResponse,
  LoggedInUserResponse,
  LoggedInUserErrorResponse,
  useAuthenticationReturn,
} from "@tdp/types";

export const useAuthentication = (): useAuthenticationReturn => {
  async function getLoggedInUser() {
    const loggedInUser = await service.me();
    const { body: user } = loggedInUser;
    StoreLoggedInUser(user);
  }

  const loginUser = useMutation<
    LoginResponse,
    LoginErrorResponse,
    LoginRequest
  >({
    mutationKey: ["login"],
    mutationFn: service.login,
    onSuccess: async (data: LoginResponse) => {
      const { jwtToken } = data.body;
      StoreJwtToken(jwtToken);

      // Make another API call to get the user information
      await getLoggedInUser();
    },
  });

  const createNewUser = useMutation<
    SignUpResponse,
    SignUpErrorResponse,
    SignUpRequest
  >({
    mutationKey: ["sign-up"],
    mutationFn: service.signUp,
  });

  const loggedInUser = useQuery<
    LoggedInUserResponse,
    LoggedInUserErrorResponse
  >({
    queryKey: ["me"],
    queryFn: service.me,
  });

  return {
    loginUser,
    loggedInUser,
    createNewUser,
  };
};
