import { useState } from "react";
import { UseFormRegister } from "react-hook-form";
import InputMask from "react-input-mask";

interface InputMaskCpfProps {
  register: UseFormRegister<any>;
  name: string;
  defaultValue?: string;
}

export function InputMaskCpfOrCnpj({ register, name }: InputMaskCpfProps) {
  const [value, setValue] = useState("");

  return (
    <InputMask
      mask={
        value.replaceAll("_", "").replaceAll("-", "").replaceAll(".", "")
          .length < 12
          ? "999.999.999-999"
          : "99.999.999/0001-99"
      }
      maskChar=""
      {...register(name)}
      value={value}
      onChange={(e) => setValue(e.target.value)}
      type="text"
      className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
    />
  );
}

export function InputMaskCpf({
  register,
  name,
  defaultValue,
}: InputMaskCpfProps) {
  const [value, setValue] = useState("");

  return (
    <InputMask
      mask={"999.999.999-999"}
      maskChar=""
      {...register(name)}
      value={value}
      onChange={(e) => setValue(e.target.value)}
      defaultValue={defaultValue + ""}
      type="text"
      className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
    />
  );
}

export function InputMaskCnpj({
  register,
  name,
  defaultValue,
}: InputMaskCpfProps) {
  const [value, setValue] = useState("");

  return (
    <InputMask
      mask={"99.999.999/9999-99"}
      maskChar=""
      {...register(name)}
      value={value}
      onChange={(e) => setValue(e.target.value)}
      defaultValue={defaultValue + ""}
      type="text"
      className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
    />
  );
}
