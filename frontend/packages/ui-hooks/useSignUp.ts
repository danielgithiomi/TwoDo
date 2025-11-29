import { SignUpRequest } from "@tdp/types";
import { useAuthentication } from "@tdp/api";

export const useSignUp = () => {
  const { createNewUser } = useAuthentication();

  const handleSignUp = (body: SignUpRequest) => {
    const { mutate } = createNewUser;
    mutate(body);
  };

  return {
      handleSignUp,
      createNewUser,
  };
};
