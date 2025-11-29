import { UseMutationResult } from "@tanstack/react-query";
import {
  LoginRequest,
  LoginResponse,
  LoginErrorResponse,
  SignUpRequest,
  SignUpResponse,
  SignUpErrorResponse,
} from "@tdp/types";

/* ----- Sign Up ----- */
export interface useAuthenticationReturn {
  loginUser: UseMutationResult<LoginResponse, LoginErrorResponse, LoginRequest>;
  createNewUser: UseMutationResult<
    SignUpResponse,
    SignUpErrorResponse,
    SignUpRequest
  >;
}
