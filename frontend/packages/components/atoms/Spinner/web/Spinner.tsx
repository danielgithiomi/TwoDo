import { FC } from "react";
import { SpinnerProps } from "../Spinner.types";

export const Spinner: FC<SpinnerProps> = ({ size = 16, color = "#fff" }) => (
  <svg
    className="animate-spin"
    width={size}
    height={size}
    viewBox="0 0 24 24"
    fill="none"
    stroke={color}
    strokeWidth="3"
    strokeLinecap="round"
    strokeLinejoin="round"
  >
    <circle cx="12" cy="12" r="10" strokeOpacity="0.25" />
    <path d="M22 12a10 10 0 0 1-10 10" />
  </svg>
);
