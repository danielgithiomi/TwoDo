import { ApiClient } from "@tdp/api";

export const GenderService = {
    getApplicationGenders : async (): Promise<string[]> => {
        return ApiClient<string[]>("/genders", true, {
            method: 'GET'
        })
    }
}