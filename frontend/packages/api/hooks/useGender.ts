import { GenderService } from "../services";
import { useQuery } from "@tanstack/react-query";
import { GenderServiceHookReturn, GlobalApiResponse } from "@tdp/types";

export const useGender = (): GenderServiceHookReturn => {
  const {
    data = [],
    error,
    isPending,
  } = useQuery<GlobalApiResponse<string[]>, Error, string[]>({
    queryKey: ["genders"],
    queryFn: GenderService.getApplicationGenders,
    select: (data) => data.body,
  });

  if (error) console.log(error);

  return {
    genders: data,
  };
};
