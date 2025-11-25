export interface InputProps extends Pick<HTMLInputElement, "id" | "name" | "placeholder"> {
    label?: string;
}