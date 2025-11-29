import { GlobalApiResponse } from "@tdp/types";

export interface SignUpRequest {
  email: string;
  gender: string;
  password: string;
  lastName: string;
  firstName: string;
}

export interface SignUpResponseBody {
  userId: string;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  gender: string;
  avatarUrl: string;
  roles: string[];
  createdAt: string;
  updatedAt: any;
}

export type SignUpResponse = GlobalApiResponse<SignUpResponseBody>
