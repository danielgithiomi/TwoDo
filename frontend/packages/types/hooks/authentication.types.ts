import { UseMutationResult } from "@tanstack/react-query";
import { SignUpRequest, SignUpResponse, SignUpErrorResponse } from "@tdp/types";

/* ----- Sign Up ----- */
export interface UseSignUpReturn {
  createNewUser: UseMutationResult<SignUpResponse, SignUpErrorResponse, SignUpRequest>;
}
