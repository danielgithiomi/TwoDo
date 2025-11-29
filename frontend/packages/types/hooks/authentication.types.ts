import { UseMutationResult } from "@tanstack/react-query";
import { SignUpRequest, SignUpResponse } from "@tdp/types";

/* ----- Sign Up ----- */
export interface UseSignUpReturn {
  createNewUser: UseMutationResult<SignUpResponse, Error, SignUpRequest>;
}
