export interface GlobalApiResponse<T> {
  body: T;
  httpStatus: string;
  statusCode: number;
  timestamp: string;
  message: string;
}
