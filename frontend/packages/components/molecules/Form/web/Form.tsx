import type { FC } from "react";
import type { FormProps } from "../Form.types";

export const Form: FC<FormProps> = ({ children }) => {
    return (
        <form action="">
            {children}
        </form>
    );
};