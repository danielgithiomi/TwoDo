import { GlobalApiResponse, GlobalApiErrorResponse } from "@tdp/types";

export interface UserResponseDTO {
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

/* -------------------------------------------------------------------------------------------------
 * Sign Up
 * -----------------------------------------------------------------------------------------------*/
export interface SignUpRequest {
  email: string;
  gender: string;
  password: string;
  lastName: string;
  firstName: string;
}

export type SignUpResponse = GlobalApiResponse<UserResponseDTO>

export type SignUpErrorResponse = GlobalApiErrorResponse;

/* -------------------------------------------------------------------------------------------------
 * Login
 * -----------------------------------------------------------------------------------------------*/
export interface LoginRequest {
  usernameOrEmail: string;
  password: string;
}

export interface LoginResponseBody {
  jwtToken: string;
  dateIssued: string;
}

export type LoginResponse = GlobalApiResponse<LoginResponseBody>

export type LoginErrorResponse = GlobalApiErrorResponse;
