export interface GlobalApiResponse<T> {
  body: T;
  httpStatus: string;
  statusCode: number;
  timestamp: string;
  message: string;
}

export interface GlobalApiErrorResponse {
  statusCode: number;
  message: string;
  errors: ApiError[];
}

interface ApiError {
  field: string;
  message: string;
}
