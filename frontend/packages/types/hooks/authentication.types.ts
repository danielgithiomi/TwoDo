import { SignUpRequest } from "@tdp/types";

/* ----- Sign Up ----- */
export interface UseSignUpReturn {
    handleSignUp: (body: SignUpRequest) => void;
}