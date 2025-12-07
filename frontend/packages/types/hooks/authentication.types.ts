import { UseMutationResult, UseQueryResult } from "@tanstack/react-query";
import {
  LoginRequest,
  LoginResponse,
  LoginErrorResponse,
  SignUpRequest,
  SignUpResponse,
  SignUpErrorResponse,
  LoggedInUserResponse,
  LoggedInUserErrorResponse,
} from "@tdp/types";

/* ----- Sign Up ----- */
export interface useAuthenticationReturn {
  loginUser: UseMutationResult<LoginResponse, LoginErrorResponse, LoginRequest>;
  loggedInUser: UseQueryResult<LoggedInUserResponse, LoggedInUserErrorResponse>;
  createNewUser: UseMutationResult<SignUpResponse,SignUpErrorResponse,SignUpRequest>;
}
