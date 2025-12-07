import {Input} from "./Input";
import {useFormContext} from "react-hook-form";
import {type FormInputProps} from "../Input.types";

export const FormInput: React.FC<FormInputProps> = ({
    id,
    name,
    label,
    placeholder,
    ...rest
}) => {
    const {
        register,
        formState: {errors},
    } = useFormContext();

    const error = errors[name];

    return (
        <Input
            id={id}
            name={name}
            label={label}
            error={error}
            register={register}
            placeholder={placeholder}
            {...rest}
        />
    );
};
