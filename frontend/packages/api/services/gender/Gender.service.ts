import { ApiClient } from "@tdp/api";
import { GlobalApiResponse } from "@tdp/types";

export const GenderService = {
    getApplicationGenders : async (): Promise<GlobalApiResponse<string[]>> => {
        return ApiClient<GlobalApiResponse<string[]>>("/genders", true, {
            method: 'GET'
        }, false)
    }
}